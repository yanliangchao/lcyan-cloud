package com.lcyan.basic.core.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcyan.basic.core.constant.CommonConstant;
import com.lcyan.basic.core.utils.DateUtils;
import com.lcyan.basic.core.utils.IdGen;

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
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    protected Long id;

    /**
     * 创建者
     */
    protected String creator;

    /**
     * 创建日期
     */
    protected Date createDate;

    /**
     * 更新者
     */
    protected String modifier;

    /**
     * 更新日期
     */
    protected Date modifyDate;

    /**
     * 删除标记 0:正常，1-删除
     */
    protected Integer delFlag = CommonConstant.DEL_FLAG_NORMAL;

    /**
     * 系统编号
     */
    protected String applicationCode;


    /**
     * 是否为新记录
     */
    protected boolean isNewRecord;

	/**
	 * 扩展字段
	 */
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

