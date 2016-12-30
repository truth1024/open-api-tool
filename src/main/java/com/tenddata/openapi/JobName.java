package com.tenddata.openapi;

/**
 * Created by LD on 2015/10/29 0029.
 */
public enum JobName {
    //开通H5api
    openapi("开通H5api接口账户，如果是openapi时，第二个参数是要开通账户的email"),
    //更改账户email
    changeemail("更改开发者的邮箱，如果是changeemail时，则第二个参数是旧的email，第三个参数是新的email"),
    //扩展平台
    extend("扩展平台，如果是extend时，则第二个参数是appid，第三个参数是平台id；android：1，ios：2，wp：4，html5：16 ,微信小程序: 32"),

    newCount("获取某月新增账户数和新增应用数，如果是newCount时，则第二个参数是时间，格式：yyyy-MM"),

    addAuthorize("增加某账户对某产品的权限，第二个参数是账户(email)名，第三个参数是appid");

    String desc;


    JobName(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
