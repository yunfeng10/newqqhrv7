package com.jeecms.cms.dao.assist;

import java.math.BigInteger;
import java.util.List;

import com.jeecms.cms.entity.assist.MisResult;
import com.jeecms.common.page.Pagination;

public interface MisResultDao {
	
	public MisResult findByDataId(String dataId);
	
	public List<MisResult> findByParam(String fl,String param);
	
	public Pagination getWebPage(String fl, String param, int pageNo, int pageSize);
	
	public void deleteByDataId(String dataId);
	
	public void saveEntity(MisResult entity);
	
	public BigInteger countTotalValue(String fl);
	
	public BigInteger countMonthValue(String fl);
	
	public BigInteger countYearValue(String fl);

}
