package com.suning.app.mwwf.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suning.app.mwwf.core.DataManager;

public class MainTest {

    public static void main(String[] args) {
		String paths[] = {"applicationContext.xml","applicationContext-wfengine.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		SpringWfEngine a = (SpringWfEngine)ctx.getBean("engine");
		KafkaBizData b = (KafkaBizData)ctx.getBean("kafkaBizData");
		//a.triggerByInsId("3838cf80-d562-4df7-ac2a-bb5eeb02f4ed", "kafka", false);
		System.out.println(a.startFlowInstance("3838cf80", "kafka"));
    }
}