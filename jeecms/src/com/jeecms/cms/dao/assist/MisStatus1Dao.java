package com.jeecms.cms.dao.assist;

import java.math.BigInteger;
import java.util.List;

import com.jeecms.cms.entity.assist.MisStatus1;
import com.jeecms.common.page.Pagination;

public interface MisStatus1Dao {

	public MisStatus1 findByDataId(String dataId);
	
	public List<MisStatus1> findByParam(String fl,String param);
	
	public Pagination getWebPage(String fl, String param, int pageNo, int pageSize);
	
	public void deleteByDataId(String dataId);
	
	public void saveEntity(MisStatus1 entity);
	
	public BigInteger countTotalReceive(String fl);
	
	public BigInteger countTotalComplete(String fl);
	
	public BigInteger countLastReceive(String fl);
	
	public BigInteger countLastComplete(String fl);
	
	public BigInteger countYearReceive(String fl);
	
	public BigInteger countYearComplete(String fl);
}
