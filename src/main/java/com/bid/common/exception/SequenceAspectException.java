package com.bid.common.exception;

/**
 * 数据表序列操作Exception
 */
public class SequenceAspectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SequenceAspectException() {
        super();
    }

    public SequenceAspectException(String message) {
        super(message);
    }

    public SequenceAspectException(Throwable cause) {
        super(cause);
    }

    public SequenceAspectException(String message, Throwable cause) {
        super(message, cause);
    }
}
