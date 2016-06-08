package com.tenddata.openapi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * Created by LD on 2015/10/29 0029.
 */
public class ChangeEmail {

    /**
     * 更新账户邮箱
     * @param oldEmail
     * @param newEmail
     * @return
     */
    public static String changeEmail(String oldEmail,String newEmail){
        String sql = null;
        if(StringUtils.isEmpty(oldEmail)
                || StringUtils.isEmpty(newEmail)){
            System.out.println("新旧email都不能为空");
        }else{
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
            Dao dao = context.getBean(Dao.class);
            System.out.println("oldEmail : "+oldEmail+",newEmail : "+newEmail);
            Integer developerid = null;
            boolean canUpdate = true;
            try {
                developerid = dao.queryDeveloperidByEmail(oldEmail);
                System.out.println("old developerid : " + developerid);
            } catch (Exception e) {
                System.out.println("通用版不存在" + oldEmail + "账户");
                e.printStackTrace();
                return sql;
            }
            try {
                Integer developerid_new = dao.queryDeveloperidByEmail(newEmail);
                System.out.println("new developerid : " + developerid);
                if(developerid_new > 0) {
                    canUpdate = false;
                }
            } catch (Exception e) {
                System.out.println("newEmail is not exist, can update.");
            }
            if(canUpdate){
                sql = "update developer set email = '"+newEmail+"' where email = '"+oldEmail+"' and developerid = "+developerid+";";
                System.out.println("sql : "+sql);
                int result = dao.update(sql);
                System.out.println("result : " + result);
                System.out.println("通用版已更新");
            }else {
                System.out.println("通用版新邮箱已经存在账户");
            }

        }
        return sql;
    }
}
