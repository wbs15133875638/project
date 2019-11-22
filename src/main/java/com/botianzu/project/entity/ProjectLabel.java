package com.botianzu.project.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 项目标签
 * </p>
 *
 * @author 山石
 * @since 2019-11-21
 */
public class ProjectLabel implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Integer id;

	private String code;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
