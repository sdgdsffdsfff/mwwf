package com.suning.app.mwwf.helper;

import java.io.InputStream;

import com.suning.app.mwwf.exception.WfEngineException;

public class StreamHelper {
	
	public static InputStream getStreamFromClasspath(String resourceName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream stream = classLoader.getResourceAsStream(resourceName);

		if (stream == null) {
			stream = StreamHelper.class.getClassLoader().getResourceAsStream(resourceName);
		}

		if (stream == null) {
			throw new WfEngineException("resource " + resourceName + " does not exist");
		}
		return stream;
	}
}
