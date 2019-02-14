package com.jeecms.cms.manager.assist.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.jeecms.cms.dao.assist.MisResultDao;
import com.jeecms.cms.dao.assist.MisStatus1Dao;
import com.jeecms.cms.dao.assist.MisStatus2Dao;
import com.jeecms.cms.entity.assist.MisResult;
import com.jeecms.cms.entity.assist.MisStatus1;
import com.jeecms.cms.entity.assist.MisStatus2;
import com.jeecms.cms.manager.assist.MisDataMng;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class MisDataMngimpl implements MisDataMng {

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat formatter2 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private MisResultDao misReaultDao;

	@Autowired
	private MisStatus1Dao misStatus1Dao;

	@Autowired
	private MisStatus2Dao misStatus2Dao;

	@Override
	public JSONObject misResultCount(String fl) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("ljsj", 0);
		json.put("ljbj", 0);
		json.put("sysj", 0);
		json.put("sybj", 0);
		json.put("dnsj", 0);
		json.put("dnbj", 0);
		return json;
	}

	@Override
	public JSONObject misStatusCount(String fl) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("ljsj", 0);
		json.put("ljbj", 0);
		json.put("sysj", 0);
		json.put("sybj", 0);
		json.put("dnsj", 0);
		json.put("dnbj", 0);
		return json;
	}

	@Override
	public JSONObject misResultPage(String fl, String param, int start, int length) throws JSONException {
		// TODO Auto-generated method stub
		int pageNo = getPageNo(start,length);
		Pagination page = misReaultDao.getWebPage(fl, param, pageNo, length);
		return createRelustJson(start,page);
	}

	@Override
	public JSONObject misStatusPage(String fl, String param, int start, int length) throws JSONException {
		// TODO Auto-generated method stub
		int pageNo = getPageNo(start,length);
		Pagination page = misStatus1Dao.getWebPage(fl, param, pageNo, length);
		return createStatusJson(start,page);
	}
	private int getPageNo(int start,int length){
		return (start/length)+1;
	}
	
	private JSONObject createRelustJson(int start, Pagination page) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("iTotalDisplayRecords", page.getTotalCount());
		json.put("iTotalRecords", page.getList().size());
		List<MisResult> pageList = (List<MisResult>) page.getList();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (MisResult item : pageList) {
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("dataId", item.getDataid());
			m.put("bh", item.getBh());
			m.put("xmmc", item.getXmmc());
			if(item.getSqsj()!=null){
				m.put("sqsj", formatter.format(item.getSqsj()));
			}else{
				m.put("sqsj", "");
			}
			
			m.put("blzt", item.getBlztms());
			mapList.add(m);
		}
		json.put("aaData", mapList);
		return json;
	}

	private JSONObject createStatusJson(int start, Pagination page) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("iTotalDisplayRecords", page.getTotalCount());
		json.put("iTotalRecords", page.getList().size());
		List<MisStatus1> pageList = (List<MisStatus1>) page.getList();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (MisStatus1 item : pageList) {
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("dataId", item.getDataid());
			m.put("bh", item.getBh());
			m.put("xmmc", item.getXmmc());
			m.put("sbdw", item.getSbdw());
			if(item.getSlsj()!=null){
				m.put("slsj", formatter.format(item.getSlsj()));
			}else{
				m.put("slsj", "");
			}
			if(item.getBlsj()!=null){
				m.put("bjsj", formatter.format(item.getBlsj()));
			}else{
				m.put("bjsj", "");
			}
			m.put("blzt", item.getBlztms());
			m.put("blcs", item.getBlcs());
			mapList.add(m);
		}
		json.put("aaData", mapList);
		return json;
	}

	@Override
	public void getMisResultData(String dataId, ModelMap model) {
		// TODO Auto-generated method stub
		MisResult result = misReaultDao.findByDataId(dataId);
		if(result!=null){
			model.addAttribute("fl", result.getFl());
			model.addAttribute("flvalue", result.getFlms());
			model.addAttribute("bh", result.getBh());
			model.addAttribute("sqdw", result.getSqdw());
			model.addAttribute("xmmc", result.getXmmc());
			model.addAttribute("xmlx", result.getXmlxms());
			model.addAttribute("sqhpzmj", result.getSqhpzmj());
			model.addAttribute("pzwh", result.getXkzh());
			if(result.getPzsj()!=null){
				model.addAttribute("pzsj", formatter2.format(result.getPzsj()));
			}else{
				model.addAttribute("pzsj", "");
			}
			model.addAttribute("dqmlzt", result.getBlztms());
		}
	}

	@Override
	public void getMisStatusData(String dataId, ModelMap model) {
		// TODO Auto-generated method stub
		MisStatus1 misStatus1 = misStatus1Dao.findByDataId(dataId);
		if(misStatus1!=null){
			List<MisStatus2> list = misStatus2Dao.findByDataId(dataId);
			model.addAttribute("fl", misStatus1.getFl());
			model.addAttribute("flvalue", misStatus1.getFlms());
			model.addAttribute("bh", misStatus1.getBh());
			model.addAttribute("sbdw", misStatus1.getSbdw());
			model.addAttribute("xmmc", misStatus1.getXmmc());
			model.addAttribute("sbsx", misStatus1.getSbsx());
			model.addAttribute("slbm", misStatus1.getSlbm());
			model.addAttribute("bldd", misStatus1.getBldd());
			if(misStatus1.getSlsj()!=null){
				model.addAttribute("slsj", formatter2.format(misStatus1.getSlsj()));
			}else{
				model.addAttribute("slsj", "");
			}
			model.addAttribute("cnqx", misStatus1.getCnqx());
			model.addAttribute("blzt", misStatus1.getBlztms());
			model.addAttribute("dqblbm", misStatus1.getDqblbm());
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			for(MisStatus2 status2 : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("hjmc", status2.getHjmc());
				map.put("blbm", status2.getBlbm());
				if(status2.getJssj()!=null){
					map.put("jssj", formatter2.format(status2.getJssj()));
				}
				if(status2.getBlsj()!=null){
					map.put("blsj", formatter2.format(status2.getBlsj()));
				}
				map.put("bljg", status2.getBljg());
				mapList.add(map);
			}
			model.addAttribute("status2list", mapList);
		}
	}
}
