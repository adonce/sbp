package kr.co.adonce.sbp.controller.model;

import java.sql.Timestamp;

import open.commons.annotation.Getter;
import open.commons.json.annotation.JSONField;
import open.commons.json.model.DefaultJSONModel;

public class AnalysisDataDTO extends DefaultJSONModel {

	/**  **/
	private static final long serialVersionUID = 1L;

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
	@JSONField(name = "rdmtRt")
	private double rdmtRt;

	/**
	 * 1순위사정률
	 */
	@JSONField(name = "fPlcRt")
	private double fPlcRt;

	/**
	 * 개찰일
	 */
	@JSONField(name = "openDay")
	private Timestamp openDay;

	@Getter(name = "baseAmnt", type = Long.class)
	public long getBaseAmnt() {
		return baseAmnt;
	}

	@Getter(name = "client", type = String.class)
	public String getClient() {
		return client;
	}

	@Getter(name = "cnstNm", type = String.class)
	public String getCnstNm() {
		return cnstNm;
	}

	@Getter(name = "event", type = String.class)
	public String getEvent() {
		return event;
	}

	@Getter(name = "exPriceRt", type = Double.class)
	public double getExPriceRt() {
		return exPriceRt;
	}

	@Getter(name = "fAmnt", type = Long.class)
	public long getfAmnt() {
		return fAmnt;
	}

	@Getter(name = "fPlcRt", type = Double.class)
	public double getfPlcRt() {
		return fPlcRt;
	}

	@Getter(name = "ntcNum", type = String.class)
	public String getNtcNum() {
		return ntcNum;
	}

	@Getter(name = "num", type = Integer.class)
	public int getNum() {
		return num;
	}

	@Getter(name = "openDay", type = Timestamp.class)
	public Timestamp getOpenDay() {
		return openDay;
	}

	public double getRdmtRt() {
		return rdmtRt;
	}

	public String getRegion() {
		return region;
	}

	public void setBaseAmnt(long baseAmnt) {
		this.baseAmnt = baseAmnt;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setCnstNm(String cnstNm) {
		this.cnstNm = cnstNm;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setExPriceRt(double exPriceRt) {
		this.exPriceRt = exPriceRt;
	}

	public void setfAmnt(long fAmnt) {
		this.fAmnt = fAmnt;
	}

	public void setfPlcRt(double fPlcRt) {
		this.fPlcRt = fPlcRt;
	}

	public void setNtcNum(String ntcNum) {
		this.ntcNum = ntcNum;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setOpenDay(Timestamp openDay) {
		this.openDay = openDay;
	}

	public void setRdmtRt(double rdmtRt) {
		this.rdmtRt = rdmtRt;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnalysisDataDTO [num=");
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
		builder.append(", rdmtRt=");
		builder.append(rdmtRt);
		builder.append(", fPlcRt=");
		builder.append(fPlcRt);
		builder.append(", openDay=");
		builder.append(openDay);
		builder.append("]");
		return builder.toString();
	}

}
