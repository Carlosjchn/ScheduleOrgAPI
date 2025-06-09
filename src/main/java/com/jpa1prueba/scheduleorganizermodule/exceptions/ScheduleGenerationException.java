package com.jpa1prueba.scheduleorganizermodule.exceptions;

public class ScheduleGenerationException extends RuntimeException {
    public ScheduleGenerationException(String message) {
        super(message);
    }

    public ScheduleGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
} 