package com.suning.app.mwwf.helper;

/**
 * 打印类
 * 
 * @author 14061798 2015年1月2日 上午11:35:36
 */
public class PrintHelper {
	
	/**
	 * 打印树结构
	 * 
	 * @param name
	 * 		节点名
	 * @param depth
	 * 		节点深度
	 */
	public static void print(String name, int depth) {
		for (int i = 0; i < depth; i++) {
			System.out.print("   ");
		}
		System.out.println("|--" + name);
	}
}
