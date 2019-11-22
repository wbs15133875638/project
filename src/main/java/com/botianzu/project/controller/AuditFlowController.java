package com.botianzu.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.botianzu.project.entity.AuditFlow;
import com.botianzu.project.service.IAuditFlowService;

/**
 * <p>
 *   审核流程 控制器
 * </p>
 *
 * @author dan
 * @since 2019-11-20
*/
@RestController
@RequestMapping("/project/audit-flow")
public class AuditFlowController{

	@Autowired
	private IAuditFlowService iAuditFlowService;
	
	/**
	 * 查询我的审核列表
	 * @param curpage 当前页
	 * @param submitUserName 提交用户
	 * @param beginTime 
	 * @param endTime  提交时间范围
	 * @param name 项目名
	 */
	@RequestMapping
	public void findAudits(Integer curpage,String submitUserName,String beginTime,String endTime,String name) {
		
		Map<String,Object> map = iAuditFlowService.findAudits(curpage,submitUserName,beginTime,endTime,name);
		
	}
}
