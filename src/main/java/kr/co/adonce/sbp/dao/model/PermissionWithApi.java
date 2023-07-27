package kr.co.adonce.sbp.dao.model;

import open.commons.annotation.ColumnDef;

public class PermissionWithApi extends Api {

	/**
	 * 권한 ID
	 */
	private String permissionId;

	/**
	 * API ID
	 */
	private String apiId;

	/**
	 * 사용자 ID
	 */
	private String userId;

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
	 * 그룹 ID
	 */
	private String groupId;

	/**
	 * 그룹 명
	 */
	private String groupName;

	/**
	 * 그룹 설명
	 */
	private String groupDescr;

	public PermissionWithApi() {

	}

	/**
	 * @return the apiId
	 */
	public String getApiId() {
		return apiId;
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
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
	 * @param userId
	 *            the userId to set
	 */
	@ColumnDef(name = "user_id", type = String.class, caseSensitive = false)
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PermissionWithApi [permissionId=");
		builder.append(permissionId);
		builder.append(", apiId=");
		builder.append(apiId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", approval=");
		builder.append(approval);
		builder.append(", applyDate=");
		builder.append(applyDate);
		builder.append(", refuseReason=");
		builder.append(refuseReason);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", groupDescr=");
		builder.append(groupDescr);
		builder.append("]");
		return builder.toString();
	}

}
