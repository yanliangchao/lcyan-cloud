package com.lcyan.basic.core.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcyan.basic.core.constant.CommonConstant;
import com.lcyan.basic.core.utils.DateUtils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity基类
 *
 * @author tangyi
 * @date 2018-08-24 18:58
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // id 字段
	@Column(columnDefinition = "INT(11) UNSIGNED COMMENT 'id'")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    protected Long id;

    /**
     * 创建者
     */
    @Column(name = "creator")
    protected String creator;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    protected Date createDate;

    /**
     * 更新者
     */
    @Column(name = "modifier")
    protected String modifier;

    /**
     * 更新日期
     */
    @Column(name = "modify_date")
    protected Date modifyDate;

    /**
     * 删除标记 0:正常，1-删除
     */
    @Column(name = "delFlag")
    protected Integer delFlag = CommonConstant.DEL_FLAG_NORMAL;

    /**
     * 系统编号
     */
    @Column(name = "application_code")
    protected String applicationCode;


    /**
     * 是否为新记录
     */
    @Column(name = "is_new_record")
    protected boolean isNewRecord;

	/**
	 * 扩展字段
	 */
    @Column(name = "ext")
	protected String ext;

    public BaseEntity(Long id) {
        this();
        this.id = id;
    }

    /**
     * 是否为新记录
     *
     * @return boolean
     */
    public boolean isNewRecord() {
        return this.isNewRecord || this.getId() == null;
    }

    /**
     * 设置基本属性
     *
     * @param userCode        用户编码
     * @param applicationCode 系统编码
     */
    public void setCommonValue(String userCode, String applicationCode) {
        Date currentDate = DateUtils.asDate(LocalDateTime.now());
        if (this.isNewRecord()) {
            this.setNewRecord(true);
            this.creator = userCode;
            this.createDate = currentDate;
        }
        this.modifier = userCode;
        this.modifyDate = currentDate;
        this.delFlag = 0;
        this.applicationCode = applicationCode;
    }

	/**
	 * 置空属性
	 */
	public void clearCommonValue() {
		this.creator = null;
		this.createDate = null;
		this.modifier = null;
		this.modifyDate = null;
    	this.delFlag = null;
    	this.applicationCode = null;
	}
}

