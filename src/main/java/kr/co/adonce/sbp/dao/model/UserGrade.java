/**
* @ProgramName : UserGrade.java
*
* Description: This is a UserGrade, and is executed continuously and interrupted
* Only to perform in case of reset or failure detection.
* @Package : kr.re.etri.spp.monitoring.controller.model
* @Project : kr.re.etri.spp.monitoring
* @Type :  UserGrade
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

package kr.co.adonce.sbp.dao.model;

import java.util.HashMap;

import open.commons.annotation.ColumnDef;
import open.commons.json.annotation.JSONField;
import open.commons.json.model.DefaultJSONModel;

/**
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class UserGrade extends DefaultJSONModel {

	private static final long serialVersionUID = 1L;

	public static final int NOT_ENTERED_ID_OR_PASSWORD = -3;
	public static final int UNKNOWN_USER = -2;
	public static final int INVALID_UNKNOWN_AND_ERROR = -1;

	/** 관리자 */
	public static final int ADMIN = 0;
	/** 일반사용자 */
	public static final int USER = 1;

	private static final HashMap<Integer, String> USER_GRADES = new HashMap<>();

	static {
		USER_GRADES.put(NOT_ENTERED_ID_OR_PASSWORD, "NOT_ENTERED_ID_OR_PASSWORD");
		USER_GRADES.put(UNKNOWN_USER, "UNKNOWN_USER");
		USER_GRADES.put(INVALID_UNKNOWN_AND_ERROR, "ERROR");

		USER_GRADES.put(ADMIN, "ROLE_ADMIN");
		USER_GRADES.put(USER, "ROLE_USER");
	}

	@JSONField(name = "descr")
	private String descr;

	@JSONField(name = "id")
	private Integer id = new Integer(0);

	@JSONField(name = "name")
	private String name;

	public UserGrade() {
	}

	private UserGrade(int grade) {
		this.id = grade;
	}

	/**
	 *
	 * @return descr
	 *
	 * @since 2016. 10. 31
	 */
	public String getDescr() {
		return this.descr;
	}

	/**
	 *
	 * @return id
	 *
	 * @since 2016. 10. 31
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 *
	 * @return name
	 *
	 * @since 2016. 10. 31
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @param descr
	 *            descr to set.
	 *
	 * @since 2016. 10. 31
	 */
	@ColumnDef(name = "descr", type = String.class, caseSensitive = false)
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 *
	 * @param id
	 *            id to set.
	 *
	 * @since 2016. 10. 31
	 */
	@ColumnDef(name = "id", type = Integer.class, caseSensitive = false)
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 *
	 * @param name
	 *            name to set.
	 *
	 * @since 2016. 10. 31
	 */
	@ColumnDef(name = "name", type = String.class, caseSensitive = false)
	public void setName(String name) {
		this.name = name;
	}

	public static boolean available(int grade) {
		switch (grade) {
		case UserGrade.ADMIN:
		case UserGrade.USER:
			return true;
		default:
			return false;
		}
	}

	public static UserGrade getUserGrade(int id, String name, String desc) {

		int _id_ = INVALID_UNKNOWN_AND_ERROR;

		if (USER_GRADES.containsKey(id)) {
			_id_ = id;
			name = USER_GRADES.get(id);
		}

		UserGrade grade = new UserGrade(_id_);

		grade.setName(name);
		grade.setDescr(desc);

		return grade;
	}

}