/**
 * @ProgramName : UserAuthenticationProvider.java
 *
 * Description: This is a UserAuthenticationProvider, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : kr.re.etri.spp.monitoring.security
 * @Project : kr.re.etri.spp.monitoring
 * @Type :  UserAuthenticationProvider
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
package kr.co.adonce.sbp.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import kr.co.adonce.sbp.dao.model.User;
import kr.co.adonce.sbp.dao.model.UserGrade;
import kr.co.adonce.sbp.service.IUserService;
import kr.co.adonce.sbp.service.impl.UserServiceImpl;
import open.commons.Result;

/**
 * 
 * @subject : 로그인 처리 핸들러
 * 
 * 
 * @revision_history : jhlee(fafanmama_at_naver_com), 2019. 5. 21., 1.0
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	@Qualifier(UserServiceImpl.BEAN_QUALIFIER)
	private IUserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Object principal = authentication.getPrincipal();
		Object password = authentication.getCredentials();

		String msg;
		GrantedAuthorityDetail grant;
		Collection<GrantedAuthorityDetail> authorities = new ArrayList<GrantedAuthorityDetail>();

		// ID / PW가 입력되지 않은 경우
		if (principal == null || password == null
				|| (principal.toString().trim().isEmpty() && password.toString().trim().isEmpty())) {
			msg = "모든 정보를 입력해 주시기 바랍니다.";
			grant = new GrantedAuthorityDetail(UserGrade.getUserGrade(UserGrade.NOT_ENTERED_ID_OR_PASSWORD, null, msg));
			authorities.add(grant);
			return new UsernamePasswordAuthenticationToken(principal, password, authorities);
		}

		// 로그인한 사용자 정보 불러오기
		Result<User> resultUser = userService.select(principal.toString(), password.toString());
		User user = resultUser.getData();

		// ID / PW에 해당하는 사용자가 없을 경우
		if (!resultUser.getResult() || resultUser.getData() == null) {
			msg = "존재하지 않는 사용자 정보";
			grant = new GrantedAuthorityDetail(UserGrade.getUserGrade(UserGrade.UNKNOWN_USER, null, msg));
			authorities.add(grant);
			return new UsernamePasswordAuthenticationToken(principal, password, authorities);
		}

		// 사용자의 권한 및 정보 추가
		grant = new GrantedAuthorityDetail(user.getUserGradeObj());
		grant.setUser(user);

		authorities.add(grant);

		return new UsernamePasswordAuthenticationToken(principal, password, authorities);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return Authentication.class.isAssignableFrom(authentication);
	}

}
