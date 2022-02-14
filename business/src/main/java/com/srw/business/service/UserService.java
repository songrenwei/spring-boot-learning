package com.srw.business.service;

import com.srw.persistence.entity.User;
import com.srw.persistence.mongodb.UserInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/10/14
 */
public interface UserService {

    List<User> findList();

    int save(User u);

    int delete(User user);

    int update(User user);

    User query(Long id);

    /**
     * 生成浏览记录
     */
    int save(UserInfo userInfo);

    /**
     * 批量删除浏览记录
     */
    int remove(Long id);

    /**
     * 获取用户浏览历史记录
     * @return
     */
    List<UserInfo> list();

    /**
     * 文件导出
     *
     * @param response
     */
    void export(HttpServletResponse response);

}
