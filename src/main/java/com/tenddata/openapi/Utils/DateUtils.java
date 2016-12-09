package com.tenddata.openapi.Utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by LD on 2016/12/9 0009.
 */
public class DateUtils {

    public static String getFirstDayForMonth(String month, String inFormat,String outFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(inFormat);
        DateTime parse = DateTime.parse(month, dateTimeFormatter);
        return parse.toString(outFormat);
    }

    public static String getLastDayForMonth(String month, String inFormat,String outFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(inFormat);
        DateTime parse = DateTime.parse(month, dateTimeFormatter);
        return parse.plusMonths(1).minusSeconds(1).toString(outFormat);
    }

    public static void main(String[] args) {
        System.out.println(getFirstDayForMonth("2016-11","yyyy-MM","yyyy-MM-dd HH:mm:ss"));
        System.out.println(getLastDayForMonth("2016-11","yyyy-MM","yyyy-MM-dd HH:mm:ss"));
    }

}
