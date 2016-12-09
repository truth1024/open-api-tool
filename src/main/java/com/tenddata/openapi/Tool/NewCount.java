package com.tenddata.openapi.Tool;

import com.tenddata.openapi.Utils.DateUtils;

/**
 * Created by LD on 2016/12/9 0009.
 */
public class NewCount extends Tool{

    private static final String inFormat = "yyyy-MM";
    private static final String outFormat = "yyyy-MM-dd HH:mm:ss";

    public static void developer(String month){
        int count = dao.queryDeveloperCountByTime(
                DateUtils.getFirstDayForMonth(month, inFormat, outFormat),
                DateUtils.getLastDayForMonth(month,inFormat,outFormat));
        System.out.println(month+" 新增开发者账户："+count);
    }

    public static void product(String month){
        int count = dao.queryProductCountByTime(
                DateUtils.getFirstDayForMonth(month, inFormat, outFormat),
                DateUtils.getLastDayForMonth(month,inFormat,outFormat));
        System.out.println(month+" 新增应用："+count);
    }
}
