package com.srw.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: renwei.song
 * @Date: 11/26/2018 14:31
 * @Description: 线程安全时间工具类
 */
public class ConcurrentDateUtils {
    public static final int YEAR = 1;
    public static final int MONTH = 2;
    public static final int DAY = 3;
    public static final int HOUR = 4;
    public static final int MINUTE = 5;
    public static final int SECOND = 6;
    public static final int MILLISECOND = 7;

    private static ThreadLocal<DateFormat> date_HHmmss_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private static ThreadLocal<DateFormat> date_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private static ThreadLocal<DateFormat> date_HHmm_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm"));

    private static ThreadLocal<DateFormat> dateHH_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-ddHH"));

    private static ThreadLocal<DateFormat> HHmm_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm"));

    private static ThreadLocal<DateFormat> yyyyMMdd_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    private static ThreadLocal<DateFormat> HHmmss_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("HHmmss"));

    private static ThreadLocal<DateFormat> yyyyMMddHHmmss_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));

    private static ThreadLocal<DateFormat> yyyyMMddSL_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd"));

    private static ThreadLocal<DateFormat> yyyyMMddHHmmssSSS_threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmssSSS"));

    public static long currentTimeStamp() {
        return System.currentTimeMillis();
    }

    public static String yyyyMMddHHmmssSSS_format(Date date) {
        return yyyyMMddHHmmssSSS_threadLocal.get().format(date);
    }

    public static String yyyyMMddHHmmss_format(Date date) {
        return date_HHmmss_threadLocal.get().format(date);
    }

    public static Date yyyyMMddHHmmss_parse(String str) throws ParseException {
        return yyyyMMddHHmmss_threadLocal.get().parse(str);
    }

    public static String format(Date date) {
        return date_threadLocal.get().format(date);
    }

    public static String formatDateTime(Date date) {
        return date_HHmmss_threadLocal.get().format(date);
    }

    public static String formatDateTimes(long timestamp) {
        Date date = new Date(timestamp);
        return yyyyMMdd_threadLocal.get().format(date);
    }

    public static String formatDateTime(long timestamp) {
        Date date = new Date(timestamp);
        return date_HHmmss_threadLocal.get().format(date);
    }

    public static String formatDateTimeByDay(long timestamp) {
        Date date = new Date(timestamp);
        return date_threadLocal.get().format(date);
    }

    public static Date getDate(String str) throws ParseException {
        return date_threadLocal.get().parse(str);
    }

    public static Date getNowDate() throws ParseException {
        DateFormat dateFormat = date_HHmmss_threadLocal.get();
        return dateFormat.parse(dateFormat.format(new Date()));
    }

    public static Date strToDate(String dateTime) throws ParseException {
        return date_HHmmss_threadLocal.get().parse(dateTime);
    }

    public static long strToLong(String dateTime) {
        try {
            return strToDate(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long strDayToLong(String dateTime) {
        try {
            return getDate(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Date getDateTime(String str) throws ParseException {
        return date_HHmm_threadLocal.get().parse(str);
    }

    public static String getSysDate() {
        Date date = new Date();
        return date_threadLocal.get().format(date);
    }

    public static String getSysTime() {
        Date date = new Date();
        return dateHH_threadLocal.get().format(date);
    }

    public static String getSys_yyyy_MM_dd_HH_mm_ss() {
        Date date = new Date();
        return date_HHmmss_threadLocal.get().format(date);
    }

    public static Date toDateTime(String day, Long hour) throws ParseException {
        return dateHH_threadLocal.get().parse(day + hour);
    }

    public static Date toDateTime(String day, int hour) throws ParseException {
        return dateHH_threadLocal.get().parse(day + hour);
    }

    public static Date toDateTime(String day, String hour) throws ParseException {
        return dateHH_threadLocal.get().parse(day + hour);
    }

    /**
     * 得到两日期之间所有日期List
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<String> getDateList(String startDate, String endDate) throws ParseException {
        List<String> dateList = new ArrayList<String>();
        DateFormat dateFormat = date_threadLocal.get();
        Date sDate = dateFormat.parse(startDate);
        Calendar c = Calendar.getInstance();
        c.setTime(sDate);
        int dayDiff = (int) dayDiff(startDate, endDate);
        for (int i = 0; i <= dayDiff; i++) {
            dateList.add(dateFormat.format(c.getTime()));
            c.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dateList;
    }

    /**
     * 计算从起始日期到今天为止的间隔天数
     *
     * @param from 起始日期
     * @return 间隔天数
     * @throws ParseException
     */
    public static long dayDiff(String from) throws ParseException {
        long diff = System.currentTimeMillis() - date_threadLocal.get().parse(from).getTime();
        return diff /= 1000 * 60 * 60 * 24;
    }

    /**
     * 计算从起始日期到今天为止的间隔天数+1
     *
     * @param from 起始日期
     * @return 间隔天数
     * @throws ParseException
     */
    public static long dayDiffPlusOne(String from) throws ParseException {
        long diff = System.currentTimeMillis() - date_threadLocal.get().parse(from).getTime();
        return (diff /= 1000 * 60 * 60 * 24) - 1;
    }


    /**
     * 计算从起始日期到结束日期间隔的天数
     *
     * @param from 起始日期
     * @param to   结束日期
     * @return 间隔天数
     * @throws ParseException
     */
    public static long dayDiff(String from, String to) throws ParseException {
        DateFormat dateFormat = date_threadLocal.get();
        long diff = dateFormat.parse(to).getTime() - dateFormat.parse(from).getTime();
        return diff /= 1000 * 60 * 60 * 24;
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date stringtoDate(String dateStr, String format) throws ParseException {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        d = formater.parse(dateStr);
        return d;
    }

    /**
     * 获取指定日期前一天
     */
    /**
     * 获得指定日期的前一天
     * @param specifiedDay 指定日期
     * @param pattern 需要返回的日期格式，例如：yyyy-MM-dd HH:mm:ss
     * @return 前一天日期
     */
    public static String getSpecifiedDayBefore(String pattern,String specifiedDay) throws Exception{
        Calendar c = Calendar.getInstance();
        Date date = new SimpleDateFormat(pattern).parse(specifiedDay);
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);
        String dayBefore=new SimpleDateFormat(pattern).format(c.getTime());
        return dayBefore;
    }

    /**
     * 得到某个日期的后一天日期
     *
     * @param d
     * @return
     */
    public static Date getAfterDate(Date d) {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 根据当前日期获取上个月第一天和最后一天日期
     *
     * @return
     * @throws ParseException
     */
    public static Map<String, Date> getLastMonth() throws ParseException {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        map.put("firstDate", getYMD(c));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        map.put("lastDate", getYMD(c));

        return map;
    }

    /**
     * 根据当前日期获取上周一和周天日期
     *
     * @return
     * @throws ParseException
     */
    public static Map<String, Date> getLastWeek() throws ParseException {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar c = Calendar.getInstance();
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DAY_OF_MONTH, -1);
        }
        c.set(Calendar.DAY_OF_WEEK, 1);
        map.put("lastSunday", getYMD(c));
        c.add(Calendar.WEEK_OF_MONTH, -1);
        c.set(Calendar.DAY_OF_WEEK, 2);
        map.put("lastMonday", getYMD(c));
        return map;
    }

    /***
     * 根据当前日期获取去年第一天和最后一天日期
     *
     * @return
     * @throws ParseException
     */
    public static Map<String, Date> getLastYear() throws ParseException {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DATE, 1);
        map.put("firstDay", getYMD(c));
        c.set(Calendar.MONTH, 11);
        c.set(Calendar.DATE, 31);
        map.put("lastDay", getYMD(c));

        return map;
    }

    /***
     * 根据当前日期获取上一季度第一天和最后一天日期
     *
     * @return
     * @throws ParseException
     */
    public static Map<String, Date> getLastQuarter() throws ParseException {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3) {
            c.add(Calendar.YEAR, -1);
            c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            map.put("firstDay", getYMD(c));
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            map.put("lastDay", getYMD(c));
        } else if (currentMonth >= 4 && currentMonth <= 6) {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            map.put("firstDay", getYMD(c));
            c.set(Calendar.MONTH, 2);
            c.set(Calendar.DATE, 31);
            map.put("lastDay", getYMD(c));

        } else if (currentMonth >= 7 && currentMonth <= 9) {
            c.set(Calendar.MONTH, 3);
            c.set(Calendar.DATE, 1);
            map.put("firstDay", getYMD(c));
            c.set(Calendar.MONTH, 5);
            c.set(Calendar.DATE, 30);
            map.put("lastDay", getYMD(c));

        } else if (currentMonth >= 10 && currentMonth <= 12) {
            c.set(Calendar.MONTH, 6);
            c.set(Calendar.DATE, 1);
            map.put("firstDay", getYMD(c));
            c.set(Calendar.MONTH, 8);
            c.set(Calendar.DATE, 30);
            map.put("lastDay", getYMD(c));

        }
        return map;
    }

    private static Date getYMD(Calendar c) throws ParseException {
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1).length() == 2 ? String.valueOf(c.get(Calendar.MONTH) + 1) : "0" + (c.get(Calendar.MONTH) + 1);
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH)).length() == 2 ? String.valueOf(c.get(Calendar.DAY_OF_MONTH)) : "0" + c.get(Calendar.DAY_OF_MONTH);
        return getDate(year + "-" + month + "-" + day);
    }

    /**
     * 根据日期获取自然月日期
     *
     * @return
     */
    public static Date addMonthsDateTime(Date date, int monthNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, monthNum);

        return c.getTime();
    }

    public static Date addDayTime(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    public static Date addHourTime(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hour);
        return c.getTime();
    }

    /**
     * 获取该日期的时分字符串
     *
     * @param date
     * @return
     */
    public static String getHourAndMinuteStr(Date date) {
        return HHmm_threadLocal.get().format(date);
    }

    public static int getHourOfDay() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    /**
     * 把时分转化成日期 获取当前日期，清除属性，设置时分
     *
     * @param hmStr 分时字符串 eg.格式：10:30 (十点三十分)
     * @return
     */
    public static Date hmStrConvertToDatetime(String hmStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        String[] strArray = hmStr.split(":");
        calendar.set(Calendar.HOUR, Integer.parseInt(strArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(strArray[1]));
        return calendar.getTime();
    }

    /**
     * 时间格式前后补0显示 eg.6:0 --> 06:00 eg.6:05 --> 06:05
     *
     * @return
     */
    public static String hmStrAppendZero(String hmStr) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(hmStr)) {
            String[] hmArray = hmStr.split(":");
            if (hmArray[0].length() == 1) {
                sb.append("0");
            }
            sb.append(hmArray[0]);
            sb.append(":");
            sb.append(hmArray[1]);
            if (hmArray[1].length() == 1) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    /**
     * 获取开始时间
     *
     * @param startDate
     * @return
     */
    public static Date getStartTime(Date startDate) {
        if (startDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTime(startDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 修改时间
     *
     * @return
     */
    public static Calendar setTime(Date date, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second, Integer milliSecond) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTime(date);
            if (year != null) {
                calendar.set(Calendar.YEAR, year);
            }
            if (month != null) {
                calendar.set(Calendar.MONTH, month);
            }
            if (day != null) {
                calendar.set(Calendar.DAY_OF_MONTH, day);
            }
            if (hour != null) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
            }
            if (minute != null) {
                calendar.set(Calendar.MINUTE, minute);
            }
            if (second != null) {
                calendar.set(Calendar.SECOND, second);
            }
            if (second != null) {
                calendar.set(Calendar.MILLISECOND, milliSecond);
            }
            return calendar;
        }
        return null;
    }

    /**
     * 获取结束时间
     *
     * @param endDate
     * @return
     */
    public static Date getEndTime(Date endDate) {
        if (endDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTime(endDate);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 获取开始时间
     *
     * @param startDate
     * @return
     * @throws ParseException
     */
    public static Date getStartTime(String startDate) throws ParseException {
        if (StringUtils.isNotBlank(startDate)) {
            startDate += " 00:00:00";
            return date_HHmmss_threadLocal.get().parse(startDate);
        }
        return null;
    }

    /**
     * 获取结束时间
     *
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static Date getEndTime(String endDate) throws ParseException {
        if (StringUtils.isNotBlank(endDate)) {
            endDate += " 23:59:59";
            return date_HHmmss_threadLocal.get().parse(endDate);
        }
        return null;
    }

    /**
     * 根据统计维度，生成开始日期~结束日期,格式：yyyy-mm-dd~yyyy-mm-dd
     *
     * @param statisticsDimension
     * @param statisticsStr
     * @return
     */
    public static String getStartAndEndDate(String statisticsDimension, String statisticsStr) {
        // 根据统计维度和日期字符串，生成开始时间(yyyy-mm-dd)和结束时间(yyyy-mm-dd)
        String startDate = "";
        String endDate = "";
        Calendar calendar;
        if ("1".equals(statisticsDimension)) {
            // 统计维度：天 2015-03-01
            startDate = statisticsStr;
            endDate = statisticsStr;
        } else if ("2".equals(statisticsDimension)) {
            // 统计维度：周 2015-04-06~2015-04-12
            String[] arrStr = statisticsStr.split("~");
            startDate = arrStr[0];
            endDate = arrStr[1];
        } else if ("3".equals(statisticsDimension)) {
            // 统计维度：月 2015-03
            calendar = Calendar.getInstance();
            calendar.clear();
            String[] arrStr = statisticsStr.split("-");
            calendar.set(Integer.parseInt(arrStr[0]), Integer.parseInt(arrStr[1]) - 1, 1);
            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            startDate = statisticsStr + "-01";
            endDate = statisticsStr + "-" + days;
        } else if ("4".equals(statisticsDimension)) {
            // 统计维度：季 2015-01~2015-03
            calendar = Calendar.getInstance();
            calendar.clear();
            String[] arrStrDates = statisticsStr.split("~");
            String[] arrStr = arrStrDates[1].split("-");
            calendar.set(Integer.parseInt(arrStr[0]), Integer.parseInt(arrStr[1]) - 1, 1);
            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            startDate = arrStrDates[0] + "-01";
            endDate = arrStrDates[1] + "-" + days;
        } else if ("5".equals(statisticsDimension)) {
            // 统计维度：年 2015
            startDate = statisticsStr + "-01-01";
            endDate = statisticsStr + "-12-31";
        }
        return startDate + "~" + endDate;
    }

    /**
     * 获取年月日
     *
     * @return
     */
    public static Date getYMD(Date date) {
        return setTime(date, null, null, null, 0, 0, 0, 0).getTime();
    }

    public static String getPlayVideoDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return sdf.format(date);
    }

    /***********************************************/

    public static String formatHHMMSS(Date date) {
        return HHmmss_threadLocal.get().format(date);
    }

    public static String formatMMYYdd(Date date) {
        return yyyyMMdd_threadLocal.get().format(date);
    }

    public static Date getYYMMddDate(String str) throws ParseException {
        return yyyyMMdd_threadLocal.get().parse(str);
    }

    public static Date getVirguleDate(String str) throws ParseException {
        return yyyyMMddSL_threadLocal.get().parse(str);
    }

    public static String formatVirguleDate(Date date) throws ParseException {
        return yyyyMMddSL_threadLocal.get().format(date);
    }

    public static String strToDateFormat(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Date newDate = formatter.parse(date);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(newDate);
    }


    /**
     * 获取当月第一天最早时间
     *
     * @return
     * @throws ParseException
     * @author zhaoqi01
     */
    public static Date getCurrentMonthFirstDate() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(theDate);
        // 设置为第一天
        gc.set(Calendar.DAY_OF_MONTH, 1);
        DateFormat dateFormat = yyyyMMdd_threadLocal.get();
        return dateFormat.parse(dateFormat.format(gc.getTime()));
    }

    /**
     * 获取当月最后一天最晚时间
     *
     * @return
     * @throws ParseException
     * @author zhaoqi01
     */
    public static Date getCurrentMonthLastDate() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return strToDate(date_threadLocal.get().format(calendar.getTime()) + " 23:59:59");
    }

    /**
     * 计算时间差
     *
     * @param date
     * @param compareTo
     * @return
     */
    public static long differentDays(Date date, Date compareTo) {
        try {
            return dayDiff(format(date), format(compareTo));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将日期格式化成 MM.dd格式的字符串 例如10.28
     *
     * @param date
     * @return
     * @author zhaoqi01
     */
    public static String formatMonthDayStr(Date date) {
        String formatStr = date_threadLocal.get().format(date);
        String[] splitArr = formatStr.split("-");
        return splitArr[1] + "." + splitArr[2];
    }

    public static long diffCount(Date oldDate, Date newDate, int type) {
        long diffMills = newDate.getTime() - oldDate.getTime();
        if (type == DAY) {
            return diffMills / 86400000;
        } else if (type == HOUR) {
            return diffMills / 3600000;
        } else if (type == MINUTE) {
            return diffMills / 60000;
        } else if (type == SECOND) {
            return diffMills / 1000;
        } else if (type == MILLISECOND) {
            return diffMills;
        } else {
            throw new RuntimeException("未知比较类型");
        }
    }

    /**
     * @return 当前时间　毫秒
     */
    public static long now() {
        return System.currentTimeMillis();
    }

    /**
     * @param timestamp 毫秒
     * @return
     */
    public static Date tsToDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 时间戳
     *
     * @return
     */
    public static Timestamp accessTimeStamp() {
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        return nousedate;
    }

    /**
     * 过期判断
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static boolean compareDate(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return false;
            } else if (dt1.getTime() < dt2.getTime()) {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

}
