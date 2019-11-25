package com.botianzu.project.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.botianzu.project.entity.AlterationMemo;
import com.botianzu.project.entity.ApprovedMemo;
import com.botianzu.project.entity.AuditFlow;
import com.botianzu.project.entity.Industry;
import com.botianzu.project.entity.MplementationRecord;
import com.botianzu.project.entity.ProductDesignRecord;
import com.botianzu.project.entity.ProjectType;
import com.botianzu.project.entity.Record;
import com.botianzu.project.entity.RequireModelRecord;
import com.botianzu.project.entity.RequireRecord;
import com.botianzu.project.entity.SoftwareDesignRecord;
import com.botianzu.project.entity.SoftwaredesignbackstageTechnologyRecord;
import com.botianzu.project.entity.SoftwaredesignfrontTechnologyRecord;
import com.botianzu.project.entity.Technology;
import com.botianzu.project.entity.TestRecord;
import com.botianzu.project.entity.vo.AuditFlowVo;
import com.botianzu.project.entity.vo.ProjectRecordDetailsVO;
import com.botianzu.project.mapper.AlterationMemoMapper;
import com.botianzu.project.mapper.ApprovedMemoMapper;
import com.botianzu.project.mapper.AuditFlowMapper;
import com.botianzu.project.mapper.IndustryMapper;
import com.botianzu.project.mapper.MplementationRecordMapper;
import com.botianzu.project.mapper.ProductDesignRecordMapper;
import com.botianzu.project.mapper.ProjectTypeMapper;
import com.botianzu.project.mapper.RecordMapper;
import com.botianzu.project.mapper.RequireModelMapper;
import com.botianzu.project.mapper.RequireModelRecordMapper;
import com.botianzu.project.mapper.RequireRecordMapper;
import com.botianzu.project.mapper.SoftwareDesignRecordMapper;
import com.botianzu.project.mapper.SoftwaredesignbackstageTechnologyRecordMapper;
import com.botianzu.project.mapper.SoftwaredesignfrontTechnologyRecordMapper;
import com.botianzu.project.mapper.TechnologyMapper;
import com.botianzu.project.mapper.TestRecordMapper;
import com.botianzu.project.service.IAuditFlowService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
@Service
public class AuditFlowServiceImpl extends ServiceImpl<AuditFlowMapper, AuditFlow> implements IAuditFlowService {

	private final Integer pageSize = 10;

	/**
	 * 项目记录
	 */
	@Autowired
	private RecordMapper recordMapper;

	/**
	 * 项目需求记录
	 */
	@Autowired
	private RequireRecordMapper requireRecordMapper;

	/**
	 * 项目模块记录
	 */
	@Autowired
	private RequireModelMapper requireModelMapper;

	@Autowired
	private ProductDesignRecordMapper productDesignRecordMapper;

	/**
	 * 审核流程mapper接口
	 */
	@Autowired
	private AuditFlowMapper auditFlowMapper;

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
	 * 项目行业
	 */
	@Autowired
	private IndustryMapper industryMapper;
	
	/**
	 * 项目类型
	 */
	@Autowired
	private ProjectTypeMapper projectTypeMapper;

	/**
	 * 软件设计记录mapper接口
	 */
	@Autowired
	private SoftwareDesignRecordMapper softwareDesignRecordMapper;
	
	@Autowired
	private RequireModelRecordMapper requireModelRecordMapper;
	
	@Autowired
	private SoftwaredesignfrontTechnologyRecordMapper softwaredesignfrontTechnologyRecordMapper;
	
	@Autowired
	private SoftwaredesignbackstageTechnologyRecordMapper softwaredesignbackstageTechnologyRecordMapper;
	
	@Autowired
	private TechnologyMapper technologyMapper;
	
	/**
	 * 项目变更审核
	 */
	@Autowired
	private AlterationMemoMapper alterationMemoMapper;
	
	/**
	 * 项目添加审核
	 */
	@Autowired
	private ApprovedMemoMapper approvedMemoMapper;

	@Override
	public Map<String, Object> findAudits(Integer curpage, String submitUserName, String beginTime, String endTime,
			String name, Integer status) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		QueryWrapper<AuditFlow> auditFlowQueryWrapper = new QueryWrapper<AuditFlow>();
		if (StringUtils.isNotBlank(submitUserName)) {
			auditFlowQueryWrapper.like("submit_user_name", submitUserName);
		}
		if (StringUtils.isNotBlank(name)) {
			auditFlowQueryWrapper.like("project_name", name);
		}
		if (status != null) {
			auditFlowQueryWrapper.eq("status", status);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		try {
			if (StringUtils.isNotBlank(beginTime)) {
				Date begin = simpleDateFormat.parse(beginTime);
				auditFlowQueryWrapper.gt("submit_time", begin);
			}
			if (StringUtils.isNotBlank(endTime)) {
				Date end = simpleDateFormat.parse(endTime);
				auditFlowQueryWrapper.lt("submit_time", end);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page<AuditFlow> auditFlowPage = new Page<AuditFlow>(curpage, pageSize);
		IPage<AuditFlow> iAuditFlowPage = page(auditFlowPage, auditFlowQueryWrapper);
		map.put("total", iAuditFlowPage.getTotal());
		map.put("maxPage", iAuditFlowPage.getPages());
		map.put("pageSize", 10);
		List<AuditFlow> records = iAuditFlowPage.getRecords();
		if (records != null && records.size() > 0) {
			ArrayList<AuditFlowVo> auditFlowVOs = new ArrayList<AuditFlowVo>();
			for (AuditFlow auditFlow : records) {
				AuditFlowVo auditFlowVo = new AuditFlowVo();
				auditFlowVo.setCode(auditFlow.getCode());
				auditFlowVo.setStatus(getStatusString(auditFlow.getStatus()));
				auditFlowVo.setSubmitTime(auditFlow.getSubmitTime().toLocaleString());
				auditFlowVo.setSubmitUser(auditFlow.getSubmitUserName());
				auditFlowVOs.add(auditFlowVo);
			}
			map.put("auditFlows", auditFlowVOs);
		}
		return map;
	}

	public String getStatusString(Integer status) {
		if (status != null) {
			switch (status) {
			case 1:
				return "待审核";
			case 2:
				return "主任审核通过";
			case 3:
				return "主任审核驳回";
			case 4:
				return "院长审核通过";
			case 5:
				return "院长审核驳回";
			default:
				return "";
			}
		}
		return "";

	}

	@Override
	public HashMap<String, Object> findDetails(String code) {
		HashMap<String,Object> map = new HashMap<>();
		AuditFlow auditFlow = baseMapper.selectById(code);
		if(auditFlow!=null) {
			//项目流程
			map.put("auditFlow", auditFlow);
			ProjectRecordDetailsVO projectRecordDetail = new ProjectRecordDetailsVO();
			
			// 查询项目记录
			QueryWrapper<Record> recordQueryWrapper = new QueryWrapper<Record>();
			recordQueryWrapper.eq("audit_code", auditFlow.getCode());
			Record projectRecord = recordMapper.selectOne(recordQueryWrapper);
			if(projectRecord!=null) {
				projectRecordDetail.setName(projectRecord.getName());
				projectRecordDetail.setProjectCodeFileUrl(projectRecord.getCodePath()) ;
				projectRecordDetail.setProjectDesc(projectRecord.getIntroduction());
				projectRecordDetail.setProjectType(findProjectTypeByCode(projectRecord.getProjectType()));
				projectRecordDetail.setProjectIndustry(findIndustryByCode(projectRecord.getProjectIndustryCode()));
			}
			
			// 查询项目需求
			QueryWrapper<RequireRecord> requireRecordQueryWrapper = new QueryWrapper<RequireRecord>();
			requireRecordQueryWrapper.eq("memo_code", auditFlow.getCode());
			RequireRecord requireRecord = requireRecordMapper.selectOne(requireRecordQueryWrapper);
			if(requireRecord!=null) {
				projectRecordDetail.setProjectDocumentUrl(requireRecord.getDocumentPath()); //项目需求文档
				projectRecordDetail.setProjectFlowDiagramUrl(requireRecord.getFlowDiagram()); //流程图
			}
			
			//查询需求模块记录
			QueryWrapper<RequireModelRecord> requireModelRecordQueryWrapper = new QueryWrapper<RequireModelRecord>();
			requireModelRecordQueryWrapper.eq("memo_code", auditFlow.getCode());
			List<RequireModelRecord> requireModelRecords = requireModelRecordMapper.selectList(requireModelRecordQueryWrapper);
			
			//查询产品设计记录
			QueryWrapper<ProductDesignRecord> productDesignRecordQueryWrapper = new QueryWrapper<ProductDesignRecord>();
			productDesignRecordQueryWrapper.eq("memo_code", auditFlow.getCode());
			ProductDesignRecord productDesignRecord = productDesignRecordMapper.selectOne(productDesignRecordQueryWrapper);
			
			if(productDesignRecord!=null) {
				projectRecordDetail.setUeUrl(productDesignRecord.getUeName()); //低保真
				projectRecordDetail.setUiUrl(productDesignRecord.getUiName()); //高保真
			}
			
			//查询软件设计记录
			QueryWrapper<SoftwareDesignRecord> softwareDesignRecordQueryWrapper = new QueryWrapper<SoftwareDesignRecord>();
			softwareDesignRecordQueryWrapper.eq("memo_code", auditFlow.getCode());
			SoftwareDesignRecord softwareDesignRecord = softwareDesignRecordMapper.selectOne(softwareDesignRecordQueryWrapper);
			
			if(softwareDesignRecord!=null) {
				projectRecordDetail.setProjectSiteStructureUrl(softwareDesignRecord.getSiteStructure()); //架构图
				projectRecordDetail.setProjectFunctionDocumentUrl(softwareDesignRecord.getServiceFile()); //功能设计文档
				projectRecordDetail.setProjectDatabaseDocumentUrl(softwareDesignRecord.getDatabaseFile()); //数据库设计文档
			}
			
			
			//查询前台技术记录
			QueryWrapper<SoftwaredesignfrontTechnologyRecord> softwaredesignfrontTechnologyRecordQueryWrapper = new QueryWrapper<SoftwaredesignfrontTechnologyRecord>();
			softwaredesignfrontTechnologyRecordQueryWrapper.eq("memo_code", auditFlow.getCode());
			List<SoftwaredesignfrontTechnologyRecord> clients = softwaredesignfrontTechnologyRecordMapper.selectList(softwaredesignfrontTechnologyRecordQueryWrapper);
			String clientNames = "";
			String clientCodes = "";
			ArrayList<String> clientCodeList = new ArrayList<String>();
			for (SoftwaredesignfrontTechnologyRecord softwaredesignfrontTechnologyRecord : clients) {
				clientCodes = clientCodes+softwaredesignfrontTechnologyRecord.getTechnologyCode()+",";
				clientCodeList.add(softwaredesignfrontTechnologyRecord.getTechnologyCode());
			}
			if(clientCodeList.size()>0) {
				QueryWrapper<Technology> t1queryWrapper = new QueryWrapper<Technology>();
				t1queryWrapper.in("code",clientCodeList);
				List<Technology> clientTs = technologyMapper.selectList(t1queryWrapper);
				for (Technology technology : clientTs) {
					clientNames = clientNames+technology.getName()+",";
				}
			}
			projectRecordDetail.setClientTechnologyCodes(clientCodes);
			//查询后台技术记录
			QueryWrapper<SoftwaredesignbackstageTechnologyRecord> softwaredesignbackstageTechnologyRecordQueryWrapper = new QueryWrapper<SoftwaredesignbackstageTechnologyRecord>();
			softwaredesignbackstageTechnologyRecordQueryWrapper.eq("memo_code", auditFlow.getCode());
			List<SoftwaredesignbackstageTechnologyRecord> services = softwaredesignbackstageTechnologyRecordMapper.selectList(softwaredesignbackstageTechnologyRecordQueryWrapper);
			String serviceNames = "";
			String serviceCodes = "";
			ArrayList<String> serviceCodeList = new ArrayList<String>();
			for (SoftwaredesignbackstageTechnologyRecord softwaredesignbackstageTechnologyRecord : services) {
				serviceCodes = clientCodes+softwaredesignbackstageTechnologyRecord.getTechnologyCode()+",";
				serviceCodeList.add(softwaredesignbackstageTechnologyRecord.getTechnologyCode());
			}
			if(serviceCodeList.size()>0) {
				QueryWrapper<Technology> t2queryWrapper = new QueryWrapper<Technology>();
				t2queryWrapper.eq("memo_code",auditFlow);
				List<Technology> serviceTs = technologyMapper.selectList(t2queryWrapper);
				for (Technology technology : serviceTs) {
					serviceNames = clientNames+technology.getName()+",";
				}
			}
			projectRecordDetail.setServerTechnologyCodes(serviceCodes);
			projectRecordDetail.setTechnology(serviceNames+clientNames);
			
			
			//查询项目实施记录
			QueryWrapper<MplementationRecord> mplementationRecordQueryWrapper = new QueryWrapper<MplementationRecord>();
			mplementationRecordQueryWrapper.eq("memo_code", auditFlow.getCode());
			MplementationRecord mplementationRecord = mplementationRecordMapper.selectOne(mplementationRecordQueryWrapper);
			
			if(mplementationRecord!=null) {
				projectRecordDetail.setBeginDate(mplementationRecord.getStartTime().toLocaleString()); //开始时间
				projectRecordDetail.setEndDate(mplementationRecord.getEndTime().toLocaleString()); //结束时间
				projectRecordDetail.setProjectPlanUrl(mplementationRecord.getPlanFile());
			}
			
			//查询测试记录
			QueryWrapper<TestRecord> testRecordQueryWrapper = new QueryWrapper<TestRecord>();
			testRecordQueryWrapper.eq("memo_code", auditFlow.getCode());
			TestRecord testRecord = testRecordMapper.selectOne(testRecordQueryWrapper);
			
			if(testRecord!=null) {
				projectRecordDetail.setTestPlanUrl(testRecord.getTestPlan()); //测试计划
				projectRecordDetail.setTestUseCaseUrl(testRecord.getTestBeans()); //测试实例
				projectRecordDetail.setTestReport(testRecord.getTestReport()); //测试报告
			}
			
			//项目当时详情
			map.put("projectRecordDetail",projectRecordDetail);
			
			QueryWrapper<AlterationMemo> alterationMemoQueryWrapper = new QueryWrapper<AlterationMemo>();
			alterationMemoQueryWrapper.eq("flow_code", auditFlow.getCode());
			List<AlterationMemo> alterationMemos = alterationMemoMapper.selectList(alterationMemoQueryWrapper);
			if(alterationMemos!=null&&alterationMemos.size()>0) {
				map.put("memos", alterationMemos);
			}else {
				QueryWrapper<ApprovedMemo> approvedMemoQueryWrapper = new QueryWrapper<ApprovedMemo>();
				approvedMemoQueryWrapper.eq("flow_code", auditFlow.getCode());
				List<ApprovedMemo> approvedMemos = approvedMemoMapper.selectList(approvedMemoQueryWrapper);
				map.put("memos", approvedMemos);
			}
			
		}
		return map;
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


}
