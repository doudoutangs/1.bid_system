package com.bid.sys.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SysOrgVo implements Serializable {
	
    private String orgId;

    @JsonProperty("pid")
    private String orgPid;

    private String orgName;

    private String orgAddress;

    private String orgStatus;

    private String orgType;

    private String orgLevel;

    @JsonProperty("iconCls")
    private String icon;

    private Integer orgSeq;

    private String description;

    private String field1;

    private String field2;

    private String field3;

    private static final long serialVersionUID = 1L;
    
    private Integer countAllChild;//子节点全部个数
    
    public Integer getCountAllChild() {
		return countAllChild;
	}

	public void setCountAllChild(Integer countAllChild) {
		this.countAllChild = countAllChild;
	}

	public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgPid() {
        return orgPid;
    }

    public void setOrgPid(String orgPid) {
        this.orgPid = orgPid == null ? null : orgPid.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    public String getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus == null ? null : orgStatus.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel == null ? null : orgLevel.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(Integer orgSeq) {
        this.orgSeq = orgSeq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1 == null ? null : field1.trim();
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2 == null ? null : field2.trim();
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3 == null ? null : field3.trim();
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysOrgVo [countAllChild=");
		builder.append(countAllChild);
		builder.append(", description=");
		builder.append(description);
		builder.append(", field1=");
		builder.append(field1);
		builder.append(", field2=");
		builder.append(field2);
		builder.append(", field3=");
		builder.append(field3);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", orgAddress=");
		builder.append(orgAddress);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", orgLevel=");
		builder.append(orgLevel);
		builder.append(", orgName=");
		builder.append(orgName);
		builder.append(", orgPid=");
		builder.append(orgPid);
		builder.append(", orgSeq=");
		builder.append(orgSeq);
		builder.append(", orgStatus=");
		builder.append(orgStatus);
		builder.append(", orgType=");
		builder.append(orgType);
		builder.append("]");
		return builder.toString();
	}

}