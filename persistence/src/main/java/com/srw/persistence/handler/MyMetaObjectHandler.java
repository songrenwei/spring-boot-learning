package com.srw.persistence.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: 时间生成策略
 * @Author: renwei.song
 * @Date: 2021/5/7 15:26
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        // 通用填充
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

        // 严格填充,只针对非主键的字段,只有该表注解了fill 并且 字段名和字段属性 能匹配到才会进行填充(严格模式填充策略,默认有值不覆盖,如果提供的值为null也不填充)
//        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
//        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    // 更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        // 通用填充
        this.setFieldValByName("updateTime", new Date(), metaObject);

        // 严格填充,只针对非主键的字段,只有该表注解了fill 并且 字段名和字段属性 能匹配到才会进行填充(严格模式填充策略,默认有值不覆盖,如果提供的值为null也不填充)
//        this.strictUpdateFill(metaObject, "updateTime",  Date.class, new Date());
    }

}
