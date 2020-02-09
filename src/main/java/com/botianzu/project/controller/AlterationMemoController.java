package com.botianzu.project.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.botianzu.project.entity.AlterationMemo;
import com.botianzu.project.entity.Project;
import com.botianzu.project.service.IAlterationMemoService;








/**
 * <p>
 * 变更记录 控制器
 * </p>
 *
 * @author dan
 * @since 2019-11-20
*/
@CrossOrigin
@RestController
@RequestMapping("/project/alteration-memo")
public class AlterationMemoController{

	
	@Autowired
	private IAlterationMemoService iAlterationMemoService;

	/**
	 * 根据条件查询变更记录
	 * @param start 起始索引位置
	 * @param pageSize 分页单位
	 * @param code 变更记录编号
	 * @param startCreateeDate 起始创建时间
	 * @param endCreateeDate 结束创建时间
	 * @param name 变更记录名称
	 * @return 变更记录
	 */
	@RequestMapping("/findAlterationMemoByCondition")
	public IPage<AlterationMemo> findAlterationMemoByCondition(Integer start, Integer pageSize,
			Date startCreateeDate, Date endCreateeDate,Project project) {
		
		IPage<AlterationMemo> alterationMemos = iAlterationMemoService.findAlterationMemoByCondition(
				start,pageSize,startCreateeDate,endCreateeDate,project);
		
		
		return alterationMemos;
	}
}
