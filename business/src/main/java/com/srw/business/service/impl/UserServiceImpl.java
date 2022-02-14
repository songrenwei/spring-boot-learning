package com.srw.business.service.impl;

import com.alibaba.excel.EasyExcel;
import com.srw.business.helper.IdempotenceHelper;
import com.srw.business.service.UserService;
import com.srw.common.annotation.Log;
import com.srw.common.dto.UserDto;
import com.srw.persistence.entity.User;
import com.srw.persistence.mapper.UserMapper;
import com.srw.persistence.mongodb.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/10/14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final IdempotenceHelper idempotenceHelper;
    private final MongoTemplate mongoTemplate;

    @Log
    @Override
    public List<User> findList() {
        return userMapper.selectList(null);
    }

    @Log
    @Override
    public int save(User user) {
//        idempotenceHelper.checkIdempotence(user.getName(), 60, TimeUnit.SECONDS);
        return userMapper.insert(user);
    }

    @Cacheable(value = "30m", key = "#id", unless = "#result == null")
    @Override
    public User query(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int delete(User user) {
        return userMapper.deleteById(user.getId());
    }

    @Override
    public int save(UserInfo userInfo) {
        mongoTemplate.save(userInfo);
        return 1;
    }

    @Override
    public List<UserInfo> list() {
        return mongoTemplate.findAll(UserInfo.class);
    }

    @Override
    public int remove(Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        mongoTemplate.remove(userInfo);
        return 1;
    }

    @Override
    public void export(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("测试", "UTF-8").replace("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), UserDto.class).sheet("模板").doWrite(findList());
        } catch (Exception e) {
            log.error("文件导出异常", e);
        }
    }

}
