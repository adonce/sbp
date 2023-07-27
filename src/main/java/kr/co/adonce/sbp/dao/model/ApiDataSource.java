package kr.co.adonce.sbp.dao.model;

import open.commons.annotation.ColumnDef;

public class ApiDataSource {

	/**
	 * API ID
	 */
	private String id;

	/**
	 * 데이터 소스 ID
	 */
	private String dataSourceId;

	/**
	 * API 이름
	 */
	private String name;

	/**
	 * API 그룹 ID
	 */
	private String groupId;

	/**
	 * API 타입
	 */
	private String type;

	/**
	 * API 설명
	 */
	private String description;

	/**
	 * API URL
	 */
	private String url;

	/**
	 * API 권한(ex: "CRUD", "RUD"..)
	 */
	private String permission;

	/**
	 * 테이블 이름
	 */
	private String tableName;

	/**
	 * 데이터 소스 이름
	 */
	private String dataSourceName;

	/**
	 * 데이터 소스 설명
	 */
	private String dataSourceDescr;

	/**
	 * API 그룹 이름
	 */
	private String groupName;

	/**
	 * API 그룹 설명
	 */
	private String groupDescr;

	public ApiDataSource() {

	}

	/**
	 * @return the dataSourceDescr
	 */
	public String getDataSourceDescr() {
		return dataSourceDescr;
	}

	/**
	 * @return the dataSourceId
	 */
	public String getDataSourceId() {
		return dataSourceId;
	}

	/**
	 * @return the dataSourceName
	 */
	public String getDataSourceName() {
		return dataSourceName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
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
	 * @param dataSourceDescr
	 *            the dataSourceDescr to set
	 */
	@ColumnDef(name = "datasource_descr", type = String.class, caseSensitive = false)
	public void setDataSourceDescr(String dataSourceDescr) {
		this.dataSourceDescr = dataSourceDescr;
	}

	/**
	 * @param dataSourceId
	 *            the dataSourceId to set
	 */
	@ColumnDef(name = "datasource_id", type = String.class, caseSensitive = false)
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	/**
	 * @param dataSourceName
	 *            the dataSourceName to set
	 */
	@ColumnDef(name = "datasource_name", type = String.class, caseSensitive = false)
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	@ColumnDef(name = "description", type = String.class, caseSensitive = false)
	public void setDescription(String description) {
		this.description = description;
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
	 * @param id
	 *            the id to set
	 */
	@ColumnDef(name = "id", type = String.class, caseSensitive = false)
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@ColumnDef(name = "name", type = String.class, caseSensitive = false)
	public void setName(String name) {
		this.name = name;
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
	 * @param tableName
	 *            the tableName to set
	 */
	@ColumnDef(name = "table_name", type = String.class, caseSensitive = false)
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
		builder.append("ApiDataSource [id=");
		builder.append(id);
		builder.append(", dataSourceId=");
		builder.append(dataSourceId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", description=");
		builder.append(description);
		builder.append(", url=");
		builder.append(url);
		builder.append(", permission=");
		builder.append(permission);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", dataSourceName=");
		builder.append(dataSourceName);
		builder.append(", dataSourceDescr=");
		builder.append(dataSourceDescr);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", groupDescr=");
		builder.append(groupDescr);
		builder.append("]");
		return builder.toString();
	}

}
