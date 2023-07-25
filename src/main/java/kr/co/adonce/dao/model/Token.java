package kr.co.adonce.dao.model;

import kr.co.adonce.controller.model.TokenDTO;
import open.commons.annotation.ColumnDef;
import open.commons.annotation.Getter;

public class Token {

	/**
	 * 토큰 ID
	 */
	private String id;

	/**
	 * API ID
	 */
	private String apiId;

	/**
	 * 토큰 이름
	 */
	private String name;

	/**
	 * 사용자 ID
	 */
	private String userId;

	/**
	 * 토큰 생성 시간
	 */
	private long createDate;

	/**
	 * 토큰 만료 기한
	 */
	private long expireDate;

	public Token() {

	}

	public Token(TokenDTO tokenDTO) {
		this.id = tokenDTO.getId();
		this.name = tokenDTO.getName();
		this.userId = tokenDTO.getUser().getId();
		this.createDate = tokenDTO.getCreateDate();
		this.expireDate = tokenDTO.getExpireDate();
	}

	/**
	 * @return the apiId
	 */
	@Getter(name = "apiId", type = String.class)
	public String getApiId() {
		return apiId;
	}

	/**
	 * @return the createDate
	 */
	@Getter(name = "createDate", type = Long.class)
	public long getCreateDate() {
		return createDate;
	}

	/**
	 * @return the expireDate
	 */
	@Getter(name = "expireDate", type = Long.class)
	public long getExpireDate() {
		return expireDate;
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
	 * @return the userId
	 */
	@Getter(name = "userId", type = String.class)
	public String getUserId() {
		return userId;
	}

	/**
	 * @param apiId the apiId to set
	 */
	@ColumnDef(name = "api_id", type = String.class, caseSensitive = false)
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	/**
	 * @param createDate the createDate to set
	 */
	@ColumnDef(name = "create_date", type = Long.class, caseSensitive = false)
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param expireDate the expireDate to set
	 */
	@ColumnDef(name = "expire_date", type = Long.class, caseSensitive = false)
	public void setExpireDate(long expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @param id the id to set
	 */
	@ColumnDef(name = "id", type = String.class, caseSensitive = false)
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	@ColumnDef(name = "name", type = String.class, caseSensitive = false)
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param userId the userId to set
	 */
	@ColumnDef(name = "user_id", type = String.class, caseSensitive = false)
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Token [id=");
		builder.append(id);
		builder.append(", apiId=");
		builder.append(apiId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", expireDate=");
		builder.append(expireDate);
		builder.append("]");
		return builder.toString();
	}

}
