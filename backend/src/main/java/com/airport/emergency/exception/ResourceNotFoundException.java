package com.airport.emergency.exception;

/**
 * 资源不存在异常
 */
public class ResourceNotFoundException extends AirportEmergencyException {
    
    private static final long serialVersionUID = 1L;
    
    public ResourceNotFoundException(String resourceName, Long id) {
        super("RESOURCE_NOT_FOUND", 
              String.format("%s with id %d not found", resourceName, id), 
              404);
    }
    
    public ResourceNotFoundException(String resourceName, String identifier) {
        super("RESOURCE_NOT_FOUND", 
              String.format("%s '%s' not found", resourceName, identifier), 
              404);
    }
    
    public ResourceNotFoundException(String message) {
        super("RESOURCE_NOT_FOUND", message, 404);
    }
}
