package com.jeecms.cms.dao.assist.impl;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jeecms.cms.dao.assist.MisResultDao;
import com.jeecms.cms.entity.assist.MisResult;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class MisResultDaoImpl extends HibernateBaseDao<MisResult, String> implements MisResultDao {

	@Override
	public MisResult findByDataId(String dataId) {
		// TODO Auto-generated method stub
		MisResult entity = get(dataId);
		return entity;
	}

	@Override
	public List<MisResult> findByParam(String fl, String param) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("from MisResult bean where 1=1");
		if (fl != null) {
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
		Finder f = createFinder(fl, param);
		return find(f, pageNo, pageSize);
	}

	private Finder createFinder(String fl, String param) {
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

	@Override
	public BigInteger countTotalValue(String fl) {
		// TODO Auto-generated method stub
		String sql = "select count(1) from misresult a where a.blzt='0'";

		if (!StringUtils.isEmpty(fl)) {
			sql = sql + " and fl='" + fl + "'";
		}

		Query query = getSession().createSQLQuery(sql);
		List result = query.list();
		return (BigInteger) result.get(0);
	}

	@Override
	public BigInteger countMonthValue(String fl) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		if (month == 0) {
			year = year - 1;
			month = 12;
		}
		String sql = "select count(1) from misresult a where a.blzt='0' and date_format(pzsj,'%Y') =" + year
				+ " and date_format(pzsj,'%m')=" + month;

		if (!StringUtils.isEmpty(fl)) {
			sql = sql + " and fl='" + fl + "'";
		}

		Query query = getSession().createSQLQuery(sql);
		List result = query.list();
		return (BigInteger) result.get(0);
	}

	@Override
	public BigInteger countYearValue(String fl) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String sql = "select count(1) from misresult a where a.blzt='0' and date_format(pzsj,'%Y') =" + year;

		if (!StringUtils.isEmpty(fl)) {
			sql = sql + " and fl='" + fl + "'";
		}

		Query query = getSession().createSQLQuery(sql);
		List result = query.list();
		return (BigInteger) result.get(0);
	}
}
