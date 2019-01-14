package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.TransferDataDao;
import com.jeecms.cms.entity.main.TransferData;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

@Repository
public class TransferDataDaoImpl extends HibernateBaseDao<TransferData, Integer> implements TransferDataDao {

	@Override
	public TransferData save(TransferData entity) {
		// TODO Auto-generated method stub
		getSession().save(entity);
		return entity;
	}

	@Override
	public List<TransferData> query(Integer tcid,Integer oldtype, Integer oldid) {
		Finder f = Finder.create("from TransferData bean where docstatus > 0");
		f.append(" and bean.newchannelid=:newchannelid ");
		f.setParam("newchannelid", tcid);
		
		if(oldtype!=null){
			f.append(" and bean.doctype=:doctype ");
			f.setParam("doctype", oldtype);
		}
		if (oldid != null) {
			f.append(" and bean.docid=:docid ");
			f.setParam("docid", oldid);
		}
		f.append(" order by bean.docid asc");
		return find(f);
	}

	@Override
	protected Class<TransferData> getEntityClass() {
		// TODO Auto-generated method stub
		return TransferData.class;
	}

}
