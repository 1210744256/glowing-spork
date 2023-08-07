package com.baizhi.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static int year;
    static boolean dayPanDuan; // 判断日期输入是否正确，月份天数正确为true，错误为false
    /* 根据年份判断属相 */
    public static void main(String[] args) {
        Date date = new Date();
        date.setTime(1046188800000l);
        System.out.println(getYear(date));
    }
    public static String ShuXiang(int year) {
        String sx = null;
        switch (year % 12) {
            case 4:
                sx = "鼠";
                break;
            case 5:
                sx = "牛";
                break;
            case 6:
                sx = "虎";
                break;
            case 7:
                sx = "兔";
                break;
            case 8:
                sx = "龙";
                break;
            case 9:
                sx = "蛇";
                break;
            case 10:
                sx = "马";
                break;
            case 11:
                sx = "羊";
                break;
            case 0:
                sx = "猴";
                break;
            case 1:
                sx = "鸡";
                break;
            case 2:
                sx = "狗";
                break;
            case 3:
                sx = "猪";
                break;
        }
        return sx;
    }

    /* 根据月份和日期判断星座 */
    public static String XingZuo(int year, int month, int day) {
        String xz = null;
        if ((month == 3 && day >= 21 && day <= 31) || (month == 4 && day >= 1 && day <= 19)) {
            xz = "白羊";
        }
        if ((month == 4 && day >= 20 && day <= 30) || (month == 5 && day >= 1 && day <= 20)) {
            xz = "金牛";
        }
        if ((month == 5 && day >= 21 && day <= 31) || (month == 6 && day >= 1 && day <= 21)) {
            xz = "双子";
        }
        if ((month == 6 && day >= 22 && day <= 30) || (month == 7 && day >= 1 && day <= 22)) {
            xz = "巨蟹";
        }
        if ((month == 7 && day >= 23 && day <= 31) || (month == 8 && day >= 1 && day <= 22)) {
            xz = "狮子";
        }
        if ((month == 8 && day >= 23 && day <= 31) || (month == 9 && day >= 1 && day <= 22)) {
            xz = "处女";
        }
        if ((month == 9 && day >= 23 && day <= 30) || (month == 10 && day >= 1 && day <= 23)) {
            xz = "天秤";
        }
        if ((month == 10 && day >= 24 && day <= 31) || (month == 11 && day >= 1 && day <= 22)) {
            xz = "天蝎";
        }
        if ((month == 11 && day >= 23 && day <= 30) || (month == 12 && day >= 1 && day <= 21)) {
            xz = "射手";
        }
        if ((month == 12 && day >= 22 && day <= 31) || (month == 1 && day >= 1 && day <= 19)) {
            xz = "摩羯";
        }
        if ((month == 1 && day >= 20 && day <= 31) || (month == 2 && day >= 1 && day <= 18)) {
            xz = "水瓶";
        }
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) { // 闰年2月29天
            if ((month == 2 && day >= 19 && day <= 29) || (month == 3 && day >= 1 && day <= 20)) {
                xz = "双鱼";
            }
        } else { // 平年2月28天
            if ((month == 2 && day >= 19 && day <= 28) || (month == 3 && day >= 1 && day <= 20)) {
                xz = "双鱼";
            }
        }
        return xz;
    }
    public static int getYear(Date date){
        Calendar c=Calendar.getInstance();
        int nowYear = c.get(Calendar.YEAR);
//                改变后的日期
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return nowYear-year;
    }
}
