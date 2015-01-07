package com.suning.app.mwwf.helper.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.suning.app.mwwf.Entity.FlowEntity;
import com.suning.app.mwwf.Entity.RouterEntity;
import com.suning.app.mwwf.bean.RouterBean;
import com.suning.app.mwwf.bean.StageBean;

public class FlowSaxHandler extends DefaultHandler {

	private static final String ELEMENT_FLOWS = "flows";
	private static final String ELEMENT_FLOW = "flow";
	private static final String ELEMENT_STAGE = "stage";
	private static final String ELEMENT_ROUTER = "router";
	
	private static final String ATTRIBUTE_NAME = "name";
	private static final String ATTRIBUTE_RULE = "rule";
	private static final String ATTRIBUTE_EVENTNAME = "eventName";
	private static final String ATTRIBUTE_TOSTAGE = "toStage";
	
	private static final String ATTRIBUTE_NAME_FIRSESTAGE = "firstStage";
	private static final String ATTRIBUTE_NAME_ENDSTAGE = "endStage";
	
	private FlowEntity flowEntity = new FlowEntity();
	private Map<String,List<StageBean>> stageInfoEntity = new LinkedHashMap<String,List<StageBean>>();
	private Map<String,List<RouterEntity>> routerEntity = new LinkedHashMap<String,List<RouterEntity>>();
	
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
        	flowEntity.setName(attr.getValue(ATTRIBUTE_NAME));
        }
        
        // 获取流程的开始节点信息
        if (ELEMENT_STAGE.equals(name) && ATTRIBUTE_NAME_FIRSESTAGE.equals(attr.getValue(ATTRIBUTE_NAME))) {
        	stageInfoEntity.s
        }

    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        if ("forecast_conditions".equals(name)) {
        }
        if ("weather".equals(name)) {
        }
    }
}
