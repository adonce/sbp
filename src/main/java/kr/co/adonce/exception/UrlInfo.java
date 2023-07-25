/*
 *
 * This file is generated under this project, "com.lguplus.items.capability".
 *
 * Date  : 2017. 12. 29. 오후 2:36:46
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package kr.co.adonce.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class UrlInfo {

    private final String method;

    private final String url;

    private final String urlPattern;

    private final Object variables;

    private final Map<String, Object> parameters;

    public UrlInfo(String method, String url, String urlPattern, Object variables, Map<String, Object> parameters) {
        this.method = method;
        this.url = url;
        this.urlPattern = urlPattern;
        this.variables = variables;
        this.parameters = parameters;
    }

    /**
     *
     * @return the method
     *
     * @since 2017. 12. 29.
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @return the parameters
     *
     * @since 2017. 12. 29.
     */
    public Map<String, Object> getParameters() {
    	Map<String, Object> parameters = new HashMap<>(this.parameters);
    	return parameters;
    }

    /**
     *
     * @return the url
     *
     * @since 2017. 12. 29.
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return the urlPattern
     *
     * @since 2017. 12. 29.
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     *
     * @return the variables
     *
     * @since 2017. 12. 29.
     */
    public Object getVariables() {
        return variables;
    }

}
