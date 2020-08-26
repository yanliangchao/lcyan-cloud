package com.lcyan.user.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcyan.basic.core.persistence.entity.BaseEntity;

import lombok.Data;

/**
 * 菜单
 *
 * @author tangyi
 * @date 2018/8/26 22:21
 */
@Data
@Entity
@Table(name = "t_menu")
@org.hibernate.annotations.Table(appliesTo = "t_menu", comment = "用户")
public class Menu extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Column(name = "name")
    private String name;

    /**
     * 菜单权限标识
     */
    @Column(name = "permission")
    private String permission;

    /**
     * url
     */
    @Column(name = "url")
    private String url;

    /**
     * 重定向url
     */
    @Column(name = "redirect")
    private String redirect;

    /**
     * 父菜单ID
     */
    @Column(name = "parent_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 排序号
     */
    @Column(name = "sort")
    private String sort;

    /**
     * 类型
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 模块
     */
    @Column(name = "component")
    private String component;

    /**
     * 路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}
