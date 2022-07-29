package com.quakelog.file;

public class LogFormatException extends RuntimeException{

	private static final long serialVersionUID = -8923849204576142901L;
	
	public LogFormatException(String message, Throwable cause) {
        super(message, cause);
    } 
	
	public LogFormatException(String message) {
        super(message);
    }

	
	
}
