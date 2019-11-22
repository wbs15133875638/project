package com.botianzu.project.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * <p>
 * 审批流程
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public class AuditFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 提交待审核
     */
    public static final Integer STATUS_SUBMIT = 1;
    
    /**
     * 主任审核通过
     */
    public static final Integer STATUS_ONE_PASS = 2;
    
    /**
     * 主任审核驳回
     */
    public static final Integer STATUS_ONE_NOPASS = 3;
    
    /**
     * 院长审核通过
     */
    public static final Integer STATUS_TWO_PASS = 4;
    
    /**
     * 院长审核驳回
     */
    public static final Integer STATUS_TWO_NOPASS = 5;
    
    
    private Integer id;

    @TableId
    private String code;

    private String name;

    @TableLogic
    private Integer deleted;
    
	private Date createTime;

    private Date updateTime;
    
    /**
     * 流程进度
     */
    private List<ApprovedMemo> approvedMemos;

    /**
     * 提交审核用户编号
     */
    private String submitUserCode;
    
    /**
     * 提交用户姓名
     */
    private String submitUserName;

    /**
     * 提交审核时间
     */
    private Date submitTime;
    
    /**
     *  项目名称
     */
    private String projectName;

    /**
     * 该流程状态
     */
    private Integer status;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	public String getSubmitUserCode() {
		return submitUserCode;
	}

	public void setSubmitUserCode(String submitUserCode) {
		this.submitUserCode = submitUserCode;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer statusSubmit) {
		this.status = statusSubmit;
	}

	public String getSubmitUserName() {
		return submitUserName;
	}

	public void setSubmitUserName(String submitUserName) {
		this.submitUserName = submitUserName;
	}

	public List<ApprovedMemo> getApprovedMemos() {
		return approvedMemos;
	}

	public void setApprovedMemos(List<ApprovedMemo> approvedMemos) {
		this.approvedMemos = approvedMemos;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

 
}
