/*
 *
 * This file is generated under this project, "fcp-appagent".
 *
 * Date  : 2019. 6. 21. 오후 5:56:06
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package kr.co.adonce.exception;

/**
 * 클라이언트가 잘못된 요청을 보냈을 때 발생하는 예외.
 * 
 * @since 2019. 6. 21.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class BadRequestException extends RuntimeException {

    /**
     *
     * @since 2019. 6. 21.
     * @version
     */
    private static final long serialVersionUID = 1L;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @since 2019. 6. 21.
     * @version
     */
    public BadRequestException() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @since 2019. 6. 21.
     * @version
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     * @since 2019. 6. 21.
     * @version
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     * @since 2019. 6. 21.
     * @version
     */
    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param cause
     * @since 2019. 6. 21.
     * @version
     */
    public BadRequestException(Throwable cause) {
        super(cause);
    }

}
