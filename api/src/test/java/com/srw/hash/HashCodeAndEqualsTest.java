package com.srw.hash;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 参考 http://blog.sina.com.cn/s/blog_79164f730102w3jh.html
 * @Author: renwei.song
 * @Date: 2021/5/13 10:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HashCodeAndEqualsTest {

    @Test
    public void test() {

        Person p1 = new Person("a",1);
        Person p2 = new Person("a",1);

        Set<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);

        set.forEach(System.out::println);

    }

}

@Setter
@Getter
class Person {

    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 重写hashCode方法
    @Override
    public int hashCode(){
        //使用字符串哈希值与Integer的哈希值的组合，这样会产生重码，实际上重码率很高
        return name.hashCode() + age;
    }

    // 重写equals方法
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Person){
            Person p = (Person)obj;
            return(name.equals(p.name) && age == p.age);
        }
        return super.equals(obj);
    }

}

