package com.botianzu.project.entity.vo;

/**
 * 项目详情response
 * @author ASUS
 *
 */
public class ProjectDetaisVO {
	
	/**
	 * 项目名称
	 */
	private String name;
	
	/**
	 * 项目编号
	 */
	private String code;
	
	/**
	 * 上传者
	 */
	private String uploadUser;
	
	/**
	 * 项目类型
	 */
	private String projectType;
	
	/**
	 * 项目行业
	 */
	private String projectIndustry;
	
	/**
	 * 标签
	 */
	private String labelName;
	
	/**
	 * 项目简介
	 */
	private String projectDesc;
	
	/**
	 * 开始时间
	 */
	private String beginDate;
	
	/**
	 * 结束时间
	 */
	private String endDate;
	
	/**
	 * 技术
	 */
	private String technology;
	
	
	
	/**
	 * 项目需求文档路径
	 */
	private String projectDocumentUrl;
	
	/**
	 * 项目业务流程图
	 */
	private String projectFlowDiagramUrl;
	
	/**
	 * 项目架构图
	 */
	private String projectSiteStructureUrl;
	
	/**
	 * 项目计划安排
	 */
	private String projectPlanUrl;
	
	/**
	 * 功能设计文档
	 */
	private String projectFunctionDocumentUrl;
	
	/**
	 * 数据库设计文档
	*/
	private String projectDatabaseDocumentUrl;
	
	/**
	 * 测试计划
	 */
	private String testPlanUrl;
	
	
	/**
	 * 测试用例
	 */
	private String testUseCaseUrl;
	
	/**
	 * 测试报告
	 */
	private String testReport;
	
	/**
	 * 低保真
	 */
	private String ueUrl;
	
	/**
	 * 高保真
	 */
	private String uiUrl;
	
	/**
	 * 项目代码
	 */
	private String projectCodeFileUrl;

	@Override
	public String toString() {
		return "ProjectDetaisVO [name=" + name + ", code=" + code + ", uploadUser=" + uploadUser + ", projectType="
				+ projectType + ", projectIndustry=" + projectIndustry + ", labelName=" + labelName + ", projectDesc="
				+ projectDesc + ", beginDate=" + beginDate + ", endDate=" + endDate + ", technology=" + technology
				+ ", projectDocumentUrl=" + projectDocumentUrl + ", projectFlowDiagramUrl=" + projectFlowDiagramUrl
				+ ", projectSiteStructureUrl=" + projectSiteStructureUrl + ", projectPlanUrl=" + projectPlanUrl
				+ ", projectFunctionDocumentUrl=" + projectFunctionDocumentUrl + ", projectDatabaseDocumentUrl="
				+ projectDatabaseDocumentUrl + ", testPlanUrl=" + testPlanUrl + ", testUseCaseUrl=" + testUseCaseUrl
				+ ", testReport=" + testReport + ", ueUrl=" + ueUrl + ", uiUrl=" + uiUrl + "]";
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

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectIndustry() {
		return projectIndustry;
	}

	public void setProjectIndustry(String projectIndustry) {
		this.projectIndustry = projectIndustry;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getProjectDocumentUrl() {
		return projectDocumentUrl;
	}

	public void setProjectDocumentUrl(String projectDocumentUrl) {
		this.projectDocumentUrl = projectDocumentUrl;
	}

	public String getProjectFlowDiagramUrl() {
		return projectFlowDiagramUrl;
	}

	public void setProjectFlowDiagramUrl(String projectFlowDiagramUrl) {
		this.projectFlowDiagramUrl = projectFlowDiagramUrl;
	}

	public String getProjectSiteStructureUrl() {
		return projectSiteStructureUrl;
	}

	public void setProjectSiteStructureUrl(String projectSiteStructureUrl) {
		this.projectSiteStructureUrl = projectSiteStructureUrl;
	}

	public String getProjectPlanUrl() {
		return projectPlanUrl;
	}

	public void setProjectPlanUrl(String projectPlanUrl) {
		this.projectPlanUrl = projectPlanUrl;
	}

	public String getProjectFunctionDocumentUrl() {
		return projectFunctionDocumentUrl;
	}

	public void setProjectFunctionDocumentUrl(String projectFunctionDocumentUrl) {
		this.projectFunctionDocumentUrl = projectFunctionDocumentUrl;
	}

	public String getProjectDatabaseDocumentUrl() {
		return projectDatabaseDocumentUrl;
	}

	public void setProjectDatabaseDocumentUrl(String projectDatabaseDocumentUrl) {
		this.projectDatabaseDocumentUrl = projectDatabaseDocumentUrl;
	}

	public String getTestPlanUrl() {
		return testPlanUrl;
	}

	public void setTestPlanUrl(String testPlanUrl) {
		this.testPlanUrl = testPlanUrl;
	}

	public String getTestUseCaseUrl() {
		return testUseCaseUrl;
	}

	public void setTestUseCaseUrl(String testUseCaseUrl) {
		this.testUseCaseUrl = testUseCaseUrl;
	}

	public String getTestReport() {
		return testReport;
	}

	public void setTestReport(String testReport) {
		this.testReport = testReport;
	}

	public String getUeUrl() {
		return ueUrl;
	}

	public void setUeUrl(String ueUrl) {
		this.ueUrl = ueUrl;
	}

	public String getUiUrl() {
		return uiUrl;
	}

	public void setUiUrl(String uiUrl) {
		this.uiUrl = uiUrl;
	}
	
	

	public String getProjectCodeFileUrl() {
		return projectCodeFileUrl;
	}

	public void setProjectCodeFileUrl(String projectCodeFileUrl) {
		this.projectCodeFileUrl = projectCodeFileUrl;
	}

	public ProjectDetaisVO(String name, String code, String uploadUser, String projectType, String projectIndustry,
			String labelName, String projectDesc, String beginDate, String endDate, String technology,
			String projectDocumentUrl, String projectFlowDiagramUrl, String projectSiteStructureUrl,
			String projectPlanUrl, String projectFunctionDocumentUrl, String projectDatabaseDocumentUrl,
			String testPlanUrl, String testUseCaseUrl, String testReport, String ueUrl, String uiUrl) {
		super();
		this.name = name;
		this.code = code;
		this.uploadUser = uploadUser;
		this.projectType = projectType;
		this.projectIndustry = projectIndustry;
		this.labelName = labelName;
		this.projectDesc = projectDesc;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.technology = technology;
		this.projectDocumentUrl = projectDocumentUrl;
		this.projectFlowDiagramUrl = projectFlowDiagramUrl;
		this.projectSiteStructureUrl = projectSiteStructureUrl;
		this.projectPlanUrl = projectPlanUrl;
		this.projectFunctionDocumentUrl = projectFunctionDocumentUrl;
		this.projectDatabaseDocumentUrl = projectDatabaseDocumentUrl;
		this.testPlanUrl = testPlanUrl;
		this.testUseCaseUrl = testUseCaseUrl;
		this.testReport = testReport;
		this.ueUrl = ueUrl;
		this.uiUrl = uiUrl;
	}

	public ProjectDetaisVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
