package kr.co.adonce.sbp.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import kr.co.adonce.sbp.util.SecurityUtil;

@Service("CustomAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LogManager.getLogger(SecurityUtil.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		String token = request.getHeader("token");

		Object obj = request.getAttribute("type");

		int type = -1;

		if (obj != null) {
			type = (int) request.getAttribute("type");
		}

		response.setStatus(401);
		response.setHeader("Content-Type", "application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();

		JSONObject resultObj = new JSONObject();

		String msg = null;

		switch (type) {
		case 1:
			msg = "API가 등록되어 있지 않습니다.";
			break;
		case 2:
			msg = "API의 Permission에 해당하지 않습니다.";
			break;
		case 3:
			msg = "올바르지 않은 토큰입니다.";
			break;
		case 4:
			msg = "사용자가 신청하지 않은 API입니다.";
			break;
		case 5:
			msg = "사용자가 발행한 토큰에 해당하지 않은 접근입니다.";
			break;
		default:
			msg = "올바르지 않은 접근입니다.";
			break;
		}

		try {
			resultObj.put("timestamp", System.currentTimeMillis());
			resultObj.put("status", "401 UNAUTHORIZED/unauthorized");
			resultObj.put("message", msg);
			resultObj.put("token", token);
		} catch (JSONException e) {
			logger.error("JSON Exception.");
		} catch (Exception e) {
			logger.error("Exception.");
		}

		out.write(resultObj.toString());

		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}
