package com.jeecms.cms.dao.assist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.dao.assist.MisResultDao;
import com.jeecms.cms.entity.assist.MisResult;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class MisResultDaoImpl extends HibernateBaseDao<MisResult, String>  implements MisResultDao {

	@Override
	public MisResult findByDataId(String dataId) {
		// TODO Auto-generated method stub
		MisResult entity = get(dataId);
		return entity;
	}

	@Override
	public List<MisResult> findByParam(String fl,String param) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from MisResult bean where 1=1");
		if(fl!=null){
			f.append(" and bean.fl=:fl ");
			f.setParam("fl", fl);
		}
		if (param != null) {
			f.append(" and (bean.bh=:bh or bean.xmmc=:xmmc)");
			f.setParam("dataid", param);
			f.setParam("xmmc", param);
		}
		f.append(" order by bean.dataid asc");
		return find(f);
	}

	@Override
	protected Class<MisResult> getEntityClass() {
		// TODO Auto-generated method stub
		return MisResult.class;
	}

	@Override
	public Pagination getWebPage(String fl, String param, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = createFinder(fl,param);
		return find(f, pageNo, pageSize);
	}
	
	private Finder createFinder(String fl, String param){
		Finder f = Finder.create("from MisResult bean where 1=1");
		if (!StringUtils.isEmpty(fl)) {
			f.append(" and bean.fl =:fl");
			f.setParam("fl", fl);
		}
		if (!StringUtils.isEmpty(param)) {
			f.append(" and (bean.bh =:bh or bean.xmmc=:xmmc)");
			f.setParam("bh", param);
			f.setParam("xmmc", param);
		}
		f.append(" order by bean.dataid desc");
		return f;
	}

	@Override
	public void deleteByDataId(String dataId) {
		// TODO Auto-generated method stub
		MisResult entity = super.get(dataId);
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	@Override
	public void saveEntity(MisResult entity) {
		// TODO Auto-generated method stub
		getSession().save(entity);
	}
}
