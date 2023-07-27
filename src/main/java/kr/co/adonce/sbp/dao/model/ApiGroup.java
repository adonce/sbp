package kr.co.adonce.sbp.dao.model;

import kr.co.adonce.sbp.controller.model.ApiGroupDTO;
import open.commons.annotation.ColumnDef;
import open.commons.annotation.Setter;

public class ApiGroup {

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

	public ApiGroup() {

	}

	public ApiGroup(ApiGroupDTO apiGroupDTO) {
		this.id = apiGroupDTO.getId();
		this.name = apiGroupDTO.getName();
		this.description = apiGroupDTO.getDescription();
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	 * @param description the description to set
	 */
	@Setter(name = "groupDescr", type = String.class)
	@ColumnDef(name = "description", type = String.class, caseSensitive = false)
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param id the id to set
	 */
	@Setter(name = "groupId", type = String.class)
	@ColumnDef(name = "id", type = String.class, caseSensitive = false)
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	@Setter(name = "groupName", type = String.class)
	@ColumnDef(name = "name", type = String.class, caseSensitive = false)
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
		builder.append("ApiGroup [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
