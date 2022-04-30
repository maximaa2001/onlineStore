package by.bsuir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductIsNotExistException extends ResponseStatusException {
    public ProductIsNotExistException(HttpStatus status) {
        super(status);
    }
}
