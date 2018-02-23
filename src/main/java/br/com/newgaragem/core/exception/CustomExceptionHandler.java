package br.com.newgaragem.core.exception;




import br.com.newgaragem.core.controller.JsonResponse;
import br.com.newgaragem.core.controller.JsonResponseFactory;
import br.com.newgaragem.core.controller.JsonResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @Autowired
    protected JsonResponseService jsonResponseService;

    @Autowired
    protected JsonResponseFactory jsonResponseFactory;

    private ResponseEntity<?> formatDefaultResponse(Exception e){
        return this.formatDefaultResponse(e, jsonResponseService.getHttpStatus());
    }
    private ResponseEntity<?> formatDefaultResponse(Exception e, HttpStatus status){
        return this.formatDefaultResponse(e.getMessage(), status);
    }


    private ResponseEntity<?> formatDefaultResponse(String messageKey, HttpStatus status){
        jsonResponseService.clearMessages();
        jsonResponseService.addError(messageKey);
        JsonResponse response = jsonResponseFactory.create(null, jsonResponseService.getMessageList());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(RuntimeException e) {
        System.out.println("------ AccessDeniedException.class =" + e.getStackTrace());
        return this.formatDefaultResponse(ExceptionMessageCode.MENSAGEM_UNAUTHORIZED_ERROR, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({EntityNotFoundException.class, CustomNotFoundException.class })
    public ResponseEntity<?> handleEntityNotFoundException(RuntimeException e) {
        System.out.println("------ NotFoundException " + e.getStackTrace());
        return this.formatDefaultResponse(ExceptionMessageCode.MENSAGEM_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RuntimeException.class, GenericException.class})
    public ResponseEntity<?> handleRuntimeException(RuntimeException e){
        System.out.println("------ RuntimeException -  GenericException " + e.getClass() + " / " + e.getMessage());
        return this.formatDefaultResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
