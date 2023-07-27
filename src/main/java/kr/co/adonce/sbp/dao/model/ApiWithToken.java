package kr.co.adonce.sbp.dao.model;

import open.commons.annotation.ColumnDef;

public class ApiWithToken extends Api {

	/**
	 * 토큰 ID
	 */
	private String token;

	public ApiWithToken() {

	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	@ColumnDef(name = "token", type = String.class, caseSensitive = false)
	public void setToken(String token) {
		this.token = token;
	}
	

}
