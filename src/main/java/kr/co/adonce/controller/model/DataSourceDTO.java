package kr.co.adonce.controller.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumn;

import kr.co.adonce.dao.model.ApiDataSource;
import kr.co.adonce.dao.model.DataSource;
import open.commons.annotation.Getter;

public class DataSourceDTO {

	/**
	 * 테이블 이름
	 */
	private String tableName;

	/**
	 * 데이터 소스 이름
	 */
	private String name;

	/**
	 * 테이블 구독 컬럼 목록
	 */
	private List<TableColumn> columns = new ArrayList<>();

	/**
	 * 데이터 소스 설명
	 */
	private String description;

	/**
	 * 그룹 이름
	 */
	private String groupName;

	public DataSourceDTO() {

	}

	public DataSourceDTO(ApiDataSource apiDataSource) {
		this.tableName = apiDataSource.getTableName();
		this.name = apiDataSource.getDataSourceName();
		this.description = apiDataSource.getDataSourceDescr();
		this.groupName = apiDataSource.getGroupName();
	}

	public DataSourceDTO(DataSource dataSource) {
		this.tableName = dataSource.getTableName();
		this.name = dataSource.getName();
		this.description = dataSource.getDescription();
		this.groupName = dataSource.getGroupName();
	}

	/**
	 * @return the columns
	 */
	public List<TableColumn> getColumns() {
		List<TableColumn> columns = new ArrayList<>(this.columns);
		return columns;
	}

	/**
	 * @return the description
	 */
	@Getter(name = "description", type = String.class)
	public String getDescription() {
		return description;
	}

	/**
	 * @return the groupName
	 */
	@Getter(name = "groupName", type = String.class)
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @return the name
	 */
	@Getter(name = "name", type = String.class)
	public String getName() {
		return name;
	}

	/**
	 * @return the tableName
	 */
	@Getter(name = "table_name", type = String.class)
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<TableColumn> columns) {
		this.columns = new ArrayList<>(columns);
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param tableName the tableName to set
	 */
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
		builder.append("DataSourceDTO [tableName=");
		builder.append(tableName);
		builder.append(", name=");
		builder.append(name);
		builder.append(", columns=");
		builder.append(columns);
		builder.append(", description=");
		builder.append(description);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append("]");
		return builder.toString();
	}

}
