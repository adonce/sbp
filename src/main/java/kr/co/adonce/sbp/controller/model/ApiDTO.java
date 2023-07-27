package kr.co.adonce.sbp.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.co.adonce.sbp.dao.model.ApiDataSource;
import kr.co.adonce.sbp.dao.model.PermissionWithApi;
import kr.co.adonce.sbp.dao.model.PermissionWithUser;
import kr.co.adonce.sbp.dao.model.TokenWithApi;
import open.commons.annotation.Getter;
import open.commons.json.annotation.JSONField;

public class ApiDTO {
	protected final Logger logger = LogManager.getLogger(getClass());

	/**
	 * API ID
	 */
	@JSONField(name = "id")
	private String id;

	/**
	 * 데이터 소스 목록
	 */
	@JSONField(name = "dataSourceList")
	private List<DataSourceDTO> dataSourceList = new ArrayList<>();

	/**
	 * API 이름
	 */
	@JSONField(name = "name")
	private String name;

	/**
	 * 그룹 정보
	 */
	@JSONField(name = "group")
	private ApiGroupDTO group;

	/**
	 * 타입 (PCL or FWL)
	 */
	@JSONField(name = "type")
	private String type;

	/**
	 * API 설명
	 */
	@JSONField(name = "description")
	private String description;

	/**
	 * API URL
	 */
	@JSONField(name = "url")
	private String url;

	/**
	 * 권한
	 */
	@JSONField(name = "permission")
	private String permission;

	/**
	 * 데이터 등록 여부
	 */
	@JSONField(name = "registed")
	private boolean registed;

	public ApiDTO() {

	}

	public ApiDTO(ApiDataSource api) {
		this.id = api.getId();
		this.name = api.getName();
		this.type = api.getType();
		this.url = api.getUrl();
		this.description = api.getDescription();
		this.permission = api.getPermission();

	}

	public ApiDTO(PermissionWithApi pwa) {
		this.id = pwa.getApiId();
		this.name = pwa.getName();
		this.type = pwa.getType();
		this.url = pwa.getUrl();
		this.description = pwa.getDescription();
		this.permission = pwa.getPermission();
	}

	public ApiDTO(PermissionWithUser permission) {
		this.id = permission.getApiId();
		this.name = permission.getApiName();
		this.type = permission.getType();
		this.url = permission.getUrl();
		this.description = permission.getApiDescr();
		this.permission = permission.getPermission();
	}

	public ApiDTO(TokenWithApi tokenWithApi) {
		this.id = tokenWithApi.getApiId();
		this.name = tokenWithApi.getApiName();
		this.type = tokenWithApi.getApiType();
		this.url = tokenWithApi.getUrl();
		this.description = tokenWithApi.getApiDescr();
		this.permission = tokenWithApi.getPermission();
		this.group = new ApiGroupDTO();
		this.group.setName(tokenWithApi.getGroupName());
	}

	public void addDataSource(DataSourceDTO dataSourceDTO) {
		if (dataSourceDTO == null) {
			logger.warn("DataSourceDTO is null.");
			return;
		}
		this.dataSourceList.add(dataSourceDTO);
	}

	/**
	 * @return the dataSourceList
	 */
	public List<DataSourceDTO> getDataSourceList() {
		List<DataSourceDTO> dataSourceList = new ArrayList<>(this.dataSourceList);
		return dataSourceList;
	}

	/**
	 * @return the description
	 */
	@Getter(name = "description", type = String.class)
	public String getDescription() {
		return description;
	}

	/**
	 * @return the group
	 */
	public ApiGroupDTO getGroup() {
		return group;
	}

	/**
	 * @return the id
	 */
	@Getter(name = "id", type = String.class)
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	@Getter(name = "name", type = String.class)
	public String getName() {
		return name;
	}

	/**
	 * @return the permission
	 */
	@Getter(name = "permission", type = String.class)
	public String getPermission() {
		return permission;
	}

	/**
	 * @return the type
	 */
	@Getter(name = "type", type = String.class)
	public String getType() {
		return type;
	}

	/**
	 * @return the url
	 */
	@Getter(name = "url", type = String.class)
	public String getUrl() {
		return url;
	}

	/**
	 * @return the registed
	 */
	public boolean isRegisted() {
		return registed;
	}

	/**
	 * @param dataSourceList the dataSourceList to set
	 */
	public void setDataSourceList(List<DataSourceDTO> dataSourceList) {
		if (dataSourceList != null) {
			this.dataSourceList = new ArrayList<>(dataSourceList);
		}
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(ApiGroupDTO group) {
		this.group = group;
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

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @param registed the registed to set
	 */
	public void setRegisted(boolean registed) {
		this.registed = registed;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param url the url to set
	 */
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
		builder.append("ApiDTO [logger=");
		builder.append(logger);
		builder.append(", id=");
		builder.append(id);
		builder.append(", dataSourceList=");
		builder.append(dataSourceList);
		builder.append(", name=");
		builder.append(name);
		builder.append(", group=");
		builder.append(group);
		builder.append(", type=");
		builder.append(type);
		builder.append(", description=");
		builder.append(description);
		builder.append(", url=");
		builder.append(url);
		builder.append(", permission=");
		builder.append(permission);
		builder.append(", registed=");
		builder.append(registed);
		builder.append("]");
		return builder.toString();
	}
}
