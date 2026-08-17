package com.airport.emergency.exception;

import lombok.Getter;

/**
 * 机场应急事件系统异常基类
 */
@Getter
public class AirportEmergencyException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private String errorCode;
    private int httpStatus;
    
    public AirportEmergencyException(String message) {
        super(message);
        this.errorCode = "INTERNAL_ERROR";
        this.httpStatus = 500;
    }
    
    public AirportEmergencyException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = 500;
    }
    
    public AirportEmergencyException(String errorCode, String message, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
    
    public AirportEmergencyException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "INTERNAL_ERROR";
        this.httpStatus = 500;
    }
    
    public AirportEmergencyException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = 500;
    }
}
