package com.srw;

//import com.cfg.srw.autocfgspringbootstarter.bean.AutoCfg;

import com.srw.business.helper.ParamDigestHelper;
import com.srw.persistence.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.next;

@SpringBootTest
class ApiApplicationTests {

//    @Resource
//    private AutoCfg autoCfg;
//
//    @Test
//    void test() {
//        System.out.println(autoCfg);
//    }

    @Test
    void contextLoads() {
        //两个请求一样，但是请求时间差一秒
        String req = "{\n" +
                "\"requestTime\" :\"20190101120001\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        String req2 = "{\n" +
                "\"requestTime\" :\"20190101120002\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        //全参数比对，所以两个参数MD5不同
        String dedupMD5 = ParamDigestHelper.delDuplicationMD5(req);
        String dedupMD52 = ParamDigestHelper.delDuplicationMD5(req2);
        System.out.println("req1MD5 = "+ dedupMD5+" , req2MD5="+dedupMD52);

        //去除时间参数比对，MD5相同
//        String dedupMD53 = ReqDedupHelper.dedupParamMD5(req,"requestTime");
//        String dedupMD54 = ReqDedupHelper.dedupParamMD5(req2,"requestTime");
//        System.out.println("req1MD5 = "+ dedupMD53+" , req2MD5="+dedupMD54);

    }

    @Test
    void test1() {
        Predicate<String> predicate = StringUtils::isNotBlank;
        predicate.test("s");
        System.out.println("--------------");
        LocalDate date = LocalDate.of(2021, 4, 21);
        System.out.println("date:"+date);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        date.withYear(2011);
        System.out.println(date);
        System.out.println("--------------");
        LocalDate date2 = date.with(next(DayOfWeek.WEDNESDAY));
        System.out.println("date2:"+date2);
        LocalDate date3 = date2.with(lastDayOfMonth());
        System.out.println("date3:"+date3);
        System.out.println("--------------");
        DayOfWeek dow = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        System.out.println(dow);
        System.out.println("--------------");
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("s1:"+s1);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("s2:"+s2);
    }

    @Test
    public void test2() {
        List<List<String>> list = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list.add(list1);
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list.add(list2);

        long size = list.stream().flatMap(item -> item.stream()).count();
        System.out.println(size);
    }

    @Test
    public void test3() {
        User user = new User();
        user.setUsername("aa");
        String s1 = Optional.ofNullable(user).orElse(get()).getUsername();
        System.out.println(s1);
        String s2 = Optional.ofNullable(user).orElseGet(this::get).getUsername();
        System.out.println(s2);
        Optional.ofNullable(user).ifPresent(value -> System.out.println(value.getUsername()));
    }

    private User get() {
        User user = new User();
        user.setUsername("bb");
        System.out.println("print : " + user);
        return user;
    }

}

