/*
 *
 * This file is generated under this project, "com.lguplus.items.capability".
 *
 * Date  : 2017. 12. 29. 오후 2:36:22
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package kr.co.adonce.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import open.commons.Result;
import open.commons.net.HttpStatusCode;

/**
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class WebUtils {

	/**
	 * 
	 * @since 2017. 12. 29.
	 */
	public WebUtils() {
	}

	/**
	 * 예외처리 응답 에러 정보를 추가한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param view
	 * @param errors
	 */
	public static void createExceptionResponseError(ModelAndView view, Object errors) {
		view.addObject("error", errors);
	}

	/**
	 * 예외처리 응답 정보를 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param view
	 * @param status
	 * @param ex
	 * @param request
	 */
	public static void createThrowableResponse(ModelAndView view, HttpStatus status, Throwable ex, HttpServletRequest request) {

		view.addObject("timestamp", System.currentTimeMillis());
		view.setStatus(status);

		HttpStatusCode httpStatusCode = HttpStatusCode.code(status.value());
		view.addObject("status", httpStatusCode.getStatus());
		view.addObject("code", httpStatusCode.getStatusCode());
		view.addObject("exception", ex.getClass());
		view.addObject("message", "Throwable Response Message.");
//		view.addObject("message", ex.getMessage());

		// view.addObject("url", new UrlInfo(request.getMethod() //
		// , (String)
		// request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) //
		// , (String)
		// request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) //
		// + (request.getQueryString() != null ? "?" + request.getQueryString() : "") //
		// , request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) //
		// , request.getParameterMap()));
	}

	/**
	 * URL 정보를 문자열로 제공한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param request
	 * @return
	 */
	public static final String getUrlInfo(HttpServletRequest request) {

		StringBuffer sb = new StringBuffer();

		sb.append("Remote: ");
		sb.append(request.getRemoteAddr());
		sb.append(" (");
		sb.append(request.getRemoteHost());
		sb.append("), URL-Pattern: ");
		sb.append(request.getMethod());
		sb.append(" | ");
		sb.append((String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) //
				+ (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
		sb.append(", URL-Variables: ");
		sb.append(request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
		sb.append(", URL: ");
		sb.append(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));

		return sb.toString();
	}

	public static Map<String, Object> queryToParameters(String urlQuery) {

		Map<String, Object> parameters = new HashMap<>();

		String[] paramArr = null;

		for (String param : urlQuery.split("&")) {
			paramArr = param.split("=");
			parameters.put(paramArr[0], paramArr[1]);
		}

		return parameters;
	}

	/**
	 * 주어진 메시지를 이용하여 응답 데이타를 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param message
	 * @return
	 */
	public static ResponseEntity<Object> result(String message) {
		Result<Object> result = new Result<Object>();
		result.setMessage(message);
		return ResponseEntity.ok(result);
	}

	/**
	 * 주어진 데이터와 메시지를 이용하여 응답 데이터를 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param data
	 * @param message
	 * @return
	 */
	public static <T> ResponseEntity<Object> result(T data, String message) {
		Result<T> result = new Result<T>(data, true);
		result.setMessage(message);
		return ResponseEntity.ok(result);
	}

	public static String createUuid() {

		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	/**
	 * HTTP Response에 Status Code와 Message를 설정한다.
	 * 
	 * @param <T>
	 * @param response
	 * @param status
	 * @param message
	 * @throws IOException
	 * @throws JSONException
	 */
	public static <T> void setStatusAndMessage(HttpServletResponse response, HttpStatus status, Result<T> resultData) throws IOException, JSONException {
		response.setStatus(status.value());
		response.setHeader("Content-Type", "application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();

		JSONObject resultObj = new JSONObject();
		resultObj.put("result", resultData.getResult());
		resultObj.put("message", resultData.getMessage());
		resultObj.put("data", resultData.getData());

		out.write(resultObj.toString());

	}

	/**
	 * Base64로 인코딩된 문자열을 디코딩하여 반환한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 8. 22.		박준홍			최초 작성
	 * </pre>
	 *
	 * @param base64EncodeString
	 *            Base64로 인코딩된 문자열
	 * @return
	 *
	 * @since 2018. 8. 22.
	 * @see Base64Utils#decodeFromUrlSafeString(String)
	 */
	public static final String decodeFromUrlSafeString(String base64EncodeString) {
		return new String(Base64Utils.decodeFromUrlSafeString(base64EncodeString));
	}
}
