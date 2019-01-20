package com.jeecms.cms.manager.assist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.assist.CmsGuestbookExt;
import com.jeecms.cms.entity.assist.SiteFileCount;
import com.jeecms.cms.entity.main.Ysqgk;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;

public interface CmsGuestbookMng {
	public Pagination getPage(Integer siteId, Integer ctgId,Integer userId, Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo,
			int pageSize);

	@Transactional(readOnly = true)
	public List<CmsGuestbook> getList(Integer siteId, Integer ctgId,
			Boolean recommend, Boolean checked, boolean desc,
			boolean cacheable, int first, int max);

	public CmsGuestbook findById(Integer id);

	public CmsGuestbook save(CmsGuestbook bean, CmsGuestbookExt ext,
			Integer ctgId, String ip);

	public CmsGuestbook save(CmsUser member, Integer siteId, Integer ctgId,
			String ip, String title, String content, String email,
			String phone, String qq,String grxxgk,String xm,String wtfsd,String txdz,String yb,String xjsfgk,String cxm,Integer djcs);

	public CmsGuestbook update(CmsGuestbook bean, CmsGuestbookExt ext,
			Integer ctgId);

	public CmsGuestbook deleteById(Integer id);

	public CmsGuestbook[] deleteByIds(Integer[] ids);

	public CmsGuestbook[] checkByIds(Integer[] ids,CmsUser checkUser,Boolean checkStatus);
	
	public Pagination getWebPage(Integer ctgId,String xm,String cxm, int pageNo,int pageSize);
	
	public Ysqgk saveYsqgk(Ysqgk entity);
	
	public Ysqgk updateYsqgk(Ysqgk entity,String[] attachmentPaths, String[] attachmentNames);
	
	public Pagination queryYsqgkPagination(int pageNo,int pageSize);
	
	public Ysqgk queryYsqgkById(Integer id);
	
	public void deleteYsqgkById(Integer id);
	
	public void countInformation(HttpServletRequest request,SiteFileCount count);
}