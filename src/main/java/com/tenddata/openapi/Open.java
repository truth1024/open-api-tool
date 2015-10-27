package com.tenddata.openapi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.UUID;

/**
 * Created by LD on 2015/10/27 0027.
 */
public class Open {
    public static void main(String[] args) {
        if(args.length > 0){
            ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
            Dao dao = context.getBean(Dao.class);
            System.out.println("email : "+args[0]);
            Integer developerid = null;
            try {
                developerid = dao.queryDeveloperidByEmail(args[0]);
                System.out.println("developerid : " + developerid);
            } catch (Exception e) {
                System.out.println("email is wrong or db is not exist.");
                System.exit(0);
            }
            String uuid = getUUID();
            System.out.println("uuid : "+uuid);
            System.out.println("insert into token (token,developerid,email) values ('"+uuid+"',"+developerid+",'"+args[0]+"')");
        }else{
            System.out.println("input email");
            System.exit(0);
        }
    }

    private static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
