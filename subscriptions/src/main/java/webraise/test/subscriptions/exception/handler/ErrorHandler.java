package webraise.test.subscriptions.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import webraise.test.subscriptions.exception.DuplicateSubscriptionException;
import webraise.test.subscriptions.exception.SubscriptionNotFoundException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<?> subscriptionNotFoundHandler(SubscriptionNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateSubscriptionException.class)
    public ResponseEntity<?> notUniqueSubscriptionHandler(DuplicateSubscriptionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
