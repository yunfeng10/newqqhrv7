package com.jeecms.cms.dao.assist;

import java.util.List;

import com.jeecms.cms.entity.assist.MisStatus1;
import com.jeecms.common.page.Pagination;

public interface MisStatus1Dao {

	public MisStatus1 findByDataId(String dataId);
	
	public List<MisStatus1> findByParam(String fl,String param);
	
	public Pagination getWebPage(String fl, String param, int pageNo, int pageSize);
}
