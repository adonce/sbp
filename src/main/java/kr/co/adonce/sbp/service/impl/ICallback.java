/**
 * @ProgramName : ICallback.java
 *
 * Description: This is a ICallback, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : kr.re.etri.spp.monitoring.service.impl
 * @Project : kr.re.etri.spp.monitoring
 * @Type :  ICallback
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
package kr.co.adonce.sbp.service.impl;

import open.commons.Result;

/**
 * 실행 정의 인터페이스
 * @since 2018. 3. 14.
 * @author jhlee
 *
 * @param <T>
 */
public interface ICallback<T> {

    public Result<T> call() throws RuntimeException;
}
