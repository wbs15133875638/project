package com.botianzu.project.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 草稿
     */
    public static final Integer STATUS_DRAFT = 1;
    
    /**
     * 审核中
     */
    public static final Integer STATUS_AUDIT = 2;
    
    /**
     * 审核通过
     */
    public static final Integer STATUS_AUDIT_PASS = 3;
    
    /**
     * 审核未通过
     */
    public static final Integer STATUS_AUDIT_NOPASS = 4;
    
    /**
     * 变更中
     */
    public static final Integer STATUS_CHANGE = 5;
    
    /**
     * 变更通过
     */
    public static final Integer STATUS_CHANGE_PASS = 6;
    
    /**
     * 变更未通过
     */
    public static final Integer STATUS_CHANGE_NOPASS = 7;

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 唯一标识
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
     * 是否删除 0:已删除 1:未删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 项目类型
     */
    private String projectType;

    /**
     * 项目简介
     */
    private String introduction;

    /**
     * 项目状态
1:草稿 2:审批中 3:审批成功 4:审批失败 5:变更中 6:变更成功 7:变更失败 
     */
    private Integer status;

    /**
     * 项目行业编号
     */
    private String projectIndustryCode;

    /**
     * 标签(暂时)
     */
    private String labels;

    /**
     * 代码路径
     */
    private String codePath;

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

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProjectIndustryCode() {
		return projectIndustryCode;
	}

	public void setProjectIndustryCode(String projectIndustryCode) {
		this.projectIndustryCode = projectIndustryCode;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}

    
}
