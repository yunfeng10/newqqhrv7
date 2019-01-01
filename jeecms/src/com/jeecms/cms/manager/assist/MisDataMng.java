package com.jeecms.cms.manager.assist;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;

public interface MisDataMng {

	public JSONObject misResultCount(String fl) throws JSONException;
	
	public JSONObject misStatusCount(String fl) throws JSONException;
	
	public JSONObject misResultPage(String fl,String param,int start,int length) throws JSONException;
	
	public JSONObject misStatusPage(String fl,String param,int start,int length) throws JSONException;
	
	public void getMisResultData(String dataId,ModelMap model);
	
	public void getMisStatusData(String dataId,ModelMap model);
}
