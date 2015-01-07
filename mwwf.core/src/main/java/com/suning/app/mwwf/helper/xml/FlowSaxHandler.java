package com.suning.app.mwwf.helper.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.suning.app.mwwf.bean.FlowBean;
import com.suning.app.mwwf.bean.RouterBean;
import com.suning.app.mwwf.bean.StageBean;
import com.suning.app.mwwf.core.FlowManager;

public class FlowSaxHandler extends DefaultHandler {

	private static final String ELEMENT_ROOT = "root";
	private static final String ELEMENT_FLOW = "flow";
	private static final String ELEMENT_STAGE = "stage";
	private static final String ELEMENT_ROUTER = "router";
	
	private static final String ATTRIBUTE_AUTO_TRIGGER = "name";
	private static final String ATTRIBUTE_NAME = "name";
	private static final String ATTRIBUTE_EVENTNAME = "eventName";
	private static final String ATTRIBUTE_TOSTAGE = "toStage";
	
	private static final String ATTRIBUTE_NAME_FIRSESTAGE = "firstStage";
	private static final String ATTRIBUTE_NAME_ENDSTAGE = "endStage";
	
	//private List<NodeInfo<?>> flowDomTree = new ArrayList<NodeInfo<?>>();
	private FlowBean flowBean = new FlowBean();
	private RouterBean routerBean = new RouterBean();
	private StageBean stageBean = new StageBean();
	
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
        	flowBean.setName(attr.getValue(ATTRIBUTE_AUTO_TRIGGER));
        	NodeInfo<FlowBean> flowNode = new NodeInfo<FlowBean>(attr.getValue(ATTRIBUTE_NAME),ELEMENT_ROOT,flowBean);
        	FlowManager.getFlowXmlEnity().add(flowNode);
        }
        
        // 获取节点信息
        if (ELEMENT_STAGE.equals(name)) {
        	stageBean.setName(attr.getValue(ATTRIBUTE_NAME));
        	NodeInfo<StageBean> stageNode = new NodeInfo<StageBean>(attr.getValue(ATTRIBUTE_NAME),flowBean.getName(),stageBean);
        	FlowManager.getFlowXmlEnity().add(stageNode);
        }
        
        // 获取路由信息
        if (ELEMENT_ROUTER.equals(name)) {
        	routerBean.setName(attr.getValue(ATTRIBUTE_NAME));
        	routerBean.setEventName(attr.getValue(ATTRIBUTE_EVENTNAME));
        	routerBean.setToStage(attr.getValue(ATTRIBUTE_TOSTAGE));
        	NodeInfo<RouterBean> routerNode = new NodeInfo<RouterBean>(attr.getValue(ATTRIBUTE_NAME),stageBean.getName(),routerBean);
        	FlowManager.getFlowXmlEnity().add(routerNode);
        }

    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        if (ELEMENT_FLOW.equals(name)) {
        	flowBean = new FlowBean();
        }
        if (ELEMENT_STAGE.equals(name)) {
        	stageBean = new StageBean();
        }
        if (ELEMENT_ROUTER.equals(name)) {
        	routerBean = new RouterBean();
        }
    }
}
