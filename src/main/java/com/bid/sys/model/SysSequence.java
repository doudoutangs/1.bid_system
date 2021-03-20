package com.bid.sys.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class SysSequence implements Serializable {

	@Id
    private String seqCode;

    private Integer currentSeq;

    private Integer minLength;

    private String fixStart;

    private String fixEnd;

    private Date createDate;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getSeqCode() {
        return seqCode;
    }

    public void setSeqCode(String seqCode) {
        this.seqCode = seqCode == null ? null : seqCode.trim();
    }

    public Integer getCurrentSeq() {
        return currentSeq;
    }

    public void setCurrentSeq(Integer currentSeq) {
        this.currentSeq = currentSeq;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public String getFixStart() {
        return fixStart;
    }

    public void setFixStart(String fixStart) {
        this.fixStart = fixStart == null ? null : fixStart.trim();
    }

    public String getFixEnd() {
        return fixEnd;
    }

    public void setFixEnd(String fixEnd) {
        this.fixEnd = fixEnd == null ? null : fixEnd.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        sb.append(", seqCode=").append(seqCode);
        sb.append(", currentSeq=").append(currentSeq);
        sb.append(", minLength=").append(minLength);
        sb.append(", fixStart=").append(fixStart);
        sb.append(", fixEnd=").append(fixEnd);
        sb.append(", createDate=").append(createDate);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}