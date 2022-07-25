package com.example.algamoney.api.esceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//esta de olho na aplicacao, e controla as mensagens dos erros
@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	//capturando uma mensagem específica de erro que pode ter
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause().toString();
		
		//para mostrar o erro para o usuario e o desenvolvedor
		class Erro {
			private String mensagemUsuario;
			private String mensagemDesenvolvedor;
			
			//gets
			public String getMensagemUsuario() {
				return mensagemUsuario;
			}
			public String getMensagemDesenvolvedor() {
				return mensagemDesenvolvedor;
			}
			
			//construtor
			public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
				super();
				this.mensagemUsuario = mensagemUsuario;
				this.mensagemDesenvolvedor = mensagemDesenvolvedor;
			}
		}
		
		return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	}
}
