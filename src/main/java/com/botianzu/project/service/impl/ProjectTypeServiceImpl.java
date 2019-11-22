package com.botianzu.project.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.botianzu.project.entity.ProjectType;
import com.botianzu.project.mapper.ProjectTypeMapper;
import com.botianzu.project.service.IProjectTypeService;

/**
 * <p>
 * 项目类型 服务实现类
 * </p>
 *
 * @author 山石
 * @since 2019-11-21
 */
@Service
public class ProjectTypeServiceImpl extends ServiceImpl<ProjectTypeMapper, ProjectType> implements IProjectTypeService {

}
