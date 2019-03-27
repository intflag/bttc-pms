package com.intflag.springboot.entity.app;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PmsPlan {
    private String planId;

    private String planName;

    private String groupId;

    private String groupName;

    private String userId;

    private String username;

    private String nickname;

    private String planType;

    private Integer planCount;

    private Integer realityCount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date submitDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private String flag;

    private Integer sort;

    private Date cdate;

    private Date mdate;

    private String description;

    private String field01;

    private String field02;

    private String field03;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType == null ? null : planType.trim();
    }

    public Integer getPlanCount() {
        return planCount;
    }

    public void setPlanCount(Integer planCount) {
        this.planCount = planCount;
    }

    public Integer getRealityCount() {
        return realityCount;
    }

    public void setRealityCount(Integer realityCount) {
        this.realityCount = realityCount;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getField01() {
        return field01;
    }

    public void setField01(String field01) {
        this.field01 = field01 == null ? null : field01.trim();
    }

    public String getField02() {
        return field02;
    }

    public void setField02(String field02) {
        this.field02 = field02 == null ? null : field02.trim();
    }

    public String getField03() {
        return field03;
    }

    public void setField03(String field03) {
        this.field03 = field03 == null ? null : field03.trim();
    }
}