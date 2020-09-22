package com.financas.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FinancasExpectionHandler extends ResponseEntityExceptionHandler{
    
    @Autowired
    private MessageSource messageSource;
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause().toString();
        List<Error> erros = Arrays.asList(new Error(mensagemUsuario, mensagemDesenvolvedor));
        
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {

    	List<Error> erros = createListErrors(ex.getBindingResult());
    	
    	return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }
    
    private List<Error> createListErrors(BindingResult bindingResult){
    	List<Error> errors = new ArrayList<>();
    	
    	bindingResult.getFieldErrors().forEach(e -> {
    		String mensagemUsuario = messageSource.getMessage(e, LocaleContextHolder.getLocale());
    		String mensagemDesenvolvedor = e.toString();
    		errors.add(new Error(mensagemUsuario, mensagemDesenvolvedor));
    	});

    	
    	return errors;
    }
    
    public static class Error {
        
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;
        
        public Error(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }
        public String getMensagemUsuario() {
            return mensagemUsuario;
        }
        public void setMensagemUsuario(String mensagemUsuario) {
            this.mensagemUsuario = mensagemUsuario;
        }
        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
        public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }
        
        
    }

}

