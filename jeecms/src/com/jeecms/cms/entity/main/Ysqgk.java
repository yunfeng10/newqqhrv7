package com.jeecms.cms.entity.main;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Ysqgk implements Serializable {

	private int id;
	private int shenQingLeiXing;
	private String gmXingMing;
	private String gmGongZuoDanWei;
	private String gmZhengJianMingChen;
	private String gmZhengJianHaoMa;
	private String gmLianXiDianHua;
	private String gmChuanZhen;
	private String gmLianXiDiZhi;
	private String gmDianZiYouXiang;
	private String gmYouZhengBianMa;
	private String qtMingCheng;
	private String qtFaRenDaiBiao;
	private String qtLianXiRenXingMing;
	private String qtJiGouDaiMa;
	private String qtLianXiRenDianHua;
	private String qtChuanZhen;
	private String qtLianXiDiZhi;
	private String qtDianZiYouXiang;
	private String qtYouZhengBianMa;
	private Date shenQingShiJian;
	private String suoXuXinXiNeiRongMiaoShu;
	private String suoXuXinXiYongTu;
	private String zhiDingTiGongFangShi;
	private String huoQuXinXiFangShi;
	private int isApplyfree;
	private String reply;
	private String searchNo;

	private List<YsqgkFile> fileList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShenQingLeiXing() {
		return shenQingLeiXing;
	}

	public void setShenQingLeiXing(int shenQingLeiXing) {
		this.shenQingLeiXing = shenQingLeiXing;
	}

	public String getGmXingMing() {
		return gmXingMing;
	}

	public void setGmXingMing(String gmXingMing) {
		this.gmXingMing = gmXingMing;
	}

	public String getGmGongZuoDanWei() {
		return gmGongZuoDanWei;
	}

	public void setGmGongZuoDanWei(String gmGongZuoDanWei) {
		this.gmGongZuoDanWei = gmGongZuoDanWei;
	}

	public String getGmZhengJianMingChen() {
		return gmZhengJianMingChen;
	}

	public void setGmZhengJianMingChen(String gmZhengJianMingChen) {
		this.gmZhengJianMingChen = gmZhengJianMingChen;
	}

	public String getGmZhengJianHaoMa() {
		return gmZhengJianHaoMa;
	}

	public void setGmZhengJianHaoMa(String gmZhengJianHaoMa) {
		this.gmZhengJianHaoMa = gmZhengJianHaoMa;
	}

	public String getGmLianXiDianHua() {
		return gmLianXiDianHua;
	}

	public void setGmLianXiDianHua(String gmLianXiDianHua) {
		this.gmLianXiDianHua = gmLianXiDianHua;
	}

	public String getGmChuanZhen() {
		return gmChuanZhen;
	}

	public void setGmChuanZhen(String gmChuanZhen) {
		this.gmChuanZhen = gmChuanZhen;
	}

	public String getGmLianXiDiZhi() {
		return gmLianXiDiZhi;
	}

	public void setGmLianXiDiZhi(String gmLianXiDiZhi) {
		this.gmLianXiDiZhi = gmLianXiDiZhi;
	}

	public String getGmDianZiYouXiang() {
		return gmDianZiYouXiang;
	}

	public void setGmDianZiYouXiang(String gmDianZiYouXiang) {
		this.gmDianZiYouXiang = gmDianZiYouXiang;
	}

	public String getGmYouZhengBianMa() {
		return gmYouZhengBianMa;
	}

	public void setGmYouZhengBianMa(String gmYouZhengBianMa) {
		this.gmYouZhengBianMa = gmYouZhengBianMa;
	}

	public String getQtMingCheng() {
		return qtMingCheng;
	}

	public void setQtMingCheng(String qtMingCheng) {
		this.qtMingCheng = qtMingCheng;
	}

	public String getQtFaRenDaiBiao() {
		return qtFaRenDaiBiao;
	}

	public void setQtFaRenDaiBiao(String qtFaRenDaiBiao) {
		this.qtFaRenDaiBiao = qtFaRenDaiBiao;
	}

	public String getQtLianXiRenXingMing() {
		return qtLianXiRenXingMing;
	}

	public void setQtLianXiRenXingMing(String qtLianXiRenXingMing) {
		this.qtLianXiRenXingMing = qtLianXiRenXingMing;
	}

	public String getQtJiGouDaiMa() {
		return qtJiGouDaiMa;
	}

	public void setQtJiGouDaiMa(String qtJiGouDaiMa) {
		this.qtJiGouDaiMa = qtJiGouDaiMa;
	}

	public String getQtLianXiRenDianHua() {
		return qtLianXiRenDianHua;
	}

	public void setQtLianXiRenDianHua(String qtLianXiRenDianHua) {
		this.qtLianXiRenDianHua = qtLianXiRenDianHua;
	}

	public String getQtChuanZhen() {
		return qtChuanZhen;
	}

	public void setQtChuanZhen(String qtChuanZhen) {
		this.qtChuanZhen = qtChuanZhen;
	}

	public String getQtLianXiDiZhi() {
		return qtLianXiDiZhi;
	}

	public void setQtLianXiDiZhi(String qtLianXiDiZhi) {
		this.qtLianXiDiZhi = qtLianXiDiZhi;
	}

	public String getQtDianZiYouXiang() {
		return qtDianZiYouXiang;
	}

	public void setQtDianZiYouXiang(String qtDianZiYouXiang) {
		this.qtDianZiYouXiang = qtDianZiYouXiang;
	}

	public String getQtYouZhengBianMa() {
		return qtYouZhengBianMa;
	}

	public void setQtYouZhengBianMa(String qtYouZhengBianMa) {
		this.qtYouZhengBianMa = qtYouZhengBianMa;
	}

	public Date getShenQingShiJian() {
		return shenQingShiJian;
	}

	public void setShenQingShiJian(Date shenQingShiJian) {
		this.shenQingShiJian = shenQingShiJian;
	}

	public String getSuoXuXinXiNeiRongMiaoShu() {
		return suoXuXinXiNeiRongMiaoShu;
	}

	public void setSuoXuXinXiNeiRongMiaoShu(String suoXuXinXiNeiRongMiaoShu) {
		this.suoXuXinXiNeiRongMiaoShu = suoXuXinXiNeiRongMiaoShu;
	}

	public String getSuoXuXinXiYongTu() {
		return suoXuXinXiYongTu;
	}

	public void setSuoXuXinXiYongTu(String suoXuXinXiYongTu) {
		this.suoXuXinXiYongTu = suoXuXinXiYongTu;
	}

	public String getZhiDingTiGongFangShi() {
		return zhiDingTiGongFangShi;
	}

	public void setZhiDingTiGongFangShi(String zhiDingTiGongFangShi) {
		this.zhiDingTiGongFangShi = zhiDingTiGongFangShi;
	}

	public String getHuoQuXinXiFangShi() {
		return huoQuXinXiFangShi;
	}

	public void setHuoQuXinXiFangShi(String huoQuXinXiFangShi) {
		this.huoQuXinXiFangShi = huoQuXinXiFangShi;
	}

	public int getIsApplyfree() {
		return isApplyfree;
	}

	public void setIsApplyfree(int isApplyfree) {
		this.isApplyfree = isApplyfree;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getSearchNo() {
		return searchNo;
	}

	public void setSearchNo(String searchNo) {
		this.searchNo = searchNo;
	}

	public List<YsqgkFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<YsqgkFile> fileList) {
		this.fileList = fileList;
	}

	private String sqsjstr;

	public String getSqsjstr() {
		if (this.shenQingShiJian == null) {
			return "";
		} else {
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sDateFormat.format(this.shenQingShiJian);
		}
	}

	public void setSqsjstr(String sqsjstr) {
		this.sqsjstr = sqsjstr;
	}

	private String tgfsstr;
	private String hqfsstr;

	public String getTgfsstr() {
		String[] strArr = zhiDingTiGongFangShi.split(",");
		if (strArr.length != 4) {
			return "";
		} else {
			String r = "";
			if (strArr[0].equals("1")) {
				r=r+" "+"纸质";
			}
			if (strArr[1].equals("1")) {
				r=r+" "+"电子邮件";
			}
			if (strArr[2].equals("1")) {
				r=r+" "+"光盘";
			}
			if (strArr[3].equals("1")) {
				r=r+" "+"磁盘";
			}
			return r;
		}
		
	}

	public void setTgfsstr(String tgfsstr) {
		this.tgfsstr = tgfsstr;
	}

	public String getHqfsstr() {
		String[] strArr = huoQuXinXiFangShi.split(",");
		if (strArr.length != 5) {
			return "";
		} else {
			String r = "";
			if (strArr[0].equals("1")) {
				r=r+" "+"邮寄";
			}
			if (strArr[1].equals("1")) {
				r=r+" "+"快递";
			}
			if (strArr[2].equals("1")) {
				r=r+" "+"电子邮件";
			}
			if (strArr[3].equals("1")) {
				r=r+" "+"传真";
			}
			if (strArr[4].equals("1")) {
				r=r+" "+"自行领取/当场阅读/抄条";
			}
			return r;
		}
	}

	public void setHqfsstr(String hqfsstr) {
		this.hqfsstr = hqfsstr;
	}

}
