package kr.co.adonce.sbp.controller.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.co.adonce.sbp.dao.model.TokenWithApi;
import kr.co.adonce.sbp.dao.model.User;
import open.commons.annotation.Setter;
import open.commons.json.annotation.JSONField;

public class TokenDTO {

	protected final Logger logger = LogManager.getLogger(getClass());

	/**
	 * 토큰 ID
	 */
	@JSONField(name = "id")
	private String id;

	/**
	 * 토큰 이름
	 */
	@JSONField(name = "name")
	private String name;

	/**
	 * 사용자 객체
	 */
	private User user;

	/**
	 * 토큰 생성 시간
	 */
	@JSONField(name = "create_date")
	private long createDate;

	/**
	 * 토큰 만료 기한
	 */
	@JSONField(name = "expire_date")
	private long expireDate;

	/**
	 * 토큰 API 목록
	 */
	private List<ApiDTO> apis = new ArrayList<>();

	private ApiGroupDTO apiGroup;

	public TokenDTO() {

	}

	public TokenDTO(TokenWithApi tokenWithApi) {
		this.id = tokenWithApi.getId();
		this.name = tokenWithApi.getName();
		this.createDate = tokenWithApi.getCreateDate();
		this.expireDate = tokenWithApi.getExpireDate();
	}

	public void addApi(ApiDTO apiDTO) {
		if (apiDTO == null) {
			logger.warn("apiDTO is null.");
			return;
		}
		this.apis.add(apiDTO);

	}

	/**
	 * @return the apiGroup
	 */
	public ApiGroupDTO getApiGroup() {
		return apiGroup;
	}

	/**
	 * @return the apis
	 */
	public List<ApiDTO> getApis() {
		List<ApiDTO> apis = new ArrayList<>(this.apis);
		return apis;
	}

	/**
	 * @return the createDate
	 */
	public long getCreateDate() {
		return createDate;
	}

	/**
	 * @return the expireDate
	 */
	public long getExpireDate() {
		return expireDate;
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param apiGroup the apiGroup to set
	 */
	public void setApiGroup(ApiGroupDTO apiGroup) {
		this.apiGroup = apiGroup;
	}

	/**
	 * @param apis the apis to set
	 */
	public void setApis(List<ApiDTO> apis) {
		this.apis = new ArrayList<>(apis);
	}

	/**
	 * @param createDate the createDate to set
	 */
	@Setter(name = "createDate", type = Long.class)
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param expireDate the expireDate to set
	 */
	@Setter(name = "expireDate", type = Long.class)
	public void setExpireDate(long expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @param id the id to set
	 */
	@Setter(name = "id", type = String.class)
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	@Setter(name = "name", type = String.class)
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", user=");
		builder.append(user);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", expireDate=");
		builder.append(expireDate);
		builder.append(", apis=");
		builder.append(apis);
		builder.append("]");
		return builder.toString();
	}
}
