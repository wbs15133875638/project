package com.botianzu.project.service;

import com.botianzu.project.entity.AlterationMemo;
import com.botianzu.project.entity.Project;

import java.util.Date;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 变更记录 服务类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public interface IAlterationMemoService extends IService<AlterationMemo> {

	IPage<AlterationMemo> findAlterationMemoByCondition(Integer start, Integer pageSize, Date startCreateeDate,
			Date endCreateeDate, Project project);

}
