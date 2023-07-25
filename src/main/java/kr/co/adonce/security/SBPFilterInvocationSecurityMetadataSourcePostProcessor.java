/**
* @ProgramName : CsleFilterInvocationSecurityMetadataSourcePostProcessor.java
*
* Description: This is a CsleFilterInvocationSecurityMetadataSourcePostProcessor, and is executed continuously and interrupted
* Only to perform in case of reset or failure detection.
* @Package : kr.re.etri.spp.monitoring.security
* @Project : kr.re.etri.spp.monitoring
* @Type :  CsleFilterInvocationSecurityMetadataSourcePostProcessor
*
* @Revision_history:
*   Date : 2017. 9. 14..,  Author : yskim,  Version : 1.0
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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.Assert;

/**
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class SBPFilterInvocationSecurityMetadataSourcePostProcessor implements BeanPostProcessor, InitializingBean {

	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(securityMetadataSource, "securityMetadataSource cannot be null");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof FilterSecurityInterceptor) {
			((FilterSecurityInterceptor) bean).setSecurityMetadataSource(securityMetadataSource);
		}
		return bean;
	}

}
