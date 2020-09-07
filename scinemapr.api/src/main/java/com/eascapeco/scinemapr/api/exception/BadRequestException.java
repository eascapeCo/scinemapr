package com.eascapeco.scinemapr.api.exception;

/**
 * 
 * 
 * @since 2020. 09. 07.
 * @author JaeHan-Kim
 *
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 2523656251260631229L;

	private final int statusCode;
	
	public BadRequestException() {
		this.statusCode = 999;
	}

    public BadRequestException(String message) {
        super(message);
        this.statusCode = 999;
    }

    public BadRequestException(Exception exception) {
        super(exception);
        this.statusCode = 999;
    }

    public BadRequestException(int statusCode) {
        this.statusCode = statusCode;
    }

    public BadRequestException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public BadRequestException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getstatusCode() {
        return statusCode;
    }
}
