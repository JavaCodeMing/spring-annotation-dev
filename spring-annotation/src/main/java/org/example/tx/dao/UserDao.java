package org.example.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author: dengzm
 * @date: 2021-08-08 16:55:47
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "insert into tbl_user (name,age) values (?,?)";
        String username = UUID.randomUUID().toString().substring(0, 5);
        int age = (int)(15 + Math.random()*(30-15+1));
        jdbcTemplate.update(sql,username,age);
    }
}
