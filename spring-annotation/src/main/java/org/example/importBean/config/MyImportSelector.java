package org.example.importBean.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要注册的组件
 *
 * @author: dengzm
 * @date: 2021-07-04 20:03:14
 */
public class MyImportSelector implements ImportSelector {
    /**
     * @param annotationMetadata 当前标注@Import注解的类的所有注解信息
     * @return 导入到容器中的组件全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //方法不要返回null值(会产生空指针异常),为空的话返回空数组
        return new String[]{"org.example.importBean.entity.Blue","org.example.importBean.entity.Yellow"};
    }

}
