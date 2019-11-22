package com.botianzu.project.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.botianzu.project.entity.ProjectStatus;
import com.botianzu.project.mapper.ProjectStatusMapper;
import com.botianzu.project.service.IProjectStatusService;

/**
 * <p>
 * 项目状态 服务实现类
 * </p>
 *
 * @author 山石
 * @since 2019-11-21
 */
@Service
public class ProjectStatusServiceImpl extends ServiceImpl<ProjectStatusMapper, ProjectStatus> implements IProjectStatusService {

}
