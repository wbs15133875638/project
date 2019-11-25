package com.botianzu.project.service;

import com.botianzu.project.entity.Project;
import com.botianzu.project.entity.vo.ChangeProjectRequest;
import com.botianzu.project.entity.vo.ProjectDetaisVO;
import com.botianzu.project.entity.vo.ProjectRecordDetailsVO;
import com.botianzu.project.entity.vo.ProjectRequest;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目 服务类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public interface IProjectService extends IService<Project> {

	/**
	 * 上传项目
	 * @param file
	 * @return
	 */
	String upload(MultipartFile file);

	/**
	 * 添加项目
	 * @param projectRequest
	 * @param isDraft
	 * @return
	 */
	boolean addProject(ProjectRequest projectRequest, Integer isDraft);

	/*
	 * 查询项目列表
	 */
	HashMap<String, Object> findProjects(QueryWrapper<Project> queryWrapper, Page<Project> page);

	/**
	 * 修改项目
	 * @param projectCode
	 * @param projectRequest
	 * @return
	 */
	boolean editProjectInfo(String projectCode, ProjectRequest projectRequest);

	/**
	 * 项目详情
	 * @param code
	 * @return
	 */
	ProjectDetaisVO queryDetaisByCode(String code);

	/**
	 * 添加项目变更记录
	 * @param changeProjectRequest
	 * @return
	 */
	boolean changeProject(ChangeProjectRequest changeProjectRequest);

	boolean auditDraft(String code, Integer passed, String user);
	
	public ProjectRecordDetailsVO getProjectRecordDetail(String auditFlowCode);

	boolean auditChange(String code, Integer passed, String user);

}
