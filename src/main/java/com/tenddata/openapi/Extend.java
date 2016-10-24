package com.tenddata.openapi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LD on 2016/10/24 0024.
 */
public class Extend {

    public static void extendPlatform(String appid,String platformid){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
        Dao dao = context.getBean(Dao.class);
        System.out.println("appid : "+ appid +", platformid : "+platformid);
        if(!"1".equals(platformid) || !"2".equals(platformid) || !"16".equals(platformid)){
            System.out.println("platformid是非法的");
            return;
        }
        List<Map<String, Object>> mapList = dao.queryProductListByAppid(appid);
        //先判断应用是否存在
        if(mapList == null || mapList.size() < 1){
            System.out.println("应用不存在");
            return;
        }

        //判断要要扩展的平台是否存在
        boolean isFirst = true;
        boolean isExist = false;
        Map<String, Object> newMap = new HashMap<String, Object>();
        for (Map<String, Object> map : mapList) {
            if(isFirst){
                newMap.put("productid",map.get("productid"));
                newMap.put("sequencenumber", map.get("sequencenumber"));
                newMap.put("developerid", map.get("developerid"));
                newMap.put("platform", Integer.parseInt(platformid));
                newMap.put("productype", map.get("productype"));
                newMap.put("productname", map.get("productname"));
                newMap.put("productmemo","");
                newMap.put("registertime", map.get("registertime").toString().split("\\.")[0]);
                newMap.put("iscompensate", 1);
                isFirst = false;
            }
            if(platformid.equals(map.get("platform").toString())){
                isExist = true;
                break;
            }
        }
        if(isExist){
            System.out.println("要扩展的平台已经存在");
            return;
        }

        //执行扩展平台操作
        String sql = generateSql(newMap);
        System.out.println(sql);
        int update = dao.update(sql);
        if(update > 0){
            System.out.println("平台扩展成功");
        }
    }

    private static String generateSql(Map<String,Object> map) {
        StringBuilder insertStr = new StringBuilder("insert into product (");
        StringBuilder valueStr = new StringBuilder();
        StringBuilder keyStr = new StringBuilder();
        for (String key : map.keySet()) {
            keyStr.append(",`").append(key).append("`");
            if(map.get(key) instanceof Integer) {
                valueStr.append(",").append(map.get(key));
            }else {
                valueStr.append(",'").append(map.get(key)).append("'");
            }
        }
        return insertStr.append(keyStr.toString().substring(1)).append(") values (").append(valueStr.toString().substring(1)).append(");").toString();
    }
}
