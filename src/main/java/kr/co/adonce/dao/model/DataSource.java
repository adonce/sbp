package kr.co.adonce.dao.model;

import kr.co.adonce.controller.model.DataSourceDTO;
import open.commons.annotation.ColumnDef;
import open.commons.annotation.Setter;

/**
 *
 * @return
 */
public class DataSource {

	/**
	 * 테이블 이름
	 */
	private String tableName;

	/**
	 * 데이터 소스 이름
	 */
	private String name;

	/**
	 * 설명
	 */
	private String description;

	/**
	 * 테이블 그룹 이름
	 */
	private String groupName;

	public DataSource() {

	}

	public DataSource(DataSourceDTO dataSourceDTO) {
		this.tableName = dataSourceDTO.getTableName();
		this.name = dataSourceDTO.getName();
		this.description = dataSourceDTO.getDescription();
		this.groupName = dataSourceDTO.getGroupName();

	}

	public DataSource(String tableName, String name, String description) {
		this.tableName = tableName;
		this.name = name;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
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
	 * @param groupName the groupName to set
	 */
	@Setter(name = "groupName", type = String.class)
	@ColumnDef(name = "group_name", type = String.class, caseSensitive = false)
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	 * @param tableName the tableName to set
	 */
	@Setter(name = "table_name", type = String.class)
	@ColumnDef(name = "table_name", type = String.class, caseSensitive = false)
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataSource [tableName=");
		builder.append(tableName);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
