package com.suning.app.mwwf.helper;

import java.io.StringWriter;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.app.mwwf.core.BizDataAbstract;

/**
 * 类VelocityHelper.java的实现描述：TODO 类实现描述
 * 
 * @author 14061798 2015年1月12日 上午11:18:53
 */
public class VelocityHelper {

	private static final Logger logger = LoggerFactory.getLogger(VelocityHelper.class);

	private static VelocityEngine velocityEngine = new VelocityEngine();

	private static final String VALIDATE_RESULT_TRUE = "true";

	private static VelocityContext initCtx(List<BizDataAbstract<?>> bizData, String keyId) {

		VelocityContext context = new VelocityContext();

		if (CollectionUtils.isEmpty(bizData)) {
			return context;
		}

		for (BizDataAbstract<?> data : bizData) {
			context.put(data.getName(), data.get(keyId));
		}
		return context;
	}

	public static boolean validate(List<BizDataAbstract<?>> bizDatas, String expression,
			String keyId) {

		try {

			// velocity从业务上下文获取
			VelocityContext context = initCtx(bizDatas, keyId);

			if(context == null) {
				throw new Exception("获取上下文失败!");
			}
			
			// 存储解析返回内容
			StringWriter writer = new StringWriter();

			// 开始解析
			boolean excuteFlg = velocityEngine.evaluate(context, writer, "SEngine", expression);
			if (excuteFlg && VALIDATE_RESULT_TRUE.equalsIgnoreCase(String.valueOf(writer).trim())) {
				return true;
			}
		} catch (ParseErrorException e) {
			logger.error("规则模板解析出错,外键为:{},规则名为:{}", new String[] { keyId, expression }, e);
		} catch (MethodInvocationException e) {
			logger.error("规则模板解析出错,外键为:{},规则名为:{}", new String[] { keyId, expression }, e);
		} catch (ResourceNotFoundException e) {
			logger.error("规则模板解析出错,外键为:{},规则名为:{}", new String[] { keyId, expression }, e);
		} catch (Exception e) {
			logger.error("规则模板解析出错,外键为:{},规则名为:{}", new String[] { keyId, expression }, e);
		}
		return false;
	}
}
