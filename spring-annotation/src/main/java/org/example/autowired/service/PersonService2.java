package org.example.autowired.service;

import org.example.autowired.dao.PersonDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: dengzm
 * @date: 2021-07-13 23:05:06
 */
@Service
public class PersonService2 {

    @Resource
    private PersonDao personDao;

    public void print(){
        System.out.println(personDao);
    }

    @Override
    public String toString() {
        return "PersonService2{" +
                "personDao=" + personDao +
                '}';
    }
}

