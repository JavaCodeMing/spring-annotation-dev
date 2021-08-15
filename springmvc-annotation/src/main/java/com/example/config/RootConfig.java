package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 父容器(即Spring容器)
 *  1.扫描"com.example"下的除Controller之外的所有组件
 *
 * @author: dengzm
 * @date: 2021-08-15 00:51:33
 */
@ComponentScan(value = "com.example",excludeFilters = {
        // 父容器排除Controller的扫描
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class RootConfig {
}
