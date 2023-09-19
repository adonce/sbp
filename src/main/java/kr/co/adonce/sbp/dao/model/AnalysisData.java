package kr.co.adonce.sbp.dao.model;

import kr.co.adonce.sbp.controller.model.AnalysisDataDTO;
import open.commons.annotation.ColumnDef;
import open.commons.json.annotation.JSONField;

public class AnalysisData {

	/**
	 * 번호
	 */
	@JSONField(name = "num")
	private int num;

	/**
	 * 공사명
	 */
	@JSONField(name = "cnstNm")
	private String cnstNm;

	/**
	 * 공고번호
	 */
	@JSONField(name = "ntcNum")
	private String ntcNum;

	/**
	 * 종목(통신, 전기)
	 */
	@JSONField(name = "event")
	private String event;

	/**
	 * 발주처
	 */
	@JSONField(name = "client")
	private String client;

	/**
	 * 지역
	 */
	@JSONField(name = "region")
	private String region;

	/**
	 * 기초금액
	 */
	@JSONField(name = "baseAmnt")
	private long baseAmnt;

	/**
	 * 예정가격사정률
	 */
	@JSONField(name = "exPriceRt")
	private double exPriceRt;

	/**
	 * 1순위금액
	 */
	@JSONField(name = "fAmnt")
	private long fAmnt;

	/**
	 * 기초대비투찰률
	 */
	@JSONField(name = "fdmtRt")
	private double fdmtRt;

	/**
	 * 1순위사정률
	 */
	@JSONField(name = "fPlcRt")
	private double fPlcRt;

	public AnalysisData() {

	}

	public long getBaseAmnt() {
		return baseAmnt;
	}

	public String getClient() {
		return client;
	}

	public String getCnstNm() {
		return cnstNm;
	}

	public String getEvent() {
		return event;
	}

	public double getExPriceRt() {
		return exPriceRt;
	}

	public long getfAmnt() {
		return fAmnt;
	}

	public double getfPlcRt() {
		return fPlcRt;
	}

	public String getNtcNum() {
		return ntcNum;
	}

	public int getNum() {
		return num;
	}

	public double getFdmtRt() {
		return fdmtRt;
	}

	public String getRegion() {
		return region;
	}

	@ColumnDef(name = "base_amnt", type = Long.class, caseSensitive = false, required = false)
	public void setBaseAmnt(long baseAmnt) {
		this.baseAmnt = baseAmnt;
	}

	@ColumnDef(name = "client", type = String.class, caseSensitive = false, required = false)
	public void setClient(String client) {
		this.client = client;
	}

	@ColumnDef(name = "cnst_nm", type = String.class, caseSensitive = false, required = false)
	public void setCnstNm(String cnstNm) {
		this.cnstNm = cnstNm;
	}

	@ColumnDef(name = "event", type = String.class, caseSensitive = false, required = false)
	public void setEvent(String event) {
		this.event = event;
	}

	@ColumnDef(name = "ex_price_rt", type = Double.class, caseSensitive = false, required = false)
	public void setExPriceRt(double exPriceRt) {
		this.exPriceRt = exPriceRt;
	}

	@ColumnDef(name = "f_amnt", type = Long.class, caseSensitive = false, required = false)
	public void setfAmnt(long fAmnt) {
		this.fAmnt = fAmnt;
	}

	@ColumnDef(name = "f_plc_rt", type = Double.class, caseSensitive = false, required = false)
	public void setfPlcRt(double fPlcRt) {
		this.fPlcRt = fPlcRt;
	}

	@ColumnDef(name = "ntc_num", type = String.class, caseSensitive = false, required = false)
	public void setNtcNum(String ntcNum) {
		this.ntcNum = ntcNum;
	}

	@ColumnDef(name = "num", type = Integer.class, caseSensitive = false, required = false)
	public void setNum(int num) {
		this.num = num;
	}

	@ColumnDef(name = "fdmt_rt", type = Double.class, caseSensitive = false, required = false)
	public void setFdmtRt(double fdmtRt) {
		this.fdmtRt = fdmtRt;
	}

	@ColumnDef(name = "region", type = String.class, caseSensitive = false, required = false)
	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnalysisData [num=");
		builder.append(num);
		builder.append(", cnstNm=");
		builder.append(cnstNm);
		builder.append(", ntcNum=");
		builder.append(ntcNum);
		builder.append(", event=");
		builder.append(event);
		builder.append(", client=");
		builder.append(client);
		builder.append(", region=");
		builder.append(region);
		builder.append(", baseAmnt=");
		builder.append(baseAmnt);
		builder.append(", exPriceRt=");
		builder.append(exPriceRt);
		builder.append(", fAmnt=");
		builder.append(fAmnt);
		builder.append(", fdmtRt=");
		builder.append(fdmtRt);
		builder.append(", fPlcRt=");
		builder.append(fPlcRt);
		builder.append("]");
		return builder.toString();
	}

}
