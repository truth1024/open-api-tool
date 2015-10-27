package com.tenddata.openapi;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by LD on 2015/10/27 0027.
 */
public class Dao {
    private JdbcTemplate jdbcTemplate;


    public int queryDeveloperidByEmail(String email){
        String sql = "select developerid from developer where email = '"+email+"'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
