package org.example.componentScan.config;

import org.example.componentScan.entity.Person;
import org.springframework.context.annotation.*;

/**
 * 配置类==配置文件
 *
 * 1.使用@Bean注解给容器中注册组件
 * 2.使用@ComponentScan注解自动扫描注册组件及指定扫描规则
 *		value: 指定要扫描的包
 *		excludeFilters: 指定扫描时,按规则排除哪些组件
 *		includeFilters: 指定扫描时,按规则只扫描哪些组件
 *		扫描过滤规则:
 *			FilterType.ANNOTATION: 按照注解
 *			FilterType.ASSIGNABLE_TYPE: 按照给定的类型
 *			FilterType.ASPECTJ: 使用ASPECTJ表达式
 *			FilterType.REGEX: 使用正则指定
 *			FilterType.CUSTOM: 使用自定义规则
 * 3.自定义扫描规则
 *		需要实现TypeFilter接口的match()方法
 *
 * @author: dengzm
 * @date: 2021-07-03 11:21:28
 */
@Configuration
@ComponentScans(value = {
		@ComponentScan(value = "org.example.componentScan",
				includeFilters = {
						//@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
						//@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
						@ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
				}
		)
})
public class MainConfig {
	// 1.使用@Bean注解给容器中注册组件
	@Bean
	public Person person() {
		System.out.println("给容器中添加Person....");
		return new Person("张三", 25);
	}
}
