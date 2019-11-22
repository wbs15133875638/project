package com.botianzu.project.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * <p>
 * 审核记录表
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public class ApprovedMemo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 审批记录名称
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
     * 审批时间
     */
    private Date approvalTime;

    /**
     * 审核人
     */
    private String approvalPeople;

    /**
     * 1 通过 2 驳回
     */
    private Integer approvalResult;

    /**
     * 备注
     */
    private String remark;

    /**
     * 对应的审批流程编号
     */
    private String flowCode;

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

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
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

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

    
}
