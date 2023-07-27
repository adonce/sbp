/**
 * @ProgramName : LoginAuthSuccessHandler.java
 *
 * Description: This is a LoginAuthSuccessHandler, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : kr.re.etri.spp.monitoring.security
 * @Project : kr.re.etri.spp.monitoring
 * @Type :  LoginAuthSuccessHandler
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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * 로그인 성공 처리 핸들러
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class LoginAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    /**
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
    }

    /**
     * @see org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler#setDefaultTargetUrl(java.lang.String)
     */
    @Override
    public void setDefaultTargetUrl(String defaultTargetUrl) {
        super.setDefaultTargetUrl(defaultTargetUrl);
    }
}
