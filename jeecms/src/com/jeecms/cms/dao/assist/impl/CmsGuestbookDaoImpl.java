package com.jeecms.cms.dao.assist.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.dao.assist.CmsGuestbookDao;
import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class CmsGuestbookDaoImpl extends
		HibernateBaseDao<CmsGuestbook, Integer> implements CmsGuestbookDao {
	public Pagination getPage(Integer siteId, Integer ctgId,Integer userId,Boolean recommend,
			Boolean checked, boolean asc, boolean cacheable, int pageNo,
			int pageSize) {
		Finder f = createFinder(siteId, ctgId,userId,recommend, checked, asc,
				cacheable);
		return find(f, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<CmsGuestbook> getList(Integer siteId, Integer ctgId,
			Boolean recommend, Boolean checked, boolean desc,
			boolean cacheable, int first, int max) {
		Finder f = createFinder(siteId, ctgId,null,recommend, checked, desc,
				cacheable);
		f.setFirstResult(first);
		f.setMaxResults(max);
		return find(f);
	}

	private Finder createFinder(Integer siteId, Integer ctgId,Integer userId,
			Boolean recommend, Boolean checked, boolean desc, boolean cacheable) {
		Finder f = Finder.create("from CmsGuestbook bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if (ctgId != null) {
			f.append(" and bean.ctg.id =:ctgId");
			f.setParam("ctgId", ctgId);
		}
		if (userId != null) {
			f.append(" and bean.member.id=:userId");
			f.setParam("userId", userId);
		}
		if (recommend != null) {
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if (desc) {
			f.append(" order by bean.id desc");
		} else {
			f.append(" order by bean.id asc");
		}
		f.setCacheable(cacheable);
		return f;
	}
	public int countGuestBook(Integer ctgId,String type) {
		
		/*Query query = getSession().createQuery(hql);
		finder.setParamsToQuery(query);
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		return ((Number) query.iterate().next()).intValue();*/
		String sqlStr = "select count(bean.id) from CmsGuestbook bean where bean.ctg="+ctgId;
		if("reply".equals(type)){
			sqlStr=sqlStr+" and bean.createTime is not null";
		}else if("notreply".equals(type)){
			sqlStr=sqlStr+" and bean.createTime is null";
		}
		Query query =getSession().createQuery(sqlStr);
		int result = ((Number) query.uniqueResult()).intValue();
		return result;
	}

	public CmsGuestbook findById(Integer id) {
		CmsGuestbook entity = get(id);
		return entity;
	}

	public CmsGuestbook save(CmsGuestbook bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsGuestbook deleteById(Integer id) {
		CmsGuestbook entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsGuestbook> getEntityClass() {
		return CmsGuestbook.class;
	}

	private Finder createFinder(Integer ctgId, String xm, String cxm){
		Finder f = Finder.create("from CmsGuestbook bean where 1=1");
		if (!StringUtils.isEmpty(ctgId)) {
			f.append(" and bean.ctg.id =:ctgId");
			f.setParam("ctgId", ctgId);
		}
		if (!StringUtils.isEmpty(xm)) {
			f.append(" and bean.ext.xm=:xm");
			f.setParam("xm", xm);
		}
		if (!StringUtils.isEmpty(cxm)) {
			f.append(" and bean.ext.cxm=:cxm");
			f.setParam("cxm", cxm);
		}
		f.append(" order by bean.id desc");
		return f;
	}
	@Override
	public Pagination getWebPage(Integer ctgId, String xm, String cxm, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Finder f = createFinder(ctgId,xm,cxm);
		return find(f, pageNo, pageSize);
	}
}