package toyproject.dcricecake.exception;

public class MySameLoginIdException extends RuntimeException{
    public MySameLoginIdException() {
    }

    public MySameLoginIdException(String message) {
        super(message);
    }

    public MySameLoginIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public MySameLoginIdException(Throwable cause) {
        super(cause);
    }
}
