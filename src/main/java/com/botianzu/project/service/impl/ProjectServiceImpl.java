package com.botianzu.project.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.botianzu.project.entity.ApprovedMemo;
import com.botianzu.project.entity.AuditFlow;
import com.botianzu.project.entity.Industry;
import com.botianzu.project.entity.Mplementation;
import com.botianzu.project.entity.MplementationRecord;
import com.botianzu.project.entity.ProductDesign;
import com.botianzu.project.entity.ProductDesignRecord;
import com.botianzu.project.entity.Project;
import com.botianzu.project.entity.ProjectStatus;
import com.botianzu.project.entity.ProjectType;
import com.botianzu.project.entity.Record;
import com.botianzu.project.entity.Require;
import com.botianzu.project.entity.RequireModel;
import com.botianzu.project.entity.RequireModelRecord;
import com.botianzu.project.entity.RequireRecord;
import com.botianzu.project.entity.SoftwareDesign;
import com.botianzu.project.entity.SoftwareDesignRecord;
import com.botianzu.project.entity.SoftwaredesignbackstageTechnology;
import com.botianzu.project.entity.SoftwaredesignfrontTechnology;
import com.botianzu.project.entity.Technology;
import com.botianzu.project.entity.Test;
import com.botianzu.project.entity.TestRecord;
import com.botianzu.project.entity.vo.ChangeProjectRequest;
import com.botianzu.project.entity.vo.ProjectDetaisVO;
import com.botianzu.project.entity.vo.ProjectRequest;
import com.botianzu.project.entity.vo.ProjectVo;
import com.botianzu.project.mapper.AuditFlowMapper;
import com.botianzu.project.mapper.IndustryMapper;
import com.botianzu.project.mapper.MplementationMapper;
import com.botianzu.project.mapper.MplementationRecordMapper;
import com.botianzu.project.mapper.ProductDesignMapper;
import com.botianzu.project.mapper.ProductDesignRecordMapper;
import com.botianzu.project.mapper.ProjectLabelMapper;
import com.botianzu.project.mapper.ProjectMapper;
import com.botianzu.project.mapper.ProjectTypeMapper;
import com.botianzu.project.mapper.RecordMapper;
import com.botianzu.project.mapper.RequireMapper;
import com.botianzu.project.mapper.RequireModelMapper;
import com.botianzu.project.mapper.RequireModelRecordMapper;
import com.botianzu.project.mapper.RequireRecordMapper;
import com.botianzu.project.mapper.SoftwareDesignMapper;
import com.botianzu.project.mapper.SoftwareDesignRecordMapper;
import com.botianzu.project.mapper.SoftwaredesignbackstageTechnologyMapper;
import com.botianzu.project.mapper.SoftwaredesignfrontTechnologyMapper;
import com.botianzu.project.mapper.TechnologyMapper;
import com.botianzu.project.mapper.TestMapper;
import com.botianzu.project.mapper.TestRecordMapper;
import com.botianzu.project.service.IProjectService;
import com.botianzu.project.utils.FastDFSClient;
import com.botianzu.project.utils.PropertyCopyUtils;
import com.botianzu.project.utils.String2Date;
import com.botianzu.project.utils.UUIDUtils;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Autowired
	private FastDFSClient fastDFSClient;

	@Autowired
	private RequireMapper requireMapper;

	@Autowired
	private RequireModelMapper requireModelMapper;

	@Autowired
	private ProductDesignMapper productDesignMapper;

	@Autowired
	private SoftwareDesignMapper softwareDesignMapper;

	@Autowired
	private SoftwaredesignfrontTechnologyMapper softwaredesignfrontTechnologyMapper;

	@Autowired
	private SoftwaredesignbackstageTechnologyMapper softwaredesignbackstageTechnologyMapper;

	@Autowired
	private MplementationMapper mplementationMapper;


	@Autowired
	private TestMapper testMapper;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 项目记录mapper接口
	 */
	@Autowired
	private RecordMapper recordMapper;

	/**
	 * 审核流程mapper接口
	 */
	@Autowired
	private AuditFlowMapper auditFlowMapper;

	/**
	 * 项目需求记录mapper接口
	 */
	@Autowired
	private RequireRecordMapper requireRecordMapper;

	/**
	 * 项目实施记录mapper接口
	 */
	@Autowired
	private MplementationRecordMapper mplementationRecordMapper;

	/**
	 * 项目测试记录mapper接口
	 */
	@Autowired
	private TestRecordMapper testRecordMapper;

	/**
	 * 软件设计记录mapper接口
	 */
	@Autowired
	private SoftwareDesignRecordMapper softwareDesignRecordMapper;

	/**
	 * 产品设计记录mapper接口
	 */
	@Autowired
	private ProductDesignRecordMapper productDesignRecordMapper;
	
	@Autowired
	private RequireModelRecordMapper requireModelRecordMapper;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Autowired
	private ProjectTypeMapper projectTypeMapper;

	@Autowired
	private ProjectLabelMapper ProjectLabelMapper;

	@Autowired
	private ProjectStatus projectStatus;

	@Autowired
	private IndustryMapper industryMapper;

	@Autowired
	private TechnologyMapper technologyMapper;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 上传
	 */
	@Override
	public String upload(MultipartFile file) {
		try {
			String uploadFace = fastDFSClient.uploadFace(file);
			return uploadFace;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * 添加一个新的项目
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean addProject(ProjectRequest projectRequest, Integer isDraft) {
		String projectCode = UUIDUtils.createUUIDCode();

		// 保存项目
		Project project = new Project();
		if (isDraft == 1) {
			project.setStatus(1);
		} else {
			ChangeProjectRequest changeProjectRequest = new ChangeProjectRequest();
			PropertyCopyUtils.copy(changeProjectRequest, projectRequest);
			changeProjectRequest.setCode(projectCode);
			boolean changeProject = changeProject(changeProjectRequest);
			
			project.setStatus(2);
		}

		project.setName(projectRequest.getName());
		project.setLabels(projectRequest.getLabelName());
		project.setProjectType(projectRequest.getProjectTypeCode());
		project.setIntroduction(projectRequest.getDescription());
		project.setCodePath(projectRequest.getProjectCodeFileUrl());
		project.setProjectIndustryCode(projectRequest.getIndustryCode());

		project.setCode(projectCode);
		Date now = new Date();
		project.setCreateTime(now);
		project.setUpdateTime(now);

		baseMapper.insert(project);

		// 保存项目需求
		Require require = new Require();
		String requireCode = UUIDUtils.createUUIDCode();
		require.setDocumentPath(projectRequest.getRequireDocumentFileUrl());
		require.setFlowDiagram(projectRequest.getProcessPictureFileUrl());
		require.setCode(requireCode);
		require.setCreateTime(now);
		require.setUpdateTime(now);
		require.setProjectCode(projectCode);
		requireMapper.insert(require);
		// 添加项目模块
		List<RequireModel> models = projectRequest.getModels();
		if (models != null && models.size() > 0) {
			for (RequireModel requireModel : models) {
				requireModel.setCode(UUIDUtils.createUUIDCode());
				requireModel.setCreateTime(now);
				requireModel.setUpdateTime(now);
				requireModel.setProjectRequireCode(requireCode);
				requireModelMapper.insert(requireModel);
			}
		}

		// 添加产品设计
		ProductDesign productDesign = new ProductDesign();
		productDesign.setUeName(projectRequest.getUeFileUrl());
		productDesign.setUiName(projectRequest.getUiFileUrl());

		productDesign.setCode(UUIDUtils.createUUIDCode());
		productDesign.setCreateTime(now);
		productDesign.setUpdateTime(now);
		productDesign.setProjectCode(projectCode);
		productDesignMapper.insert(productDesign);

		// 添加软件设计
		SoftwareDesign softwareDesign = new SoftwareDesign();
		String softWareCode = UUIDUtils.createUUIDCode();

		softwareDesign.setDatabaseFile(projectRequest.getDatabaseDocumentFileUrl());
		softwareDesign.setServiceFile(projectRequest.getFunctionDocumentFileUrl());
		softwareDesign.setSiteStructure(projectRequest.getSiteStructureFileUrl());

		softwareDesign.setCode(softWareCode);
		softwareDesign.setCreateTime(now);
		softwareDesign.setUpdateTime(now);
		softwareDesign.setProjectCode(projectCode);
		softwareDesignMapper.insert(softwareDesign);

		// 添加前台技术
		String clientTechnologyCodes = projectRequest.getClientTechnologyCodes();
		if (clientTechnologyCodes != null && !"".equals(clientTechnologyCodes.trim())) {
			String[] split = clientTechnologyCodes.split(",");
			for (String string : split) {
				SoftwaredesignfrontTechnology softwaredesignfrontTechnology = new SoftwaredesignfrontTechnology();
				softwaredesignfrontTechnology.setSoftwareDesignCode(softWareCode);
				softwaredesignfrontTechnology.setTechnologyCode(string);
				softwaredesignfrontTechnologyMapper.insert(softwaredesignfrontTechnology);
			}
		}

		// 添加后台技术
		String serverTechnologyCodes = projectRequest.getServerTechnologyCodes();
		if (serverTechnologyCodes != null && !"".equals(serverTechnologyCodes.trim())) {
			String[] split = serverTechnologyCodes.split(",");
			for (String string : split) {
				SoftwaredesignbackstageTechnology softwaredesignbackstageTechnology = new SoftwaredesignbackstageTechnology();
				softwaredesignbackstageTechnology.setSoftwareDesignCode(softWareCode);
				softwaredesignbackstageTechnology.setTechnologyCode(string);
				softwaredesignbackstageTechnologyMapper.insert(softwaredesignbackstageTechnology);
			}
		}

		// 添加项目实施
		Mplementation mplementation = new Mplementation();
		mplementation.setCode(UUIDUtils.createUUIDCode());
		mplementation.setCreateTime(now);
		mplementation.setUpdateTime(now);
		mplementation.setProjectCode(projectCode);
		mplementation.setPlanFile(projectRequest.getWbsFileUrl());
		mplementation.setWorkNum(projectRequest.getWorkingDays());

		String projectBeginDate = projectRequest.getProjectBeginDate();
		if (projectBeginDate != null && !"".equals(projectBeginDate.trim())) {
			mplementation.setCreateTime(String2Date.transition(projectBeginDate));
		}
		String projectEndDate = projectRequest.getProjectEndDate();
		if (projectEndDate != null && !"".equals(projectEndDate.trim())) {
			mplementation.setCreateTime(String2Date.transition(projectEndDate));
		}
		mplementationMapper.insert(mplementation);

		// 添加项目测试
		Test test = new Test();
		test.setCode(UUIDUtils.createUUIDCode());
		test.setCreateTime(now);
		test.setUpdateTime(now);
		test.setProjectCode(projectCode);
		test.setTestPlan(projectRequest.getProjectTestPlanDocumentFileUrl());
		test.setTestBeans(projectRequest.getProjectTestInstanceDocumentFileUrl());
		test.setTestReport(projectRequest.getProjectTestReportDocumentFileUrl());
		testMapper.insert(test);

		return true;
	}

	/**
	 * 查询项目表单数据
	 */
	@Override
	public HashMap<String, Object> findProjects(QueryWrapper<Project> queryWrapper, Page<Project> page) {

//		queryWrapper.ne("status", Project.STATUS_DRAFT);

		HashMap<String, Object> map = new HashMap<>();

		IPage<Project> projectPage = page(page, queryWrapper);

		map.put("pageSize", projectPage.getSize());
		map.put("total", projectPage.getTotal());

		List<Project> projects = projectPage.getRecords();

		if (projectPage != null && projects.size() > 0) {
			ArrayList<ProjectVo> projectVos = new ArrayList<ProjectVo>();
			for (Project project : projects) {
				ProjectVo projectVo = new ProjectVo();
				projectVo.setCode(project.getCode());
				projectVo.setName(project.getName());
				projectVo.setLabel(project.getLabels());
				projectVo.setIndustry(findIndustryByCode(project.getProjectIndustryCode()));
				projectVo.setDescription(project.getIntroduction());

				if (project.getStatus() != null) {
					String status = "";
					switch (project.getStatus()) {
					case 1:
						status = "草稿";
						break;
					case 2:
						status = "审核中";
						break;
					case 3:
						status = "审核通过";
						break;
					case 4:
						status = "审核未通过";
						break;
					case 5:
						status = "变更中";
						break;
					case 6:
						status = "变更成功";
						break;
					case 7:
						status = "变更失败";
						break;
					default:
						break;
					}
					projectVo.setStatus(status);
				}

				QueryWrapper<Require> requireQueryWrapper = new QueryWrapper<Require>();
				requireQueryWrapper.eq("project_code", project.getCode());
				Require require = requireMapper.selectOne(requireQueryWrapper);
				if (require != null) {
					QueryWrapper<RequireModel> requireModelQueryWrapper = new QueryWrapper<RequireModel>();
					requireModelQueryWrapper.eq("project_require_code", require.getCode());
					List<RequireModel> models = requireModelMapper.selectList(requireModelQueryWrapper);
					projectVo.setModels(models);
				}

				projectVo.setTypeName(findProjectTypeByCode(project.getProjectType()));

				QueryWrapper<SoftwareDesign> softWareQueryWrapper = new QueryWrapper<SoftwareDesign>();
				softWareQueryWrapper.eq("project_code", project.getCode());
				SoftwareDesign softwareDesign = softwareDesignMapper.selectOne(softWareQueryWrapper);
				if (softwareDesign != null) {
					QueryWrapper<SoftwaredesignfrontTechnology> SFTQueryWrapper = new QueryWrapper<SoftwaredesignfrontTechnology>();
					SFTQueryWrapper.eq("software_design_code", softwareDesign.getCode());
					List<SoftwaredesignfrontTechnology> frontTechnologies = softwaredesignfrontTechnologyMapper
							.selectList(SFTQueryWrapper);
					QueryWrapper<SoftwaredesignbackstageTechnology> SBTQueryWrapper = new QueryWrapper<SoftwaredesignbackstageTechnology>();
					SBTQueryWrapper.eq("software_design_code", softwareDesign.getCode());
					List<SoftwaredesignbackstageTechnology> backTechnologies = softwaredesignbackstageTechnologyMapper
							.selectList(SBTQueryWrapper);
					String techbologyCodes = "";
					for (SoftwaredesignbackstageTechnology backT : backTechnologies) {
						techbologyCodes = techbologyCodes + backT.getTechnologyCode() + ",";
					}
					for (SoftwaredesignfrontTechnology frontT : frontTechnologies) {
						techbologyCodes = techbologyCodes + frontT.getTechnologyCode() + ",";
					}
					if (StringUtils.isNoneBlank(techbologyCodes)) {
						String techbologies = findTchbologiesByCodes(techbologyCodes);
						projectVo.setTechnologs(techbologies);
					}
				}
				projectVos.add(projectVo);
			}
			map.put("projects", projectVos);
		}

		return map;
	}

	/**
	 * 根据编号集 查询技术并返回字符串
	 * 
	 * @param techbologyCodes
	 * @return
	 */
	private String findTchbologiesByCodes(String techbologyCodes) {

		if (StringUtils.isNotBlank(techbologyCodes)) {
			ArrayList<String> arrayList = new ArrayList<String>();
			String[] split = techbologyCodes.split(",");
			for (String string : split) {
				arrayList.add(string);
			}
			QueryWrapper<Technology> queryWrapper = new QueryWrapper<Technology>();
			queryWrapper.in("code", arrayList);
			List<Technology> selectList = technologyMapper.selectList(queryWrapper);
			String ts = "";
			for (Technology technology : selectList) {
				ts = ts + technology.getName() + ",";
			}
			return ts;
		}
		return "";
	}

	/**
	 * 根据项目类型编号查询项目类型
	 * 
	 * @param projectType
	 * @return
	 */
	private String findProjectTypeByCode(String code) {

		QueryWrapper<ProjectType> queryWrapper = new QueryWrapper<ProjectType>();
		queryWrapper.eq("code", code);
		ProjectType projectType = projectTypeMapper.selectOne(queryWrapper);
		if (projectType != null) {
			return projectType.getName();
		}
		return "";
	}

	/**
	 * 根据编号查询行业
	 * 
	 * @param string
	 * @return
	 */
	private String findIndustryByCode(String code) {

		QueryWrapper<Industry> queryWrapper = new QueryWrapper<Industry>();
		queryWrapper.eq("code", code);
		Industry industry = industryMapper.selectOne(queryWrapper);
		if (industry != null) {
			return industry.getName();
		}
		return "";
	}

	@Override
	@Transactional
	public boolean editProjectInfo(String projectCode, ProjectRequest projectRequest) {

		Project project = baseMapper.selectById(projectCode);
		if (project != null) {
			// 设置新的项目属性
			project.setName(projectRequest.getName());
			project.setLabels(projectRequest.getLabelName());
			project.setProjectType(projectRequest.getProjectTypeCode());
			project.setIntroduction(projectRequest.getDescription());
			project.setCodePath(projectRequest.getProjectCodeFileUrl());
			project.setProjectIndustryCode(projectRequest.getIndustryCode());
			Date now = new Date();
			project.setUpdateTime(now);
			baseMapper.updateById(project);

			// 设置新的项目需求
			QueryWrapper<Require> requireQueryWrapper = new QueryWrapper<Require>();
			requireQueryWrapper.eq("project_code", projectCode);
			Require require = requireMapper.selectOne(requireQueryWrapper);
			require.setDocumentPath(projectRequest.getRequireDocumentFileUrl());
			require.setFlowDiagram(projectRequest.getProcessPictureFileUrl());
			require.setUpdateTime(now);

			// 修改新的模板
			QueryWrapper<RequireModel> requireModelQueryWrapper = new QueryWrapper<RequireModel>();
			requireModelQueryWrapper.eq("project_require_code", require.getCode());
			requireModelMapper.delete(requireModelQueryWrapper);
			List<RequireModel> models = projectRequest.getModels();
			for (RequireModel requireModel : models) {
				requireModel.setProjectRequireCode(require.getCode());
				requireModel.setCode(UUIDUtils.createUUIDCode());
				requireModel.setCreateTime(now);
				requireModel.setUpdateTime(now);
				requireModelMapper.insert(requireModel);
			}

			// 修改产品设计
			QueryWrapper<ProductDesign> productDesignQueryWrapper = new QueryWrapper<ProductDesign>();
			productDesignQueryWrapper.eq("project_code", projectCode);
			ProductDesign productDesign = productDesignMapper.selectOne(productDesignQueryWrapper);
			productDesign.setUeName(projectRequest.getUeFileUrl());
			productDesign.setUiName(projectRequest.getUiFileUrl());
			productDesign.setUpdateTime(now);
			productDesignMapper.insert(productDesign);

			// 修改软件设计
			QueryWrapper<SoftwareDesign> softwareDesignQueryWrapper = new QueryWrapper<SoftwareDesign>();
			softwareDesignQueryWrapper.eq("project_code", projectCode);
			SoftwareDesign softwareDesign = softwareDesignMapper.selectOne(softwareDesignQueryWrapper);

			softwareDesign.setDatabaseFile(projectRequest.getDatabaseDocumentFileUrl());
			softwareDesign.setServiceFile(projectRequest.getFunctionDocumentFileUrl());
			softwareDesign.setSiteStructure(projectRequest.getSiteStructureFileUrl());

			softwareDesign.setUpdateTime(now);
			softwareDesignMapper.updateById(softwareDesign);

			// 修改前台技术
			String clientTechnologyCodes = projectRequest.getClientTechnologyCodes();
			if (clientTechnologyCodes != null && !"".equals(clientTechnologyCodes.trim())) {
				QueryWrapper<SoftwaredesignfrontTechnology> softwaredesignfrontTechnologyQueryWrapper = new QueryWrapper<SoftwaredesignfrontTechnology>();
				softwaredesignfrontTechnologyQueryWrapper.eq("software_design_code", softwareDesign.getCode());
				softwaredesignfrontTechnologyMapper.delete(softwaredesignfrontTechnologyQueryWrapper);
				String[] split = clientTechnologyCodes.split(",");
				for (String string : split) {
					SoftwaredesignfrontTechnology softwaredesignfrontTechnology = new SoftwaredesignfrontTechnology();
					softwaredesignfrontTechnology.setSoftwareDesignCode(softwareDesign.getCode());
					softwaredesignfrontTechnology.setTechnologyCode(string);
					softwaredesignfrontTechnologyMapper.insert(softwaredesignfrontTechnology);
				}
			}

			// 修改后台技术
			String serverTechnologyCodes = projectRequest.getServerTechnologyCodes();
			if (serverTechnologyCodes != null && !"".equals(serverTechnologyCodes.trim())) {
				QueryWrapper<SoftwaredesignbackstageTechnology> softwaredesignbackstageTechnologyQueryWrapper = new QueryWrapper<SoftwaredesignbackstageTechnology>();
				softwaredesignbackstageTechnologyQueryWrapper.eq("software_design_code", softwareDesign.getCode());
				softwaredesignbackstageTechnologyMapper.delete(softwaredesignbackstageTechnologyQueryWrapper);
				String[] split = serverTechnologyCodes.split(",");
				for (String string : split) {
					SoftwaredesignbackstageTechnology softwaredesignbackstageTechnology = new SoftwaredesignbackstageTechnology();
					softwaredesignbackstageTechnology.setSoftwareDesignCode(softwareDesign.getCode());
					softwaredesignbackstageTechnology.setTechnologyCode(string);
					softwaredesignbackstageTechnologyMapper.insert(softwaredesignbackstageTechnology);
				}
			}

			// 添加项目实施
			QueryWrapper<Mplementation> mplementationQueryWrapper = new QueryWrapper<Mplementation>();
			mplementationQueryWrapper.eq("project_code", projectCode);
			Mplementation mplementation = mplementationMapper.selectOne(mplementationQueryWrapper);
			mplementation.setUpdateTime(now);
			mplementation.setProjectCode(projectCode);
			mplementation.setPlanFile(projectRequest.getWbsFileUrl());
			mplementation.setWorkNum(projectRequest.getWorkingDays());

			String projectBeginDate = projectRequest.getProjectBeginDate();
			if (projectBeginDate != null && !"".equals(projectBeginDate.trim())) {
				mplementation.setCreateTime(String2Date.transition(projectBeginDate));
			}
			String projectEndDate = projectRequest.getProjectEndDate();
			if (projectEndDate != null && !"".equals(projectEndDate.trim())) {
				mplementation.setCreateTime(String2Date.transition(projectEndDate));
			}
			mplementationMapper.updateById(mplementation);

			// 修改项目测试
			QueryWrapper<Test> testQueryWrapper = new QueryWrapper<Test>();
			testQueryWrapper.eq("project_code", projectCode);
			Test test = testMapper.selectOne(testQueryWrapper);
			test.setUpdateTime(now);
			test.setTestPlan(projectRequest.getProjectTestPlanDocumentFileUrl());
			test.setTestBeans(projectRequest.getProjectTestInstanceDocumentFileUrl());
			test.setTestReport(projectRequest.getProjectTestReportDocumentFileUrl());
			testMapper.updateById(test);
		}

		return true;
	}

	/**
	 * 查询项目详情
	 * 
	 * @param code 项目编号
	 * @return 项目详情
	 */
	@Override
	public ProjectDetaisVO queryDetaisByCode(String code) {

		// 判断项目编号是否为空
		if (code == null || code == "") {
			return null;
		}

		// 初始化一个项目详情响应对象
		ProjectDetaisVO projectDetaisVO = new ProjectDetaisVO();

		// 查询项目
		QueryWrapper<Project> queryWrapperProject = new QueryWrapper<>();
		queryWrapperProject.eq("code", code);
		Project project = baseMapper.selectOne(queryWrapperProject);
		if (project != null) {
			projectDetaisVO.setName(project.getName()); // 项目名称
			projectDetaisVO.setCode(project.getCode()); // 项目编号
			projectDetaisVO.setUploadUser("上传者"); // 项目上传者
			projectDetaisVO.setProjectType(findProjectTypeByCode(project.getProjectType())); // 项目类型
			projectDetaisVO.setProjectDesc(project.getIntroduction()); // 项目简介
			projectDetaisVO.setLabelName(project.getLabels()); // 项目标签
			projectDetaisVO.setProjectIndustry(findIndustryByCode(project.getProjectIndustryCode()));
			projectDetaisVO.setProjectCodeFileUrl(project.getCodePath());
		}

		// 查询项目实施(计划安排)
		QueryWrapper<Mplementation> queryWrapperMplementation = new QueryWrapper<>();
		queryWrapperMplementation.eq("project_code", code);
		Mplementation mplementation = mplementationMapper.selectOne(queryWrapperMplementation);
		if (mplementation != null) {
			// 格式化时间
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (mplementation.getStartTime() != null) {
				String beginDate = simpleDateFormat.format(mplementation.getStartTime());
				projectDetaisVO.setBeginDate(beginDate);// 开始时间
			}
			if (mplementation.getEndTime() != null) {
				String endDate = simpleDateFormat.format(mplementation.getEndTime());
				projectDetaisVO.setEndDate(endDate);// 结束时间
			}
			projectDetaisVO.setProjectPlanUrl(mplementation.getPlanFile()); // 项目计划安排
		}

		// 查询低保真 高保真
		QueryWrapper<ProductDesign> queryWrapperProductDesign = new QueryWrapper<>();
		queryWrapperProductDesign.eq("project_code", code);
		ProductDesign productDesign = productDesignMapper.selectOne(queryWrapperProductDesign);
		if (productDesign != null) {
			projectDetaisVO.setUeUrl(productDesign.getUeName()); // 低保真
			projectDetaisVO.setUiUrl(productDesign.getUiName()); // 高保真
		}

		// 查询项目需求文档 流程图
		QueryWrapper<Require> queryWrapperRequire = new QueryWrapper<>();
		queryWrapperRequire.eq("project_code", code);
		Require require = requireMapper.selectOne(queryWrapperRequire);
		if (require != null) {
			projectDetaisVO.setProjectDocumentUrl(require.getDocumentPath()); // 项目需求文档路径
			projectDetaisVO.setProjectFlowDiagramUrl(require.getFlowDiagram());// 项目业务流程图
		}

		// 查询测试计划 测试用例 测试报告
		QueryWrapper<Test> queryWrapperTest = new QueryWrapper<>();
		queryWrapperTest.eq("project_code", code);
		Test test = testMapper.selectOne(queryWrapperTest);
		if (test != null) {
			projectDetaisVO.setTestPlanUrl(test.getTestPlan()); // 测试计划
			projectDetaisVO.setTestReport(test.getTestReport()); // 测试报告
			projectDetaisVO.setTestUseCaseUrl(test.getTestBeans()); // 测试用例
		}

		// 查询 项目架构图 功能接口设计文档 数据库设计文档
		QueryWrapper<SoftwareDesign> queryWrapperSoftwareDesign = new QueryWrapper<>();
		queryWrapperSoftwareDesign.eq("project_code", code);
		SoftwareDesign softwareDesign = softwareDesignMapper.selectOne(queryWrapperSoftwareDesign);
		if (softwareDesign != null) {
			projectDetaisVO.setProjectSiteStructureUrl(softwareDesign.getSiteStructure()); // 项目架构图
			projectDetaisVO.setProjectFunctionDocumentUrl(softwareDesign.getServiceFile()); // 项目功能设计文档
			projectDetaisVO.setProjectDatabaseDocumentUrl(softwareDesign.getDatabaseFile()); // 数据库设计文档
		}

		// 装载项目详情响应对象
		// 项目技术
		if (softwareDesign != null) {
			QueryWrapper<SoftwaredesignfrontTechnology> SFTQueryWrapper = new QueryWrapper<SoftwaredesignfrontTechnology>();
			SFTQueryWrapper.eq("software_design_code", softwareDesign.getCode());
			List<SoftwaredesignfrontTechnology> frontTechnologies = softwaredesignfrontTechnologyMapper
					.selectList(SFTQueryWrapper);
			QueryWrapper<SoftwaredesignbackstageTechnology> SBTQueryWrapper = new QueryWrapper<SoftwaredesignbackstageTechnology>();
			SBTQueryWrapper.eq("software_design_code", softwareDesign.getCode());
			List<SoftwaredesignbackstageTechnology> backTechnologies = softwaredesignbackstageTechnologyMapper
					.selectList(SBTQueryWrapper);
			String techbologyCodes = "";
			for (SoftwaredesignbackstageTechnology backT : backTechnologies) {
				techbologyCodes = techbologyCodes + backT.getTechnologyCode() + ",";
			}
			for (SoftwaredesignfrontTechnology frontT : frontTechnologies) {
				techbologyCodes = techbologyCodes + frontT.getTechnologyCode() + ",";
			}
			if (StringUtils.isNoneBlank(techbologyCodes)) {
				String techbologies = findTchbologiesByCodes(techbologyCodes);
				projectDetaisVO.setTechnology(techbologies);
			}
		}

		return projectDetaisVO;
	}

	/**
	 * 提交变更项目
	 * 
	 * @param changeProjectRequest
	 * @return flag 是否变更成功
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean changeProject(ChangeProjectRequest changeProjectRequest) {

		// 判断参数是否为空 若为空return false
		if (changeProjectRequest == null) {
			return false;
		}
		
		boolean flag = false;

		// 创建系统当前时间
		Date now = new Date();

		// 生成code
		String UUIDCode = UUIDUtils.createUUIDCode();

		// 创建一个变更流程
		AuditFlow auditFlow = new AuditFlow();
		auditFlow.setCode(UUIDCode);
		auditFlow.setDeleted(1);
		auditFlow.setCreateTime(now);
		auditFlow.setUpdateTime(now);
		auditFlow.setSubmitUserCode("code");
		auditFlow.setSubmitTime(now);
		auditFlow.setStatus(AuditFlow.STATUS_SUBMIT); // 变更流程提交待审核

		// 项目记录基本信息
		Record projectRcord = new Record();
		projectRcord.setCode(changeProjectRequest.getCode()); // 项目编号
		projectRcord.setName(changeProjectRequest.getName()); // 项目名称
		projectRcord.setLabels(changeProjectRequest.getLabelName()); // 项目标签
		projectRcord.setProjectType(changeProjectRequest.getProjectTypeCode()); // 项目类型
		projectRcord.setProjectIndustryCode(changeProjectRequest.getIndustryCode()); // 项目行业
		projectRcord.setIntroduction(changeProjectRequest.getDescription()); // 项目简介
		projectRcord.setCreateTime(now);
		projectRcord.setUpdateTime(now);
		projectRcord.setStatus(Project.STATUS_CHANGE); // 项目变更中
		projectRcord.setDeleted(1);
		projectRcord.setCodePath(changeProjectRequest.getProjectCodeFileUrl()); // 项目路径
		projectRcord.setAuditCode(UUIDCode); // 变更流程编号

		// 项目需求记录
		RequireRecord requireRecord = new RequireRecord();
		requireRecord.setProjectCode(changeProjectRequest.getCode()); // 项目编号
		requireRecord.setDocumentPath(changeProjectRequest.getRequireDocumentFileUrl()); // 需求文档
		requireRecord.setFlowDiagram(changeProjectRequest.getProcessPictureFileUrl()); // 流程图
		requireRecord.setCreateTime(now);
		requireRecord.setUpdateTime(now);
		requireRecord.setCode(UUIDCode);
		requireRecord.setDeleted(1);
		requireRecord.setMemoCode(UUIDCode); // 关联变更流程

		// 软件设计记录
		SoftwareDesignRecord softwareDesignRecord = new SoftwareDesignRecord();
		softwareDesignRecord.setServiceFile(changeProjectRequest.getFunctionDocumentFileUrl()); // 功能设计文档
		softwareDesignRecord.setDatabaseFile(changeProjectRequest.getDatabaseDocumentFileUrl()); // 数据库设计文档
		softwareDesignRecord.setSiteStructure(changeProjectRequest.getSiteStructureFileUrl()); // 架构图
		softwareDesignRecord.setProjectCode(changeProjectRequest.getCode()); // 项目编号
		softwareDesignRecord.setCreateTime(now);
		softwareDesignRecord.setUpdateTime(now);
		softwareDesignRecord.setCode(UUIDCode);
		softwareDesignRecord.setDeleted(1);
		softwareDesignRecord.setMemoCode(UUIDCode); // 关联变更流程

		// 项目实施记录
		MplementationRecord mplementationRecord = new MplementationRecord();
		mplementationRecord.setProjectCode(changeProjectRequest.getCode()); // 项目编号
		mplementationRecord.setPlanFile(changeProjectRequest.getWbsFileUrl()); // 项目计划wbs文件
		mplementationRecord.setWorkNum(changeProjectRequest.getWorkingDays()); // 可用工作日
		mplementationRecord.setCreateTime(now);
		mplementationRecord.setUpdateTime(now);
		mplementationRecord.setCode(UUIDCode);
		mplementationRecord.setDeleted(1);
		mplementationRecord.setMemoCode(UUIDCode);// 关联变更流程

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			mplementationRecord.setStartTime(String2Date.transition(changeProjectRequest.getProjectBeginDate())); // 项目开始时间
			mplementationRecord.setEndTime(String2Date.transition(changeProjectRequest.getProjectEndDate())); // 项目结束时间
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 项目测试记录
		TestRecord testRecord = new TestRecord();
		testRecord.setProjectCode(changeProjectRequest.getCode()); // 项目编号
		testRecord.setTestBeans(changeProjectRequest.getProjectTestInstanceDocumentFileUrl()); // 测试用例
		testRecord.setTestPlan(changeProjectRequest.getProjectTestPlanDocumentFileUrl()); // 测试计划
		testRecord.setTestReport(changeProjectRequest.getProjectTestReportDocumentFileUrl()); // 测试报告
		testRecord.setCreateTime(now);
		testRecord.setUpdateTime(now);
		testRecord.setCode(UUIDCode);
		testRecord.setDeleted(1);
		testRecord.setMemoCode(UUIDCode); // 关联变更流程

		// 产品设计记录
		ProductDesignRecord productDesignRecord = new ProductDesignRecord();
		productDesignRecord.setProjectCode(changeProjectRequest.getCode()); // 项目编号
		productDesignRecord.setUiName(changeProjectRequest.getUiFileUrl()); // 低保真
		productDesignRecord.setUeName(changeProjectRequest.getUeFileUrl()); // 高保真
		productDesignRecord.setCreateTime(now);
		productDesignRecord.setUpdateTime(now);
		productDesignRecord.setCode(UUIDCode);
		productDesignRecord.setDeleted(1); // 未删除
		productDesignRecord.setMemoCode(UUIDCode); // 关联变更流程

		try {

			List<RequireModel> models = changeProjectRequest.getModels();

			for (RequireModel requireModel : models) {
				RequireModelRecord requireModelRecord = new RequireModelRecord();

				requireModelRecord.setCode(UUIDCode);
				requireModelRecord.setCreateTime(now);
				requireModelRecord.setUpdateTime(now);
				requireModelRecord.setDeleted(1);
				requireModelRecord.setProjectRequireModelcol(UUIDCode);
				requireModelRecord.setContext(requireModel.getContext()); // 模块需求
				requireModelRecord.setName(requireModel.getName()); // 需求名称
				requireModelRecord.setMemoCode(UUIDCode);

				requireModelRecordMapper.insert(requireModelRecord);

			}

			// 持久化项目记录基本信息
			recordMapper.insert(projectRcord);
			// 持久化项目需求记录
			requireRecordMapper.insert(requireRecord);
			// 持久化软件设计记录
			softwareDesignRecordMapper.insert(softwareDesignRecord);
			// 持久化项目实施记录
			mplementationRecordMapper.insert(mplementationRecord);
			// 持久化项目测试记录
			testRecordMapper.insert(testRecord);
			// 持久化产品设计记录
			productDesignRecordMapper.insert(productDesignRecord);
			// 持久化变更流程
			auditFlowMapper.insert(auditFlow);

			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	
	@Override
	@Transactional
	public boolean auditDraft(String code, Integer passed, String user) {
		
		//创建一条审核记录
		ApprovedMemo approvedMemo = new ApprovedMemo();
		Date now = new Date();
		approvedMemo.setCode(UUIDUtils.createUUIDCode());
		approvedMemo.setCreateTime(now);
		approvedMemo.setUpdateTime(now);
		approvedMemo.setApprovalTime(now);
		approvedMemo.setApprovalPeople(user);
		approvedMemo.setFlowCode(code);
		
		UpdateWrapper<AuditFlow> auditFlowUpdateWrapper = new UpdateWrapper<AuditFlow>();
		auditFlowUpdateWrapper.eq("code",code);
		if(passed==1) {
			//审核通过
			approvedMemo.setApprovalResult(passed);
			if("院长".equals(user)) {
				auditFlowUpdateWrapper.set("status",AuditFlow.STATUS_TWO_PASS);
				//修改项目数据
				
			}else if("主任".equals(user)) {
				auditFlowUpdateWrapper.set("status",AuditFlow.STATUS_ONE_PASS);
			}
		}else {
			//审核不通过
			if("院长".equals(user)) {
				auditFlowUpdateWrapper.set("status",AuditFlow.STATUS_TWO_NOPASS);
				//修改项目状态
			}else if("主任".equals(user)) {
				auditFlowUpdateWrapper.set("status",AuditFlow.STATUS_ONE_NOPASS);
				//修改项目状态
			}
		}
		//修改流程状态
		auditFlowMapper.update(null, auditFlowUpdateWrapper);
		
		return true;
	}

}
