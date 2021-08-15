package org.example.autowired.service;

import org.example.autowired.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: dengzm
 * @date: 2021-07-13 23:00:48
 */
@Service
public class PersonService1 {

    @Autowired
    private PersonDao personDao;

    public void print(){
        System.out.println(personDao);
    }

    @Override
    public String toString() {
        return "PersonService1{" +
                "personDao=" + personDao +
                '}';
    }
}
