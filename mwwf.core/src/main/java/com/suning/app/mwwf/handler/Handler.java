package com.suning.app.mwwf.handler;

/**
 * 
 * 所有的模型对象需要实现的接口，需要实现execute方法，每个节点的执行方式不一样
 * 
 * @author 吴圣平 
 * 		2014年12月23日 下午2:20:04
 * 
 */
public interface Handler {
	
	boolean execute();
}