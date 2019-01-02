package com.jeecms.cms.manager.assist;

import org.springframework.ui.ModelMap;

import com.jeecms.cms.entity.main.Ysqgk;

public interface YsqgkMng {
	
	public Ysqgk save(Ysqgk entity);
	
	public Ysqgk findById(int id);
	
	public int queryYsqgkId(int queryType, String queryName, String queryJgdm, String querySearchNO);
	
	public void setYsqgkData(int id, ModelMap model);

}
