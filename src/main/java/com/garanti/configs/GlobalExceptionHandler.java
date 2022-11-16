package com.garanti.configs;

import com.garanti.props.Rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handler( MethodArgumentNotValidException exception ) {
        Rest rest = new Rest();

        List<Map> lshm = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getFieldErrors();
        for ( FieldError error : fieldErrors ) {
            Map<String, String > hm = new HashMap<>();
            String filed = error.getField();
            String message = error.getDefaultMessage();
            hm.put("filed", filed );
            hm.put("message", message);
            lshm.add(hm);
        }
        rest.setStatus(false);
        rest.setResult(lshm);
        return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
    }

}
