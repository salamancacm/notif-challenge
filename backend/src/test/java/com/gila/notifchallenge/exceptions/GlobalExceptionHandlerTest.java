package com.gila.notifchallenge.exceptions;

import com.fasterxml.jackson.core.JsonParser;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest mockWebRequest;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        mockWebRequest = mock(WebRequest.class);
        when(mockWebRequest.getDescription(false)).thenReturn("uri=/api/test");
    }

    @Test
    void handleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");
        ResponseEntity<Object> response = globalExceptionHandler.handleIllegalArgumentException(ex, mockWebRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Bad Request", body.get("error"));
        assertEquals("Invalid argument", body.get("message"));
    }

    @Test
    void handleGlobalException() {
        Exception ex = new Exception("General error");
        ResponseEntity<Object> response = globalExceptionHandler.handleGlobalException(ex, mockWebRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Internal Server Error", body.get("error"));
        assertEquals("An unexpected error occurred", body.get("message"));
    }

    @Test
    void handleInvalidCategoryException() {
        InvalidCategoryException ex = new InvalidCategoryException("Invalid category");
        ResponseEntity<Object> response = globalExceptionHandler.handleInvalidCategoryException(ex, mockWebRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Invalid Category", body.get("error"));
        assertEquals("Invalid category", body.get("message"));
    }

    @Test
    void handleUnrecognizedPropertyException() {
        //A mock JsonParser
        JsonParser mockJsonParser = mock(JsonParser.class);

        //Adummy object to represent the source of the exception
        Object dummySource = new Object();

        //An UnrecognizedPropertyException with the mocked JsonParser and dummy source
        UnrecognizedPropertyException ex = UnrecognizedPropertyException.from(
                mockJsonParser,
                dummySource,
                "unknownField",
                Collections.emptyList()
        );

        ResponseEntity<Object> response = globalExceptionHandler.handleUnrecognizedPropertyException(ex, mockWebRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Bad Request", body.get("error"));
        assertEquals("Unknown field: unknownField", body.get("message"));
    }

    @Test
    void handleRuntimeException() {
        RuntimeException ex = new RuntimeException("Runtime exception");
        ResponseEntity<Object> response = globalExceptionHandler.handleRuntimeException(ex, mockWebRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Internal Server Error", body.get("error"));
        assertEquals("An unexpected error occurred: Runtime exception", body.get("message"));
    }

    @Test
    void handleUnsupportedChannelException() {
        UnsupportedChannelException ex = new UnsupportedChannelException("Channel not supported");
        ResponseEntity<Object> response = globalExceptionHandler.handleUnsupportedChannelException(ex, mockWebRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Unsupported Channel", body.get("error"));
        assertEquals("Channel not supported", body.get("message"));
    }
}
