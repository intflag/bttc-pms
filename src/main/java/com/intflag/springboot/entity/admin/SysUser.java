package com.intflag.springboot.entity.admin;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.intflag.springboot.common.util.DateUtils;

public class SysUser {

    private String userId;

    @Excel(name = "学号/教师工号",width = 20)
    private String username;

    private String password;

    @Excel(name = "姓名",width = 20)
    private String nickname;

    @Excel(name = "性别",width = 20)
    private String gender;

    @Excel(name = "邮箱",width = 20)
    private String email;

    private String address;

    @Excel(name = "电话",width = 20)
    private String telephone;

    private String flag;

    private Date cdate;

    private Date mdate;

    private String description;

    @Excel(name = "微信号",width = 20)
    private String wechatId;

    @Excel(name = "QQ号",width = 20)
    private String qqId;

    private String groupName;

    private String groupId;

    private String userType;

    private String field01;

    private String field02;


    // 角色ID
    private String roleId[];

    public String getCdateStr() {
        return DateUtils.date2String(this.cdate);
    }

    public String getMdateStr() {
        return DateUtils.date2String(this.mdate);
    }

    public String[] getRoleId() {
        return roleId;
    }

    public void setRoleId(String[] roleId) {
        this.roleId = roleId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
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

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId == null ? null : qqId.trim();
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
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
}