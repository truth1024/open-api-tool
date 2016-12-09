package com.tenddata.openapi.Tool;

import com.tenddata.openapi.Dao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by LD on 2016/12/9 0009.
 */
public class Tool {
    static Dao dao;

    static {
        System.out.println("spring init ...");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
        System.out.println("get dao bean ...");
        dao = context.getBean(Dao.class);
    }

}
