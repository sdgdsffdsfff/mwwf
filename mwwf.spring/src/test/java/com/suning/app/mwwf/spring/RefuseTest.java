package com.suning.app.mwwf.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RefuseTest {

    public static void main(String[] args) {
		String paths[] = {"applicationContext.xml","applicationContext-wfengine.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		SpringWfEngine a = (SpringWfEngine)ctx.getBean("engine");
		KafkaBizData b = (KafkaBizData)ctx.getBean("kafkaBizData");
		
		// 本次流程实例id
		String uuid = "3838cf80-d562-4df7-ac2a-bb5eeb02f4ed";
		
		// 插入业务数据
		KafkaFlowEntity kafkaFlowEntity = new KafkaFlowEntity();
		kafkaFlowEntity.setAction("REFUSE");
		kafkaFlowEntity.setApplicantId("111");
		kafkaFlowEntity.setApproverId("222");
		kafkaFlowEntity.setFlowInsId(uuid);
		kafkaFlowEntity.setAction("AGREE");
		kafkaFlowEntity.setIsLeader("0");
		kafkaFlowEntity.setRemark("謝謝你了");
		
		kafkaFlowEntity.setSystemName("搜索");
		kafkaFlowEntity.setSystemDes("搜索平台");
		kafkaFlowEntity.setTopic("firstTopic");
		kafkaFlowEntity.setPartition("10");
		kafkaFlowEntity.setRepitition("20");
		
		b.insertKafkaFlow(kafkaFlowEntity);
		b.insertKafkaFlowStage(kafkaFlowEntity);
		// 驱动流程
		a.triggerByInsId(uuid, "kafka", false);
		// --------------------------------------------
		// 首次申请自己是主管
		uuid = "4838cf81-d562-4df7-ac2a-bb5eeb02f4ed";
		a.startFlowInstance(uuid, "kafka");
		
		// 插入业务数据
		kafkaFlowEntity = new KafkaFlowEntity();
		kafkaFlowEntity.setAction("APPLY");
		kafkaFlowEntity.setApplicantId("111");
		kafkaFlowEntity.setApproverId("1112");
		kafkaFlowEntity.setFlowInsId(uuid);
		kafkaFlowEntity.setAction("AGREE");
		kafkaFlowEntity.setIsLeader("1");
		kafkaFlowEntity.setRemark("謝謝你了");
		
		kafkaFlowEntity.setSystemName("搜索");
		kafkaFlowEntity.setSystemDes("搜索平台");
		kafkaFlowEntity.setTopic("firstTopic");
		kafkaFlowEntity.setPartition("10");
		kafkaFlowEntity.setRepitition("20");
		
		b.insertKafkaFlow(kafkaFlowEntity);
		b.insertKafkaFlowStage(kafkaFlowEntity);
		
		// 驱动流程
		a.triggerByInsId(uuid, "kafka", false);
    }
}