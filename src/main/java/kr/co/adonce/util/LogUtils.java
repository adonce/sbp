package kr.co.adonce.util;

import java.util.Calendar;

import open.commons.utils.StringUtils;

public class LogUtils {

    public LogUtils() {
    }

    /**
     * 기본 메소드에 대한 로그 찍기 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 23.		  jhlee		최초 작성
     * </pre>
     * 
     * @param title
     * @return
     */
    public static String showBasicLog(String title) {

        String log = String.format("\n=================================================================\n" + "%s: %s",
                title, CommonUtils.formatTime(Calendar.getInstance().getTimeInMillis()));

        return log;
    }

    /**
     * 파라미터까지 포함해서 로그 찍기 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 5. 23.		  jhlee		최초 작성
     * </pre>
     * 
     * @param title
     * @param paramTitle
     * @param params
     * @return
     */
    public static String showParameterLog(String title, String paramTitle, Object... params) {

        String log = String.format("\n=================================================================\n" + "%s: %s",
                title, CommonUtils.formatTime(Calendar.getInstance().getTimeInMillis()));

        log += String.format("\n" + paramTitle + "\n=================================================================",
                params);

        return log;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 3.     박준홍         최초 작성
     * </pre>
     *
     * @param title
     * @param messages
     * @return
     *
     * @since 2019. 1. 3.
     */
    public static String toLog(String title, Object... messages) {

        StringBuffer buf = new StringBuffer(title);
        buf.append('\t');

        for (Object msg : messages) {
            buf.append(msg);
        }

        return buf.toString();
    }

    /**
     * 탭('\t')으로 문자열을 연결하여 하나의 문자열로 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 1. 3.     박준홍         최초 작성
     * </pre>
     *
     * @param messages
     * @return
     *
     * @since 2019. 1. 3.
     */
    public static String toLogSplitedByTab(Object... messages) {
        return StringUtils.concatenate("\t", messages);
    }

}
