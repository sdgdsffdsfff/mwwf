package com.suning.app.mwwf.helper.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.suning.app.mwwf.bean.FlowInfoBean;
import com.suning.app.mwwf.bean.RouterInfoBean;
import com.suning.app.mwwf.bean.StageInfoBean;
import com.suning.app.mwwf.core.FlowManager;

public class FlowSaxHandler extends DefaultHandler {

	private static final String ELEMENT_ROOT = "root";
	private static final String ELEMENT_FLOW = "flow";
	private static final String ELEMENT_STAGE = "stage";
	private static final String ELEMENT_ROUTER = "router";
	
	private static final String ATTRIBUTE_AUTO_TRIGGER = "autoTrigger";
	private static final String ATTRIBUTE_NAME = "name";
	private static final String ATTRIBUTE_EVENTNAME = "eventName";
	private static final String ATTRIBUTE_TOSTAGE = "toStage";
	
	private FlowInfoBean flowBean = new FlowInfoBean();
	private RouterInfoBean routerBean = new RouterInfoBean();
	private StageInfoBean stageBean = new StageInfoBean();
	
	@Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String name,
            Attributes attr) throws SAXException {
    	
    	// 获取流程信息
        if (ELEMENT_FLOW.equals(name)) {
        	flowBean.setName(attr.getValue(ATTRIBUTE_NAME));
        	flowBean.setAutoTrigger(attr.getValue(ATTRIBUTE_AUTO_TRIGGER));
        	NodeInfo<FlowInfoBean> flowNode = new NodeInfo<FlowInfoBean>(attr.getValue(ATTRIBUTE_NAME),ELEMENT_ROOT,flowBean);
        	FlowManager.getFlowXmlEnity().add(flowNode);
        }
        
        // 获取节点信息
        if (ELEMENT_STAGE.equals(name)) {
        	stageBean.setName(attr.getValue(ATTRIBUTE_NAME));
        	stageBean.setToStage(attr.getValue(ATTRIBUTE_TOSTAGE));
        	NodeInfo<StageInfoBean> stageNode = new NodeInfo<StageInfoBean>(attr.getValue(ATTRIBUTE_NAME),flowBean.getName(),stageBean);
        	FlowManager.getFlowXmlEnity().add(stageNode);
        }
        
        // 获取路由信息
        if (ELEMENT_ROUTER.equals(name)) {
        	routerBean.setName(attr.getValue(ATTRIBUTE_NAME));
        	routerBean.setEventName(attr.getValue(ATTRIBUTE_EVENTNAME));
        	routerBean.setToStage(attr.getValue(ATTRIBUTE_TOSTAGE));
        	NodeInfo<RouterInfoBean> routerNode = new NodeInfo<RouterInfoBean>(attr.getValue(ATTRIBUTE_NAME),stageBean.getName(),routerBean);
        	FlowManager.getFlowXmlEnity().add(routerNode);
        }

    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        if (ELEMENT_FLOW.equals(name)) {
        	flowBean = new FlowInfoBean();
        }
        if (ELEMENT_STAGE.equals(name)) {
        	stageBean = new StageInfoBean();
        }
        if (ELEMENT_ROUTER.equals(name)) {
        	routerBean = new RouterInfoBean();
        }
    }
}
