package com.botianzu.project.entity.vo;

import java.util.List;

import com.botianzu.project.entity.RequireModel;

/**
 * 修改项目时 接收到的数据
 * @author 刘彦昌
 *
 */
public class ChangeProjectRequest {

	
	/**
	 * 项目编号
	 */
	private String code;
	
	/**
	 * 项目名
	 */
	private String name;
	
	/**
	 * 标签
	 */
	private String labelName;
	
	/**
	 * 项目类型
	 */
	private String projectTypeCode;
	
	/**
	 * 行业
	 */
	private String industryCode;
	
	/**
	 * 简介
	 */
	private String description;
	
	/**
	 * 需求文档路径
	 */
	private String requireDocumentFileUrl;
	
	/**
	 *  流程图路径
	 */
	private String processPictureFileUrl;
	
	/**
	 * UE文件路径
	 */
	private String ueFileUrl;
	
	/**
	 * UI文件路径
	 */
	private String uiFileUrl;
	
	/**
	 * 客户端技术
	 */
	private String clientTechnologyCodes;
	
	/**
	 * 服务端技术
	 */
	private String serverTechnologyCodes;
	
	/**
	 * 功能文档路径
	 */
	private String functionDocumentFileUrl;
	
	/**
	 * 数据库设计文档 路径
	 */
	private String databaseDocumentFileUrl;
	
	/**
	 * 项目开始时间
	 */
	private String projectBeginDate;
	
	/**
	 * 项目结束时间
	 */
	private String projectEndDate;
	
	/**
	 * 工作天数
	 */
	private Integer workingDays;
	
	/**
	 * 工作安排文档 路径
	 */
	private String wbsFileUrl;
	
	/**
	 * 项目测试计划 文档 路径
	 */
	private String projectTestPlanDocumentFileUrl;
	
	/**
	 * 项目测试实例 文档 路径
	 */
	private String projectTestInstanceDocumentFileUrl;
	
	/**
	 * 项目测试报告 文档 路径
	 */
	private String projectTestReportDocumentFileUrl;
	
	/**
	 * 项目代码路径
	 */
	private String projectCodeFileUrl;
	
	/**
	 * 软件架构图 路径
	 */
	private String SiteStructureFileUrl;
	
	
	
	/**
	 * 项目模块
	 */
	private List<RequireModel> models;



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



	public String getLabelName() {
		return labelName;
	}



	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}



	public String getProjectTypeCode() {
		return projectTypeCode;
	}



	public void setProjectTypeCode(String projectTypeCode) {
		this.projectTypeCode = projectTypeCode;
	}



	public String getIndustryCode() {
		return industryCode;
	}



	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getRequireDocumentFileUrl() {
		return requireDocumentFileUrl;
	}



	public void setRequireDocumentFileUrl(String requireDocumentFileUrl) {
		this.requireDocumentFileUrl = requireDocumentFileUrl;
	}



	public String getProcessPictureFileUrl() {
		return processPictureFileUrl;
	}



	public void setProcessPictureFileUrl(String processPictureFileUrl) {
		this.processPictureFileUrl = processPictureFileUrl;
	}



	public String getUeFileUrl() {
		return ueFileUrl;
	}



	public void setUeFileUrl(String ueFileUrl) {
		this.ueFileUrl = ueFileUrl;
	}



	public String getUiFileUrl() {
		return uiFileUrl;
	}



	public void setUiFileUrl(String uiFileUrl) {
		this.uiFileUrl = uiFileUrl;
	}



	public String getClientTechnologyCodes() {
		return clientTechnologyCodes;
	}



	public void setClientTechnologyCodes(String clientTechnologyCodes) {
		this.clientTechnologyCodes = clientTechnologyCodes;
	}



	public String getServerTechnologyCodes() {
		return serverTechnologyCodes;
	}



	public void setServerTechnologyCodes(String serverTechnologyCodes) {
		this.serverTechnologyCodes = serverTechnologyCodes;
	}



	public String getFunctionDocumentFileUrl() {
		return functionDocumentFileUrl;
	}



	public void setFunctionDocumentFileUrl(String functionDocumentFileUrl) {
		this.functionDocumentFileUrl = functionDocumentFileUrl;
	}



	public String getDatabaseDocumentFileUrl() {
		return databaseDocumentFileUrl;
	}



	public void setDatabaseDocumentFileUrl(String databaseDocumentFileUrl) {
		this.databaseDocumentFileUrl = databaseDocumentFileUrl;
	}



	public String getProjectBeginDate() {
		return projectBeginDate;
	}



	public void setProjectBeginDate(String projectBeginDate) {
		this.projectBeginDate = projectBeginDate;
	}



	public String getProjectEndDate() {
		return projectEndDate;
	}



	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}



	public Integer getWorkingDays() {
		return workingDays;
	}



	public void setWorkingDays(Integer workingDays) {
		this.workingDays = workingDays;
	}



	public String getWbsFileUrl() {
		return wbsFileUrl;
	}



	public void setWbsFileUrl(String wbsFileUrl) {
		this.wbsFileUrl = wbsFileUrl;
	}



	public String getProjectTestPlanDocumentFileUrl() {
		return projectTestPlanDocumentFileUrl;
	}



	public void setProjectTestPlanDocumentFileUrl(String projectTestPlanDocumentFileUrl) {
		this.projectTestPlanDocumentFileUrl = projectTestPlanDocumentFileUrl;
	}



	public String getProjectTestInstanceDocumentFileUrl() {
		return projectTestInstanceDocumentFileUrl;
	}



	public void setProjectTestInstanceDocumentFileUrl(String projectTestInstanceDocumentFileUrl) {
		this.projectTestInstanceDocumentFileUrl = projectTestInstanceDocumentFileUrl;
	}



	public String getProjectTestReportDocumentFileUrl() {
		return projectTestReportDocumentFileUrl;
	}



	public void setProjectTestReportDocumentFileUrl(String projectTestReportDocumentFileUrl) {
		this.projectTestReportDocumentFileUrl = projectTestReportDocumentFileUrl;
	}



	public String getProjectCodeFileUrl() {
		return projectCodeFileUrl;
	}



	public void setProjectCodeFileUrl(String projectCodeFileUrl) {
		this.projectCodeFileUrl = projectCodeFileUrl;
	}



	public String getSiteStructureFileUrl() {
		return SiteStructureFileUrl;
	}



	public void setSiteStructureFileUrl(String siteStructureFileUrl) {
		SiteStructureFileUrl = siteStructureFileUrl;
	}





	public List<RequireModel> getModels() {
		return models;
	}



	public void setModels(List<RequireModel> models) {
		this.models = models;
	}


	


	
	
}
