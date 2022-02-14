package com.srw.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.srw.persistence.entity.User;
import com.srw.persistence.enums.SexEnum;
import com.srw.persistence.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/5/7 14:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MpTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test1() {
        List<User> list = userMapper.findList();
        list.forEach(System.out::println);
        List<User> list1 = userMapper.selectList(null);
        list1.forEach(System.out::println);
    }

    @Test
    public void test2() {
        User user = new User();
        user.setUsername("admin");
        user.setAge(21);
        int result = userMapper.insert(user); // 帮我们自动生成id
        System.out.println(result); // 受影响的行数
        System.out.println(user); // 看到id会自动填充
    }

    @Test
    public void test3() {
        User user = userMapper.selectById(1L);
        // 通过条件自动拼接动态sql
        user.setUsername("admin123");
        user.setAge(11);
        user.setSex(SexEnum.FEMALE);
        // 注意：updateById 但是参数是一个对象！
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void test4() {
        //参数1：当前页 参数2：页面大小
        Page<User> page = new Page<>(1,10);
//        Page<User> page1 = userMapper.selectPage(page.addOrder(OrderItem.desc("id")),null);
        userMapper.queryPage(page);
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println(page.getTotal());//总记录
        System.out.println(page.getSize());//查询的页面大小
        System.out.println(page.getCurrent());//查询的当前页

    }

    @Test
    public void test5(){
        //根据id删除
        userMapper.deleteById(1L);
        //根据id批量删除
        userMapper.deleteBatchIds(Arrays.asList(1,2,3));
        //根据map删除
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","dog1");
        userMapper.deleteByMap(map);
    }

    @Test
    public void test6() {
        // 相比上一个例子，更推荐这个，通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda()
                .eq(User::getUsername, "song11")
                .eq(User::getAge, 21)
                .eq(User::getIsDelete, false);

        //        // 也可以使用 Wrappers 中的静态方法
        //        LambdaQueryWrapper<User> eq = Wrappers.<User>lambdaQuery()
        //                .eq(User::getName, "song11")
        //                .eq(User::getPassword, "song11")
        //                .eq(User::getIsDeleted, false);

        User user = userMapper.selectOne(eq);
        Assertions.assertNotNull(user);
    }

    @Test
    public void test7() {
        // 相比上一个例子，更推荐这个，通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
//        User user = new LambdaQueryChainWrapper<>(userMapper)
//                .eq(User::getName, "song11")
//                .eq(User::getAge, 31)
//                .eq(User::getIsDeleted, false)
//                .one();

        // 也可以使用 Wrappers 中的静态方法
        User user = ChainWrappers.lambdaQueryChain(userMapper)
                .eq(User::getUsername, "Jone")
                .eq(User::getAge, 18)
                .eq(User::getIsDelete, false)
                .one();

        Assertions.assertNotNull(user);
    }

    @Test
    public void test8() {
        LambdaQueryWrapper<User> eq = Wrappers.<User>lambdaQuery()
//                .eq(User::getName, "song11")
//                .eq(User::getPassword, "song11")
                .eq(User::getIsDelete, false);

        Integer integer = userMapper.selectCount(eq);
        Assertions.assertTrue(integer > 0);
    }

    @Test
    public void test9() {
        LambdaQueryWrapper<User> eq = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, "cat1");
        List<User> list = userMapper.selectList(eq);
        list.forEach(System.out::println);
    }

    @Test
    public void test10() {
        // 模拟输入的查询条件
        String name = "";
        Integer age = 10;

        LambdaQueryWrapper<User> eq = Wrappers.<User>lambdaQuery()
                .eq(StringUtils.hasLength(name), User::getUsername, name) // name 不为空时，使用该条件
                .eq(!ObjectUtils.isEmpty(age), User::getAge, age); // age 不为空时，使用该条件
//                .eq(User::getIsDeleted, false);

        List<User> list = userMapper.selectList(eq);
        list.forEach(System.out::println);
    }

    @Test
    public void test11() {
        User user = new User();
        user.setUsername("张三");
        user.setAge(32);
        user.setSex(SexEnum.MALE);
        // 插入
        userMapper.insert(user);

        // 查询结果
        User user1 = userMapper.selectById(user.getId());
        Assertions.assertNotNull(user1);
    }

    @Test
    public void test12() {
        User user = new User();
        user.setUsername("songrenwei");
        user.setAge(22);
        user.setSex(SexEnum.MALE);

        User.OtherInfo otherInfo = new User.OtherInfo();
        otherInfo.setCity("300000");
        otherInfo.setHobby("篮球");
        user.setOtherInfo(otherInfo);

        // 插入
        userMapper.insert(user);

        // 查询结果
        User user1 = userMapper.selectById(user.getId());
        System.out.println(user1);

        // 更新
        User.OtherInfo otherInfo1 = new User.OtherInfo();
        otherInfo1.setCity("上海");
        otherInfo1.setHobby("足球");
        User u = new User();
        u.setOtherInfo(otherInfo1);
        ChainWrappers.lambdaUpdateChain(userMapper)
                .eq(User::getId, user.getId())
                .update(u);

        // 查询结果
        final User one = ChainWrappers.lambdaQueryChain(userMapper)
                .eq(User::getId, user.getId())
                .one();
        System.out.println(one);
    }

}
