package com.airport.emergency.exception;

/**
 * 未授权异常
 */
public class UnauthorizedException extends AirportEmergencyException {
    
    private static final long serialVersionUID = 1L;
    
    public UnauthorizedException(String message) {
        super("UNAUTHORIZED", message, 401);
    }
    
    public UnauthorizedException() {
        super("UNAUTHORIZED", "User is not authenticated", 401);
    }
}
