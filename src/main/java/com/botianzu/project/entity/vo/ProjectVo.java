package com.botianzu.project.entity.vo;

import java.util.List;

import com.botianzu.project.entity.RequireModel;

public class ProjectVo {

	/**
	 * 项目名
	 */
	private String name;
	
	/**
	 * 项目编号
	 */
	private String code;
	
	/**
	 * 项目类型
	 */
	private String typeName;
	
	/**
	 * 行业
	 */
	private String industry;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 标签
	 */
	private String label;
	
	/**
	 * 技术点
	 */
	private String technologs;
	
	/**
	 * 简介
	 */
	private String description;
	
	/**
	 * 项目模块
	 */
	private List<RequireModel> models;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTechnologs() {
		return technologs;
	}

	public void setTechnologs(String technologs) {
		this.technologs = technologs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RequireModel> getModels() {
		return models;
	}

	public void setModels(List<RequireModel> models) {
		this.models = models;
	}
	
}
