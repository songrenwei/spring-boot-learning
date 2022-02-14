package com.srw.jsont;

import com.fasterxml.jackson.core.type.TypeReference;
import com.srw.common.utils.JacksonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/5/13 13:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonTest {

    @Test
    public void test() {
        String body = "[{\"name\":\"小米\",\"age\":10},{\"name\":\"华为\",\"age\":11},{\"name\":\"阿里巴巴\",\"age\":12}]";
        List<People> people = JacksonUtils.json2Obj(body, new TypeReference<List<People>>() {
        });

        return;
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class People {

    private String name;
    private int age;

}

