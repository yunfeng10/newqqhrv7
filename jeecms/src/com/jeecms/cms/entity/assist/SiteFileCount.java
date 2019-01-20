package com.jeecms.cms.entity.assist;

import java.util.Calendar;
import java.util.Date;

public class SiteFileCount {

	private Integer year;
	
	private Integer totalFile;
	
	private Integer picFile;
	
	private Integer docFile;
	
	private Integer videoFile;
	
	private Integer otherFile;
	
	public SiteFileCount(){
		Calendar calendar = Calendar.getInstance();
		this.year=calendar.get(calendar.YEAR);
		this.totalFile=0;
		this.picFile=0;
		this.docFile=0;
		this.videoFile=0;
		this.otherFile=0;
	}
	public SiteFileCount(Integer year){
		this.year=year;
		this.totalFile=0;
		this.picFile=0;
		this.docFile=0;
		this.videoFile=0;
		this.otherFile=0;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getTotalFile() {
		return totalFile;
	}

	public void setTotalFile(Integer totalFile) {
		this.totalFile = totalFile;
	}

	public Integer getPicFile() {
		return picFile;
	}

	public void setPicFile(Integer picFile) {
		this.picFile = picFile;
	}

	public Integer getDocFile() {
		return docFile;
	}

	public void setDocFile(Integer docFile) {
		this.docFile = docFile;
	}

	public Integer getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(Integer videoFile) {
		this.videoFile = videoFile;
	}

	public Integer getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(Integer otherFile) {
		this.otherFile = otherFile;
	}
	
	public void countFile(String fileName){
		totalFile++;
		fileName = fileName.toLowerCase();
		if(fileName.endsWith("bmp")||fileName.endsWith("jpg")||fileName.endsWith("png")||fileName.endsWith("tif")||fileName.endsWith("gif")||fileName.endsWith("pcx")||fileName.endsWith("tga")||fileName.endsWith("exif")||fileName.endsWith("fpx")||fileName.endsWith("svg")||fileName.endsWith("psd")||fileName.endsWith("cdr")||fileName.endsWith("pcd")||fileName.endsWith("dxf")||fileName.endsWith("ufo")||fileName.endsWith("eps")||fileName.endsWith("ai")||fileName.endsWith("raw")||fileName.endsWith("wmf")||fileName.endsWith("webp")){
			picFile++;
		}else if(fileName.endsWith("doc")||fileName.endsWith("xls")||fileName.endsWith("ppt")||fileName.endsWith("docx")||fileName.endsWith("xlsx")||fileName.endsWith("pptx")){
			docFile++;
		}else if(fileName.endsWith("mp4")||fileName.endsWith("flv")){
			videoFile++;
		}else{
			otherFile++;
		}
	}
	
}
