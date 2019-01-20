package com.jeecms.cms.manager.assist.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.CmsGuestbookDao;
import com.jeecms.cms.dao.main.YsqgkDao;
import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.assist.CmsGuestbookExt;
import com.jeecms.cms.entity.assist.SiteFileCount;
import com.jeecms.cms.entity.main.Ysqgk;
import com.jeecms.cms.entity.main.YsqgkFile;
import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
import com.jeecms.cms.manager.assist.CmsGuestbookExtMng;
import com.jeecms.cms.manager.assist.CmsGuestbookMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.web.util.CmsUtils;

@Service
@Transactional
public class CmsGuestbookMngImpl implements CmsGuestbookMng {
	@Transactional(readOnly = true)
	public Pagination getPage(Integer siteId, Integer ctgId,Integer userId, Boolean recommend,
			Boolean checked, boolean desc, boolean cacheable, int pageNo,
			int pageSize) {
		return dao.getPage(siteId, ctgId,userId, recommend, checked, desc, cacheable,
				pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List<CmsGuestbook> getList(Integer siteId, Integer ctgId,
			Boolean recommend, Boolean checked, boolean desc,
			boolean cacheable, int first, int max) {
		return dao.getList(siteId, ctgId, recommend, checked, desc, cacheable,
				first, max);
	}

	@Transactional(readOnly = true)
	public CmsGuestbook findById(Integer id) {
		CmsGuestbook entity = dao.findById(id);
		return entity;
	}

	public CmsGuestbook save(CmsGuestbook bean, CmsGuestbookExt ext,
			Integer ctgId, String ip) {
		bean.setCtg(cmsGuestbookCtgMng.findById(ctgId));
		bean.setIp(ip);
		bean.setCreateTime(new Timestamp(System.currentTimeMillis()));
		bean.init();
		dao.save(bean);
		cmsGuestbookExtMng.save(ext, bean);
		return bean;
	}

	public CmsGuestbook save(CmsUser member, Integer siteId, Integer ctgId,
			String ip, String title, String content, String email,
			String phone, String qq,
			String grxxgk,String xm,String wtfsd,String txdz,String yb,String xjsfgk,String cxm,Integer djcs) {
		CmsGuestbook guestbook = new CmsGuestbook();
		guestbook.setMember(member);
		guestbook.setSite(cmsSiteMng.findById(siteId));
		guestbook.setIp(ip);
		CmsGuestbookExt ext = new CmsGuestbookExt();
		ext.setTitle(title);
		ext.setContent(content);
		ext.setEmail(email);
		ext.setPhone(phone);
		ext.setQq(qq);
		ext.setGrxxgk(grxxgk);
		ext.setXm(xm);
		ext.setWtfsd(wtfsd);
		ext.setTxdz(txdz);
		ext.setYb(yb);
		ext.setXjsfgk(xjsfgk);
		ext.setCxm(cxm);
		ext.setDjcs(djcs);
		return save(guestbook, ext, ctgId, ip);
	}

	public CmsGuestbook update(CmsGuestbook bean, CmsGuestbookExt ext,
			Integer ctgId) {
		Updater<CmsGuestbook> updater = new Updater<CmsGuestbook>(bean);
		bean = dao.updateByUpdater(updater);
		bean.setCtg(cmsGuestbookCtgMng.findById(ctgId));
		cmsGuestbookExtMng.update(ext);
		return bean;
	}

	public CmsGuestbook deleteById(Integer id) {
		CmsGuestbook bean = dao.deleteById(id);
		return bean;
	}

	public CmsGuestbook[] deleteByIds(Integer[] ids) {
		CmsGuestbook[] beans = new CmsGuestbook[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public CmsGuestbook[] checkByIds(Integer[] ids,CmsUser checkUser,Boolean checkStatus) {
		CmsGuestbook[] beans = new CmsGuestbook[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = checkById(ids[i],checkUser,checkStatus);
		}
		return beans;
	}
	
	private CmsGuestbook checkById(Integer id,CmsUser checkUser,Boolean checkStatus){
		CmsGuestbook bean=findById(id);
		Updater<CmsGuestbook> updater = new Updater<CmsGuestbook>(bean);
		bean = dao.updateByUpdater(updater);
		if(checkStatus){
			bean.setAdmin(checkUser);
		}
		bean.setChecked(checkStatus);
		return bean;
	}
	

	private CmsGuestbookCtgMng cmsGuestbookCtgMng;
	private CmsGuestbookExtMng cmsGuestbookExtMng;
	private CmsSiteMng cmsSiteMng;
	private CmsGuestbookDao dao;

	@Autowired
	public void setDao(CmsGuestbookDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setCmsGuestbookExtMng(CmsGuestbookExtMng cmsGuestbookExtMng) {
		this.cmsGuestbookExtMng = cmsGuestbookExtMng;
	}

	@Autowired
	public void setCmsGuestbookCtgMng(CmsGuestbookCtgMng cmsGuestbookCtgMng) {
		this.cmsGuestbookCtgMng = cmsGuestbookCtgMng;
	}

	@Autowired
	public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
		this.cmsSiteMng = cmsSiteMng;
	}

	/**
	 *前台 新增
	 */
	@Override
	public Pagination getWebPage(Integer ctgId, String xm, String cxm, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getWebPage(ctgId,xm,cxm,pageNo,pageSize);
	}

	@Autowired
	private YsqgkDao ysqgkDao;
	
	@Override
	public Ysqgk saveYsqgk(Ysqgk entity) {
		// TODO Auto-generated method stub
		ysqgkDao.save(entity);
		return entity;
	}

	@Override
	public Pagination queryYsqgkPagination(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return ysqgkDao.getPage(pageNo, pageSize);
	}

	@Override
	public Ysqgk queryYsqgkById(Integer id) {
		// TODO Auto-generated method stub
		
		return ysqgkDao.findById(id);
	}

	@Override
	public void deleteYsqgkById(Integer id) {
		// TODO Auto-generated method stub
		ysqgkDao.delete(id);
	}

	@Override
	public Ysqgk updateYsqgk(Ysqgk entity,String[] attachmentPaths, String[] attachmentNames) {
		List<YsqgkFile> fileList = new ArrayList<YsqgkFile>();
		if (attachmentPaths != null && attachmentPaths.length > 0) {
			for (int i = 0, len = attachmentPaths.length; i < len; i++) {
				if (attachmentPaths[i]!="") {
					YsqgkFile file = new YsqgkFile();
					file.setId(entity.getId());
					file.setAttachmentNames(attachmentNames[i]);
					file.setAttachmentPaths(attachmentPaths[i]);
					fileList.add(file);
				}
			}
		}
		entity.setFileList(fileList);
		Updater<Ysqgk> updater = new Updater<Ysqgk>(entity);
		entity = ysqgkDao.updateByUpdater(updater);
		return entity;
	}

	@Override
	public void countInformation(HttpServletRequest request, SiteFileCount count) {
		// TODO Auto-generated method stub
		CmsSite site = CmsUtils.getSite(request);
		String year = count.getYear()+"";
		String path = site.getUploadPath();
		String cpath = request.getSession().getServletContext().getRealPath("");
		File baseFile = new File(cpath+File.separator+path);
		if(baseFile.exists()){
			File[] baseList = baseFile.listFiles();
			for(File baseItem :baseList){
				if(baseItem.isDirectory() && baseItem.getName().startsWith(year)){
					File[] itemList = baseItem.listFiles();
					for(File item : itemList){
						count.countFile(item.getName());
					}
				}
			}
		}
	}

}