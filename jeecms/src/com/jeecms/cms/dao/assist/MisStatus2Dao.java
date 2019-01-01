package com.jeecms.cms.dao.assist;

import java.util.List;

import com.jeecms.cms.entity.assist.MisStatus2;

public interface MisStatus2Dao {

	public List<MisStatus2> findByDataId(String dataId);
}
