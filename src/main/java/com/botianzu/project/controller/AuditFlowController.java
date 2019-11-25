package com.botianzu.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/auditflow")
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
	public Map<String,Object> findAudits(Integer curpage,String submitUserName,String beginTime,String endTime,String name,Integer status) {
		
		if(curpage==null||curpage<1) {
			curpage = 1;
		}
		
		Map<String,Object> map = iAuditFlowService.findAudits(curpage,submitUserName,beginTime,endTime,name,status);
		return map;
	}
	
	@RequestMapping("details")
	public HashMap<String,Object> findAuditFlowDetail(String code) {
		if(StringUtils.isNotBlank(code)) {
			HashMap<String,Object> findDetails = iAuditFlowService.findDetails(code);
			return findDetails;
		}
		return null;
	}
}
