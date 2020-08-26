package com.lcyan.user.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.lcyan.basic.core.persistence.entity.BaseEntity;

import lombok.Data;

/**
 * 用户基本信息
 *
 * @author tangyi
 * @date 2018-08-25 15:30
 */
@Data
@Entity
@Table(name = "t_user", indexes = { 
	@Index(name = "name", columnList = "name", unique = true),
})
@org.hibernate.annotations.Table(appliesTo = "t_user", comment = "用户")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
    @Id // id 字段
	@Column(columnDefinition = "INT(11) UNSIGNED COMMENT 'id'")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;*/
    
	/**
     * 姓名
     */
	@Column(name = "name")
    private String name;

    /**
     * 电话
     */
    @Pattern(regexp = "^\\d{11}$", message = "请输入11位手机号")
    @Column(name = "phone")
    private String phone;

    /**
     * 头像id
     */
    @Column(name = "avatar_id")
    private Long avatarId;

    /**
     * 邮箱
     */
    @Column(name = "email")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 出生日期
     */
    @Column(name = "born")
    private Date born;

    /**
     * 描述
     */
    @Column(name = "user_desc")
    private String userDesc;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 角色列表
     */
    //private List<Role> roleList;

    /**
     * 最近登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 用户归档时间
     */
    @Column(name = "lock_time")
    private Date lockTime;

    /**
     * 微信
     */
    @Column(name = "wechat")
    private String wechat;

    /**
     * 个性签名
     */
    @Column(name = "signature")
    private String signature;
    
    
}
