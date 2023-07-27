package kr.co.adonce.sbp.controller.model;

public class CommonSearchReq {

	private int pageNum = 0;

	private int itemCountPerPage = 0;

	private String orderColumn;

	private boolean isDesc;

	private String keyword;

	public CommonSearchReq() {
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum
	 *            the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the itemCountPerPage
	 */
	public int getItemCountPerPage() {
		return itemCountPerPage;
	}

	/**
	 * @param itemCountPerPage
	 *            the itemCountPerPage to set
	 */
	public void setItemCountPerPage(int itemCountPerPage) {
		this.itemCountPerPage = itemCountPerPage;
	}

	/**
	 * @return the orderColumn
	 */
	public String getOrderColumn() {
		return orderColumn;
	}

	/**
	 * @param orderColumn
	 *            the orderColumn to set
	 */
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	/**
	 * @return the isDesc
	 */
	public boolean isDesc() {
		return isDesc;
	}

	/**
	 * @param isDesc
	 *            the isDesc to set
	 */
	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonSearchReq [pageNum=");
		builder.append(pageNum);
		builder.append(", itemCountPerPage=");
		builder.append(itemCountPerPage);
		builder.append(", orderColumn=");
		builder.append(orderColumn);
		builder.append(", isDesc=");
		builder.append(isDesc);
		builder.append(", keyword=");
		builder.append(keyword);
		builder.append("]");
		return builder.toString();
	}
}
