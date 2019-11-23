package com.botianzu.project.service.impl;

import com.botianzu.project.entity.ApprovedMemo;
import com.botianzu.project.entity.Project;
import com.botianzu.project.mapper.ApprovedMemoMapper;
import com.botianzu.project.service.IApprovedMemoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核记录表 服务实现类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
@Service
public class ApprovedMemoServiceImpl extends ServiceImpl<ApprovedMemoMapper, ApprovedMemo>
		implements IApprovedMemoService {

	@Autowired
	private ApprovedMemoMapper approvedMemoMapper;

	@Override
	public IPage<ApprovedMemo> findApprovedMemoByCondition(Integer start, Integer pageSize, Date startCreateeDate,
			Date endCreateeDate, Project project) {

		// 分页单位默认为3
		pageSize = 3;

		// 请求页若为空从第一条开始查询
		if (start == null || start == 0) {
			start = 0;
		}

		QueryWrapper<ApprovedMemo> queryWrapper = new QueryWrapper<>();

		// 根据编号查询
		if (project.getCode() != null && project.getCode() != "") {
			queryWrapper.eq("code", project.getCode());
		}

		// 根据名称模糊查询
		if (project.getName() != null && project.getName() != "") {
			queryWrapper.like("name", project.getName());
		}

		// 根据创建时间区间查询
		if (startCreateeDate != null && endCreateeDate != null) {
			queryWrapper.between("create_time", startCreateeDate, endCreateeDate);
		}

		// 项目类型查询
		if (project.getProjectType() != null) {
			queryWrapper.eq("project_type", project.getProjectType());
		}

		// 项目所在行业查询
		if (project.getProjectIndustryCode() != null && project.getProjectIndustryCode() != "") {
			queryWrapper.eq("project_industry_code", project.getProjectIndustryCode());
		}

		// 分页
		Page<ApprovedMemo> page = new Page<>(start, pageSize);

		// 查询数据
		//IPage<ApprovedMemo> approvedMemos = iApprovedMemoService.page(page, queryWrapper);

		IPage<ApprovedMemo> approvedMemos = approvedMemoMapper.selectPage(page, queryWrapper);
		
		return approvedMemos;
	}

}
