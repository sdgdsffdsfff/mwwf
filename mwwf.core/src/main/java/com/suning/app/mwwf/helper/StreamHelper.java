package com.suning.app.mwwf.helper;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.suning.app.mwwf.helper.xml.FlowSaxHandler;

public class StreamHelper {

	private static final Logger logger = LoggerFactory.getLogger(StreamHelper.class);
	
	/**
	 * 从当前环境或者当前线程下加载资源
	 * 
	 * @param resourceName 资源的名称
	 * @return 资源的流
	 */
	public static InputStream getStreamFromClasspath(String resourceName) {
		InputStream stream = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			stream = classLoader.getResourceAsStream(resourceName);

			if (stream == null) {
				stream = StreamHelper.class.getClassLoader().getResourceAsStream(resourceName);
			}

			if (stream == null) {
				throw new Exception("resource " + resourceName + " does not exist");
			}

			logger.info("加载资源文件成功:{}",resourceName);
			
		} catch (SecurityException  e) {
			logger.error("未能获取上下文的ClassLoader",e);
		} catch (Exception e) {
			logger.error("加载资源文件成功:{}",resourceName,e);
		}
		return stream;
	}

	/**
	 * 解析flow的xml文件
	 * 
	 * @param resourceName 资源名
	 */
	public static void parseFlowXml(String resourceName) {
		
		InputStream flowIs = null;
		try {
			
			// 创建sax的实例
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			
			// 获取xml的流信息
			flowIs = getStreamFromClasspath(resourceName);
			
			// 解析flow的xml文件
			parser.parse(flowIs, new FlowSaxHandler());
			
		} catch (ParserConfigurationException e) {
			logger.error("配置出错", e);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(flowIs != null) {
				try {
					flowIs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
