package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_SPECIAL;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.dao.assist.CmsGuestbookDao;
import com.jeecms.cms.dao.assist.MisResultDao;
import com.jeecms.cms.dao.assist.MisStatus1Dao;
import com.jeecms.cms.dao.assist.MisStatus2Dao;
import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.cms.entity.assist.CmsGuestbookExt;
import com.jeecms.cms.entity.main.Ysqgk;
import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
import com.jeecms.cms.manager.assist.CmsGuestbookExtMng;
import com.jeecms.cms.manager.assist.CmsGuestbookMng;
import com.jeecms.cms.manager.assist.YsqgkMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class GuestbookAct {
	private static final Logger log = LoggerFactory.getLogger(GuestbookAct.class);

	public static final String GUESTBOOK_INDEX = "tpl.guestbookIndex";
	public static final String GUESTBOOK_CTG = "tpl.guestbookCtg";
	public static final String GUESTBOOK_DETAIL = "tpl.guestbookDetail";

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 留言板首页或类别页
	 * 
	 * @param ctgId
	 *            留言类别
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/guestbook*.jspx", method = RequestMethod.GET)
	public String index(Integer ctgId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		CmsGuestbookCtg ctg = null;
		if (ctgId != null) {
			ctg = cmsGuestbookCtgMng.findById(ctgId);
		}
		if (ctg == null) {
			// 留言板首页
			System.out.println(FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_SPECIAL, GUESTBOOK_INDEX));
			return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_SPECIAL, GUESTBOOK_INDEX);
		} else {
			// 留言板类别页
			model.addAttribute("ctg", ctg);
			return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_SPECIAL, GUESTBOOK_CTG);
		}
	}

	@RequestMapping(value = "news_jzxx_detail.jspx", method = RequestMethod.GET)
	public String news_jzxx_detail(Integer dataId, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsGuestbook guestbook = null;
		guestbook = cmsGuestbookMng.findById(dataId);
		if (guestbook != null) {
			model.addAttribute("title", guestbook.getTitle());
			model.addAttribute("content", guestbook.getContent());
			model.addAttribute("createTime", formatter.format(guestbook.getCreateTime()));
			model.addAttribute("xm", guestbook.getXm());
			model.addAttribute("wtfsd", guestbook.getWtfsd());
			model.addAttribute("phone", guestbook.getPhone());
			model.addAttribute("txdz", guestbook.getTxdz());
			model.addAttribute("yb", guestbook.getYb());
			model.addAttribute("email", guestbook.getEmail());
			model.addAttribute("reply", guestbook.getReply());
			model.addAttribute("replyTime", formatter.format(guestbook.getReplayTime()));
			model.addAttribute("grxxgk", guestbook.getGrxxgk());
			model.addAttribute("xjsfgk", guestbook.getXjsfgk());
			int cs = guestbook.getDjcs();
			CmsGuestbookExt ext = new CmsGuestbookExt();
			ext.setId(dataId);
			ext.setDjcs(cs + 1);
			cmsGuestbookExtMng.update(ext);
		} else {
			model.addAttribute("title", "");
			model.addAttribute("content", "");
			model.addAttribute("createTime", "");
			model.addAttribute("xm", "");
			model.addAttribute("wtfsd", "");
			model.addAttribute("phone", "");
			model.addAttribute("txdz", "");
			model.addAttribute("yb", "");
			model.addAttribute("email", "");
			model.addAttribute("reply", "");
			model.addAttribute("replyTime", "");
			model.addAttribute("grxxgk", "");
			model.addAttribute("xjsfgk", "");
		}

		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);

		return site.getSolutionPath() + "/detail/news_jzxx_detail.html";
	}
	
	@RequestMapping(value = "siteMap.jspx", method = RequestMethod.GET)
	public String siteMap(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		

		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);

		return site.getSolutionPath() + "/channel/news_map.html";
	}

	@RequestMapping(value = "news_zxzx_detail.jspx", method = RequestMethod.GET)
	public String news_zxzx_detail(Integer dataId, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsGuestbook guestbook = null;
		guestbook = cmsGuestbookMng.findById(dataId);
		if (guestbook != null) {
			model.addAttribute("title", guestbook.getTitle());
			model.addAttribute("content", guestbook.getContent());
			model.addAttribute("createTime", formatter.format(guestbook.getCreateTime()));
			model.addAttribute("xm", guestbook.getXm());
			model.addAttribute("wtfsd", guestbook.getWtfsd());
			model.addAttribute("phone", guestbook.getPhone());
			model.addAttribute("txdz", guestbook.getTxdz());
			model.addAttribute("yb", guestbook.getYb());
			model.addAttribute("email", guestbook.getEmail());
			model.addAttribute("reply", guestbook.getReply());
			model.addAttribute("replyTime", formatter.format(guestbook.getReplayTime()));
			model.addAttribute("grxxgk", guestbook.getGrxxgk());
			model.addAttribute("xjsfgk", guestbook.getXjsfgk());
			int cs = guestbook.getDjcs();
			CmsGuestbookExt ext = new CmsGuestbookExt();
			ext.setId(dataId);
			ext.setDjcs(cs + 1);
			cmsGuestbookExtMng.update(ext);
		} else {
			model.addAttribute("title", "");
			model.addAttribute("content", "");
			model.addAttribute("createTime", "");
			model.addAttribute("xm", "");
			model.addAttribute("wtfsd", "");
			model.addAttribute("phone", "");
			model.addAttribute("txdz", "");
			model.addAttribute("yb", "");
			model.addAttribute("email", "");
			model.addAttribute("reply", "");
			model.addAttribute("replyTime", "");
			model.addAttribute("grxxgk", "");
			model.addAttribute("xjsfgk", "");
		}
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return site.getSolutionPath() + "/detail/news_zxzx_detail.html";
	}

	@RequestMapping(value = "/guestbook/{id}.jspx", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsGuestbook guestbook = null;
		if (id != null) {
			guestbook = cmsGuestbookMng.findById(id);
		}
		model.addAttribute("guestbook", guestbook);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_SPECIAL, GUESTBOOK_DETAIL);

	}

	/**
	 * 提交留言。ajax提交。
	 * 
	 * @param contentId
	 * @param pageNo
	 * @param request
	 * @param response
	 * @param model
	 * @throws JSONException
	 */
	@RequestMapping(value = "/guestbook.jspx", method = RequestMethod.POST)
	public void submit(Integer siteId, Integer ctgId, String title, String content, String email, String phone,
			String qq, String grxxgk, String xm, String wtfsd, String txdz, String yb, String xjsfgk, String cxm,
			Integer djcs, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws JSONException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser member = CmsUtils.getUser(request);
		if (siteId == null) {
			siteId = site.getId();
		}
		JSONObject json = new JSONObject();
		try {
			if (!imageCaptchaService.validateResponseForID(session.getSessionId(request, response), captcha)) {
				json.put("success", false);
				json.put("status", 1);
				ResponseUtils.renderJson(response, json.toString());
				return;
			}
		} catch (CaptchaServiceException e) {
			json.put("success", false);
			json.put("status", 1);
			ResponseUtils.renderJson(response, json.toString());
			log.warn("", e);
			return;
		}
		String ip = RequestUtils.getIpAddr(request);
		if (cxm == null || cxm.length() == 0) {
			cxm = this.getRandomString(8);
		}
		if (djcs == null) {
			djcs = 0;
		}
		cmsGuestbookMng.save(member, siteId, ctgId, ip, title, content, email, phone, qq, grxxgk, xm, wtfsd, txdz, yb,
				xjsfgk, cxm, djcs);
		json.put("success", true);
		json.put("searchno", cxm);
		json.put("status", 0);
		ResponseUtils.renderJson(response, json.toString());
	}

	@RequestMapping(value = "/countguestbook.jspx")
	public void detailguestbook(Integer dataId, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		CmsGuestbook guestbook = null;
		JSONObject json = new JSONObject();
		int total = cmsGuestbookDao.countGuestBook(dataId, "");
		int reply = cmsGuestbookDao.countGuestBook(dataId, "reply");
		int notreply = cmsGuestbookDao.countGuestBook(dataId, "notreply");
		json.put("success", true);
		json.put("total", total);
		json.put("reply", reply);
		json.put("notreply", notreply);

		ResponseUtils.renderJson(response, json.toString());
	}

	private int getPageNo(int start, int length) {
		return (start / length) + 1;
	}

	@RequestMapping(value = "/dtguestbook.jspx", method = RequestMethod.POST)
	public void guestbook_dt(Integer ctgId, String xm, String cxm, int start, int length, int draw,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser member = CmsUtils.getUser(request);
		Pagination page = cmsGuestbookMng.getWebPage(ctgId, xm, cxm, getPageNo(start, length), length);

		ResponseUtils.renderJson(response, createJSONObjectByPagination(start, page).toString());
	}

	private JSONObject createJSONObjectByPagination(int start, Pagination page) throws JSONException {
		JSONObject json = new JSONObject();

		json.put("iTotalDisplayRecords", page.getTotalCount());

		json.put("iTotalRecords", page.getList().size());
		List<CmsGuestbook> pageList = (List<CmsGuestbook>) page.getList();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (CmsGuestbook item : pageList) {
			Map<String, Object> m = new HashMap<String, Object>();
			start++;
			m.put("dataIndex", String.valueOf(start));
			m.put("title", item.getTitle());
			m.put("djcs", String.valueOf(item.getDjcs()));
			m.put("createDate", formatter.format(item.getCreateTime()));
			m.put("dataId", item.getId());

			mapList.add(m);
		}
		json.put("aaData", mapList);
		return json;
	}

	public String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	@Autowired
	private CmsGuestbookCtgMng cmsGuestbookCtgMng;
	@Autowired
	private CmsGuestbookMng cmsGuestbookMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private CmsGuestbookExtMng cmsGuestbookExtMng;
	@Autowired
	private CmsGuestbookDao cmsGuestbookDao;
	
	@Autowired
	private MisResultDao resultDao;
	@Autowired
	private MisStatus1Dao status1Dao;
	@Autowired
	private MisStatus2Dao status2Dao;
	
	@Autowired
	private YsqgkMng ysqgkMng;

	@RequestMapping(value = "/addysqgk.jspx", method = RequestMethod.GET)
	public String addYsqgk(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "";
	}

	@RequestMapping(value = "/ysqgkdetail.jspx", method = RequestMethod.GET)
	public String ysqgkDetail(int id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		ysqgkMng.setYsqgkData(id, model);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);

		return site.getSolutionPath() + "/channel/news_ysqgk_detail.html";
	}

	@RequestMapping(value = "/saveysqgk.jspx", method = RequestMethod.POST)
	public void saveYsqgk(Ysqgk ysqgk, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model)throws JSONException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser member = CmsUtils.getUser(request);
		JSONObject json = new JSONObject();
		try {
			if (!imageCaptchaService.validateResponseForID(session.getSessionId(request, response), captcha)) {
				json.put("success", false);
				json.put("status", 1);
				ResponseUtils.renderJson(response, json.toString());
				return;
			}
		} catch (CaptchaServiceException e) {
			json.put("success", false);
			json.put("status", 1);
			ResponseUtils.renderJson(response, json.toString());
			log.warn("", e);
			return;
		}
		ysqgk.setSearchNo(this.getRandomString(8));
		ysqgk.setShenQingShiJian(new Date());
		ysqgkMng.save(ysqgk);
		json.put("searchno", ysqgk.getSearchNo());
		json.put("success", true);
		json.put("status", 0);
		ResponseUtils.renderJson(response, json.toString());
	}

	@RequestMapping(value = "/queryysqgk.jspx", method = RequestMethod.POST)
	public void queryYsqgk(int queryType, String queryName, String queryJgdm, String querySearchNo, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
		int dataId = ysqgkMng.queryYsqgkId(queryType, queryName, queryJgdm, querySearchNo);
		json.put("success", true);
		json.put("status", 0);
		json.put("dataId", dataId);
		ResponseUtils.renderJson(response, json.toString());
	}
	
	@RequestMapping(value = "/countbljg.jspx")
	public void countbljg(String fl, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
		BigInteger resultTotal = resultDao.countTotalValue(fl);
		BigInteger resultMonth = resultDao.countMonthValue(fl);
		BigInteger resultYear = resultDao.countYearValue(fl);
		json.put("resultTotal",resultTotal);
		json.put("resultMonth",resultMonth);
		json.put("resultYear",resultYear);
		ResponseUtils.renderJson(response, json.toString());
	}
	@RequestMapping(value = "/countblzt.jspx")
	public void countblzt(String fl, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
	
		BigInteger totalReceive = status1Dao.countTotalReceive(fl);
		BigInteger totalComplete = status1Dao.countTotalComplete(fl);
		BigInteger lastReceive = status1Dao.countLastReceive(fl);
		BigInteger lastComplete = status1Dao.countLastComplete(fl);
		BigInteger yearReceive = status1Dao.countYearReceive(fl);
		BigInteger yearComplete = status1Dao.countYearComplete(fl);

		json.put("totalReceive",totalReceive);
		json.put("totalComplete",totalComplete);
		json.put("lastReceive",lastReceive);
		json.put("lastComplete",lastComplete);
		json.put("yearReceive",yearReceive);
		json.put("yearComplete",yearComplete);
		ResponseUtils.renderJson(response, json.toString());
	}
}
