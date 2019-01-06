package com.jeecms.cms.entity.main;

import java.util.Date;

public class TransferData {

	private int docid;//编号
	private int docchannel;//所属栏目
	private int docversion;//版本号
	private int doctype;//类型
	private String doctitle;//文档标题
	private int docsource;//文档来源
	private int docsecurity;//安全级别
	private int docstatus;//文档状态
	private int dockind;//文档分类 
	private String doccontent;//纯文本内容
	private String dochtmlcon;//HTML 文本内容
	private String docabstract;//内容摘要
	private String dockeywords;//关键词
	private String docrelwords;//相关词
	private String docpeople;//涉及的人物 
	private String docplace;//涉及的地址
	private String docauthor;//作者
	private String doceditor;//编辑
	private String docauditor;//编审
	private int docoutupid;//输入编号
	private Date docvalid;//有效时间
	private String docpuburl;//发布后的链接地址
	private Date docpubtime;//发布时间
	private Date docreltime;//撰写时间
	private String cruser;//对象创建者
	private Date crtime;//对象创建时间
	private int docwordscount;//纯文本内容单词数
	private int docpro;//文档属性
	private int rightdefined;//独立的权限设置
	private String titlecolor;//标题的颜色
	private int templateid;//设置的模板编号
	private int schedule;//计划任务编号
	private String docno;//其它编号
	private int docflag;//标记
	private String editor;//文档编辑器
	private String attribute;//扩展属性
	private int hitscount;//点击数
	private String docpubhtmlcon;//发布 HTML 内容
	private String subdoctitle;//副标题
	private int attachpic;//是否附图
	private String doclink;//链接地址 
	private String docfilename;//外部文件名
	private int docfromversion;//从哪个版本恢复
	private Date opertime;//操作时间
	private String operuser;//操作人
	private String flowoperationmark;
	private String flowpreoperationmark;
	private String flowoperationmaskenum;
	private String docsourcename;
	private String doclinkto;
	private String docmirrorto;
	private String randomserial;
	private String postuser;
	private int ispageimg;
	private String publishdate;
	private String pagenum;
	private String pagename;
	private String pdffilename;
	private String pageimagefilename;
	private String map;
	private String yinti;
	private int siteid;
	private int srcsiteid;
	private Date docfirstpubtime;
	private int nodeid;
	private int orderid;
	private String crdept;
	private int docform;
	private int doclevel;
	private String olddocpuburl;
	private String zn;
	private String gzfg;
	private String ftdd;
	private String ftjb;
	private String ftsj;
	private String xm_xmbm;
	private String xy_yyzzzch;
	private String xy_zjlx;
	private String xy_zjh;
	private String approvalnumber;
	private String approvalyear;
	private String projectname;
	private String docwork;
	private String docjob;
	private String docname;
	private String docdivision;
	private Integer newchannelid;
	private Integer newdocid;
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public int getDocchannel() {
		return docchannel;
	}
	public void setDocchannel(int docchannel) {
		this.docchannel = docchannel;
	}
	public int getDocversion() {
		return docversion;
	}
	public void setDocversion(int docversion) {
		this.docversion = docversion;
	}
	public int getDoctype() {
		return doctype;
	}
	public void setDoctype(int doctype) {
		this.doctype = doctype;
	}
	public String getDoctitle() {
		return doctitle;
	}
	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}
	public int getDocsource() {
		return docsource;
	}
	public void setDocsource(int docsource) {
		this.docsource = docsource;
	}
	public int getDocsecurity() {
		return docsecurity;
	}
	public void setDocsecurity(int docsecurity) {
		this.docsecurity = docsecurity;
	}
	public int getDocstatus() {
		return docstatus;
	}
	public void setDocstatus(int docstatus) {
		this.docstatus = docstatus;
	}
	public int getDockind() {
		return dockind;
	}
	public void setDockind(int dockind) {
		this.dockind = dockind;
	}
	public String getDoccontent() {
		return doccontent;
	}
	public void setDoccontent(String doccontent) {
		this.doccontent = doccontent;
	}
	public String getDochtmlcon() {
		return dochtmlcon;
	}
	public void setDochtmlcon(String dochtmlcon) {
		this.dochtmlcon = dochtmlcon;
	}
	public String getDocabstract() {
		return docabstract;
	}
	public void setDocabstract(String docabstract) {
		this.docabstract = docabstract;
	}
	public String getDockeywords() {
		return dockeywords;
	}
	public void setDockeywords(String dockeywords) {
		this.dockeywords = dockeywords;
	}
	public String getDocrelwords() {
		return docrelwords;
	}
	public void setDocrelwords(String docrelwords) {
		this.docrelwords = docrelwords;
	}
	public String getDocpeople() {
		return docpeople;
	}
	public void setDocpeople(String docpeople) {
		this.docpeople = docpeople;
	}
	public String getDocplace() {
		return docplace;
	}
	public void setDocplace(String docplace) {
		this.docplace = docplace;
	}
	public String getDocauthor() {
		return docauthor;
	}
	public void setDocauthor(String docauthor) {
		this.docauthor = docauthor;
	}
	public String getDoceditor() {
		return doceditor;
	}
	public void setDoceditor(String doceditor) {
		this.doceditor = doceditor;
	}
	public String getDocauditor() {
		return docauditor;
	}
	public void setDocauditor(String docauditor) {
		this.docauditor = docauditor;
	}
	public int getDocoutupid() {
		return docoutupid;
	}
	public void setDocoutupid(int docoutupid) {
		this.docoutupid = docoutupid;
	}
	public Date getDocvalid() {
		return docvalid;
	}
	public void setDocvalid(Date docvalid) {
		this.docvalid = docvalid;
	}
	public String getDocpuburl() {
		return docpuburl;
	}
	public void setDocpuburl(String docpuburl) {
		this.docpuburl = docpuburl;
	}
	public Date getDocpubtime() {
		return docpubtime;
	}
	public void setDocpubtime(Date docpubtime) {
		this.docpubtime = docpubtime;
	}
	public Date getDocreltime() {
		return docreltime;
	}
	public void setDocreltime(Date docreltime) {
		this.docreltime = docreltime;
	}
	public String getCruser() {
		return cruser;
	}
	public void setCruser(String cruser) {
		this.cruser = cruser;
	}
	public Date getCrtime() {
		return crtime;
	}
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}
	public int getDocwordscount() {
		return docwordscount;
	}
	public void setDocwordscount(int docwordscount) {
		this.docwordscount = docwordscount;
	}
	public int getDocpro() {
		return docpro;
	}
	public void setDocpro(int docpro) {
		this.docpro = docpro;
	}
	public int getRightdefined() {
		return rightdefined;
	}
	public void setRightdefined(int rightdefined) {
		this.rightdefined = rightdefined;
	}
	public String getTitlecolor() {
		return titlecolor;
	}
	public void setTitlecolor(String titlecolor) {
		this.titlecolor = titlecolor;
	}
	public int getTemplateid() {
		return templateid;
	}
	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	public int getSchedule() {
		return schedule;
	}
	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}
	public String getDocno() {
		return docno;
	}
	public void setDocno(String docno) {
		this.docno = docno;
	}
	public int getDocflag() {
		return docflag;
	}
	public void setDocflag(int docflag) {
		this.docflag = docflag;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public int getHitscount() {
		return hitscount;
	}
	public void setHitscount(int hitscount) {
		this.hitscount = hitscount;
	}
	public String getDocpubhtmlcon() {
		return docpubhtmlcon;
	}
	public void setDocpubhtmlcon(String docpubhtmlcon) {
		this.docpubhtmlcon = docpubhtmlcon;
	}
	public String getSubdoctitle() {
		return subdoctitle;
	}
	public void setSubdoctitle(String subdoctitle) {
		this.subdoctitle = subdoctitle;
	}
	public int getAttachpic() {
		return attachpic;
	}
	public void setAttachpic(int attachpic) {
		this.attachpic = attachpic;
	}
	public String getDoclink() {
		return doclink;
	}
	public void setDoclink(String doclink) {
		this.doclink = doclink;
	}
	public String getDocfilename() {
		return docfilename;
	}
	public void setDocfilename(String docfilename) {
		this.docfilename = docfilename;
	}
	public int getDocfromversion() {
		return docfromversion;
	}
	public void setDocfromversion(int docfromversion) {
		this.docfromversion = docfromversion;
	}
	public Date getOpertime() {
		return opertime;
	}
	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}
	public String getOperuser() {
		return operuser;
	}
	public void setOperuser(String operuser) {
		this.operuser = operuser;
	}
	public String getFlowoperationmark() {
		return flowoperationmark;
	}
	public void setFlowoperationmark(String flowoperationmark) {
		this.flowoperationmark = flowoperationmark;
	}
	public String getFlowpreoperationmark() {
		return flowpreoperationmark;
	}
	public void setFlowpreoperationmark(String flowpreoperationmark) {
		this.flowpreoperationmark = flowpreoperationmark;
	}
	public String getFlowoperationmaskenum() {
		return flowoperationmaskenum;
	}
	public void setFlowoperationmaskenum(String flowoperationmaskenum) {
		this.flowoperationmaskenum = flowoperationmaskenum;
	}
	public String getDocsourcename() {
		return docsourcename;
	}
	public void setDocsourcename(String docsourcename) {
		this.docsourcename = docsourcename;
	}
	public String getDoclinkto() {
		return doclinkto;
	}
	public void setDoclinkto(String doclinkto) {
		this.doclinkto = doclinkto;
	}
	public String getDocmirrorto() {
		return docmirrorto;
	}
	public void setDocmirrorto(String docmirrorto) {
		this.docmirrorto = docmirrorto;
	}
	public String getRandomserial() {
		return randomserial;
	}
	public void setRandomserial(String randomserial) {
		this.randomserial = randomserial;
	}
	public String getPostuser() {
		return postuser;
	}
	public void setPostuser(String postuser) {
		this.postuser = postuser;
	}
	public int getIspageimg() {
		return ispageimg;
	}
	public void setIspageimg(int ispageimg) {
		this.ispageimg = ispageimg;
	}
	public String getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	public String getPagenum() {
		return pagenum;
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	public String getPdffilename() {
		return pdffilename;
	}
	public void setPdffilename(String pdffilename) {
		this.pdffilename = pdffilename;
	}
	public String getPageimagefilename() {
		return pageimagefilename;
	}
	public void setPageimagefilename(String pageimagefilename) {
		this.pageimagefilename = pageimagefilename;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public String getYinti() {
		return yinti;
	}
	public void setYinti(String yinti) {
		this.yinti = yinti;
	}
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public int getSrcsiteid() {
		return srcsiteid;
	}
	public void setSrcsiteid(int srcsiteid) {
		this.srcsiteid = srcsiteid;
	}
	public Date getDocfirstpubtime() {
		return docfirstpubtime;
	}
	public void setDocfirstpubtime(Date docfirstpubtime) {
		this.docfirstpubtime = docfirstpubtime;
	}
	public int getNodeid() {
		return nodeid;
	}
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getCrdept() {
		return crdept;
	}
	public void setCrdept(String crdept) {
		this.crdept = crdept;
	}
	public int getDocform() {
		return docform;
	}
	public void setDocform(int docform) {
		this.docform = docform;
	}
	public int getDoclevel() {
		return doclevel;
	}
	public void setDoclevel(int doclevel) {
		this.doclevel = doclevel;
	}
	public String getOlddocpuburl() {
		return olddocpuburl;
	}
	public void setOlddocpuburl(String olddocpuburl) {
		this.olddocpuburl = olddocpuburl;
	}
	public String getZn() {
		return zn;
	}
	public void setZn(String zn) {
		this.zn = zn;
	}
	public String getGzfg() {
		return gzfg;
	}
	public void setGzfg(String gzfg) {
		this.gzfg = gzfg;
	}
	public String getFtdd() {
		return ftdd;
	}
	public void setFtdd(String ftdd) {
		this.ftdd = ftdd;
	}
	public String getFtjb() {
		return ftjb;
	}
	public void setFtjb(String ftjb) {
		this.ftjb = ftjb;
	}
	public String getFtsj() {
		return ftsj;
	}
	public void setFtsj(String ftsj) {
		this.ftsj = ftsj;
	}
	public String getXm_xmbm() {
		return xm_xmbm;
	}
	public void setXm_xmbm(String xm_xmbm) {
		this.xm_xmbm = xm_xmbm;
	}
	public String getXy_yyzzzch() {
		return xy_yyzzzch;
	}
	public void setXy_yyzzzch(String xy_yyzzzch) {
		this.xy_yyzzzch = xy_yyzzzch;
	}
	public String getXy_zjlx() {
		return xy_zjlx;
	}
	public void setXy_zjlx(String xy_zjlx) {
		this.xy_zjlx = xy_zjlx;
	}
	public String getXy_zjh() {
		return xy_zjh;
	}
	public void setXy_zjh(String xy_zjh) {
		this.xy_zjh = xy_zjh;
	}
	public String getApprovalnumber() {
		return approvalnumber;
	}
	public void setApprovalnumber(String approvalnumber) {
		this.approvalnumber = approvalnumber;
	}
	public String getApprovalyear() {
		return approvalyear;
	}
	public void setApprovalyear(String approvalyear) {
		this.approvalyear = approvalyear;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getDocwork() {
		return docwork;
	}
	public void setDocwork(String docwork) {
		this.docwork = docwork;
	}
	public String getDocjob() {
		return docjob;
	}
	public void setDocjob(String docjob) {
		this.docjob = docjob;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public String getDocdivision() {
		return docdivision;
	}
	public void setDocdivision(String docdivision) {
		this.docdivision = docdivision;
	}
	public Integer getNewchannelid() {
		return newchannelid;
	}
	public void setNewchannelid(Integer newchannelid) {
		this.newchannelid = newchannelid;
	}
	public Integer getNewdocid() {
		return newdocid;
	}
	public void setNewdocid(Integer newdocid) {
		this.newdocid = newdocid;
	}
	
	
	
}
