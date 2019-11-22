package com.botianzu.project.service.impl;

import com.botianzu.project.entity.Test;
import com.botianzu.project.mapper.TestMapper;
import com.botianzu.project.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目测试 服务实现类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
