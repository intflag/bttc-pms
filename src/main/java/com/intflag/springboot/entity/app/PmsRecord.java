package com.intflag.springboot.entity.app;

import java.util.Date;

public class PmsRecord {
    private String recordId;

    private String planId;

    private String planType;

    private String planName;

    private String userId;

    private String username;

    private String nickname;

    private String teachId;

    private String teachUser;

    private String teachNick;

    private String paperName;

    private Integer paperSubmitCount;

    private Integer guideCount;

    private Date releaseDate;

    private String flag;

    private Integer sort;

    private Date cdate;

    private Date mdate;

    private String description;

    private String field01;

    private String field02;

    private String field03;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType == null ? null : planType.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
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

    public String getTeachId() {
        return teachId;
    }

    public void setTeachId(String teachId) {
        this.teachId = teachId == null ? null : teachId.trim();
    }

    public String getTeachUser() {
        return teachUser;
    }

    public void setTeachUser(String teachUser) {
        this.teachUser = teachUser == null ? null : teachUser.trim();
    }

    public String getTeachNick() {
        return teachNick;
    }

    public void setTeachNick(String teachNick) {
        this.teachNick = teachNick == null ? null : teachNick.trim();
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public Integer getPaperSubmitCount() {
        return paperSubmitCount;
    }

    public void setPaperSubmitCount(Integer paperSubmitCount) {
        this.paperSubmitCount = paperSubmitCount;
    }

    public Integer getGuideCount() {
        return guideCount;
    }

    public void setGuideCount(Integer guideCount) {
        this.guideCount = guideCount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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