package kr.co.adonce.sbp.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.adonce.sbp.controller.model.CommonSearchReq;
import kr.co.adonce.sbp.dao.model.User;
import kr.co.adonce.sbp.pagination.PaginationResult;
import kr.co.adonce.sbp.service.IUserService;
import kr.co.adonce.sbp.service.impl.UserServiceImpl;
import kr.co.adonce.sbp.util.CommonUtils;
import kr.co.adonce.sbp.util.SecurityUtil;
import kr.co.adonce.sbp.util.WebUtils;
import open.commons.Result;

@RestController
@RequestMapping("/management/users")
public class UserController extends BaseController {

	@Autowired
	@Qualifier(UserServiceImpl.BEAN_QUALIFIER)
	private IUserService userService;

	public UserController() {

	}

	/**
	 * 사용자 전체 목록 제공
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 *
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Object> selectAll(HttpServletRequest request, HttpServletResponse response //
			, @RequestParam(value = "keyword", defaultValue = "") String keyword //
			, @RequestParam(value = "pageNum") int pageNum //
			, @RequestParam(value = "itemCountPerPage") int itemCountPerPage //
			, @RequestParam(value = "orderColumn") String orderColumn //
			, @RequestParam(value = "isDesc") boolean isDesc //
	) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		if (logger.isInfoEnabled()) {
			logger.info(WebUtils.getUrlInfo(request));
		}

		CommonSearchReq cmReqEntity = new CommonSearchReq();
		cmReqEntity.setPageNum(pageNum);
		cmReqEntity.setItemCountPerPage(itemCountPerPage);
		cmReqEntity.setOrderColumn(orderColumn);
		cmReqEntity.setDesc(isDesc);
		cmReqEntity.setKeyword(CommonUtils.decodeUrlAsUTF8(keyword));

		Result<PaginationResult<User>> result = userService.selectAll(cmReqEntity);

		return ResponseEntity.ok(result);
	}

	/**
	 * 회원 가입
	 * 
	 * @param request
	 * @param response
	 * @param user     등록할 사용자 정보
	 * @return
	 *
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<Object> registUser(HttpServletRequest request, HttpServletResponse response //
			, @RequestBody User user) {

		if (logger.isInfoEnabled()) {
			logger.info(WebUtils.getUrlInfo(request));
		}

		return ResponseEntity.ok(userService.registUser(user));
	}

	/**
	 * 사용자 정보 수정
	 * 
	 * @param request
	 * @param response
	 * @param user     사용자 정보
	 * @return
	 *
	 */
	@RequestMapping(method = RequestMethod.PATCH, produces = { "application/json" })
	public ResponseEntity<Object> modifyUserInfo(HttpServletRequest request, HttpServletResponse response //
			, @RequestBody User user) {

		if (logger.isInfoEnabled()) {
			logger.info(WebUtils.getUrlInfo(request));
		}
		User userObj = SecurityUtil.getUser();
		user.setId(userObj.getId());

		return ResponseEntity.ok(userService.modifyUserInfo(user));
	}

	/**
	 * 사용자 권한 목록 조회
	 * 
	 * @param request
	 * @param response
	 * @return
	 *
	 */
	@RequestMapping(value = "/grades", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> getUserGrades(HttpServletRequest request, HttpServletResponse response //
	) {

		if (logger.isInfoEnabled()) {
			logger.info(WebUtils.getUrlInfo(request));
		}

		return ResponseEntity.ok(userService.getGradesAll());
	}

	/**
	 * 사용자 권한을 수정한다.
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 *
	 */
	@RequestMapping(value = "/grades", method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<Object> modifyUserGrade(HttpServletRequest request, HttpServletResponse response //
			, @RequestBody User user //
	) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		if (logger.isInfoEnabled()) {
			logger.info(WebUtils.getUrlInfo(request));
		}

		return ResponseEntity.ok(userService.updateUserGrade(user));
	}

}
