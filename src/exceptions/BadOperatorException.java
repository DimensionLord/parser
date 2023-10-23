package exceptions;

public class BadOperatorException extends IllegalArgumentException{
    public BadOperatorException() {
        super();
    }

    public BadOperatorException(String s) {
        super(s);
    }

    public BadOperatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadOperatorException(Throwable cause) {
        super(cause);
    }
}
