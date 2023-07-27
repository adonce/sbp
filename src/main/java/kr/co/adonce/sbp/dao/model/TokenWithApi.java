package kr.co.adonce.sbp.dao.model;

import open.commons.annotation.ColumnDef;
import open.commons.annotation.Getter;

public class TokenWithApi extends Token {

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
	private String groupDescription;
	
	/**
	 * API 타입
	 */
	private String apiType;

	/**
	 * API 설명
	 */
	private String apiDescr;

	/**
	 * API URL
	 */
	private String url;

	/**
	 * API 권한
	 */
	private String permission;

	public TokenWithApi() {

	}

	/**
	 * @return the apiDescr
	 */
	@Getter(name = "apiDescription", type = String.class)
	public String getApiDescr() {
		return apiDescr;
	}

	/**
	 * @return the apiName
	 */
	@Getter(name = "apiName", type = String.class)
	public String getApiName() {
		return apiName;
	}

	/**
	 * @return the apiType
	 */
	@Getter(name = "apiType", type = String.class)
	public String getApiType() {
		return apiType;
	}

	/**
	 * @return the groupDescription
	 */
	@Getter(name = "groupDescr", type = String.class)
	public String getGroupDescription() {
		return groupDescription;
	}

	/**
	 * @return the groupId
	 */
	@Getter(name = "groupId", type = String.class)
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @return the groupName
	 */
	@Getter(name = "groupName", type = String.class)
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @return the permission
	 */
	@Getter(name = "permission", type = String.class)
	public String getPermission() {
		return permission;
	}

	/**
	 * @return the url
	 */
	@Getter(name = "url", type = String.class)
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
	 * @param apiName
	 *            the apiName to set
	 */
	@ColumnDef(name = "api_name", type = String.class, caseSensitive = false)
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * @param apiType
	 *            the apiType to set
	 */
	@ColumnDef(name = "api_type", type = String.class, caseSensitive = false)
	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	/**
	 * @param groupDescription the groupDescription to set
	 */
	@ColumnDef(name = "group_description", type = String.class, caseSensitive = false)
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
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
	 * @param groupName the groupName to set
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
		builder.append("TokenWithApi [apiName=");
		builder.append(apiName);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", apiType=");
		builder.append(apiType);
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
