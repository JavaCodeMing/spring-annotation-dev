package org.example.autowired.service;

import org.example.autowired.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author: dengzm
 * @date: 2021-07-13 22:04:05
 */
@Service
public class BookService2 {

    @Qualifier("bookDao2")
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
