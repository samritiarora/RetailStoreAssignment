package enterprise.org.assignment.exception;

import enterprise.org.assignment.exception.model.ErrorResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseModel> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        log.error("IllegalArgumentExceptionHandler:", ex);
        ErrorResponseModel errorResponseModel =
                ErrorResponseModel.builder().code(ex.getMessage()).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ErrorResponseModel> handleServiceException(ServiceException ex) {
        log.error("Got Service Exception.", ex);

        ErrorResponseModel response =
                ErrorResponseModel.builder().code(ex.getMessage()).message(ex.getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> genericExceptionHandler(Exception ex) {
        log.error("Unknown exception occurred", ex);
        return new ResponseEntity<>(ErrorResponseModel.builder().code("UNKNOWN").message(ex.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
