package com.bid.sys.model.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SysDictVo implements Serializable {

    private String dictId;

    @JsonProperty("pid")
    private String dictPid;

    private String dictType;

    private String dictCode;

    private String dictName;

    private String dictFlag;

    private Integer dictSeq;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    public String getDictPid() {
        return dictPid;
    }

    public void setDictPid(String dictPid) {
        this.dictPid = dictPid == null ? null : dictPid.trim();
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictFlag() {
        return dictFlag;
    }

    public void setDictFlag(String dictFlag) {
        this.dictFlag = dictFlag == null ? null : dictFlag.trim();
    }

    public Integer getDictSeq() {
        return dictSeq;
    }

    public void setDictSeq(Integer dictSeq) {
        this.dictSeq = dictSeq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dictId=").append(dictId);
        sb.append(", dictPid=").append(dictPid);
        sb.append(", dictType=").append(dictType);
        sb.append(", dictCode=").append(dictCode);
        sb.append(", dictName=").append(dictName);
        sb.append(", dictFlag=").append(dictFlag);
        sb.append(", dictSeq=").append(dictSeq);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}