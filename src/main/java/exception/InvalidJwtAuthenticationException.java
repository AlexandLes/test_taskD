package exception;

public class InvalidJwtAuthenticationException extends RuntimeException {
    public InvalidJwtAuthenticationException(String expiredOrInvalidJwtToken, RuntimeException e) {
        super(expiredOrInvalidJwtToken, e);
    }
}
