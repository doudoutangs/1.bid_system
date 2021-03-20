package com.bid.sys.model;

import java.io.Serializable;

public class SysRoleResource implements Serializable {

    private String roleId;

    private String resourceId;

    private static final long serialVersionUID = 1L;

    public SysRoleResource(String roleId, String resourceId) {
		super();
		this.roleId = roleId;
		this.resourceId = resourceId;
	}
	
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}