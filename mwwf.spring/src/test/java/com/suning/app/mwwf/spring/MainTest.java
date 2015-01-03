package com.suning.app.mwwf.spring;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static SqlSessionTemplate sqlSession;

	@SuppressWarnings("resource")
    public static void main(String[] args) {
		String paths[] = {"applicationContext.xml","applicationContext-wfengine.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		SpringWfEngine a = (SpringWfEngine)ctx.getBean("engine");
		a.engineStart();
	}
}