package com.srw.persistence.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Description: 数据源配置
 * @Author: songrenwei
 * @Date: 2020/11/4/15:58
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.srw.persistence.mapper")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * SQL执行效率插件
     */
//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启，保证我们的效率
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        performanceInterceptor.setMaxTime(5000); //ms 设置sql执行的最大时间，如果超过了则不执行
//        performanceInterceptor.setFormat(true);
//        return performanceInterceptor;
//    }

    /**
     * 注册乐观锁
     */
//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
//        return new OptimisticLockerInterceptor();
//    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        // 设置最大分页数， -1: 不受限制
        paginationInnerInterceptor.setMaxLimit(-1L);
        // 溢出总页数后是否进行处理
        paginationInnerInterceptor.setOverflow(false);
        // 生成 countSql 优化掉 join 现在只支持 left join
        paginationInnerInterceptor.setOptimizeJoin(true);
        plusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

        // 注册乐观锁
        plusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return plusInterceptor;
    }

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }

}
