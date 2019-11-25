package com.botianzu.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.botianzu.project.entity.Industry;
import com.botianzu.project.entity.Project;
import com.botianzu.project.entity.ProjectLabel;
import com.botianzu.project.entity.ProjectStatus;
import com.botianzu.project.entity.ProjectType;
import com.botianzu.project.entity.vo.ProjectDetaisVO;
import com.botianzu.project.entity.vo.ProjectRecordDetailsVO;
import com.botianzu.project.entity.vo.ProjectRequest;
import com.botianzu.project.service.IIndustryService;
import com.botianzu.project.service.IProjectLabelService;
import com.botianzu.project.service.IProjectService;
import com.botianzu.project.service.IProjectStatusService;
import com.botianzu.project.service.IProjectTypeService;
import com.botianzu.project.service.ITechnologyService;
import com.botianzu.project.utils.FastDFSClient;

/**
 * <p>
 * 项目 控制器
 * </p>
 *
 * @author dan
 * @since 2019-11-20
*/
@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController{
	
	private final Integer pageSize = 5;
	
	@Autowired
	private IProjectService iProjectService;
	
	@Autowired
	private FastDFSClient fastDFSClient;
	
	@Autowired
	private IProjectTypeService iProjectTypeService;
	
	@Autowired
	private IProjectLabelService iProjectLabelService;
	
	@Autowired
	private IProjectStatusService IProjectStatusService;
	
	@Autowired
	private ITechnologyService iTechnologyService;
	
	@Autowired
	private IIndustryService iIndustryService;
	
	/**
	 *上传文件
	 * @param file 文件
	 * @return 文件存储路径
	 * @throws IOException 
	 */
	@RequestMapping("upload")
	public String upload(MultipartFile file) throws IOException {
		
		String fileUrl = fastDFSClient.uploadFile(file);
		
		return fileUrl;
		
	}
	
	/**
	 * 添加项目
	 * @param projectRequest
	 * @param isDraft
	 * @return
	 */
	@RequestMapping("/save/{isDraft}")
	public Boolean saveProjectInfo2Draft(@RequestBody ProjectRequest projectRequest, @PathVariable Integer isDraft) {
		boolean result = iProjectService.addProject(projectRequest,isDraft);
		return result;
	}
	
	/**
	 * 草稿状态 修改项目
	 * @param projectRequest
	 * @param projectCode
	 * @return
	 */
	@RequestMapping("/修改/{projectCode}")
	public Boolean updateProjectInfo2DRraft(@RequestBody ProjectRequest projectRequest, @PathVariable String projectCode) {
		
		boolean result = iProjectService.editProjectInfo(projectCode,projectRequest);
		
		return result;
		
	}
	
	/**
	 * 查询项目列表数据
	 * @param name
	 * @param industryCode
	 * @param statusCode
	 * @param typeCode
	 * @param labelName
	 * @param curPage
	 * @return
	 */
	@RequestMapping
	public Map findProjects(@RequestParam(defaultValue="" , name="name")String name, @RequestParam(defaultValue="" , name="industryCode")String industryCode, @RequestParam(defaultValue="" , name="statusCode") String statusCode, @RequestParam(defaultValue="" , name="typeCode")String typeCode, @RequestParam(defaultValue="" , name="labelName")String labelName, Integer curpage) {
		
		
		QueryWrapper<Project> queryWrapper = new QueryWrapper<Project>();
		
		if(!"".equals(name.trim())) {
			queryWrapper.like("name",name.trim());
		}
		if(!"".equals(industryCode.trim())) {
			queryWrapper.eq("project_industry_code", industryCode.trim());
		}
		if(!"".equals(statusCode.trim())) {
			queryWrapper.eq("status", statusCode.trim());
		}
		if(StringUtils.isNotBlank(typeCode)) {
			queryWrapper.eq("project_type", typeCode.trim());
		}
		if(!"".equals(labelName.trim())) {
			queryWrapper.like("labels", labelName.trim());
		}
		queryWrapper.orderByDesc("create_time");
		
		if(curpage==null||curpage<1) {
			curpage = 1;
		}
		Page<Project> page = new Page<Project>(curpage, pageSize);
		
		HashMap<String, Object> hashMap = iProjectService.findProjects(queryWrapper,page);
		
		return hashMap;
	}
	
	/**
	 * 项目类型集合
	 * @return
	 */
	@RequestMapping("types")
	public List<ProjectType> findProjectTypes(){
		List<ProjectType> list = iProjectTypeService.list();
		return list;
	}
	
	/**
	 * 项目状态集合
	 * @return
	 */
	@RequestMapping("status")
	public List<ProjectStatus> findProjectStatus(){
		List<ProjectStatus> list = IProjectStatusService.list();
		return list;
	}
	
	/**
	 * 行业池
	 * @return
	 */
	@RequestMapping("industries")
	public List<Industry> findIndustries(){
		List<Industry> list = iIndustryService.list();
		return list;
	}
	
	/**
	 * 标签池
	 * @return
	 */
	@RequestMapping("labels")
	public List<ProjectLabel> findProjectLabels(){

		List<ProjectLabel> list = iProjectLabelService.list();
		return list;
	}

	/**
	 * 查询项目详情
	 * 
	 * @param code 项目编号
	 * @return 项目详情
	 */
	@GetMapping("/details")
	public ProjectDetaisVO queryDetaisByCode(String code) {


		ProjectDetaisVO projectDetaisVO = iProjectService.queryDetaisByCode(code);


		return projectDetaisVO;
	}
	
	/**
	 * 审核草稿
	 * @param code 审批流程编号
	 * @param passed 是否通过
	 * @param user 审批人
	 * @return
	 */
	@RequestMapping("auditdraft")
	public boolean auditDraft(String code,Integer passed,String user) {
		
		boolean result = iProjectService.auditDraft(code,passed,user);
		
		return result;
	}
	
	/**
	 * 项目审批流程编号
	 * @param code
	 * @return
	 */
	@RequestMapping("/recordDetail")
	public ProjectRecordDetailsVO findProjectRecordDetail(String code) {
		ProjectRecordDetailsVO projectRecordDetail = iProjectService.getProjectRecordDetail(code);
		return projectRecordDetail;
	}
	
	/**
	 * 审核变更
	 * @param code
	 * @param passed
	 * @param user
	 * @return
	 */
	@RequestMapping("/auditchange")
	public boolean auditChange(String code,Integer passed,String user) {
		
		boolean result = iProjectService.auditChange(code,passed,user); 
		
		return true;
	}
	/**
	 * 修改项目
	 * @param projectCode
	 * @param projectRequest
	 * @return
	 */
	public boolean editProjectInfo(String projectCode, ProjectRequest projectRequest) {
		
		boolean result = iProjectService.editProjectInfo(projectCode, projectRequest);
		return result;
	}
}
