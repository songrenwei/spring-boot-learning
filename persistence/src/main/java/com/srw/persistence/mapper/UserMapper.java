package com.srw.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.srw.persistence.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO
 * @Author: songrenwei
 * @Date: 2020/11/4/16:17
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> findList();

    // 自定义分页
    @Select("select id, name, password, is_deleted, create_time, update_time from t_user")
    IPage<User> queryPage(Page<?> page);

    // 自定义分页
    @Select({"<script>",
            "select id, name, password, is_deleted, create_time, update_time from t_user",
            "<where>",
            "<if test = 'id != null'>",
            "id > #{id}",
            "</if>",
            "</where>",
            "</script>"})
    IPage<User> queryPageById(Page<?> page, @Param("id") Long id);

}
