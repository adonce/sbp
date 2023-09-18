package kr.co.adonce.sbp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.adonce.sbp.util.WebUtils;

@RestController
@RequestMapping("/analysis")
public class AnalysisController extends BaseController {

	public AnalysisController() {

	}

	/**
	 * 모든 분석 데이터 목록 조회
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> getAnalysisDataAll(HttpServletRequest request, HttpServletResponse response //
	) {

		if (logger.isInfoEnabled()) {
			logger.info(WebUtils.getUrlInfo(request));
		}

		return ResponseEntity.ok(null);
	}

}
