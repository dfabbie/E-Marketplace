package com.marketplace.demo.Exception;

import com.marketplace.demo.Controller.error.BadRequestAlertException;
import com.marketplace.demo.Controller.error.ForbiddenAlertException;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Builder
    record Error(Integer code, String error ,String message){
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BadRequestAlertException.class })
    public Error handleAll(AbstractThrowableProblem problem) {
        Error error = Error.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .error(problem.getDetail())
                .message(problem.getMessage())
                .build();
        return error;
//        return Error.builder()
//        .code(Objects.requireNonNull(problem.getStatus()).getStatusCode()).error(problem.getTitle()).message(problem.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ ForbiddenAlertException.class })
    public Error handleForbidden (AbstractThrowableProblem problem) {
        Error error = Error.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .message(problem.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error).getBody();
//        return Error.builder()
//                .code(Objects.requireNonNull(problem.getStatus()).getStatusCode())
//                .error(problem.getTitle())
//                .message(problem.getMessage())
//                .build();
    }
}
