package by.bsuir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuctionAlsoExistsException extends ResponseStatusException {
    public AuctionAlsoExistsException(HttpStatus status) {
        super(status);
    }
}
