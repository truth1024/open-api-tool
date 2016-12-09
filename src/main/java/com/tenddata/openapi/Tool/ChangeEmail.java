package com.tenddata.openapi.Tool;

import com.tenddata.openapi.Dao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * Created by LD on 2015/10/29 0029.
 */
public class ChangeEmail extends Tool {

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
            System.out.println("oldEmail : "+oldEmail+",newEmail : "+newEmail);
            Integer developerid = null;
            boolean canUpdate = true;
            try {
                developerid = dao.queryDeveloperidByEmail(oldEmail);
                System.out.println("old developerid : " + developerid);
            } catch (Exception e) {
                if(e.getMessage().contains("Incorrect result size")){
                    System.out.println("通用版不存在" + oldEmail + "账户");
                }else {
                    e.printStackTrace();
                }
                return sql;
            }
            try {
                Integer developerid_new = dao.queryDeveloperidByEmail(newEmail);
                System.out.println("new developerid : " + developerid);
                if(developerid_new > 0) {
                    canUpdate = false;
                }
            } catch (Exception e) {
                if(e.getMessage().contains("Incorrect result size")){
                    System.out.println("newEmail is not exist, can update.");
                }else{
                    e.printStackTrace();
                    canUpdate = false;
                }
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
