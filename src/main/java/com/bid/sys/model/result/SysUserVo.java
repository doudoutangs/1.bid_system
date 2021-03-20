package com.bid.sys.model.result;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bid.sys.model.SysOrg;
import com.bid.sys.model.SysRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SysUserVo implements Serializable {
	
	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("login_name")
    private String loginName;

    private String password;
    
    private String plainPassword;

    private String salt;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_status")
    private String userStatus;

    private String userLevel;

    private String userFlag;

    @JsonProperty("org_id")
    private String orgId;

    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthDate;

    private String phone;

    private String email;

    private String address;

    private String description;

	@JsonProperty("login_count")
    private Integer loginCount;

	@JsonProperty("lastvisit_date")
    private Date lastvisitDate;

    @JsonProperty("create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;
    
    private List<SysRole> roleList; //角色集合
    private String roleIds;
    private SysOrg sysOrg;	//所属机构

    private static final long serialVersionUID = 1L;

	public SysOrg getSysOrg() {
		return sysOrg;
	}

	public void setSysOrg(SysOrg sysOrg) {
		this.sysOrg = sysOrg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLastvisitDate() {
		return lastvisitDate;
	}

	public void setLastvisitDate(Date lastvisitDate) {
		this.lastvisitDate = lastvisitDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysUserVo [address=");
		builder.append(address);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", email=");
		builder.append(email);
		builder.append(", field1=");
		builder.append(field1);
		builder.append(", field2=");
		builder.append(field2);
		builder.append(", field3=");
		builder.append(field3);
		builder.append(", field4=");
		builder.append(field4);
		builder.append(", field5=");
		builder.append(field5);
		builder.append(", lastvisitDate=");
		builder.append(lastvisitDate);
		builder.append(", loginCount=");
		builder.append(loginCount);
		builder.append(", loginName=");
		builder.append(loginName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", plainPassword=");
		builder.append(plainPassword);
		builder.append(", roleIds=");
		builder.append(roleIds);
		builder.append(", roleList=");
		builder.append(roleList);
		builder.append(", salt=");
		builder.append(salt);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", userFlag=");
		builder.append(userFlag);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userLevel=");
		builder.append(userLevel);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userStatus=");
		builder.append(userStatus);
		builder.append("]");
		return builder.toString();
	}
	
}
