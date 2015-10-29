package com.tenddata.openapi;

/**
 * Created by LD on 2015/10/29 0029.
 */
public enum JobName {
    //开通H5api
    openapi("开通H5api接口账户，如果是openapi时，第二个参数是要开通账户的email"),
    //更改账户email
    changeemail("更改开发者的邮箱，如果是changeemail时，则第二个参数是旧的email，第三个参数是新的email");

    String desc;


    JobName(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
