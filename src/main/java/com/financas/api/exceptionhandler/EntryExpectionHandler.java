package com.financas.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.financas.api.exception.UnknownOrInactivePersonException;

@ControllerAdvice
public class EntryExpectionHandler extends ResponseEntityExceptionHandler{
    
    @Autowired
    private MessageSource messageSource;
    
    @ExceptionHandler({UnknownOrInactivePersonException.class})
public ResponseEntity<Object> handleUnknownOrInactivePersonException(UnknownOrInactivePersonException ex, WebRequest request){
    	
    	String userMessage = messageSource.getMessage("person.unknown-or-inactive", null, LocaleContextHolder.getLocale());
        String devMessage = ExceptionUtils.getRootCauseMessage(ex);
        List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
        
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    	
    }
    
   public static class Error {
        
        private String userMessage;
        private String devMessage;
        
        public Error(String userMessage, String devMessage) {
            this.userMessage = userMessage;
            this.devMessage = devMessage;
        }
        public String getUserMessage() {
            return userMessage;
        }
        public void setUserMessage(String userMessage) {
            this.userMessage = userMessage;
        }
        public String getDevMessage() {
            return devMessage;
        }
        public void setDevMessage(String devMessage) {
            this.devMessage = devMessage;
        }
        
        
    }

}

