package com.pilog.plontology.exception;



import com.pilog.plontology.exception.enums.ExceptionMessage;
import com.pilog.plontology.utils.PlontologyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomErrorHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(ExceptionMessage.INERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessage.INERNAL_SERVER_ERROR.getErrorCode()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(notFoundException.getStatusCode()).body(getErrorResponse(notFoundException.getMessage(), notFoundException.getRawStatusCode(), notFoundException.getStatusText()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException badRequestException) {
        return ResponseEntity.status(badRequestException.getStatusCode()).body(getErrorResponse(badRequestException.getMessage(), badRequestException.getRawStatusCode(), badRequestException.getStatusText()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponseDto errors = ex.getBindingResult().getFieldErrors().stream().filter(fieldError -> fieldError.getDefaultMessage() != null).map(this::getReadableErrorMessage).findFirst().orElse(ErrorResponseDto.builder().errorCode("NaN").message("NaN").timestamp(PlontologyUtils.getCurrentDateTime()).build());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body is missing or invalid.");
    }


    public static ErrorResponseDto getErrorResponse(String message, Integer statusCode, String errorCode) {
        return ErrorResponseDto.builder().message(message).timestamp(PlontologyUtils.getCurrentDateTime()).status(statusCode).errorCode(errorCode).build();
    }

    private ErrorResponseDto getReadableErrorMessage(FieldError fieldError) {
        return ErrorResponseDto.builder().message(fieldError.getDefaultMessage()).timestamp(PlontologyUtils.getCurrentDateTime()).status(HttpStatus.BAD_REQUEST.value()).errorCode(fieldError.toString()).build();
    }


}
