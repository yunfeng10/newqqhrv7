package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.TransferData;

public interface TransferDataDao {

	public TransferData save(TransferData entity);
	
	public List<TransferData> query(Integer tcid,Integer oldtype,Integer oldid);
}
