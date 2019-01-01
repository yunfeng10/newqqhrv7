package com.jeecms.cms.action.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
import com.jeecms.cms.manager.assist.MisDataMng;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class MisDataAct {

	@Autowired
	private MisDataMng misDataMng;

	@RequestMapping(value = "/misdata/querymisresult.jspx", method = RequestMethod.GET)
	public String misResultQueryPage(String fl, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		if(fl==null){
			model.addAttribute("fl", "");
		}else{
			model.addAttribute("fl", fl);
		}
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return site.getSolutionPath()+"/channel/news_bljg_query.html";
	}

	@RequestMapping(value = "/misdata/querymisstatus.jspx", method = RequestMethod.GET)
	public String misStatusQueryPage(String fl, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		if(fl==null){
			model.addAttribute("fl", "");
		}else{
			model.addAttribute("fl", fl);
		}
		
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return site.getSolutionPath()+"/channel/news_blzt_query.html";
	}
	@RequestMapping(value = "/misdata/misresultdetail.jspx", method = RequestMethod.GET)
	public String misResultDetailPage(String dataId, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		misDataMng.getMisResultData(dataId, model);
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return site.getSolutionPath()+"/channel/news_bljg_detail.html";
	}

	@RequestMapping(value = "/misdata/misstatusdetail.jspx", method = RequestMethod.GET)
	public String misStatusDeatilPage(String dataId, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		misDataMng.getMisStatusData(dataId, model);
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return site.getSolutionPath()+"/channel/news_blzt_detail.html";
	}

	@RequestMapping(value = "/misdata/countmisresult.jspx")
	public void misResultCount(String fl, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
		json= misDataMng.misResultCount(fl);
		ResponseUtils.renderJson(response, json.toString());
	}
	@RequestMapping(value = "/misdata/countmisstatus.jspx")
	public void misStatusCount(String fl, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
		json = misDataMng.misStatusCount(fl);
		ResponseUtils.renderJson(response, json.toString());
	}

	@RequestMapping(value = "/misdata/misresultpagedata.jspx", method = RequestMethod.POST)
	public void misResultPageData(String fl,String param,int start,int length,int draw,
			HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
		json = misDataMng.misResultPage(fl, param, start, length);
		ResponseUtils.renderJson(response, json.toString());
	}
	@RequestMapping(value = "/misdata/misstatuspagedata.jspx", method = RequestMethod.POST)
	public void misStatusPageData(String fl,String param,int start,int length,int draw,
			HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
		json = misDataMng.misStatusPage(fl, param, start, length);
		ResponseUtils.renderJson(response, json.toString());
	}

}
