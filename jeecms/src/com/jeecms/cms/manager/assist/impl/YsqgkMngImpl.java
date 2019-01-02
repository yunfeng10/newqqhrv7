package com.jeecms.cms.manager.assist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.jeecms.cms.dao.main.YsqgkDao;
import com.jeecms.cms.entity.main.Ysqgk;
import com.jeecms.cms.manager.assist.YsqgkMng;

@Service
@Transactional
public class YsqgkMngImpl implements YsqgkMng {

	@Autowired
	private YsqgkDao ysqgkDao;
	
	@Override
	public Ysqgk save(Ysqgk entity) {
		// TODO Auto-generated method stub
		return ysqgkDao.save(entity);
	}

	@Override
	public Ysqgk findById(int id) {
		// TODO Auto-generated method stub
		return ysqgkDao.findById(id);
		
	}

	@Override
	public int queryYsqgkId(int queryType, String queryName, String queryJgdm, String querySearchNO) {
		// TODO Auto-generated method stub
		return ysqgkDao.queryYsqgkId(queryType,queryName,queryJgdm,querySearchNO);
	}

	@Override
	public void setYsqgkData(int id, ModelMap model) {
		Ysqgk ysqgk = ysqgkDao.findById(id);
		if(ysqgk!=null){
			model.addAttribute("shenQingLeiXing", ysqgk.getShenQingLeiXing());
			if(ysqgk.getShenQingLeiXing()==0){
				model.addAttribute("shenQingLeiXingTxt", "公民");
			}else{
				model.addAttribute("shenQingLeiXingTxt", "机构其他组织");
			}
			model.addAttribute("gmXingMing", ysqgk.getGmXingMing());
			model.addAttribute("gmGongZuoDanWei", ysqgk.getGmGongZuoDanWei());
			model.addAttribute("gmZhengJianMingChen", ysqgk.getGmZhengJianMingChen());
			model.addAttribute("gmZhengJianHaoMa", ysqgk.getGmZhengJianHaoMa());
			model.addAttribute("gmLianXiDianHua", ysqgk.getGmLianXiDianHua());
			model.addAttribute("gmChuanZhen", ysqgk.getGmChuanZhen());
			model.addAttribute("gmLianXiDiZhi", ysqgk.getGmLianXiDiZhi());
			model.addAttribute("gmDianZiYouXiang", ysqgk.getGmDianZiYouXiang());
			model.addAttribute("gmYouZhengBianMa", ysqgk.getGmYouZhengBianMa());
			model.addAttribute("qtMingCheng", ysqgk.getQtMingCheng());
			model.addAttribute("qtFaRenDaiBiao", ysqgk.getQtFaRenDaiBiao());
			model.addAttribute("qtLianXiRenXingMing", ysqgk.getQtLianXiRenXingMing());
			model.addAttribute("qtJiGouDaiMa", ysqgk.getQtJiGouDaiMa());
			model.addAttribute("qtLianXiRenDianHua", ysqgk.getQtLianXiRenDianHua());
			model.addAttribute("qtChuanZhen", ysqgk.getQtChuanZhen());
			model.addAttribute("qtLianXiDiZhi", ysqgk.getQtLianXiDiZhi());
			model.addAttribute("qtDianZiYouXiang", ysqgk.getQtDianZiYouXiang());
			model.addAttribute("qtYouZhengBianMa", ysqgk.getQtYouZhengBianMa());
			model.addAttribute("sqsjstr", ysqgk.getSqsjstr());
			model.addAttribute("suoXuXinXiNeiRongMiaoShu", ysqgk.getSuoXuXinXiNeiRongMiaoShu());
			model.addAttribute("suoXuXinXiYongTu", ysqgk.getSuoXuXinXiYongTu());
			model.addAttribute("tgfsstr", ysqgk.getHqfsstr());
			model.addAttribute("hqfsstr", ysqgk.getHqfsstr());
			model.addAttribute("reply", ysqgk.getReply());
			model.addAttribute("fileList", ysqgk.getFileList());
		}
		
	}

}
