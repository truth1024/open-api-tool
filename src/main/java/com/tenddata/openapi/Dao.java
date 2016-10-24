package com.tenddata.openapi;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by LD on 2015/10/27 0027.
 */
public class Dao {
    private JdbcTemplate jdbcTemplate;


    public int queryDeveloperidByEmail(String email){
        String sql = "select developerid from developer where email = '"+email+"'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int update(String sql) {
        return jdbcTemplate.update(sql);
    }

    public List<Map<String, Object>> queryProductListByAppid(String appid) {
        String sql = "select * from product where sequencenumber = '" + appid + "';";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String,Object> queryProductByAppidAndPlatfromid(String appid,String platformid) {
        String sql = "select * from product where sequencenumber = '" + appid + "' and platformid = " + platformid + ";";
        return jdbcTemplate.queryForMap(sql);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
