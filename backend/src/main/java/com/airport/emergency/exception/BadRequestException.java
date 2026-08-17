package com.airport.emergency.exception;

/**
 * 请求参数错误异常
 */
public class BadRequestException extends AirportEmergencyException {
    
    private static final long serialVersionUID = 1L;
    
    public BadRequestException(String message) {
        super("BAD_REQUEST", message, 400);
    }
    
    public BadRequestException(String fieldName, String message) {
        super("BAD_REQUEST", 
              String.format("Invalid %s: %s", fieldName, message), 
              400);
    }
}
