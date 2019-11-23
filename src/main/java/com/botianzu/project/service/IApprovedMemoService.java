package com.botianzu.project.service;

import com.botianzu.project.entity.ApprovedMemo;
import com.botianzu.project.entity.Project;

import java.util.Date;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 审核记录表 服务类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public interface IApprovedMemoService extends IService<ApprovedMemo> {

	IPage<ApprovedMemo> findApprovedMemoByCondition(Integer start, Integer pageSize, Date startCreateeDate,
			Date endCreateeDate, Project project);

}
