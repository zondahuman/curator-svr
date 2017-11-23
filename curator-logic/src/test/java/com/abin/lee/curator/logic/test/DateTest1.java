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
        Long result = getCurrentOneWeekTime(df.parse("2017-10-5 12:00:01"));
//        System.out.println(result);
//
        get(new Date(result));
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

    public static void get(Date createTime){
        List<CallRecordDetail> detailList = Lists.newArrayList();
        final  CallRecordDetail detail1 = new CallRecordDetail();
        detail1.setDuration(101);
        detail1.setToMobile("13300009999");
        detail1.setStartTime(DateUtil.getYMDHMSTime("2017-10-1 12:00:01"));
        detailList.add(detail1);
        detail1.setDuration(102);
        detail1.setToMobile("13300009991");
        detail1.setStartTime(DateUtil.getYMDHMSTime("2017-10-5 12:00:01"));
        detailList.add(detail1);
        detail1.setDuration(103);
        detail1.setToMobile("13300009992");
        detail1.setStartTime(DateUtil.getYMDHMSTime("2017-9-25 12:00:01"));
        detailList.add(detail1);
        List<CallRecordDetail> tempList = detailList.stream().filter(item -> (null != item.getStartTime() && item.getStartTime().getTime() > DateUtils.getCurrentOneWeekTime(createTime))).collect(Collectors.toList());

        System.out.println("tempList= "+ JsonUtil.toJson(tempList));
    }



}
class CallRecordDetail{
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