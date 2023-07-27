package kr.co.adonce.sbp.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiByGroupDTO {

	protected final Logger logger = LogManager.getLogger(getClass());

	/**
	 * API 그룹 객체
	 */
	private ApiGroupDTO group;

	/**
	 * API 객체 목록
	 */
	private List<ApiDTO> apiList = new ArrayList<>();

	public ApiByGroupDTO() {

	}

	/**
	 * @return the apiList
	 */
	public List<ApiDTO> getApiList() {
		List<ApiDTO> apiList = new ArrayList<>(this.apiList);
		return apiList;
	}

	/**
	 * @return the group
	 */
	public ApiGroupDTO getGroup() {
		return group;
	}

	/**
	 * @param apiList the apiList to set
	 */
	public void setApiList(List<ApiDTO> apiList) {
		this.apiList = new ArrayList<>(apiList);
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(ApiGroupDTO group) {
		this.group = group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApiByGroupDTO [group=");
		builder.append(group);
		builder.append(", apiList=");
		builder.append(apiList);
		builder.append("]");
		return builder.toString();
	}

	public void addApi(ApiDTO apiDTO) {
		if (apiDTO == null) {
			logger.warn("DataSourceDTO is null.");
			return;
		}
		this.apiList.add(apiDTO);
	}

}
