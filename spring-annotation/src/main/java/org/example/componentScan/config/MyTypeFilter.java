package org.example.componentScan.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author: dengzm
 * @date: 2021-07-03 11:45:25
 */
public class MyTypeFilter implements TypeFilter {
	/**
	 * @param metadataReader        the metadata reader for the target class(读取到的当前正在扫描的类的信息)
	 * @param metadataReaderFactory a factory for obtaining metadata readers for other classes
	 *                              (such as superclasses and interfaces)(可以获取到其他任何类的信息)
	 * @return
	 * @throws IOException
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		//获取当前类注解的信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		//获取当前正在扫描的类的类信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		//获取当前类资源（类的路径）
		Resource resource = metadataReader.getResource();

		String className = classMetadata.getClassName();
		System.out.println("当前扫描的类：" + className);

		// 自定义过滤条件
		if (className.contains("ller")) {
			return true;
		}
		return false;
	}
}
