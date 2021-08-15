package org.example.autowired.service;

import org.example.autowired.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author: dengzm
 * @date: 2021-07-13 22:48:26
 */
@Service
public class BookService3 {

    // id为bookDao1的BookDao不存在于容器中
    @Qualifier("bookDao1")
    @Autowired(required = false)
    private BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService3{" +
                "bookDao=" + bookDao +
                '}';
    }
}
