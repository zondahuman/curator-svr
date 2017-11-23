package com.abin.lee.curator.svr.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static final Log LOG = LogFactory.getLog(DateUtils.class);

    private static final String[] CN_MM = { "", "\u4e00\u6708", "\u4e8c\u6708", "\u4e09\u6708", "\u56db\u6708",
            "\u4e94\u6708", "\u516d\u6708", "\u4e03\u6708", "\u516b\u6708", "\u4e5d\u6708", "\u5341\u6708",
            "\u5341\u4e00\u6708", "\u5341\u4e8c\u6708" };

    /**
     * 给指定的时间串 time 加 mm 分钟 返回 fmt 的串
     * 
     * @param time
     * @param mm
     * @return
     */
    public static String getAddMinutes(String time, String mm, String fmt) {

        String sTime = "";
        SimpleDateFormat formatter = new SimpleDateFormat(fmt);
        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        Date d1 = null;
        try {
            int iAdd = Integer.parseInt(mm);
            d1 = formatter.parse(time);
            long lRst = d1.getTime() + iAdd * 60 * 1000;
            sdf.applyPattern(fmt);
            sTime = sdf.format(new Date(lRst));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sTime;
    }

    /**
     * 得到月份的中文名
     * 
     * @param myDate
     * @return
     */
    public static String formatDateCN(Date myDate) {
        String strDate = CN_MM[getMonth(myDate)];
        return strDate;
    }

    /**
     * 得到日期对应的月份数
     * 
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到本月对应的第一天
     * 
     * @return String
     */
    public static String getMonthFristDay() {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String firstDay = dft.format(cld.getTime());
        return firstDay;
    }

    /**
     * 得到本月对应的最后一天
     * 
     * @return String
     */
    public static String getMonthEndDay() {
        Calendar cld = Calendar.getInstance();
        cld.add(Calendar.MONTH, 1);
        cld.set(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String endDay = dft.format(cld.getTime());
        return endDay;
    }

    /**
     * 得到指定月的第一天
     * 
     * @return String
     */
    public static String getFristDayInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String firstDay = dft.format(calendar.getTime());
        return firstDay;
    }

    /**
     * 得到指定月的最后一天
     * 
     * @return String
     */
    public static String getEndDayInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month + 1, 0);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String endDay = dft.format(calendar.getTime());
        return endDay;
    }

    /**
     * 获取当前月份的日期
     * 
     * @return
     */
    public static String getCurMonthDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取格式化今天的日期字符串
     * 
     * @return
     */
    public static String getCurDayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获得指定格式的日期字符串
     * 
     * @return
     */
    public static String getCurrentDate(String format) {
        String dateString = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date currentTime_1 = new Date();
            dateString = formatter.format(currentTime_1);
        } catch (Exception e) {
        }
        return dateString;
    }

    /**
     * 获取格式化今天的时间字符串 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static String getTimeStr(Date d) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(d);
    }

    /**
     * 得到今天的长整型时间since January 1, 1970, 00:00:00 GMT represented by this Date
     * object.
     * 
     * @return
     */
    public static long getTodayTime() {
        Calendar cld = Calendar.getInstance();
        // cld.setTime(new Date());
        int year = cld.get(Calendar.YEAR);
        int month = cld.get(Calendar.MONTH);
        int day = cld.get(Calendar.DAY_OF_MONTH);
        Calendar todaycld = Calendar.getInstance();
        todaycld.set(year, month, day, 0, 0, 0);
        return todaycld.getTime().getTime();
    }

    /**
     * 判断是否今天
     * 
     * @param atime
     * @return
     */
    public static boolean isTodayTime(long atime) {
        Calendar cld = Calendar.getInstance();
        // cld.setTime(new Date());
        int year = cld.get(Calendar.YEAR);
        int month = cld.get(Calendar.MONTH);
        int day = cld.get(Calendar.DAY_OF_MONTH);
        Calendar todaycld = Calendar.getInstance();
        todaycld.set(year, month, day, 0, 0, 0);
        if (atime + 1000l >= todaycld.getTime().getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否昨天
     * 
     * @param atime
     * @return
     */
    public static boolean isLastdayTime(long atime) {
        Calendar cld = Calendar.getInstance();
        // cld.setTime(new Date());
        cld.add(Calendar.DAY_OF_MONTH, -1);
        int year = cld.get(Calendar.YEAR);
        int month = cld.get(Calendar.MONTH);
        int day = cld.get(Calendar.DAY_OF_MONTH);
        Calendar lastdaycld = Calendar.getInstance();
        lastdaycld.set(year, month, day, 0, 0, 0);
        if (atime >= lastdaycld.getTime().getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否昨天
     * 
     * @param atime
     * @return
     */
    public static boolean isLastday(long atime) {
        Calendar today = Calendar.getInstance();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONDAY), today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Calendar cld = Calendar.getInstance();
        cld.add(Calendar.DAY_OF_MONTH, -1);
        int year = cld.get(Calendar.YEAR);
        int month = cld.get(Calendar.MONTH);
        int day = cld.get(Calendar.DAY_OF_MONTH);
        Calendar lastdaycld = Calendar.getInstance();
        lastdaycld.set(year, month, day, 0, 0, 0);
        if (atime >= lastdaycld.getTimeInMillis() && atime < today.getTimeInMillis()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否在今天之前
     * 
     * @param atime
     * @return
     */
    public static boolean isBeforeToday(long atime) {
        Calendar cld = Calendar.getInstance();
        int year = cld.get(Calendar.YEAR);
        int month = cld.get(Calendar.MONTH);
        int day = cld.get(Calendar.DAY_OF_MONTH);
        Calendar todaycld = Calendar.getInstance();
        todaycld.set(year, month, day, 0, 0, 0);
        if (atime <= todaycld.getTimeInMillis()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 转换字符串为日期
     * 
     * @param s
     * @return
     * @throws Exception
     */
    public static Date getFormatDateOnDay(String s) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(s);
    }

    /**
     * 转换字符串为日期,精确到时分秒
     * 
     * @param s
     * @return
     * @throws Exception
     */
    public static Date getFormatDateOnDay(String s, String format) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(s);
    }

    /*
     * 转换为中文日期
     */
    public static String getFormatZHDay(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("yyyy\u5E74MM\u6708dd\u65E5");
        return sf.format(date);
    }

    /**
     * 转换字符串为日期和时间
     * 
     * @param s
     * @return
     * @throws Exception
     */
    public static Date getFormatDateOnDayAndTime(String s) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换时间字符串为日期和时间
     * 
     * @param s
     * @return
     */
    public static Date getFormatTime(String s) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            return simpleDateFormat.parse(s);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取前几天的日期字符串
     * 
     * @param num
     * @return
     * @throws Exception
     */
    public static String getPriorDayDateStr(int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, date.get(Calendar.DATE) - num);
        String beforeDate = dft.format(date.getTime());
        return beforeDate;
    }

    public static String getPriorDayDateStr(String cDate, int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(getFormatDateOnDay(cDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        date.set(Calendar.DATE, date.get(Calendar.DATE) - num);
        String beforeDate = dft.format(date.getTime());
        return beforeDate;
    }

    /**
     * 获取前几天的日期
     * 
     * @param num
     * @return
     */
    public static Date getPriorDayDate(int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, date.get(Calendar.DATE) - num);
        String beforeDate = dft.format(date.getTime()) + " 00:00:00";
        Date fdate = null;
        try {
            fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fdate;
    }

    public static Date getPriorDayLastTime(int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, date.get(Calendar.DATE) - num);
        String beforeDate = dft.format(date.getTime()) + " 23:59:59";
        Date fdate = null;
        try {
            fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fdate;
    }

    /**
     * 获取后几天的日期
     * 
     * @param num
     * @return
     */
    public static Date getNextSecondDate(int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.SECOND, date.get(Calendar.SECOND) + num);
        Date ndate = null;
        try {
            ndate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ndate;
    }

    /**
     * 获取后几天的日期
     * 
     * @param num
     * @return
     */
    public static Date getNextDayDate(int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, date.get(Calendar.DATE) + num);
        String nextDate = dft.format(date.getTime()) + " 00:00:00";
        Date ndate = null;
        try {
            ndate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(nextDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ndate;
    }

    /**
     * 获取后几天的日期
     * 
     * @param num
     * @return
     */
    public static Date getNextDayDate(Date cDate, int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(cDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        date.set(Calendar.DATE, date.get(Calendar.DATE) + num);
        String nextDate = dft.format(date.getTime()) + " 00:00:00";
        Date ndate = null;
        try {
            ndate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(nextDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ndate;
    }

    /**
     * 获取今年第一天的日期
     * 
     * @return
     * @throws Exception
     */
    public static Date getCurYearFristDate() throws Exception {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar date = Calendar.getInstance();
        String beforeDate = date.get(Calendar.YEAR) + "-01-01 00:00:00";
        return dft.parse(beforeDate);
    }

    /**
     * 时间转换
     * 
     * @param year
     * @param month
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static long Time2Long(int year, int month, int date, int hour, int minute, int second) {
        Calendar cld = Calendar.getInstance();
        month = month - 1;
        cld.set(year, month, date, hour, minute, second);
        return cld.getTime().getTime();
    }

    /**
     * 格式化日期
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static String getFormatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static String getFormatDateOnTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getFormatDateOnMinite(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化日期
     * 
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 计算两个日期相差的月份
     */
    public static int getDistMonth(long beginTime, long endTime) {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTimeInMillis(beginTime);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(endTime);
        int yearDiff, monthDiff;
        if (beginTime > endTime) {
            yearDiff = beginCalendar.get(Calendar.YEAR) - endCalendar.get(Calendar.YEAR);
            monthDiff = beginCalendar.get(Calendar.MONTH) - endCalendar.get(Calendar.MONTH);
        } else {
            yearDiff = endCalendar.get(Calendar.YEAR) - beginCalendar.get(Calendar.YEAR);
            monthDiff = endCalendar.get(Calendar.MONTH) - beginCalendar.get(Calendar.MONTH);
        }
        int result = yearDiff * 12 + monthDiff;
        return result == 0 ? 1 : result;
    }


    /**
     * 返回两个日期相差的天数
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int getDistDates(Date startDate, Date endDate) {
        long totalDate = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        long timestart = calendar.getTimeInMillis();
        calendar.setTime(endDate);
        long timeend = calendar.getTimeInMillis();
        totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
        return (int) totalDate;
    }

    public static int getDistDates(String startDate, String endDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
        Date eDate = null;
        try {
            sDate = dft.parse(startDate);
            eDate = dft.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long num = (eDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24);
        return (int) num;
    }

    public static int getDistHours(Date startTime, Date endTime) {
        long num = (endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60);
        return (int) num;
    }

    public static int getDistMinutes(Date startTime, Date endTime) {
        long num = (endTime.getTime() - startTime.getTime()) / (60000);
        return (int) num;
    }

    public static Date getPriorDayStartTime(int num, Date startTime) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        if (startTime != null) {
            date.setTime(startTime);
        }
        date.add(Calendar.DATE, -num);
        String beforeDate = dft.format(date.getTime()) + " 00:00:00";
        Date fdate = null;
        try {
            fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fdate;
    }

    public static int getQuarter(Date d) {
        int m = getMonth(d);
        if (0 < m && m < 4) {
            return 1;
        } else if (3 < m && m < 7) {
            return 2;
        } else if (6 < m && m < 10) {
            return 3;
        } else {
            return 4;
        }
    }

    public static String getDayOfWeekCn() {

        return getDayOfWeekCn(null);
    }

    public static String getDayOfWeekCn(Date date) {

        return getDayOfWeekCn(date, null);
    }

    public static String getDayOfWeekCn(Date date, String style) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        LOG.debug("============= " + style + "  =========== " + date);
        if (StringUtils.isBlank(style)) {
            style = "星期";
        }
        String week = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1: {
                week = style + "日";
            }
                break;
            case 2: {
                week = style + "一";
            }
                break;
            case 3: {
                week = style + "二";
            }
                break;
            case 4: {
                week = style + "三";
            }
                break;
            case 5: {
                week = style + "四";
            }
                break;
            case 6: {
                week = style + "五";
            }
                break;
            case 7: {
                week = style + "六";
            }
                break;
            default:
                break;
        }
        return week;
    }

    public static Date getPriorDayEndTime(int num, Date startTime) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        if (startTime != null) {
            date.setTime(startTime);
        }
        date.add(Calendar.DATE, -num);
        String beforeDate = dft.format(date.getTime()) + " 23:59:59";
        Date fdate = null;
        try {
            fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fdate;
    }

    /**
     * 获得当前日期与本周一相差的天数
     * 
     * @return
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 获得本周星期日的日期
     * 
     * @return
     */
    public static Date getCurrentWeekday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dft.format(currentDate.getTime()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获得本周星期一的日期
     * 
     * @return
     */
    public static Date getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dft.format(currentDate.getTime()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间到本周日凌晨所剩秒数
     * 
     * @return
     */
    public static int getTimeMillisFromNowToWeekend() {
        long currentTimeMillis = System.currentTimeMillis();
        // 1000*60*60*8为本时区与UTC时差(8小时)
        long todayLeftTimeMillis = 1000 * 60 * 60 * 24
                - (currentTimeMillis % (1000 * 60 * 60 * 24) + 1000 * 60 * 60 * 8);
        // 明天到本周日的天数
        int days = getMondayPlus() + 6;
        return (int) (60 * 60 * 24 * days + todayLeftTimeMillis / 1000);
    }

    /**
     * 获取当前时间到今天凌晨所剩毫秒数
     * 
     * @return
     */
    public static int getTimeMillisLeftToday() {
        long currentTimeMillis = System.currentTimeMillis();
        // 1000*60*60*8为本时区与UTC时差(8小时)
        long todayLeftTimeMillis = 1000 * 60 * 60 * 24
                - (currentTimeMillis % (1000 * 60 * 60 * 24) + 1000 * 60 * 60 * 8);
        return (int) todayLeftTimeMillis;
    }

    public static Date getNextDayTime(Date cDate, int num) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(cDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        date.set(Calendar.DATE, date.get(Calendar.DATE) + num);
        String nextDate = dft.format(date.getTime());
        Date ndate = null;
        try {
            ndate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nextDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ndate;
    }

    /**
     * 根据秒数计算出时长
     * 
     * @return
     */
    public static String getTimeBySeconds(int seconds) {
        StringBuffer time = new StringBuffer();
        int day = seconds / (24 * 60 * 60);
        int hour = seconds % (24 * 60 * 60) / (60 * 60);
        int minute = seconds % (24 * 60 * 60) % (60 * 60) / 60;
        int second = seconds % (24 * 60 * 60) % (60 * 60) % 60;
        if (day == 0 && hour == 0 && minute == 0) {
            time.append(second + "秒");
        } else if (day == 0 && hour == 0) {
            time.append(minute + "分钟" + second + "秒");
        } else if (day == 0) {
            time.append(hour + "小时" + minute + "分钟" + second + "秒");
        } else {
            time.append(day + "天" + hour + "小时" + minute + "分钟" + second + "秒");
        }
        return time.toString();
    }

    /**
     * 获取当前月剩余天数
     * 
     * @return
     */
    public static int getCurMonthDaysLeft() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int passDay = c.get(Calendar.DAY_OF_MONTH);
        return day - passDay;
    }

    /**
     * 获取当前月天数
     * 
     * @return
     */
    public static int getCurMonthDays() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期(yyyy-MM-dd)
     * 
     * @param date
     * @return
     */
    public static Date parseSimpleDate(Date date) {
        String pattern = "yyyy-MM-dd";
        try {
            return parseDate(DateFormatUtils.format(date, pattern), pattern);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 获取字段
     * 
     * @param date
     * @param field
     * @return
     */
    public static Integer getField(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 获取日期
     * 
     * @param date
     * @return
     */
    public static Integer getDate(Date date) {
        return getField(date, Calendar.DATE);
    }

    /**
     * 转换日期，转换失败时返回null
     * 
     * @param str
     * @param parsePatterns
     * @return
     */
    public static Date parse(String str, String parsePatterns) {
        try {
            return parseDate(str, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String format(Date date, String parsePatterns) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(parsePatterns).format(date);
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    public static boolean isWorkTime(Date date) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (dayOfWeek >= 2 && dayOfWeek <= 6 && hour >= 10 && hour < 19) {
            return true;
        }
        return false;
    }

    public static Long getCurrentOneWeekTime(Date date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTime(date); // 获取时间
        System.out.println(df.format(cal.getTime()));
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        System.out.println(df.format(cal.getTime()));
        return cal.getTime().getTime();
    }

}