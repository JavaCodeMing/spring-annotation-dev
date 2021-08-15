package org.example.autowired.controller;

import org.example.autowired.service.BookService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author: dengzm
 * @date: 2021-07-06 23:38:37
 */
@Controller
public class BookController {
    @Autowired
    private BookService1 bookService1;
}
