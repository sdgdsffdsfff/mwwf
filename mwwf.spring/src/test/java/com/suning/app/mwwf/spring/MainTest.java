package com.suning.app.mwwf.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	@SuppressWarnings("resource")
    public static void main(String[] args) {
		String paths[] = {"applicationContext.xml","applicationContext-wfengine.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		SpringWfEngine a = (SpringWfEngine)ctx.getBean("engine");
		a.triggerByInsId("11", "11", false);
	}
}