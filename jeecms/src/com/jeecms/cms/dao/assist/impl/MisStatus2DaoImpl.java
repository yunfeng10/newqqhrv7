package com.jeecms.cms.dao.assist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.assist.MisStatus2Dao;
import com.jeecms.cms.entity.assist.MisStatus2;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

@Repository
public class MisStatus2DaoImpl extends HibernateBaseDao<MisStatus2, String>  implements MisStatus2Dao {

	@Override
	public List<MisStatus2> findByDataId(String dataId) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from MisStatus2 bean where 1=1");
		if(dataId!=null){
			f.append(" and bean.refid=:refid ");
			f.setParam("refid", dataId);
		}
		f.append(" order by bean.blsj asc");
		return find(f);
	}

	@Override
	protected Class<MisStatus2> getEntityClass() {
		// TODO Auto-generated method stub
		return MisStatus2.class;
	}

	@Override
	public void deleteByDataId(String dataId) {
		// TODO Auto-generated method stub
		MisStatus2 entity = super.get(dataId);
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	@Override
	public void saveEntity(MisStatus2 entity) {
		// TODO Auto-generated method stub
		getSession().save(entity);
	}

}
