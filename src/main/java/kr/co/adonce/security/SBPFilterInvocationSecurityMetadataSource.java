/**
* @ProgramName : SppMonitoringFilterInvocationSecurityMetadataSource.java
*
* Description: This is a SppMonitoringFilterInvocationSecurityMetadataSource, and is executed continuously and interrupted
* Only to perform in case of reset or failure detection.
* @Package : kr.re.etri.spp.monitoring.security
* @Project : kr.re.etri.spp.monitoring
* @Type :  SppMonitoringFilterInvocationSecurityMetadataSource
*
* @Revision_history:
*   Date : 2018. 9. 14..,  Author : jhlee,  Version : 1.0
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

package kr.co.adonce.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import kr.co.adonce.dao.model.Api;
import kr.co.adonce.dao.model.ApiWithToken;
import kr.co.adonce.dao.model.SysUrlAuth;
import kr.co.adonce.dao.model.User;
import kr.co.adonce.dao.model.UserGrade;
import kr.co.adonce.util.SecurityUtil;

/**
 * URL에 사용자 권한을 연결하여 보안을 지원하는 클래스.
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class SBPFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	protected Logger logger = LogManager.getLogger(getClass());

	private static final String ROLE = "ROLE_TOKEN";
	private static final String ROLE_NO_SESSION = "ROLE_NO_SESSION";

//	@Autowired
//	@Qualifier(ApiServiceImpl.BEAN_QUALIFIER)
//	private IApiService apiService;
//
//	@Autowired
//	@Qualifier(UserServiceImpl.BEAN_QUALIFIER)
//	private IUserService userService;
//
//	@Autowired
//	@Qualifier(UserGradeDaoImpl.BEAN_QUALIFIER)
//	private IUserGradeDao userGradeDao;
//
//	@Autowired
//	@Qualifier(SystemPropertiesServiceImpl.BEAN_QUALIFIER)
//	private ISystemPropertiesService systemPropertiesService;

	public SBPFilterInvocationSecurityMetadataSource() {

	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		FilterInvocation filterInv = (FilterInvocation) object;
		String url = filterInv.getRequestUrl();
		String httpMethod = filterInv.getRequest().getMethod();
		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

		if (logger.isDebugEnabled()) {
			logger.debug("http-method: " + httpMethod + ", http-url: " + url + ", security-config: " + attributes);
		}

		// Query Param 제거
		int queryParamIndex = url.indexOf("?");
		if (queryParamIndex > -1) {
			url = url.substring(0, queryParamIndex);
		}

		if (url.equals("/")) {
			return attributes;
		}

		// Open API 비교
		// 'openapi'가 포함되어 있으면 검사하지 않음
		// 또는 '/subscription/sensing-info/buildings'
		// 또는 '/subscription/event-info/buildings'
		// 구독 해지(DELETE) '/subscription' 가 포함되어 있으면 검사하지 않음(2020-08-05 추가)
		if (!url.matches("/open-api/([^/]+)") && !url.matches("/open-api/([^/]+)/([^/]+)")
				&& !url.matches("/open-api/([^/]+)/([^/]+)/([^/]+)")
				&& !url.matches("/subscription/sensing-info/buildings")
				&& !url.matches("/subscription/event-info/buildings")
				&& !(url.matches("/subscription") && "DELETE".equalsIgnoreCase(httpMethod))) {

			// TODO: PCL Web URL ROLE 확인

//			attributes = validatePlatformAPI(url, filterInv, attributes);

			return attributes;
		}

		// Open API 이용 검증
//		attributes = validateOpenAPI(url, filterInv, attributes);

		return attributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

//	// #1. API가 등록되어 있는지 확인
//	private List<Api> isAPI(String url) {
//		return apiService.selectApiByUrl(url).getData();
//	}
//
//	// #2. API의 Permission 확인
//	private boolean validateAPIPermission(HttpServletRequest request, Api api) {
//		// C : PUT
//		// R : GET, POST
//		// U : PATCH
//		// D : DELETE
//		String httpMethod = request.getMethod();
//		// 문자별 자르기
//		String[] permissions = api.getPermission().split("");
//
//		boolean isPermission = false;
//		String userPermision = "";
//
//		switch (httpMethod) {
//		case "POST":
//			userPermision = "C";
//			break;
//		case "GET":
//			userPermision = "R";
//			break;
//		case "PUT":
//			userPermision = "C";
//			break;
//		case "PATCH":
//			userPermision = "U";
//			break;
//		case "DELETE":
//			userPermision = "D";
//			break;
//		default:
//			userPermision = "C";
//			break;
//		}
//		for (String perms : permissions) {
//			if (userPermision.equals(perms)) {
//				isPermission = true;
//				break;
//			}
//		}
//
//		return isPermission;
//	}
//
//	// #3. 사용자가 신청한 API인지 확인
////	private Api isUserAPI(String url, String userID) {
////		return apiService.selectUserApiByUrl(url, userID).getData();
////	}
//
//	// #4. 사용자가 발행한 토큰에 해당하는 API인지 확인
//	private List<ApiWithToken> isTokenAPI(String url, String token) {
//		return apiService.selectTokenApiByUrl(url, token).getData();
//	}
//
//	// 토큰별 사용자 ID 가져오기
//	private String getUserIDByToken(String token) {
//
//		User user = userService.selectUserIdByToken(token).getData();
//
//		if (user == null) {
//			return null;
//		}
//
//		return user.getId();
//	}
//
//	/**
//	 * Open API URL 권한 검증 <br>
//	 * 
//	 * <pre>
//	 * [개정이력]
//	 *      날짜    	| 작성자	|	내용
//	 * ------------------------------------------
//	 * 2019. 7. 1.		  jhlee		최초 작성
//	 * </pre>
//	 * 
//	 * @param url
//	 * @param filterInv
//	 * @param attributes
//	 * 
//	 * @return
//	 */
//	@SuppressWarnings("unused")
//	private List<ConfigAttribute> validateOpenAPI(String url, FilterInvocation filterInv,
//			List<ConfigAttribute> attributes) {
//
//		List<Api> api = isAPI(url);
//
//		// #1. API가 등록되어있는지 확인
//		if (api.size() < 1) {
//			if (logger.isInfoEnabled()) {
//				logger.warn("API가 등록되어 있지 않습니다.");
//			}
//			filterInv.getHttpRequest().setAttribute("type", 1);
//			attributes.add(new SecurityConfig(ROLE));
//			return attributes;
//		}
//
//		// #2. API의 Permission 확인
//		if (!validateAPIPermission(filterInv.getHttpRequest(), api.get(0))) {
//			if (logger.isInfoEnabled()) {
//				logger.warn("API의 Permission에 해당하지 않습니다.");
//			}
//			filterInv.getHttpRequest().setAttribute("type", 2);
//			attributes.add(new SecurityConfig(ROLE));
//			return attributes;
//		}
//
//		// 받은 토큰
//		String token = filterInv.getRequest().getHeader("token");
//
//		String userID = null;
//
//		// #3. 토큰 정보로 사용자 확인
//		if ((userID = getUserIDByToken(token)) == null) {
//			if (logger.isInfoEnabled()) {
//				logger.warn("올바르지 않은 토큰입니다.");
//				filterInv.getHttpRequest().setAttribute("type", 3);
//				attributes.add(new SecurityConfig(ROLE));
//				return attributes;
//			}
//		}
//
//		// #4. 사용자가 신청한 API인지 확인
//		// if (isUserAPI(url, userID) == null) {
//		// if (logger.isInfoEnabled()) {
//		// logger.warn("사용자가 신청하지 않은 API입니다.");
//		// }
//		// filterInv.getHttpRequest().setAttribute("type", 4);
//		// attributes.add(new SecurityConfig(ROLE));
//		// return attributes;
//		// }
//
//		List<ApiWithToken> apiList = isTokenAPI(url, token);
//
//		// #5. 사용자가 발행한 토큰에 해당하는 API인지 확인
//		if (apiList.size() < 1) {
//			if (logger.isInfoEnabled()) {
//				logger.warn("사용자가 발행한 토큰에 해당하지 않은 접근입니다.");
//			}
//			filterInv.getHttpRequest().setAttribute("type", 5);
//			attributes.add(new SecurityConfig(ROLE));
//			return attributes;
//		}
//
//		return attributes;
//	}
//
//	/**
//	 * Platform API URL 권한 검증 <br>
//	 * 
//	 * <pre>
//	 * [개정이력]
//	 *      날짜    	| 작성자	|	내용
//	 * ------------------------------------------
//	 * 2019. 7. 1.		  jhlee		최초 작성
//	 * </pre>
//	 * 
//	 * @param api
//	 * @param url
//	 * @param filterInv
//	 * @param attributes
//	 * @return
//	 */
//	private List<ConfigAttribute> validatePlatformAPI(String url, FilterInvocation filterInv,
//			List<ConfigAttribute> attributes) {
//
//		// #1. URL의 권한 확인
//		SysUrlAuth urlAuth = systemPropertiesService.validateUrl(url);
//		if (SecurityUtil.getUser() == null) {
//			attributes.add(new SecurityConfig(ROLE_NO_SESSION));
//		}
//		String userId = SecurityUtil.getUser().getId();
//		if (logger.isInfoEnabled()) {
//			logger.info("[URL 사용자 권한 목록] auth: " + (urlAuth != null ? urlAuth.getGrade() : "") + ", url: " + url);
//			logger.info("[사용자 ID] userId: " + userId);
//		}
//
//		if (urlAuth != null) {
//			for (String attr : urlAuth.getGrades()) {
//				attributes.add(new SecurityConfig(attr));
//			}
//		} else {
//			if (!"/".equals(url) && !"/signup".equals(url) && !"/favicon.ico".equals(url)) {
//				List<UserGrade> securities = getAllSecurity();
//				for (UserGrade security : securities) {
//					attributes.add(new SecurityConfig(security.getName()));
//				}
//			}
//		}
//
//		return attributes;
//	}
//
//	private List<UserGrade> getAllSecurity() {
//		return userGradeDao.selectAll().getData();
//	}

}
