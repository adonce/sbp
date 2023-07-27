package kr.co.adonce.sbp.exception;

/**
 * 사용자 정보가 존재하지 않는 예외상황 클래스.
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class UnknownUserException extends RuntimeException {

    /**
     *
     * @since 2017. 9. 27.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @since 2017. 9. 27.
     */
    public UnknownUserException() {
    }

    /**
     * @param message
     * @since 2017. 9. 27.
     */
    public UnknownUserException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     * @since 2017. 9. 27.
     */
    public UnknownUserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     * @since 2017. 9. 27.
     */
    public UnknownUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param cause
     * @since 2017. 9. 27.
     */
    public UnknownUserException(Throwable cause) {
        super(cause);
    }

}
