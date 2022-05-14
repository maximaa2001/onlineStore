package by.bsuir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuctionIsNotLinkedException extends ResponseStatusException {
    public AuctionIsNotLinkedException(HttpStatus status) {
        super(status);
    }
}
