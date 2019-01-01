package com.jeecms.cms.dao.assist;

import java.util.List;

import com.jeecms.cms.entity.assist.MisResult;
import com.jeecms.common.page.Pagination;

public interface MisResultDao {
	
	public MisResult findByDataId(String dataId);
	
	public List<MisResult> findByParam(String fl,String param);
	
	public Pagination getWebPage(String fl, String param, int pageNo, int pageSize);

}
