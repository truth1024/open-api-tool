package com.tenddata.openapi;

import org.springframework.util.StringUtils;

/**
 * Created by LD on 2015/10/29 0029.
 */
public class Main {

    public static void main(String[] args) {
        if(args.length > 1){
            String sql = null;
            JobName jobName = null;
            try {
                jobName = JobName.valueOf(args[0]);
            } catch (IllegalArgumentException e) {
                remind();
                System.exit(0);
            }
            switch (jobName){
                case openapi :
                    try {
                        sql = Open.openH5api(args[1]);
                    } catch (Exception e) {
                        System.out.println("请传入第二个参数");
                    }
                    break;
                case changeemail :
                    try {
                        sql = ChangeEmail.changeEmail(args[1],args[2]);
                    } catch (Exception e) {
                        remind();
                    }
                    break;
                default:
                    remind();
                    break;
            }
//            if(!StringUtils.isEmpty(sql)){
//                sendEmailContent(sql);
//            }
        }else{
            remind();
        }
    }

    /**
     * 提示信息
     */
    public static void remind(){
        System.out.println("请传入至少两个参数");
        System.out.println("第一个参数是操作名称，可选参数：");
        for (JobName jobName : JobName.values()) {
            System.out.println(jobName.toString()+"："+jobName.getDesc());
        }
    }

    /**
     * 生成发送邮件的内容
     * @param sql
     */
    public static void sendEmailContent(String sql){
        StringBuilder stringBuilder = new StringBuilder("-------------------以下是邮件发送内容，请复制----------------------\n");
        stringBuilder.append("帮执行下面的sql语句：").append("\n");
        stringBuilder.append("mysql数据库\n")
                .append("ip : ").append(DatabaseConfig.getProperties("jdbc.ip")).append("\n")
                .append("port : ").append(DatabaseConfig.getProperties("jdbc.port")).append("\n")
                .append("db : ").append(DatabaseConfig.getProperties("jdbc.db")).append("\n")
                .append("sql : ").append(sql).append("\n\n")
                .append("谢谢");
        System.out.println(stringBuilder);
    }
}
