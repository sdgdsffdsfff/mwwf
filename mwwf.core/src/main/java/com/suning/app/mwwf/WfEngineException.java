package com.suning.app.mwwf;

public class WfEngineException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5220859421440167454L;

	public WfEngineException() {
		super();
	}

	public WfEngineException(String msg, Throwable cause) {
		super(msg);
		super.initCause(cause);
	}

	public WfEngineException(String msg) {
		super(msg);
	}

	public WfEngineException(Throwable cause) {
		super();
		super.initCause(cause);
	}
}
