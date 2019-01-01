package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.Ysqgk;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface YsqgkDao {
	
	public Pagination getPage(int pageNo, int pageSize);
	
	public Ysqgk findById(int id);
	
	public Ysqgk save(Ysqgk entity);
	
	public void delete(int id);
	
	public Ysqgk updateByUpdater(Updater<Ysqgk> updater);
}
