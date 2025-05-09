package webraise.test.subscriptions.exception;

public class DuplicateSubscriptionException extends RuntimeException {

    public DuplicateSubscriptionException(String message) {
        super(message);
    }
}
