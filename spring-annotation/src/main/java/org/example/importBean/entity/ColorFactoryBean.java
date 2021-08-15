package org.example.importBean.entity;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author: dengzm
 * @date: 2021-07-04 17:19:59
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    //返回一个Color对象，这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean...getObject...");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    // 该方法能够决定工厂创建的实例[getObject()返回的对象]是否是单例
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
