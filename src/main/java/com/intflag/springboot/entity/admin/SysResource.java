package com.intflag.springboot.entity.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intflag.springboot.common.util.DateUtils;

public class SysResource {
	private String resourceId = "";

	private String resname = "";

	private String resurl = "";

	private String rescode = "";

	private String type = "";

	private String icon = "";

	private String parentId = "";

	private Byte isParent = new Byte("0");

	private String description = "";

	private String flag = "";

	private Integer sort = 0;

	private Date cdate;

	private Date mdate;

	List<SysResource> sysResources = new ArrayList<>();

	public String getCdateStr() {
		return DateUtils.date2String(this.cdate);
	}

	public String getMdateStr() {
		return DateUtils.date2String(this.mdate);
	}

	public String getJump() {
		return this.resurl;
	}

	public String getName() {
		return this.resurl;
	}

	public String getTitle() {
		return this.resname;
	}

	public List<SysResource> getList() {
		return this.sysResources;
	}

	public List<SysResource> getSysResources() {
		return sysResources;
	}

	public void setSysResources(List<SysResource> sysResources) {
		this.sysResources = sysResources;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId == null ? null : resourceId.trim();
	}

	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname == null ? null : resname.trim();
	}

	public String getResurl() {
		return resurl;
	}

	public void setResurl(String resurl) {
		this.resurl = resurl == null ? null : resurl.trim();
	}

	public String getRescode() {
		return rescode;
	}

	public void setRescode(String rescode) {
		this.rescode = rescode == null ? null : rescode.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public Byte getIsParent() {
		return isParent;
	}

	public void setIsParent(Byte isParent) {
		this.isParent = isParent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
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

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
}