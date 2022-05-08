package by.bsuir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RatingAlsoExistsException extends ResponseStatusException {
    public RatingAlsoExistsException(HttpStatus status) {
        super(status);
    }
}
