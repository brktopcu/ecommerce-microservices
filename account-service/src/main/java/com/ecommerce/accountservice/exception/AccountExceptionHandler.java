package com.ecommerce.accountservice.exception;

import com.ecommerce.accountservice.response.NotFoundExceptionResponse;
import com.ecommerce.accountservice.response.UsernameAlreadyExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex,
                                                                WebRequest request){
        NotFoundExceptionResponse response = new NotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex,
                                                                             WebRequest request){
        UsernameAlreadyExistsResponse response = new UsernameAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
