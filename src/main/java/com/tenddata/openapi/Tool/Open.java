package com.tenddata.openapi.Tool;

import com.tenddata.openapi.Dao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Created by LD on 2015/10/27 0027.
 */
public class Open extends Tool{

    private static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 开通H5的api
     * @param email
     * @return
     */
    public static String openH5api(String email){
        String sql = null;
        if(!StringUtils.isEmpty(email)){
            System.out.println("email : "+email);
            Integer developerid;
            try {
                developerid = dao.queryDeveloperidByEmail(email);
                System.out.println("developerid : " + developerid);
                String uuid = getUUID();
                System.out.println("uuid : "+uuid);
                sql = "insert into token (token,developerid,email) values ('"+uuid+"',"+developerid+",'"+email+"');";
            } catch (Exception e) {
                System.out.println("email is wrong or db is not exist.");
            }
        }else{
            System.out.println("请传入 email");
        }
        return sql;
    }
}
