package io.github.leocklaus.projectsmanager.api.exceptionhandler;

import io.github.leocklaus.projectsmanager.domain.exception.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public static final String GENERIC_ERROR_MESSAGE
            = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(UserException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var exceptionType = ExceptionType.USER_EXCEPTION;
        String detail = GENERIC_ERROR_MESSAGE;
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        var exceptionType = ExceptionType.USER_NOT_FOUND;
        String detail = ex.getMessage();
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<?> handleUserNotAuthorizedException(UserNotAuthorizedException ex, WebRequest request){
        var status = HttpStatus.FORBIDDEN;
        var exceptionType = ExceptionType.USER_NOT_AUTHORIZED;
        String detail = ex.getMessage();
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserNotMatchingPasswordsException.class)
    public ResponseEntity<?> handleUserNotMatchingPasswordException(UserNotMatchingPasswordsException ex, WebRequest request){
        var status = HttpStatus.FORBIDDEN;
        var exceptionType = ExceptionType.USER_NOT_AUTHORIZED;
        String detail = ex.getMessage();
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<?> handleTaskException(TaskException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var exceptionType = ExceptionType.TASK_EXCEPTION;
        String detail = GENERIC_ERROR_MESSAGE;
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> handleTaskNotFoundException(TaskNotFoundException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        var exceptionType = ExceptionType.TASK_NOT_FOUND;
        String detail = ex.getMessage();
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<?> handleProjectException(ProjectException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var exceptionType = ExceptionType.PROJECT_EXCEPTION;
        String detail = GENERIC_ERROR_MESSAGE;
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<?> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        var exceptionType = ExceptionType.PROJECT_NOT_FOUND;
        String detail = ex.getMessage();
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> handleCommentNotFoundException(CommentNotFoundException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        var exceptionType = ExceptionType.COMMENT_NOT_FOUND;
        String detail = ex.getMessage();
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<?> handleMemberNotFoundException(MemberNotFoundException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        var exceptionType = ExceptionType.MEMBER_NOT_FOUND;
        String detail = ex.getMessage();
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<?> handleNotificationNotFoundException(NotificationNotFoundException ex, WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        var exceptionType = ExceptionType.NOTIFICATION_NOT_FOUND;
        String detail = ex.getMessage();
        ExceptionModel exceptionModel = exceptionBuilder(status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var bindingResult = ex.getBindingResult();
        String detail = "Um ou mais campos estão inválidos. Preencha o formulário novamente.";

        List<ExceptionModel.Object> errors = new ArrayList<>();

        errors = bindingResult.getFieldErrors().stream()
                .map(error -> {
                    String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
                    return ExceptionModel.Object.builder()
                            .name(error.getField())
                            .userMessage(message)
                            .build();
                }).toList();

        var exceptionType = ExceptionType.INVALID_DATA;

        ExceptionModel exceptionModel = exceptionBuilder((HttpStatus) status, exceptionType, detail)
                .userMessage(detail)
                .objects(errors)
                .build();

        return handleExceptionInternal(ex, exceptionModel, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var detail = String.format("O recurso '%s' que você tentou acessar é inexistente", ex.getRequestURL());
        var exceptionType = ExceptionType.RESOURCE_NOT_FOUND;

        ExceptionModel exception = exceptionBuilder((HttpStatus) status, exceptionType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, exception, headers, status, request);

    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if(body == null){
            body = ExceptionModel.builder()
                    .status(statusCode.value())
                    .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                    .type(ex.getMessage())
                    .title(ex.getMessage())
                    .detail(ex.getMessage())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ExceptionModel.ExceptionModelBuilder exceptionBuilder(HttpStatus status, ExceptionType exceptionType, String detail){
        return ExceptionModel.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .type(exceptionType.getURI())
                .title(exceptionType.getTitle())
                .detail(detail);
    }
}
