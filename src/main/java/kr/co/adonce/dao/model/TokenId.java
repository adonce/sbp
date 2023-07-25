package kr.co.adonce.dao.model;

import open.commons.annotation.ColumnDef;

public class TokenId {

	/**
	 * 토큰 ID
	 */
	private String id;

	public TokenId() {

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@ColumnDef(name = "id", type = String.class, caseSensitive = false)
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokenId [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}
