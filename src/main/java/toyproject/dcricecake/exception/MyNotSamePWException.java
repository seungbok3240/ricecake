package toyproject.dcricecake.exception;

public class MyNotSamePWException extends RuntimeException {
    public MyNotSamePWException() {
    }

    public MyNotSamePWException(String message) {
        super(message);
    }

    public MyNotSamePWException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyNotSamePWException(Throwable cause) {
        super(cause);
    }
}
