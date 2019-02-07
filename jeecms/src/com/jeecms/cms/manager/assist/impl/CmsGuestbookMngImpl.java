package com.jeecms.cms.manager.assist.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jeecms.cms.dao.assist.CmsGuestbookDao;
import com.jeecms.cms.dao.assist.MisResultDao;
import com.jeecms.cms.dao.assist.MisStatus1Dao;
import com.jeecms.cms.dao.assist.MisStatus2Dao;
import com.jeecms.cms.dao.main.YsqgkDao;
import com.jeecms.cms.entity.assist.CmsGuestbook;
import com.jeecms.cms.entity.assist.CmsGuestbookExt;
import com.jeecms.cms.entity.assist.MisResult;
import com.jeecms.cms.entity.assist.MisStatus1;
import com.jeecms.cms.entity.assist.MisStatus2;
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

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Transactional(readOnly = true)
	public Pagination getPage(Integer siteId, Integer ctgId, Integer userId, Boolean recommend, Boolean checked,
			boolean desc, boolean cacheable, int pageNo, int pageSize) {
		return dao.getPage(siteId, ctgId, userId, recommend, checked, desc, cacheable, pageNo, pageSize);
	}

	@Transactional(readOnly = true)
	public List<CmsGuestbook> getList(Integer siteId, Integer ctgId, Boolean recommend, Boolean checked, boolean desc,
			boolean cacheable, int first, int max) {
		return dao.getList(siteId, ctgId, recommend, checked, desc, cacheable, first, max);
	}

	@Transactional(readOnly = true)
	public CmsGuestbook findById(Integer id) {
		CmsGuestbook entity = dao.findById(id);
		return entity;
	}

	public CmsGuestbook save(CmsGuestbook bean, CmsGuestbookExt ext, Integer ctgId, String ip) {
		bean.setCtg(cmsGuestbookCtgMng.findById(ctgId));
		bean.setIp(ip);
		bean.setCreateTime(new Timestamp(System.currentTimeMillis()));
		bean.init();
		dao.save(bean);
		cmsGuestbookExtMng.save(ext, bean);
		return bean;
	}

	public CmsGuestbook save(CmsUser member, Integer siteId, Integer ctgId, String ip, String title, String content,
			String email, String phone, String qq, String grxxgk, String xm, String wtfsd, String txdz, String yb,
			String xjsfgk, String cxm, Integer djcs) {
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

	public CmsGuestbook update(CmsGuestbook bean, CmsGuestbookExt ext, Integer ctgId) {
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

	public CmsGuestbook[] checkByIds(Integer[] ids, CmsUser checkUser, Boolean checkStatus) {
		CmsGuestbook[] beans = new CmsGuestbook[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = checkById(ids[i], checkUser, checkStatus);
		}
		return beans;
	}

	private CmsGuestbook checkById(Integer id, CmsUser checkUser, Boolean checkStatus) {
		CmsGuestbook bean = findById(id);
		Updater<CmsGuestbook> updater = new Updater<CmsGuestbook>(bean);
		bean = dao.updateByUpdater(updater);
		if (checkStatus) {
			bean.setAdmin(checkUser);
		}
		bean.setChecked(checkStatus);
		return bean;
	}

	@Autowired
	private MisResultDao resultDao;
	@Autowired
	private MisStatus1Dao status1Dao;
	@Autowired
	private MisStatus2Dao status2Dao;

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
	 * 前台 新增
	 */
	@Override
	public Pagination getWebPage(Integer ctgId, String xm, String cxm, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getWebPage(ctgId, xm, cxm, pageNo, pageSize);
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
	public Ysqgk updateYsqgk(Ysqgk entity, String[] attachmentPaths, String[] attachmentNames) {
		List<YsqgkFile> fileList = new ArrayList<YsqgkFile>();
		if (attachmentPaths != null && attachmentPaths.length > 0) {
			for (int i = 0, len = attachmentPaths.length; i < len; i++) {
				if (attachmentPaths[i] != "") {
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
		String year = count.getYear() + "";
		String path = site.getUploadPath();
		String cpath = request.getSession().getServletContext().getRealPath("");
		File baseFile = new File(cpath + File.separator + path);
		if (baseFile.exists()) {
			File[] baseList = baseFile.listFiles();
			for (File baseItem : baseList) {
				if (baseItem.isDirectory() && baseItem.getName().startsWith(year)) {
					File[] itemList = baseItem.listFiles();
					for (File item : itemList) {
						count.countFile(item.getName());
					}
				}
			}
		}
	}

	private String getCellValueForExcel(Row row,int index){
		try{
			return row.getCell(index).getStringCellValue()+"";
		}catch(Exception e){
			return "";
		}
		
	}
	@Override
	public String importExcel(String excelPath) {
		// TODO Auto-generated method stub
		String fileReadResult = "";

		String sheet0ReadResult = "";
		int sheet0TotalResult = 0;
		int sheet0SuccessResult = 0;
		int sheet0FailResult = 0;

		String sheet1ReadResult = "";
		int sheet1TotalResult = 0;
		int sheet1SuccessResult = 0;
		int sheet1FailResult = 0;

		String sheet2ReadResult = "";
		int sheet2TotalResult = 0;
		int sheet2SuccessResult = 0;
		int sheet2FailResult = 0;

		fileReadResult = "文件读取成功";
		Workbook workbook = null;
		FormulaEvaluator formulaEvaluator = null;
		InputStream is = null;
		try {
			// 读取目标文件
			File excelFile = new File(excelPath);
			is = new FileInputStream(excelFile);
			// 判断文件是xlsx还是xls
			if (excelFile.getName().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(is);
				// formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook)
				// workbook);
			} else if (excelFile.getName().endsWith("xls")) {
				workbook = new HSSFWorkbook(is);
				// formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook)
				// workbook);
			} else {
				workbook = null;
			}
			// 判断excel文件打开是否正确
			if (workbook == null) {
				fileReadResult = "文件读取失败";
				return fileReadResult;
			}
		} catch (Exception e) {
			if (is != null) {
				try {
					is.close();
				} catch (Exception ce) {

				}
			}
			fileReadResult = "文件读取失败";
			return fileReadResult;
		}
		// sheet0
		try {
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet == null) {
				sheet0ReadResult = "失败";
			} else {
				sheet0ReadResult = "成功";
				sheet0TotalResult = sheet.getLastRowNum();
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					try {
						
						MisResult entity = new MisResult();
						entity.setDataid(getCellValueForExcel(row,0));
						entity.setFl(getCellValueForExcel(row,1));
						entity.setFlms(getCellValueForExcel(row,2));
						entity.setBh(getCellValueForExcel(row,3));
						entity.setXmmc(getCellValueForExcel(row,4));
						entity.setSqsjstr(getCellValueForExcel(row,5));
						if (!StringUtils.isEmpty(entity.getSqsjstr())) {
							entity.setSqsj(sdf.parse(entity.getSqsjstr()));
						}
						
						entity.setBlzt(getCellValueForExcel(row,6));
						entity.setBlztms(getCellValueForExcel(row,7));
						entity.setXmlxms(getCellValueForExcel(row,8));
						entity.setSqhpzmj(getCellValueForExcel(row,9));
						entity.setPzsjstr(getCellValueForExcel(row,10));
						if (!StringUtils.isEmpty(entity.getPzsjstr())) {
							entity.setPzsj(sdf.parse(entity.getPzsjstr()));
						}
						entity.setXkzh(getCellValueForExcel(row,11));
						entity.setSqdw(getCellValueForExcel(row,12));
						resultDao.deleteByDataId(entity.getDataid());
						resultDao.saveEntity(entity);
						sheet0SuccessResult++;
					} catch (Exception e) {
						sheet0FailResult++;
					}

				}
			}
		} catch (Exception e) {
			sheet0ReadResult = "失败";
		}
		// sheet1
		try {
			Sheet sheet = workbook.getSheetAt(1);
			if (sheet == null) {
				sheet1ReadResult = "失败";
			} else {
				sheet1ReadResult = "成功";
				sheet1TotalResult = sheet.getLastRowNum();
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					try {

						MisStatus1 entity = new MisStatus1();
						entity.setDataid(getCellValueForExcel(row,0));
						entity.setFl(getCellValueForExcel(row,1));
						entity.setFlms(getCellValueForExcel(row,2));
						entity.setBh(getCellValueForExcel(row,3));
						entity.setXmmc(getCellValueForExcel(row,4));
						entity.setSbdw(getCellValueForExcel(row,5));
						entity.setSlsjstr(getCellValueForExcel(row,6));
						if (!StringUtils.isEmpty(entity.getSlsjstr())) {
							entity.setSlsj(sdf.parse(entity.getSlsjstr()));
						}
						entity.setBlsjstr(getCellValueForExcel(row,7));
						if (!StringUtils.isEmpty(entity.getBlsjstr())) {
							entity.setBlsj(sdf.parse(entity.getBlsjstr()));
						}

						entity.setBlzt(getCellValueForExcel(row,8));
						entity.setBlztms(getCellValueForExcel(row,9));
						entity.setBlcs(getCellValueForExcel(row,10));
						entity.setSbsx(getCellValueForExcel(row,11));

						entity.setSlbm(getCellValueForExcel(row,12));
						entity.setBldd(getCellValueForExcel(row,13));
						entity.setCnqx(getCellValueForExcel(row,14));
						entity.setDqblbm(getCellValueForExcel(row,15));
						status1Dao.deleteByDataId(entity.getDataid());
						status1Dao.saveEntity(entity);
						sheet1SuccessResult++;
					} catch (Exception e) {
						sheet1FailResult++;
					}
				}
			}
		} catch (Exception e) {
			sheet1ReadResult = "失败";
		}
		// sheet2
		try {
			Sheet sheet = workbook.getSheetAt(2);
			if (sheet == null) {
				sheet2ReadResult = "失败";
			} else {
				sheet2ReadResult = "成功";
				sheet2TotalResult = sheet.getLastRowNum();
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					try {

						MisStatus2 entity = new MisStatus2();
						entity.setDataid(getCellValueForExcel(row,0));
						entity.setRefid(getCellValueForExcel(row,1));
						entity.setHjmc(getCellValueForExcel(row,2));
						entity.setBlbm(getCellValueForExcel(row,3));

						entity.setJssjstr(getCellValueForExcel(row,4));
						if (!StringUtils.isEmpty(entity.getJssjstr())) {
							entity.setJssj(sdf.parse(entity.getJssjstr()));
						}
						entity.setBlsjstr(getCellValueForExcel(row,5));
						if (!StringUtils.isEmpty(entity.getBlsjstr())) {
							entity.setBlsj(sdf.parse(entity.getBlsjstr()));
						}
						entity.setBljg(getCellValueForExcel(row,6));
						status2Dao.deleteByDataId(entity.getDataid());
						status2Dao.saveEntity(entity);
						sheet2SuccessResult++;
					} catch (Exception e) {
						sheet2FailResult++;
					}

				}
			}
		} catch (Exception e) {
			sheet2ReadResult = "失败";
		}
		if (is != null) {
			try {
				is.close();
			} catch (Exception ce) {

			}
		}
		String result = String.format(
				"%s , 结果数据读取 %s ,共 %d 条数据,成功处理 %d ,失败处理 %d ,状态数据读取 %s ,共 %d 条数据,成功处理 %d ,失败处理 %d ,状态明细数据读取 %s ,共 %d 条数据,成功处理 %d ,失败处理 %d ",
				fileReadResult, sheet0ReadResult, sheet0TotalResult, sheet0SuccessResult, sheet0FailResult,
				sheet1ReadResult, sheet1TotalResult, sheet1SuccessResult, sheet1FailResult, sheet2ReadResult,
				sheet2TotalResult, sheet2SuccessResult, sheet2FailResult);
		return result;
	}

}