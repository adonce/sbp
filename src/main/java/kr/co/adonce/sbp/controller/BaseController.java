/*
 *
 * This file is generated under this project, "com.lguplus.items.capability".
 *
 * Date  : 2017. 12. 29. 오후 2:34:57
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package kr.co.adonce.sbp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 
 *
 * @since 2023. 7. 28.
 * @author 김재환
 */
public class BaseController {

	protected final Logger logger = LogManager.getLogger(getClass());

	/**
	 * 
	 * @since 2017. 12. 29.
	 */
	public BaseController() {
	}

	public void setDownloadFile(byte[] file, String filename, HttpServletResponse response) throws IOException {

		response.setContentLength(file.length);
		response.setContentType("application/download; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
		response.addHeader("fileName", filename);

		OutputStream out = response.getOutputStream();
		out.write(file);
		out.flush();
		out.close();

	}

	public String setFilename(String filename) throws UnsupportedEncodingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return URLEncoder.encode(filename + "-" + format.format(new Date()) + ".xls", "UTF-8");
	}

	/**
	 * 파라미터 세팅
	 * 
	 * @param paramKeys
	 * @param request
	 * @return
	 */
	protected Map<String, Object> setParamMap(Enumeration<String> paramKeys, HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();

		String key = null;

		while (paramKeys.hasMoreElements()) {

			key = paramKeys.nextElement();
			if ("return-type".equals(key)) {
				continue;
			}

			paramMap.put(key, request.getParameter(key));
		}
		return paramMap;
	}

	/**
	 * return-type에 따라 Header 설정 
	 * 
	 * @param returnType
	 * @return
	 */
	protected HttpHeaders getHeader(String returnType) {

		HttpHeaders header = new HttpHeaders();

		switch (returnType) {
		case "json":
			header.setContentType(MediaType.APPLICATION_JSON);
			break;
		case "xml":
			header.setContentType(MediaType.APPLICATION_XML);
			break;
		default:
			header.setContentType(MediaType.APPLICATION_JSON);
			break;
		}
		return header;
	}
}
