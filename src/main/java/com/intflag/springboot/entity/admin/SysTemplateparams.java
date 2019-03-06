package com.intflag.springboot.entity.admin;

import java.util.Date;

import com.intflag.springboot.common.util.DateUtils;

public class SysTemplateparams {
	private String templateId;

	private String tablename;

	private String functioncomment;

	private String classnames;

	private String classpath;

	private String filepath;

	private String keywordcolumn;

	private String pkcolumn;

	private String actionurl;

	private String addfun;

	private String updatefun;

	private String selectfun;

	private String deletefun;

	private String addmenu;

	private String pid;

	private String author;

	private String pattern;

	private String flag;

	private Integer sort;

	private Date cdate;

	private String description;

	public String getCdateStr() {
		return DateUtils.date2String(this.cdate);
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId == null ? null : templateId.trim();
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename == null ? null : tablename.trim();
	}

	public String getFunctioncomment() {
		return functioncomment;
	}

	public void setFunctioncomment(String functioncomment) {
		this.functioncomment = functioncomment == null ? null : functioncomment.trim();
	}

	public String getClassnames() {
		return classnames;
	}

	public void setClassnames(String classnames) {
		this.classnames = classnames == null ? null : classnames.trim();
	}

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath == null ? null : classpath.trim();
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath == null ? null : filepath.trim();
	}

	public String getKeywordcolumn() {
		return keywordcolumn;
	}

	public void setKeywordcolumn(String keywordcolumn) {
		this.keywordcolumn = keywordcolumn == null ? null : keywordcolumn.trim();
	}

	public String getPkcolumn() {
		return pkcolumn;
	}

	public void setPkcolumn(String pkcolumn) {
		this.pkcolumn = pkcolumn == null ? null : pkcolumn.trim();
	}

	public String getActionurl() {
		return actionurl;
	}

	public void setActionurl(String actionurl) {
		this.actionurl = actionurl == null ? null : actionurl.trim();
	}

	public String getAddfun() {
		return addfun;
	}

	public void setAddfun(String addfun) {
		this.addfun = addfun == null ? null : addfun.trim();
	}

	public String getUpdatefun() {
		return updatefun;
	}

	public void setUpdatefun(String updatefun) {
		this.updatefun = updatefun == null ? null : updatefun.trim();
	}

	public String getSelectfun() {
		return selectfun;
	}

	public void setSelectfun(String selectfun) {
		this.selectfun = selectfun == null ? null : selectfun.trim();
	}

	public String getDeletefun() {
		return deletefun;
	}

	public void setDeletefun(String deletefun) {
		this.deletefun = deletefun == null ? null : deletefun.trim();
	}

	public String getAddmenu() {
		return addmenu;
	}

	public void setAddmenu(String addmenu) {
		this.addmenu = addmenu == null ? null : addmenu.trim();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern == null ? null : pattern.trim();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}