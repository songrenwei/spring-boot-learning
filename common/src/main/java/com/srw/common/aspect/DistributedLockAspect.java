package com.srw.common.aspect;

import com.srw.common.annotation.DistributedLock;
import com.srw.common.exception.LockAcquireException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedisLockRegistry lockRegistry;
//    private final ZookeeperLockRegistry lockRegistry;
    private final SpelExpressionParser parser = new SpelExpressionParser();
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Around(value = "@annotation(distributedLock)")
    public Object distributedLockAround(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        Assert.hasLength(distributedLock.key(), "key不能为空");
        String lockKey = getLockKey(joinPoint, distributedLock);
        // 加分布式锁
        Lock obtain = lockRegistry.obtain(lockKey);
        if (obtain.tryLock()) {
            try {
                // 执行业务逻辑
                return joinPoint.proceed();
            } finally {
                // 释放锁
                obtain.unlock();
            }
        } else {
            throw new LockAcquireException(StringUtils.hasLength(distributedLock.failMessage()) ? distributedLock.failMessage() : "获取锁失败");
        }
    }

    private String getLockKey(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 获取方法参数名称
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        Assert.notNull(parameterNames, "参数列表不能为空");
        // 构建spel上下文
        SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        // 解析spel参数
        Expression expression = parser.parseExpression(distributedLock.key());
        String lockKey = expression.getValue(context, String.class);
        Assert.hasLength(lockKey, "lockKey不能为空");

        return lockKey;
    }

}
