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
            Integer newDeveloperid = null;
            boolean canUpdate = false;
            try {
                developerid = dao.queryDeveloperidByEmail(oldEmail);
                System.out.println("old developerid : " + developerid);
            } catch (Exception e) {
                if(e.getMessage().contains("Incorrect result size")){
                    System.out.println("old email is wrong or db is not exist.");
                }else{
                    e.printStackTrace();
                }
                return sql;
            }
            try {
                newDeveloperid = dao.queryDeveloperidByEmail(newEmail);
                System.out.println("new developerid : " + newDeveloperid);
            } catch (Exception e) {
                if(e.getMessage().contains("Incorrect result size")){
                    System.out.println("new email is not exist. can update");
                    canUpdate = true;
                }else{
                    e.printStackTrace();
                }
            }
            if(canUpdate){
                sql = "update developer set email = '"+newEmail+"' where email = '"+oldEmail+"' and developerid = "+developerid+";";
            }
        }
        return sql;
    }
}
