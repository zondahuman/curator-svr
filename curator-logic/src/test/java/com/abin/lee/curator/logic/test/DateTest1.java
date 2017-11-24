package com.abin.lee.curator.logic.test;

import com.abin.lee.curator.svr.common.DateUtil;
import com.abin.lee.curator.svr.common.DateUtils;
import com.abin.lee.curator.svr.common.JsonUtil;
import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by abin on 2017/11/23 19:51.
 * curator-svr
 * com.abin.lee.curator.logic
 */
public class DateTest1 {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String input = "2017-10-5 12:00:01";
//        Long formerOneWeek = getCurrentOneWeekTime(df.parse(input));
//        System.out.println("formerOneWeek="+formerOneWeek);
//        Long formerTwoWeek = getCurrentTwoWeekTime(df.parse(input));
//        System.out.println("formerTwoWeek="+formerTwoWeek);

        Long formerOneWeek = getformweWhichWeekTime(df.parse(input), 1);
        System.out.println("formerOneWeek="+formerOneWeek);
        Long formerTwoWeek = getformweWhichWeekTime(df.parse(input), 2 );
        System.out.println("formerTwoWeek="+formerTwoWeek);
        System.out.println("---------------------------------------------");
        Long formerOneMonth = getformweWhichMonth(df.parse(input), 1);
        System.out.println("formerOneMonth="+formerOneMonth);
        Long formerTwoMonth = getformweWhichMonth(df.parse(input), 2);
        System.out.println("formerTwoMonth="+formerTwoMonth);
//        Date date1 = DateUtil.getYMDHMSTime(input);
//        get(date1);
    }

    public static Long getformweWhichMonth(Date date, int interval) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(date); // 获取时间
        System.out.println(df.format(cal.getTime()));
        cal.add(Calendar.MONTH, -interval);
        System.out.println(df.format(cal.getTime()));
        return cal.getTime().getTime();
    }

    public static Long getformweWhichWeekTime(Date date, int interval) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(date); // 获取时间
        System.out.println(df.format(cal.getTime()));
        cal.add(Calendar.WEEK_OF_YEAR, -interval);
        System.out.println(df.format(cal.getTime()));
        return cal.getTime().getTime();
    }


    public static Long getCurrentOneWeekTime(Date date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(date); // 获取时间
        System.out.println(df.format(cal.getTime()));
//        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
        cal.add(Calendar.WEEK_OF_YEAR, -1);// 增加一个星期，才是我们中国人理解的本周日的日期
        System.out.println(df.format(cal.getTime()));
        return cal.getTime().getTime();
    }
    public static Long getCurrentTwoWeekTime(Date date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(date); // 获取时间
        System.out.println(df.format(cal.getTime()));
        cal.add(Calendar.WEEK_OF_YEAR, -2);
        System.out.println(df.format(cal.getTime()));
        return cal.getTime().getTime();
    }



    public static void get(Date createTime) {
//        List<CallRecordDetail> detailList = null;
        List<CallRecordDetail> detailList = Lists.newArrayList();
        CallRecordDetail detail1 = new CallRecordDetail();
        detail1.setDuration(101);
        detail1.setToMobile("13300009999");
        detail1.setStartTime(DateUtil.getYMDHMSTime("2017-10-1 12:00:01"));
        detailList.add(detail1);
        CallRecordDetail detail2 = new CallRecordDetail();
        detail2.setDuration(102);
        detail2.setToMobile("13300009991");
        detail2.setStartTime(DateUtil.getYMDHMSTime("2017-10-5 12:00:01"));
        detailList.add(detail2);
        CallRecordDetail detail3 = new CallRecordDetail();
        detail3.setDuration(103);
        detail3.setToMobile("13300009992");
        detail3.setStartTime(DateUtil.getYMDHMSTime("2017-9-25 12:00:01"));
        detailList.add(detail3);
        detailList.add(null);
        List<CallRecordDetail> tempList = detailList.stream().filter(item -> ( null != item && null != item.getStartTime() && (item.getStartTime().getTime() > DateUtils.getCurrentOneWeekTime(createTime)))).collect(Collectors.toList());
//        List<CallRecordDetail> tempList = Lists.newArrayList();
//        detailList.stream().forEach(item -> {
//            if (null != item.getStartTime() && (item.getStartTime().getTime() < DateUtils.getCurrentOneWeekTime(createTime)))
//                tempList.add(item);
//        });

        System.out.println("tempList= " + JsonUtil.toJson(tempList));
    }


}

class CallRecordDetail {
    private String toMobile;
    private Integer duration;
    private Date startTime;


    public String getToMobile() {
        return toMobile;
    }

    public void setToMobile(String toMobile) {
        this.toMobile = toMobile;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}