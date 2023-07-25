/**
 * @ProgramName : GenericServiceImpl.java
 *
 * Description: This is a GenericServiceImpl, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : kr.re.etri.spp.monitoring.service.impl
 * @Project : kr.re.etri.spp.monitoring
 * @Type :  GenericServiceImpl
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
package kr.co.adonce.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import open.commons.Result;

/**
 * 서비스 공통 기능 클래스
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public abstract class GenericServiceImpl implements InitializingBean, DisposableBean {

	protected final String SCN = getClass().getSimpleName();

	protected final Logger logger = LogManager.getLogger(getClass());

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

	}

	/**
	 * 실행 후 결과를 제공한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param callback
	 * @return
	 */
	protected <T> Result<T> call(ICallback<T> callback) {

		Result<T> result = null;

		try {
			result = callback.call();
		} catch (Exception e) {

			result = new Result<T>();
			result.andFalse().setMessage("Call Error Exception.");

			logger.error("Call Error Exception.");
		}

		return result;
	}

	/**
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
	}

	/**
	 * 에러 메시지를 생성하여 제공한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param originalMsg
	 * @param coverMsg
	 * @return
	 */
	public String getErrorMsg(String originalMsg, String coverMsg) {

		String msg = coverMsg;
		JSONObject obj = null;
		Object exception = null;

		try {
			obj = new JSONObject(originalMsg);
			exception = obj.get("Exception");
			if (exception != null && !exception.toString().isEmpty()) {
				msg += ", " + exception.toString();
			}

		} catch (JSONException e) {
			logger.error(
					"Error Message 파싱 중 오류가 발생하였습니다. Message: " + originalMsg + ", Error: " + e.getLocalizedMessage());
		}

		return msg;

	}

	/**
	 * 에러 메시지를 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param result
	 * @param msg
	 */
	public void setMsgWhenReturnFalse(Result<?> result, String msg) {
		if (!result.getResult() && result.getMessage() == null) {
			result.setMessage(msg);

		} else if (!result.getResult() && result.getMessage() != null && result.getMessage().isEmpty()) {
			result.setMessage(msg);

		} else if (!result.getResult() && result.getMessage() != null && !result.getMessage().isEmpty()) {
			String origianlMsg = getErrorMsg(result.getMessage(), msg);
			result.setMessage(origianlMsg);
		}
	}

}
