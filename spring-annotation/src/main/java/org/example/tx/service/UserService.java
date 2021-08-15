package org.example.tx.service;

import org.example.tx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: dengzm
 * @date: 2021-08-08 16:55:32
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser(){
        userDao.insert();
        //otherDao.other();xxx
        System.out.println("插入完成...");
        int i = 10/0;
    }
}
