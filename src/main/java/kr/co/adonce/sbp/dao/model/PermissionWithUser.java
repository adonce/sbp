package kr.co.adonce.sbp.dao.model;

import open.commons.annotation.ColumnDef;
import open.commons.annotation.Getter;

public class PermissionWithUser extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 권한 ID
	 */
	private String permissionId;

	/**
	 * API ID
	 */
	private String apiId;

	/**
	 * 승인 여부
	 */
	private String approval;

	/**
	 * 신청 날짜
	 */
	private long applyDate;

	/**
	 * 거절 사유
	 */
	private String refuseReason;

	/**
	 * API 이름
	 */
	private String apiName;

	/**
	 * API 그룹 ID
	 */
	private String groupId;

	/**
	 * API 그룹 이름
	 */
	private String groupName;

	/**
	 * API 그룹 설명
	 */
	private String groupDescr;

	/**
	 * API 타입
	 */
	private String type;

	/**
	 * API 설명
	 */
	private String apiDescr;

	/**
	 * API URL
	 */
	private String url;

	/**
	 * 권한
	 */
	private String permission;

	public PermissionWithUser() {

	}

	@Getter(name = "user", type = User.class)
	public User getUser() {
		User user;
		user = (User) super.clone();
		return user;
	}

	/**
	 * @return the apiDescr
	 */
	public String getApiDescr() {
		return apiDescr;
	}

	/**
	 * @return the apiId
	 */
	public String getApiId() {
		return apiId;
	}

	/**
	 * @return the apiName
	 */
	public String getApiName() {
		return apiName;
	}

	/**
	 * @return the applyDate
	 */
	public long getApplyDate() {
		return applyDate;
	}

	/**
	 * @return the approval
	 */
	public String getApproval() {
		return approval;
	}

	/**
	 * @return the groupDescr
	 */
	public String getGroupDescr() {
		return groupDescr;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @return the permissionId
	 */
	public String getPermissionId() {
		return permissionId;
	}

	/**
	 * @return the refuseReason
	 */
	public String getRefuseReason() {
		return refuseReason;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param apiDescr
	 *            the apiDescr to set
	 */
	@ColumnDef(name = "api_descr", type = String.class, caseSensitive = false)
	public void setApiDescr(String apiDescr) {
		this.apiDescr = apiDescr;
	}

	/**
	 * @param apiId
	 *            the apiId to set
	 */
	@ColumnDef(name = "api_id", type = String.class, caseSensitive = false)
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	/**
	 * @param apiName
	 *            the apiName to set
	 */
	@ColumnDef(name = "api_name", type = String.class, caseSensitive = false)
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * @param applyDate
	 *            the applyDate to set
	 */
	@ColumnDef(name = "apply_date", type = Long.class, caseSensitive = false)
	public void setApplyDate(long applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * @param approval
	 *            the approval to set
	 */
	@ColumnDef(name = "approval", type = String.class, caseSensitive = false)
	public void setApproval(String approval) {
		this.approval = approval;
	}

	/**
	 * @param groupDescr
	 *            the groupDescr to set
	 */
	@ColumnDef(name = "group_descr", type = String.class, caseSensitive = false)
	public void setGroupDescr(String groupDescr) {
		this.groupDescr = groupDescr;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	@ColumnDef(name = "group_id", type = String.class, caseSensitive = false)
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	@ColumnDef(name = "group_name", type = String.class, caseSensitive = false)
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	@ColumnDef(name = "permission", type = String.class, caseSensitive = false)
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @param permissionId
	 *            the permissionId to set
	 */
	@ColumnDef(name = "permission_id", type = String.class, caseSensitive = false)
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * @param refuseReason
	 *            the refuseReason to set
	 */
	@ColumnDef(name = "refuse_reason", type = String.class, caseSensitive = false)
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	@ColumnDef(name = "type", type = String.class, caseSensitive = false)
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	@ColumnDef(name = "url", type = String.class, caseSensitive = false)
	public void setUrl(String url) {
		this.url = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PermissionWithUser [permissionId=");
		builder.append(permissionId);
		builder.append(", apiId=");
		builder.append(apiId);
		builder.append(", approval=");
		builder.append(approval);
		builder.append(", applyDate=");
		builder.append(applyDate);
		builder.append(", refuseReason=");
		builder.append(refuseReason);
		builder.append(", apiName=");
		builder.append(apiName);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", groupDescr=");
		builder.append(groupDescr);
		builder.append(", type=");
		builder.append(type);
		builder.append(", apiDescr=");
		builder.append(apiDescr);
		builder.append(", url=");
		builder.append(url);
		builder.append(", permission=");
		builder.append(permission);
		builder.append("]");
		return builder.toString();
	}

}
