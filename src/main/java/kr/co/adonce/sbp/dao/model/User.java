package kr.co.adonce.sbp.dao.model;

import open.commons.annotation.ColumnDef;
import open.commons.annotation.Hide;
import open.commons.json.annotation.JSONField;
import open.commons.json.model.DefaultJSONModel;

public class User extends DefaultJSONModel {

	/**  **/
	private static final long serialVersionUID = 1L;

	/**
	 * 계정 ID
	 */
	@JSONField(name = "id")
	private String id;

	/**
	 * 비밀번호
	 */
	@Hide
	@JSONField(name = "password")
	private String password;

	private String passwordConfirm;

	private String currentPassword;

	/** 사용자 권한 객체 */
	@JSONField(name = "userGradeObj")
	private UserGrade userGradeObj;

	/**
	 * 사용자 명
	 */
	@JSONField(name = "name")
	private String name;

	/**
	 * 연락처
	 */
	@JSONField(name = "phone")
	private String phone;

	/**
	 * 권한 (1: 사용자, 0: 관리자)
	 */
	@JSONField(name = "grade")
	private int grade = 1;

	/**
	 * 회사 이름
	 */
	@JSONField(name = "compName")
	private String compName;

	/**
	 * 회사 주소
	 */
	@JSONField(name = "compAddress")
	private String compAddress;

	/**
	 * 부서 이름
	 */
	@JSONField(name = "deptName")
	private String deptName;

	/**
	 * 생년월일
	 */
	@JSONField(name = "birth")
	private long birth;

	/**
	 * 직위
	 */
	@JSONField(name = "rank")
	private String rank;

	/**
	 * 업무
	 */
	@JSONField(name = "task")
	private String task;

	public User() {
	}

	public final Object clone() {
		User user;
		try {
			user = (User) super.clone();
			return user;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public User(PermissionWithUser permission) {
		this.id = permission.getId();
//		this.password = permission.getPassword();
		this.name = permission.getName();
		this.grade = permission.getGrade();
		this.phone = permission.getPhone();
		this.compName = permission.getCompName();
		this.deptName = permission.getDeptName();
		this.compAddress = permission.getCompAddress();
		this.birth = permission.getBirth();
		this.rank = permission.getRank();
		this.task = permission.getTask();
	}

	/**
	 * @return the birth
	 */
	public long getBirth() {
		return birth;
	}

	/**
	 * @return the compAddress
	 */
	public String getCompAddress() {
		return compAddress;
	}

	/**
	 * @return the compName
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * @return the currentPassword
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @return the grade
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * @return the userGradeObj
	 */
	public UserGrade getUserGradeObj() {
		return userGradeObj;
	}

	/**
	 * @param birth the birth to set
	 */
	@ColumnDef(name = "birth", type = Long.class, caseSensitive = false, required = false)
	public void setBirth(long birth) {
		this.birth = birth;
	}

	/**
	 * @param compAddress the compAddress to set
	 */
	@ColumnDef(name = "comp_address", type = String.class, caseSensitive = false, required = false)
	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}

	/**
	 * @param compName the compName to set
	 */
	@ColumnDef(name = "comp_name", type = String.class, caseSensitive = false, required = false)
	public void setCompName(String compName) {
		this.compName = compName;
	}

	/**
	 * @param currentPassword the currentPassword to set
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	/**
	 * @param deptName the deptName to set
	 */
	@ColumnDef(name = "dept_name", type = String.class, caseSensitive = false, required = false)
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @param grade the grade to set
	 */
	@ColumnDef(name = "grade", type = Integer.class, caseSensitive = false, required = false)
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * @param id the id to set
	 */
	@ColumnDef(name = "id", type = String.class, caseSensitive = false, required = false)
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	@ColumnDef(name = "name", type = String.class, caseSensitive = false, required = false)
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	/**
	 * @param phone the phone to set
	 */
	@ColumnDef(name = "phone", type = String.class, caseSensitive = false, required = false)
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param rank the rank to set
	 */
	@ColumnDef(name = "rank", type = String.class, caseSensitive = false, required = false)
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @param task the task to set
	 */
	@ColumnDef(name = "task", type = String.class, caseSensitive = false, required = false)
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * @param userGradeObj the userGradeObj to set
	 */
	public void setUserGradeObj(UserGrade userGradeObj) {
		this.userGradeObj = userGradeObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordConfirm=");
		builder.append(passwordConfirm);
		builder.append(", currentPassword=");
		builder.append(currentPassword);
		builder.append(", userGradeObj=");
		builder.append(userGradeObj);
		builder.append(", name=");
		builder.append(name);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", grade=");
		builder.append(grade);
		builder.append(", compName=");
		builder.append(compName);
		builder.append(", compAddress=");
		builder.append(compAddress);
		builder.append(", deptName=");
		builder.append(deptName);
		builder.append(", birth=");
		builder.append(birth);
		builder.append(", rank=");
		builder.append(rank);
		builder.append(", task=");
		builder.append(task);
		builder.append("]");
		return builder.toString();
	}

}
