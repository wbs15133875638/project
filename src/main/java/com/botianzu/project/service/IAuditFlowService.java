package com.botianzu.project.service;

import com.botianzu.project.entity.AuditFlow;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public interface IAuditFlowService extends IService<AuditFlow> {

	Map<String, Object> findAudits(Integer curpage, String submitUserName, String beginTime, String endTime,
			String name, Integer status);

	HashMap<String, Object> findDetails(String code);

}
