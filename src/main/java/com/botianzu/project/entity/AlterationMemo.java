package com.botianzu.project.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * <p>
 * 变更记录
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public class AlterationMemo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 变更记录名称
     */
    private String name;

    /**
     * 唯一标识符
     */
    @TableId
    private String code;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 0 已删除 1 未删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 提交变更时间
     */
    private Date submissionApprovalTime;

    /**
     * 变更时间
     */
    private Date approvalTime;

    /**
     * 提交变更时间
     */
    private String submissionApprovalPeople;

    /**
     * 审核人
     */
    private String approvalPeople;

    /**
     * 0 变更中 1 变更成功 2 变更失败
     */
    private Integer approvalResult;

    /**
     * 备注
     */
    private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getSubmissionApprovalTime() {
		return submissionApprovalTime;
	}

	public void setSubmissionApprovalTime(Date submissionApprovalTime) {
		this.submissionApprovalTime = submissionApprovalTime;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getSubmissionApprovalPeople() {
		return submissionApprovalPeople;
	}

	public void setSubmissionApprovalPeople(String submissionApprovalPeople) {
		this.submissionApprovalPeople = submissionApprovalPeople;
	}

	public String getApprovalPeople() {
		return approvalPeople;
	}

	public void setApprovalPeople(String approvalPeople) {
		this.approvalPeople = approvalPeople;
	}

	public Integer getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
