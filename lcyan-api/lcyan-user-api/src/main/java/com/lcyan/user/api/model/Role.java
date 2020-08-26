package com.lcyan.user.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.lcyan.basic.core.persistence.entity.BaseEntity;

import lombok.Data;

/**
 * 角色
 *
 * @author tangyi
 * @date 2018-08-25 13:58
 */
@Data
@Entity
@Table(name = "t_role")
@org.hibernate.annotations.Table(appliesTo = "t_role", comment = "用户")
public class Role extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "角色名称不能为空")
    @Column(name = "role_name")
    private String roleName;

    @NotBlank(message = "角色标识不能为空")
    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_desc")
    private String roleDesc;

    @Column(name = "status")
    private Integer status;

    @Column(name = "dept_name")
    private String deptName;

    //private List<Menu> menus;

    /**
     * 是否默认 0-否，1-是
     */
    @Column(name = "is_default")
    private Integer isDefault;
}
