package exam.management.be.exceptions;
        
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>("Requested resource not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>("There’s been a glitch…", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BeanDefinitionStoreException.class)
    public ResponseEntity<?> beanDefinitionStoreException(BeanDefinitionStoreException exception) {
        return new ResponseEntity<>("There’s been a glitch…", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseEntity<?> fileNotFoundException(FileNotFoundException exception) {
        return new ResponseEntity<>("There’s been a glitch…", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>("Request không hợp lệ.", HttpStatus.BAD_REQUEST);
    }
}
