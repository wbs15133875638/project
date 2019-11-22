package com.botianzu.project.service.impl;

import com.botianzu.project.entity.AuditFlow;
import com.botianzu.project.mapper.AuditFlowMapper;
import com.botianzu.project.mapper.ProductDesignMapper;
import com.botianzu.project.mapper.ProductDesignRecordMapper;
import com.botianzu.project.mapper.RecordMapper;
import com.botianzu.project.mapper.RequireModelMapper;
import com.botianzu.project.mapper.RequireRecordMapper;
import com.botianzu.project.service.IAuditFlowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	public Map<String, Object> findAudits(Integer curpage, String submitUserName, String beginTime, String endTime,
			String name) {
		
		QueryWrapper<AuditFlow> auditFlowQueryWrapper = new QueryWrapper<AuditFlow>();
		auditFlowQueryWrapper.like("submit_user_name",submitUserName);
		auditFlowQueryWrapper.like("project_name",name);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		try {
			if(StringUtils.isNotBlank(beginTime)) {
				Date begin = simpleDateFormat.parse(beginTime);
				auditFlowQueryWrapper.gt("submit_time",begin);
			} 
			if(StringUtils.isNotBlank(endTime)) {
				Date end = simpleDateFormat.parse(endTime);
				auditFlowQueryWrapper.lt("submit_time",end);
			} 
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page<AuditFlow> auditFlowPage = new Page<AuditFlow>(curpage,pageSize);
		IPage<AuditFlow> iAuditFlowPage = page(auditFlowPage, auditFlowQueryWrapper);
		
		
		
		
		return null;
	}

}
