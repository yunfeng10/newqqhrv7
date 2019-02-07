package com.jeecms.cms.entity.assist;

import java.io.Serializable;
import java.util.Date;

public class MisStatus2 implements Serializable {

	private String dataid;
	
	private String refid;
	
	private String hjmc;
	
	private String blbm;
	
	private Date jssj;
	
	private String jssjstr;
	
	private Date blsj;
	
	private String blsjstr;
	
	private String bljg;

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getHjmc() {
		return hjmc;
	}

	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}

	public String getBlbm() {
		return blbm;
	}

	public void setBlbm(String blbm) {
		this.blbm = blbm;
	}

	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}

	public Date getBlsj() {
		return blsj;
	}

	public void setBlsj(Date blsj) {
		this.blsj = blsj;
	}

	public String getBljg() {
		return bljg;
	}

	public void setBljg(String bljg) {
		this.bljg = bljg;
	}

	public String getJssjstr() {
		return jssjstr;
	}

	public void setJssjstr(String jssjstr) {
		this.jssjstr = jssjstr;
	}

	public String getBlsjstr() {
		return blsjstr;
	}

	public void setBlsjstr(String blsjstr) {
		this.blsjstr = blsjstr;
	}
	
	
}
