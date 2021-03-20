package com.bid.sys.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SysResourceVo implements Serializable {

	private String resourceId;

	@JsonProperty("pid")
	private String resourcePid;

	private String resourceName;

	private String resourceType;

	private String permCode;

	private String url;

	@JsonProperty("iconCls")
	private String icon;

	private String resourceStatus;

	private String resourceLevel;

	private String resourceFlag;

	private Integer resourceSeq;

	private String description;

	private String field1;

	private String field2;

	private String field3;

	private String state;// oper,closed
	
	private Integer countAllChild;//子节点全部个数
	private Integer countMenuChild;//子节点为菜单类型的个数

	private static final long serialVersionUID = 1L;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId == null ? null : resourceId.trim();
	}

	public String getResourcePid() {
		return resourcePid;
	}

	public void setResourcePid(String resourcePid) {
		this.resourcePid = resourcePid == null ? null : resourcePid.trim();
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName == null ? null : resourceName.trim();
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType == null ? null : resourceType.trim();
	}

	public String getPermCode() {
		return permCode;
	}

	public void setPermCode(String permCode) {
		this.permCode = permCode == null ? null : permCode.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public String getResourceStatus() {
		return resourceStatus;
	}

	public void setResourceStatus(String resourceStatus) {
		this.resourceStatus = resourceStatus == null ? null : resourceStatus
				.trim();
	}

	public String getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(String resourceLevel) {
		this.resourceLevel = resourceLevel == null ? null : resourceLevel
				.trim();
	}

	public String getResourceFlag() {
		return resourceFlag;
	}

	public void setResourceFlag(String resourceFlag) {
		this.resourceFlag = resourceFlag == null ? null : resourceFlag.trim();
	}

	public Integer getResourceSeq() {
		return resourceSeq;
	}

	public void setResourceSeq(Integer resourceSeq) {
		this.resourceSeq = resourceSeq;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setState(Boolean bool) {
		if (bool) {
			this.state = "open";
		} else {
			this.state = "closed";
		}
	}
	

	public Integer getCountAllChild() {
		return countAllChild;
	}

	public void setCountAllChild(Integer countAllChild) {
		this.countAllChild = countAllChild;
	}

	public Integer getCountMenuChild() {
		return countMenuChild;
	}

	public void setCountMenuChild(Integer countMenuChild) {
		this.countMenuChild = countMenuChild;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysResourceVo [countAllChild=");
		builder.append(countAllChild);
		builder.append(", countMenuChild=");
		builder.append(countMenuChild);
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
		builder.append(", permCode=");
		builder.append(permCode);
		builder.append(", resourceFlag=");
		builder.append(resourceFlag);
		builder.append(", resourceId=");
		builder.append(resourceId);
		builder.append(", resourceLevel=");
		builder.append(resourceLevel);
		builder.append(", resourceName=");
		builder.append(resourceName);
		builder.append(", resourcePid=");
		builder.append(resourcePid);
		builder.append(", resourceSeq=");
		builder.append(resourceSeq);
		builder.append(", resourceStatus=");
		builder.append(resourceStatus);
		builder.append(", resourceType=");
		builder.append(resourceType);
		builder.append(", state=");
		builder.append(state);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

	

}