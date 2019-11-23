package com.botianzu.project.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.botianzu.project.entity.ApprovedMemo;
import com.botianzu.project.entity.Project;
import com.botianzu.project.service.IApprovedMemoService;

/**
 * <p>
 * 审核记录表 控制器
 * </p>
 *
 * @author dan
 * @since 2019-11-20
*/
@RestController
@RequestMapping("/project/approved-memo")
public class ApprovedMemoController{
	
	
	/**
	 * 注入审核记录服务接口
	 */
	@Autowired
	private IApprovedMemoService iApprovedMemoService;
	
	
	
	
	/**
	 * 查询审核记录
	 * 
	 * @param start            起始索引位置
	 * @param pageSize         分页单位
	 * @param code             审核记录编号
	 * @param startCreateeDate 起始创建时间
	 * @param endCreateeDate   结束创建时间
	 * @param name             审核记录名称
	 * @return 审核记录
	 */
	@RequestMapping("/findApprovedMemoByCondition")
	public IPage<ApprovedMemo> findApprovedMemoByCondition(Integer start, Integer pageSize,
			Date startCreateeDate, Date endCreateeDate,Project project) {
		
		IPage<ApprovedMemo> alterationMemos =  iApprovedMemoService.findApprovedMemoByCondition(
				start,pageSize,startCreateeDate,endCreateeDate,project);
		
		

		return alterationMemos;
	}
}
