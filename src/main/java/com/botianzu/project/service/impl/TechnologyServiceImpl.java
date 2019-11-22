package com.botianzu.project.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.botianzu.project.entity.Technology;
import com.botianzu.project.mapper.TechnologyMapper;
import com.botianzu.project.service.ITechnologyService;

/**
 * <p>
 * 技术池 服务实现类
 * </p>
 *
 * @author 山石
 * @since 2019-11-21
 */
@Service
public class TechnologyServiceImpl extends ServiceImpl<TechnologyMapper, Technology> implements ITechnologyService {

}
