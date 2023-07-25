package kr.co.adonce.dao.model;

import java.util.UUID;

import kr.co.adonce.controller.model.ApiDTO;
import open.commons.annotation.ColumnDef;
import open.commons.annotation.Setter;

public class Api {

	/**
	 * API ID
	 */
	private String id;

	/**
	 * 데이터 소스 ID(테이블명)
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
	 * 타입 (PCL or FWL)
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
	 * 권한
	 */
	private String permission;

	public Api() {

	}

	public Api(ApiDTO apiDto) {
		this.id = UUID.randomUUID().toString();
		this.name = apiDto.getName();
		// dataSourceId는 목록으로 받아 DAO에서 처리
		this.groupId = apiDto.getGroup().getId();
		this.type = apiDto.getType();
		this.description = apiDto.getDescription();
		this.url = apiDto.getUrl();
		this.permission = apiDto.getPermission();
	}

	/**
	 * @return the dataSourceId
	 */
	public String getDataSourceId() {
		return dataSourceId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
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
	 * @param dataSourceId the dataSourceId to set
	 */
	@Setter(name = "datasource_id", type = String.class)
	@ColumnDef(name = "datasource_id", type = String.class, caseSensitive = false)
	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	/**
	 * @param description the description to set
	 */
	@Setter(name = "description", type = String.class)
	@ColumnDef(name = "description", type = String.class, caseSensitive = false)
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param groupId the groupId to set
	 */
	@Setter(name = "groupId", type = String.class)
	@ColumnDef(name = "group_id", type = String.class, caseSensitive = false)
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @param id the id to set
	 */
	@Setter(name = "id", type = String.class)
	@ColumnDef(name = "id", type = String.class, caseSensitive = false)
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	@Setter(name = "name", type = String.class)
	@ColumnDef(name = "name", type = String.class, caseSensitive = false)
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param permission the permission to set
	 */
	@Setter(name = "permission", type = String.class)
	@ColumnDef(name = "permission", type = String.class, caseSensitive = false)
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @param type the type to set
	 */
	@Setter(name = "type", type = String.class)
	@ColumnDef(name = "type", type = String.class, caseSensitive = false)
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param url the url to set
	 */
	@Setter(name = "url", type = String.class)
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
		builder.append("Api [id=");
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
		builder.append("]");
		return builder.toString();
	}

}
