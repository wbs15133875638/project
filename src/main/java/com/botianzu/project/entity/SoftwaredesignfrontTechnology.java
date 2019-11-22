package com.botianzu.project.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 项目软件设计 前台_技术中间表 
 * </p>
 *
 * @author dan
 * @since 2019-11-20
 */
public class SoftwaredesignfrontTechnology implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 对应的软件设计编号
     */
    private String softwareDesignCode;

    /**
     * 技术编号
     */
    private String technologyCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSoftwareDesignCode() {
		return softwareDesignCode;
	}

	public void setSoftwareDesignCode(String softwareDesignCode) {
		this.softwareDesignCode = softwareDesignCode;
	}

	public String getTechnologyCode() {
		return technologyCode;
	}

	public void setTechnologyCode(String technologyCode) {
		this.technologyCode = technologyCode;
	}


}
