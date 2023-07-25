/**
 * @ProgramName : SecurityUtil.java
 *
 * Description: This is a SecurityUtil, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : kr.re.etri.spp.monitoring.utils
 * @Project : kr.re.etri.spp.monitoring
 * @Type :  SecurityUtil
 *
 * @Revision_history:
 *   Date : 2017. 9. 14..,  Author : Park_Jun_Hong_(fafanmama_at_naver_com),  Version : 1.0
 * 
 * Opensource License:
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.co.adonce.util;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.co.adonce.Const;
import kr.co.adonce.dao.model.User;
import kr.co.adonce.dao.model.UserGrade;
import kr.co.adonce.exception.UnknownUserException;
import kr.co.adonce.security.GrantedAuthorityDetail;
import open.commons.Result;
import open.commons.text.NamedTemplate;
import open.commons.utils.ArrayUtils;

/**
 * 로그인 보안 유틸리티
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class SecurityUtil {

	private static final Logger logger = LogManager.getLogger(SecurityUtil.class);

	// prevent to create an instance.
	public SecurityUtil() {
	}

	/**
	 * 현재 로그인한 유저가 사용자 정보를 요청하는 자와 동일한지 여부와 로그인한 유저 ID를 반환한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param targetUserID
	 * @return
	 */
	public static Result<String> allowToAccessUserInfo(String targetUserID) {
		Result<String> result = new Result<String>();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			result.setMessage(Const.UserDataAccess.ILLEGAL_USER_DATA_ACCESS);

			if (logger.isDebugEnabled()) {
				logger.debug(result.getMessage());
			}
		} else {
			boolean valid = auth.getPrincipal().equals(targetUserID);

			NamedTemplate tpl = new NamedTemplate(valid ? Const.UserDataAccess.USER_DATA_ACCESS_BY_SELF
					: Const.UserDataAccess.USER_DATA_ACCESS_BY_OTHER);
			tpl.addValue("req", auth.getPrincipal());
			tpl.addValue("target", targetUserID == null ? "" : targetUserID);

			result.setMessage(tpl.format());
			result.setData(auth.getPrincipal().toString());
			result.setResult(valid);

			if (logger.isDebugEnabled()) {
				logger.debug(result.getMessage());
			}
		}

		return result;
	}

	public static String assertAuthorization() {

		String userID = SecurityUtil.getCurrentUserID();

		if (userID == null) {
			try {
				throw new UnknownUserException("user-id: " + userID);
			} catch (UnknownUserException e) {
				logger.error("Unknown User Exception.");
			}

		}

		return userID;
	}

	/**
	 * 해당 사용자가 관리자 권한인지 확인한다. - 수정: null 반환 없이, 세션이 없을 경우도 무조건 false로 반환 <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @return 관리자 일경우 true, 아닐경우 false, session이 없거나, 잘못된 사용자 권한일 경우 null
	 */
	public static Boolean checkAdminGrade() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
			if (logger.isWarnEnabled()) {
				logger.warn("(Invalid Security Access) There is no Security.");
			}
			return false;
		}

		Iterator<? extends GrantedAuthority> itrAuthority = auth.getAuthorities().iterator();

		GrantedAuthorityDetail authDetail = null;
		while (itrAuthority.hasNext()) {
			authDetail = (GrantedAuthorityDetail) itrAuthority.next();

			// 사용자 권한 확인하기
			Integer grade = authDetail.getGrade().getId();
			switch (grade) {
			case 1: // SUPER_ADMIN
			case 2: // ADMIN
				return true;
			case 3: // SALES_MAN
				return false;
			default: // INVALID_USER_GRADE
				if (logger.isWarnEnabled()) {
					logger.warn("(Invalid User Grade) User Grade is 1,2,3 only. Grade: " + grade);
				}
				return false;
			}
		}

		return null;

	}

	/**
	 * Spring Security 인증 정보를 제거한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 *
	 */
	public static void clearAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	/**
	 * Http Session 을 제거한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param request
	 */
	public static void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * 사용자 정보와 jsecurity session 정보를 가지고 사용자 로그인 토큰을 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param jsession
	 * @param userID
	 * @return
	 */
	public static String createLoginToken(byte[] jsession, byte[] userID) {
		return UUID.nameUUIDFromBytes(ArrayUtils.merge(jsession, userID)).toString();
	}

	/**
	 * 사용자 정보와 jsecurity session 정보를 가지고 사용자 로그인 토큰을 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param jsession
	 * @param userID
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String createLoginToken(String jsession, String userID) throws UnsupportedEncodingException {
		return createLoginToken(jsession.getBytes("UTF-8"), userID.getBytes("UTF-8"));
	}

	/**
	 * 현재 로그인한 유저의 ID를 반환한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @return
	 */
	public static String getCurrentUserID() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return auth != null ? (String) auth.getPrincipal() : null;
	}

	/**
	 * 사용자 권한을 반환한다. (멀티 권한을 가진 사용자의 경우, 가장 최상위의 권한만 반환한다.) <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @return
	 */
	public static UserGrade getGrade() {

		Integer gradeCmp = Integer.MAX_VALUE;
		UserGrade grade = new UserGrade();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Iterator<? extends GrantedAuthority> keys = auth.getAuthorities().iterator();

		while (keys.hasNext()) {
			GrantedAuthorityDetail key = (GrantedAuthorityDetail) keys.next();
			grade = gradeCmp.compareTo(key.getGrade().getId()) < 0 ? null : key.getGrade();
		}

		return grade;

	}

	/**
	 * 사용자 정보 가져오기 <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 20.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @return
	 */
	public static User getUser() {

		User user = new User();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Iterator<? extends GrantedAuthority> keys = auth.getAuthorities().iterator();

		while (keys.hasNext()) {
			GrantedAuthorityDetail key = (GrantedAuthorityDetail) keys.next();
			user = key.getUser();
		}

		return user;

	}

	// TODO: 리눅스 계정으로 바꾸기
	public static String getLinuxAccount(String userEmail) {
		// 알파벳, 숫자, 언더바 제외한 나머지 캐릭터 언더바로 바꿈
		String linuxAccount = userEmail.replaceAll("[^_0-9a-zA-Z]", "_");

		return linuxAccount;
	}

	public static void setUser(User user) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Iterator<? extends GrantedAuthority> keys = auth.getAuthorities().iterator();

		while (keys.hasNext()) {
			GrantedAuthorityDetail key = (GrantedAuthorityDetail) keys.next();
			key.setUser(user);
		}
	}

}
