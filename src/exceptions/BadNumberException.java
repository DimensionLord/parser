package exceptions;

public class BadNumberException extends IllegalArgumentException{
    public BadNumberException() {
        super();
    }

    public BadNumberException(String s) {
        super(s);
    }

    public BadNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadNumberException(Throwable cause) {
        super(cause);
    }
}
