package com.jeecms.cms.manager.assist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
