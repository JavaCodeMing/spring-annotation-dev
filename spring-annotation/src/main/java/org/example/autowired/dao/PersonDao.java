package org.example.autowired.dao;

import org.springframework.stereotype.Repository;

/**
 * @author: dengzm
 * @date: 2021-07-13 22:24:41
 */
@Repository
public class PersonDao {
    private String lable = "1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "PersonDao{" +
                "lable='" + lable + '\'' +
                '}';
    }
}
