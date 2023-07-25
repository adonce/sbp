package kr.co.adonce.dao.model;

import open.commons.annotation.ColumnDef;

public class TokenAPI {

	/**
	 * 토큰 ID
	 */
	private String tokenId;

	/**
	 * API ID
	 */
	private String apiId;

	public TokenAPI() {

	}

	/**
	 * @return the apiId
	 */
	public String getApiId() {
		return apiId;
	}

	/**
	 * @return the tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param apiId
	 *            the apiId to set
	 */
	@ColumnDef(name = "api_id", type = String.class, caseSensitive = false)
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	/**
	 * @param tokenId
	 *            the tokenId to set
	 */
	@ColumnDef(name = "token_id", type = String.class, caseSensitive = false)
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenAPI [tokenId=");
		builder.append(tokenId);
		builder.append(", apiId=");
		builder.append(apiId);
		builder.append("]");
		return builder.toString();
	}

}
