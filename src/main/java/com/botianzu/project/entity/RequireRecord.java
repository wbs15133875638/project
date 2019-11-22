package com.botianzu.project.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 项目需求
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
@TableName("project_require_record")
public class RequireRecord implements Serializable {

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
     * 需求文档
     */
    private String documentPath;

    /**
     * 流程图
     */
    private String flowDiagram;

    /**
     * 对应项目编号
     */
    private String projectCode;

    /**
     * 记录编号
     */
    private String memoCode;

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

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getFlowDiagram() {
		return flowDiagram;
	}

	public void setFlowDiagram(String flowDiagram) {
		this.flowDiagram = flowDiagram;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getMemoCode() {
		return memoCode;
	}

	public void setMemoCode(String memoCode) {
		this.memoCode = memoCode;
	}

    
}
