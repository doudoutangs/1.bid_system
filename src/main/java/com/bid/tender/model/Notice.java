package com.bid.tender.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 16:33
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 3. gitee(码云)主页：https://gitee.com/spdoudoutang
 */
@Table(name = "b_notice")
public class Notice implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String userId;
    private String noticeDate;

    private String title;

    private String content;

    private String fileId;

    private String type;
    private String tenderDate;
    private String badDate;

    private String delFlag;
    private String amount;
    /**
     * 行业类型
     */
    private String industryType;
    /**
     * 招标状态：0-投标中；1-评标中；2-招标结束
     */
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(String tenderDate) {
        this.tenderDate = tenderDate;
    }

    public String getBadDate() {
        return badDate;
    }

    public void setBadDate(String badDate) {
        this.badDate = badDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate == null ? null : noticeDate.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", noticeDate=").append(noticeDate);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", fileId=").append(fileId);
        sb.append(", type=").append(type);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}