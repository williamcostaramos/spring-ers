package com.williamramos.cursoalgaworks.api.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static String MSG_ERRO_GENERICA_USUARIO = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleErroInternoSistema(Exception ex, WebRequest request){
        String detail =MSG_ERRO_GENERICA_USUARIO;
        Problem problem=createProblem(HttpStatus.INTERNAL_SERVER_ERROR.value(),TypeProblem.ERRO_DE_SISTEMA.getUrl(), TypeProblem.ERRO_DE_SISTEMA.getDescricao(), detail, MSG_ERRO_GENERICA_USUARIO);
        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handlerEntidadeNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest request) {
        Problem problem = createProblem(HttpStatus.NOT_FOUND.value(), TypeProblem.RECURSO_NAO_ENCONTRADO.getUrl(), TypeProblem.RECURSO_NAO_ENCONTRADO.getDescricao(), e.getMessage(), e.getMessage());
        return this.handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request){
        String parametro = e.getParameter().getParameterName();
        String valor = e.getValue().toString();
        String type = e.getRequiredType().getSimpleName();
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s",parametro,valor,type);
        Problem problem = createProblem(HttpStatus.BAD_REQUEST.value(), TypeProblem.PARAMETRO_INVALIDO.getUrl(), TypeProblem.PARAMETRO_INVALIDO.getDescricao(), detail, MSG_ERRO_GENERICA_USUARIO);
        return this.handleExceptionInternal(e,problem,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handlerEntidadeEmUso(EntidadeEmUsoException e, WebRequest request) {

        Problem problem = createProblem(HttpStatus.CONFLICT.value(), TypeProblem.ENTIDADE_EM_USO.getUrl(), TypeProblem.ENTIDADE_EM_USO.getDescricao(), e.getMessage(), e.getMessage());
        return this.handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handlerNegocioException(NegocioException e, WebRequest request) {

        return this.handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable causeRoot = ExceptionUtils.getRootCause(ex);
        if (causeRoot instanceof InvalidFormatException) {
            return handleExceptionInvalidFormatException((InvalidFormatException) causeRoot, status, request);
        }else if(causeRoot instanceof PropertyBindingException){
            return handlePropertyBindingException((PropertyBindingException) causeRoot, status, request);
        }
        Problem problem = createProblem(status.value(), TypeProblem.MENSAGEM_INCOMPREENSIVEL.getUrl(), TypeProblem.MENSAGEM_INCOMPREENSIVEL.getDescricao(), "O corpo da requisicao está invalido. Verifique erro de sintaxe", MSG_ERRO_GENERICA_USUARIO);
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleExceptionInvalidFormatException(InvalidFormatException ex, HttpStatus status, WebRequest request) {
        Problem problem;
        String propriedade = ex.getPath().stream().map(prop -> prop.getFieldName()).collect(Collectors.joining("."));
        String valor = (String) ex.getValue();
        String type = ex.getTargetType().getSimpleName();
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo invalido, Corrija e informe um valor compativel com %s ", propriedade, valor, type);
        problem = createProblem(status.value(), TypeProblem.MENSAGEM_INCOMPREENSIVEL.getUrl(), TypeProblem.MENSAGEM_INCOMPREENSIVEL.getDescricao(), detail, MSG_ERRO_GENERICA_USUARIO);
        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException exception, HttpStatus status, WebRequest request){
        Problem problem;
        String propriedade = exception.getPath().stream().map(prop -> prop.getFieldName()).collect(Collectors.joining(", "));
        String detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", propriedade);
        problem = createProblem(status.value(),TypeProblem.MENSAGEM_INCOMPREENSIVEL.getUrl(), TypeProblem.MENSAGEM_INCOMPREENSIVEL.getDescricao(), detail, MSG_ERRO_GENERICA_USUARIO);

        return handleExceptionInternal(exception, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = String.format("O recurso %s, que você tentou acessar usando o metodo %s é inexistente.", ex.getRequestURL(), ex.getHttpMethod());
        Problem problem = createProblem(status.value(), TypeProblem.RECURSO_NAO_ENCONTRADO.getUrl(), TypeProblem.RECURSO_NAO_ENCONTRADO.getDescricao(), detail, MSG_ERRO_GENERICA_USUARIO);
        return this.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = createProblem(status.value(), status.name(), status.getReasonPhrase(), ex.getMessage(), ex.getMessage());
        } else if (body instanceof String) {
            body = createProblem(status.value(), status.name(), status.getReasonPhrase(), (String) body,(String) body );
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem createProblem(int status, String type, String title, String detail, String userMessage) {
        Problem problem = new Problem();
        problem.status(status).type(type).title(title).detail(detail).userMessage(userMessage).timestamp(LocalDateTime.now());
        return problem;
    }

}
