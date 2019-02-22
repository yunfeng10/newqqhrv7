package com.jeecms.cms.action.admin.assist;

import static com.jeecms.cms.Constants.INDEXTITLE_PATH;
import static com.jeecms.common.page.SimplePage.cpn;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.cms.entity.assist.CmsGuestbookExt;
import com.jeecms.cms.entity.assist.SiteFileCount;
import com.jeecms.cms.entity.main.Ysqgk;
import com.jeecms.cms.manager.assist.CmsFileMng;
import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
import com.jeecms.cms.manager.assist.CmsGuestbookMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.common.util.StrUtils;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.Ftp;
import com.jeecms.core.manager.CmsLogMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.manager.DbFileMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;

@Controller
public class CmsGuestbookAct {
	private static final Logger log = LoggerFactory
			.getLogger(CmsGuestbookAct.class);
	@Autowired
	private DbFileMng dbFileMng;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private CmsFileMng fileMng;
	
	@RequiresPermissions("sitefilecount:count")
	@RequestMapping("/sitefilecount/count.do")
	public String siteFileCount(Integer year,HttpServletRequest request,ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		Integer countYear = null;
		if(year == null){
			Calendar calendar = Calendar.getInstance();
			countYear = calendar.get(calendar.YEAR);
		}else{
			countYear=year;
			
		}
		SiteFileCount count = new SiteFileCount(countYear);
		manager.countInformation(request, count);
		model.addAttribute("year", count.getYear());
		model.addAttribute("totalFile", count.getTotalFile());
		model.addAttribute("picFile", count.getPicFile());
		model.addAttribute("docFile", count.getDocFile());
		model.addAttribute("videoFile", count.getVideoFile());
		model.addAttribute("otherFile", count.getOtherFile());
		return "sitefilecount/count";
	}
	@RequiresPermissions("indextitle:title")
	@RequestMapping("/indextitle/title.do")
	public String indexTitle(HttpServletRequest request,ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		String fpath = site.getContextPath() == null ? "" : site.getContextPath();
		fpath = fpath+File.separator+INDEXTITLE_PATH;
		model.addAttribute("indextitle", IndexTitleUtil.getTitle());
		return "indextitle/title";
	}
	@RequiresPermissions("indextitle:o_savetitle")
	@RequestMapping("/indextitle/o_savetitle.do")
	public String indexTitle(String indextitle,HttpServletRequest request,ModelMap model){
		//System.out.println(indextitle);
		String cpath = request.getSession().getServletContext().getRealPath("");
		cpath = cpath+File.separator+INDEXTITLE_PATH;
		IndexTitleUtil.saveTitle(indextitle);
		return "redirect:title.do";
	}
	@RequiresPermissions("importdata:import")
	@RequestMapping("/importdata/import.do")
	public String importData(HttpServletRequest request,ModelMap model){
		CmsSite site = CmsUtils.getSite(request);

		return "importdata/importdata";
	}
	@RequiresPermissions("importdata:dealFile")
	@RequestMapping("/importdata/dealFile.do")
	public void generateTags(String filepath,HttpServletRequest request,HttpServletResponse response) throws JSONException {
		JSONObject json = new JSONObject();
		String ctx = request.getSession().getServletContext().getRealPath("");
		String filePath = ctx+File.separator+filepath;
		String message = manager.importExcel(filePath);
		json.put("message", message);
		ResponseUtils.renderJson(response, json.toString());
	}
	@RequiresPermissions("importdata:o_upload_media")
	@RequestMapping("/importdata/o_upload_media.do")
	public String uploadMedia(
			@RequestParam(value = "mediaFile", required = false) MultipartFile file,
			String filename, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		String origName = file.getOriginalFilename();
		String ext = FilenameUtils.getExtension(origName).toLowerCase(
				Locale.ENGLISH);
		// TODO 检查允许上传的后缀
		try {
			String fileUrl;
			String ctx = request.getContextPath();
			fileUrl = fileRepository.storeByExt2(site.getUploadPath(),
					ext, file);
			// 加上部署路径
			fileUrl = ctx + fileUrl;
			
			/*cmsUserMng.updateUploadSize(user.getId(), Integer.parseInt(String.valueOf(file.getSize()/1024)));
			if(fileMng.findByPath(fileUrl)!=null){
				fileMng.saveFileByPath(fileUrl, origName, false);
			}*/
			model.addAttribute("mediaPath", fileUrl);
			model.addAttribute("mediaExt", ext);
		} catch (IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute("error", e.getMessage());
			log.error("upload file error!", e);
		}
		return "importdata/media_iframe";
	}
	
	@RequiresPermissions("ysqgk:v_list")
	@RequestMapping("/ysqgk/v_list.do")
	public String ysqgkList(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Pagination pagination = manager.queryYsqgkPagination(cpn(pageNo),CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		return "ysqgk/list";
	}
	
	@RequiresPermissions("ysqgk:v_edit")
	@RequestMapping("/ysqgk/v_edit.do")
	public String ysqgkEdit(Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Ysqgk bean = manager.queryYsqgkById(id);
		model.addAttribute("ysqgk", bean);
		model.addAttribute("pageNo", pageNo);
		return "ysqgk/edit";
	}

	@RequiresPermissions("ysqgk:o_update")
	@RequestMapping("/ysqgk/o_update.do")
	public String ysqgkUpdate(Ysqgk bean,String[] attachmentPaths, String[] attachmentNames,HttpServletRequest request, ModelMap model) {
		bean.setIsApplyfree(1);
		manager.updateYsqgk(bean,attachmentPaths,attachmentNames);
		return "redirect:v_list.do";
	}
	
	@RequiresPermissions("ysqgk:o_delete")
	@RequestMapping("/ysqgk/o_delete.do")
	public String ysqgkDelete(Integer id, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		manager.deleteYsqgkById(id);
		return ysqgkList(pageNo, request,model);
	}
	
	@RequiresPermissions("guestbook:v_list")
	@RequestMapping("/guestbook/v_list.do")
	public String list(Integer queryCtgId, Boolean queryRecommend,
			Boolean queryChecked, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Pagination pagination = manager.getPage(site.getId(),queryCtgId,null,
				queryRecommend, queryChecked, true, false, cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("queryCtgId", queryCtgId);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		return "guestbook/list";
	}
	
	@RequiresPermissions("guestbook:v_add")
	@RequestMapping("/guestbook/v_add.do")
	public String add(Integer queryCtgId,HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsGuestbookCtg> ctgList = cmsGuestbookCtgMng
				.getList(site.getId());
		model.addAttribute("ctgList", ctgList);
		model.addAttribute("queryCtgId", queryCtgId);
		return "guestbook/add";
	}

	@RequiresPermissions("guestbook:v_edit")
	@RequestMapping("/guestbook/v_edit.do")
	public String edit(Integer queryCtgId,Integer id, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsGuestbook cmsGuestbook = manager.findById(id);
		List<CmsGuestbookCtg> ctgList = cmsGuestbookCtgMng
				.getList(site.getId());

		model.addAttribute("cmsGuestbook", cmsGuestbook);
		model.addAttribute("ctgList", ctgList);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("queryCtgId", queryCtgId);
		return "guestbook/edit";
	}

	@RequiresPermissions("guestbook:o_save")
	@RequestMapping("/guestbook/o_save.do")
	public String save(CmsGuestbook bean, CmsGuestbookExt ext, Integer ctgId,Integer queryCtgId,String[] attachmentPaths, String[] attachmentNames,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String ip = RequestUtils.getIpAddr(request);
		bean = manager.save(bean, ext, ctgId, ip,attachmentPaths,attachmentNames);
		log.info("save CmsGuestbook id={}", bean.getId());
		cmsLogMng.operating(request, "cmsGuestbook.log.save", "id="
				+ bean.getId() + ";title=" + bean.getTitle());
		return "redirect:v_list.do?queryCtgId="+queryCtgId;
	}

	@RequiresPermissions("guestbook:o_update")
	@RequestMapping("/guestbook/o_update.do")
	public String update(Integer queryCtgId, Boolean queryRecommend,
			Boolean queryChecked, String oldreply,CmsGuestbook bean, CmsGuestbookExt ext,
			Integer ctgId, Integer pageNo,String[] attachmentPaths, String[] attachmentNames, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Date now=new Date();
		if(StringUtils.isNotBlank(ext.getReply())&&!oldreply.equals(ext.getReply())){
			bean.setReplayTime(now);
			if(bean.getAdmin()!=null){
				if(!bean.getAdmin().equals(CmsUtils.getUser(request))){
					bean.setAdmin(CmsUtils.getUser(request));
				}
			}else{
				bean.setAdmin(CmsUtils.getUser(request));
			}
		}
		bean = manager.update(bean, ext, ctgId,attachmentPaths,attachmentNames);
		log.info("update CmsGuestbook id={}.", bean.getId());
		cmsLogMng.operating(request, "cmsGuestbook.log.update", "id="
				+ bean.getId() + ";title=" + bean.getTitle());
		return list(queryCtgId, queryRecommend, queryChecked, pageNo, request,
				model);
	}

	@RequiresPermissions("guestbook:o_delete")
	@RequestMapping("/guestbook/o_delete.do")
	public String delete(Integer queryCtgId, Boolean queryRecommend,
			Boolean queryChecked, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsGuestbook[] beans = manager.deleteByIds(ids);
		for (CmsGuestbook bean : beans) {
			log.info("delete CmsGuestbook id={}", bean.getId());
			cmsLogMng.operating(request, "cmsGuestbook.log.delete", "id="
					+ bean.getId() + ";title=" + bean.getTitle());
		}
		return list(queryCtgId, queryRecommend, queryChecked, pageNo, request,
				model);
	}
	
	@RequiresPermissions("guestbook:o_check")
	@RequestMapping("/guestbook/o_check.do")
	public String check(Integer queryCtgId, Boolean queryRecommend,
			Boolean queryChecked, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsGuestbook[] beans = manager.checkByIds(ids,CmsUtils.getUser(request),true);
		for (CmsGuestbook bean : beans) {
			log.info("delete CmsGuestbook id={}", bean.getId());
			cmsLogMng.operating(request, "cmsGuestbook.log.check", "id="
					+ bean.getId() + ";title=" + bean.getTitle());
		}
		return list(queryCtgId, queryRecommend, queryChecked, pageNo, request,
				model);
	}
	
	@RequiresPermissions("guestbook:o_check_cancel")
	@RequestMapping("/guestbook/o_check_cancel.do")
	public String cancel_check(Integer queryCtgId, Boolean queryRecommend,
			Boolean queryChecked, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsGuestbook[] beans = manager.checkByIds(ids,CmsUtils.getUser(request),false);
		for (CmsGuestbook bean : beans) {
			log.info("delete CmsGuestbook id={}", bean.getId());
			cmsLogMng.operating(request, "cmsGuestbook.log.check", "id="
					+ bean.getId() + ";title=" + bean.getTitle());
		}
		return list(queryCtgId, queryRecommend, queryChecked, pageNo, request,
				model);
	}

	private WebErrors validateSave(CmsGuestbook bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		bean.setSite(site);
		return errors;
	}

	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id, site.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id, site.getId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, site.getId(), errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsGuestbook entity = manager.findById(id);
		if (errors.ifNotExist(entity, CmsGuestbook.class, id)) {
			return true;
		}
		if (!entity.getSite().getId().equals(siteId)) {
			errors.notInSite(CmsGuestbook.class, id);
			return true;
		}
		return false;
	}

	@Autowired
	private CmsGuestbookCtgMng cmsGuestbookCtgMng;
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private CmsGuestbookMng manager;
}