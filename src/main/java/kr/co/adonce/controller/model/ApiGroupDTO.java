package kr.co.adonce.controller.model;

import kr.co.adonce.dao.model.ApiDataSource;
import kr.co.adonce.dao.model.ApiGroup;
import kr.co.adonce.dao.model.PermissionWithApi;
import kr.co.adonce.dao.model.PermissionWithUser;
import open.commons.annotation.Getter;

public class ApiGroupDTO {
	/**
	 * API 그룹 ID(UUID)
	 */
	private String id;

	/**
	 * API 그룹 이름
	 */
	private String name;

	/**
	 * API 그룹 설명
	 */
	private String description;

	public ApiGroupDTO() {

	}

	public ApiGroupDTO(ApiGroup apiGroup) {
		this.id = apiGroup.getId();
		this.name = apiGroup.getName();
		this.description = apiGroup.getDescription();
	}

	public ApiGroupDTO(ApiDataSource apiDataSource) {
		this.id = apiDataSource.getGroupId();
		this.name = apiDataSource.getGroupName();
		this.description = apiDataSource.getGroupDescr();
	}

	public ApiGroupDTO(PermissionWithApi pwa) {
		this.id = pwa.getGroupId();
		this.name = pwa.getGroupName();
		this.description = pwa.getGroupDescr();
	}

	public ApiGroupDTO(PermissionWithUser permission) {
		this.id = permission.getGroupId();
		this.name = permission.getGroupName();
		this.description = permission.getGroupDescr();
	}

	/**
	 * @return the description
	 */
	@Getter(name = "groupDescr", type = String.class)
	public String getDescription() {
		return description;
	}

	/**
	 * @return the id
	 */
	@Getter(name = "groupId", type = String.class)
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	@Getter(name = "groupName", type = String.class)
	public String getName() {
		return name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApiGroupDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
