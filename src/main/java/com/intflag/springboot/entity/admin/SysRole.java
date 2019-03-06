package com.intflag.springboot.entity.admin;

import java.util.Date;

import com.intflag.springboot.common.util.DateUtils;

public class SysRole {
	private String roleId;

	private String rolename;

	private String flag;

	private Integer sort;

	private String description;

	private Date cdate;

	private Date mdate;

	// 资源权限
	private String resourceIds;

	public String getCdateStr() {
		return DateUtils.date2String(this.cdate);
	}

	public String getMdateStr() {
		return DateUtils.date2String(this.mdate);
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename == null ? null : rolename.trim();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
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