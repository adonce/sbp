/**
 * @ProgramName : GrantedAuthorityDetail.java
 *
 * Description: This is a GrantedAuthorityDetail, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : kr.re.etri.spp.monitoring.security
 * @Project : kr.re.etri.spp.monitoring
 * @Type :  GrantedAuthorityDetail
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
package kr.co.adonce.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import kr.co.adonce.dao.model.User;
import kr.co.adonce.dao.model.UserGrade;

/**
 * 로그인 정보 객체
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class GrantedAuthorityDetail implements GrantedAuthority {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final UserGrade grade;

	private User user;

	private String mapAuthKey;

	private String mapAuthDomain;

	public String getMapAuthKey() {
		return mapAuthKey;
	}

	public void setMapAuthKey(String mapAuthKey) {
		this.mapAuthKey = mapAuthKey;
	}

	public String getMapAuthDomain() {
		return mapAuthDomain;
	}

	public void setMapAuthDomain(String mappAuthDomain) {
		this.mapAuthDomain = mappAuthDomain;
	}

	public GrantedAuthorityDetail(UserGrade grade) {
		this.grade = grade;
	}

	@Override
	public String getAuthority() {
		return this.grade.getName();
	}

	/**
	 * 사용자 등급을 반환한다. <br>
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
	public UserGrade getGrade() {
		return grade;
	}

	/**
	 * 사용자 정보를 제공한다. <br>
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
	public User getUser() {
		return user;
	}

	/**
	 * 사용자 정보를 설정한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
