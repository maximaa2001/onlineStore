package by.bsuir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductIsNotLinkedException extends ResponseStatusException {
    public ProductIsNotLinkedException(HttpStatus status) {
        super(status);
    }
}
