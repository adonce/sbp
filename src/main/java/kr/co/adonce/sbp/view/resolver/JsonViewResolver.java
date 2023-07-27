/**
* @ProgramName : JsonViewResolver.java
*
* Description: This is a JsonViewResolver, and is executed continuously and interrupted
* Only to perform in case of reset or failure detection.
* @Package : kr.re.etri.spp.monitoring.view.resolver
* @Project : kr.re.etri.spp.monitoring
* @Type :  JsonViewResolver
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

package kr.co.adonce.sbp.view.resolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 
 * @subject : JsonViewResolver
 * 
 * 
 * @revision_history : JHLEE, 2019. 5. 21., 1.0
 */
public class JsonViewResolver implements ViewResolver {
	/**
	 * Get the view to use.
	 * 
	 * @return Always returns an instance of {@link MappingJackson2JsonView}.
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		return view;
	}
}