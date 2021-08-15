package org.example.autowired.dao;

import org.springframework.stereotype.Repository;

/**
 * @author: dengzm
 * @date: 2021-07-06 23:22:24
 */
@Repository
public class BookDao {
    private String lable = "1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "lable='" + lable + '\'' +
                '}';
    }
}
