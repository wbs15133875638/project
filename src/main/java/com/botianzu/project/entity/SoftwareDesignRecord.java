package com.botianzu.project.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

/**
 * <p>
 * 软件设计表记录
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public class SoftwareDesignRecord implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 架构图
     */
    private String siteStructure;

    /**
     * 功能接口设计文档
     */
    private String serviceFile;

    /**
     * 数据库设计文档
     */
    private String databaseFile;

    /**
     * 记录编号
     */
    private String memoCode;
    
    private String projectCode;
    

    
    /**
     * 项目前台技术
     */
    private String clientTechnologies;
    
    /**
     * 项目后台技术
     */
    private String serviceTechnologies;

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

	public String getSiteStructure() {
		return siteStructure;
	}

	public void setSiteStructure(String siteStructure) {
		this.siteStructure = siteStructure;
	}

	public String getServiceFile() {
		return serviceFile;
	}

	public void setServiceFile(String serviceFile) {
		this.serviceFile = serviceFile;
	}

	public String getDatabaseFile() {
		return databaseFile;
	}

	public void setDatabaseFile(String databaseFile) {
		this.databaseFile = databaseFile;
	}

	public String getMemoCode() {
		return memoCode;
	}

	public void setMemoCode(String memoCode) {
		this.memoCode = memoCode;
	}

	public String getClientTechnologies() {
		return clientTechnologies;
	}

	public void setClientTechnologies(String clientTechnologies) {
		this.clientTechnologies = clientTechnologies;
	}

	public String getServiceTechnologies() {
		return serviceTechnologies;
	}

	public void setServiceTechnologies(String serviceTechnologies) {
		this.serviceTechnologies = serviceTechnologies;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

    
}
