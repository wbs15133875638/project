package com.botianzu.project.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 项目测试记录
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
@TableName("project_test_record")
public class TestRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 唯一标识
     */
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
    private Integer deleted;

    /**
     * 测试计划
     */
    private String testPlan;

    /**
     * 测试实例
     */
    private String testBeans;

    /**
     * 测试报告
     */
    private String testReport;

    /**
     * 对应的项目的编号
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

	public String getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(String testPlan) {
		this.testPlan = testPlan;
	}

	public String getTestBeans() {
		return testBeans;
	}

	public void setTestBeans(String testBeans) {
		this.testBeans = testBeans;
	}

	public String getTestReport() {
		return testReport;
	}

	public void setTestReport(String testReport) {
		this.testReport = testReport;
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
