package kg.edu.alatoo.online.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EmailNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(EmailNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({RefreshTokenNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(RefreshTokenNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({RoleNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(RoleNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({BagNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(BagNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}

