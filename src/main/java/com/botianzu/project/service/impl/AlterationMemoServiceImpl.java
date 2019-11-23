package com.botianzu.project.service.impl;

import com.botianzu.project.entity.AlterationMemo;
import com.botianzu.project.entity.Project;
import com.botianzu.project.mapper.AlterationMemoMapper;
import com.botianzu.project.service.IAlterationMemoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 变更记录 服务实现类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
@Service
public class AlterationMemoServiceImpl extends ServiceImpl<AlterationMemoMapper, AlterationMemo>
		implements IAlterationMemoService {

	@Autowired
	private AlterationMemoMapper alterationMemoMapper;

	@Override
	public IPage<AlterationMemo> findAlterationMemoByCondition(Integer start, Integer pageSize, Date startCreateeDate,
			Date endCreateeDate, Project project) {

		// 分页单位默认为3
		pageSize = 2;

		// 请求页若为空从第一条开始查询
		if (start == null || start == 0) {
			start = 0;
		}
		
		QueryWrapper<AlterationMemo> queryWrapper = new QueryWrapper<>();

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
		Page<AlterationMemo> page = new Page<>(start, pageSize);

		// 查询变更记录
		//IPage<AlterationMemo> alterationMemos = iAlterationMemoService.page(page, queryWrapper);
		
		
		IPage<AlterationMemo> alterationMemos = alterationMemoMapper.selectPage(page, queryWrapper);

		return alterationMemos;
	}

}
