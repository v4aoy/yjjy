package com.airport.emergency.exception;

/**
 * 禁止访问异常
 */
public class ForbiddenException extends AirportEmergencyException {
    
    private static final long serialVersionUID = 1L;
    
    public ForbiddenException(String message) {
        super("FORBIDDEN", message, 403);
    }
    
    public ForbiddenException() {
        super("FORBIDDEN", "Access denied", 403);
    }
}
