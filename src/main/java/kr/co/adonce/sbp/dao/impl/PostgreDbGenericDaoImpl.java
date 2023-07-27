/**
 * @ProgramName : MariaDbGenericDaoImpl.java
 *
 * Description: This is a MariaDbGenericDaoImpl, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : kr.re.etri.spp.monitoring.dao.impl
 * @Project : kr.re.etri.spp.monitoring
 * @Type :  MariaDbGenericDaoImpl
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
package kr.co.adonce.sbp.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * MariaDB 연동 상위 클래스
 * 
 * @since 2023. 07. 26.
 * @author jaehwankim
 *
 */
public abstract class PostgreDbGenericDaoImpl extends AbstractGenericDaoImpl {

	public PostgreDbGenericDaoImpl() {

	}

	/**
	 * @see kr.re.etri.spp.monitoring.dao.impl.AbstractDaoImpl#setDataSource(javax.sql.DataSource)
	 */
	@Autowired
	@Qualifier("dataSource")
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @see kr.re.etri.spp.monitoring.dao.impl.AbstractDaoImpl#setQuerySource(org.springframework.context.support.ReloadableResourceBundleMessageSource)
	 */
	@Autowired
	@Qualifier("querySource")
	@Override
	public void setQuerySource(ReloadableResourceBundleMessageSource querySource) {
		this.querySource = querySource;
	}
}
