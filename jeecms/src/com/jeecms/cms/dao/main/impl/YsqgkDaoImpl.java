package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.YsqgkDao;
import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.main.Ysqgk;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class YsqgkDaoImpl extends HibernateBaseDao<Ysqgk, Integer> implements YsqgkDao {

	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("select  bean from Ysqgk bean ");
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Ysqgk findById(int id) {
		// TODO Auto-generated method stub
		Ysqgk entity = get(id);
		return entity;
	}

	@Override
	protected Class<Ysqgk> getEntityClass() {
		// TODO Auto-generated method stub
		return Ysqgk.class;
	}

	@Override
	public Ysqgk save(Ysqgk entity) {
		// TODO Auto-generated method stub
		getSession().save(entity);
		return entity;
	}

	@Override
	public void delete(int id) {
		Ysqgk entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		//return entity;
		
	}

	@Override
	public int queryYsqgkId(int queryType, String queryName, String queryJgdm, String querySearchNO) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from Ysqgk bean where 1=1");
		f.append(" and bean.shenQingLeiXing=:shenQingLeiXing ");
		f.setParam("shenQingLeiXing", queryType);
		if(queryType==0){
			f.append(" and bean.gmXingMing=:gmXingMing ");
			f.setParam("gmXingMing", queryName);
		}else{
			f.append(" and bean.qtJiGouDaiMa=:qtJiGouDaiMa ");
			f.setParam("qtJiGouDaiMa", queryJgdm);
		}
		f.append(" and bean.searchNo=:searchNo ");
		f.setParam("searchNo", querySearchNO);
		List<Ysqgk> list = find(f);
		if(list.size()==0){
			return 0;
		}else{
			return list.get(0).getId();
		}
		
	}

}
