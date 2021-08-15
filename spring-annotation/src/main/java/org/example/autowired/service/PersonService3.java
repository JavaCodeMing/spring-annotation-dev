package org.example.autowired.service;

import org.example.autowired.dao.PersonDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author: dengzm
 * @date: 2021-07-13 23:13:52
 */
@Service
public class PersonService3 {
    @Inject
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
