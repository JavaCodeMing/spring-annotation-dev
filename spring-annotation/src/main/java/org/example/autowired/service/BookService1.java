package org.example.autowired.service;

import org.example.autowired.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: dengzm
 * @date: 2021-07-06 23:36:17
 */
@Service
public class BookService1 {

    @Autowired
    private BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService [bookDao=" + bookDao + "]";
    }

}
