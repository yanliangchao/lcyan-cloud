package com.lcyan.basic.core.model;

import java.io.Serializable;

import com.lcyan.basic.core.constant.ApiMsg;

import lombok.Data;

/**
 * 封装返回数据
 * @author ayan2070
 *
 * @param <T>
 */
@Data
public class ResponseBean<T> implements Serializable {

	public static final long serialVersionUID = 42L;

	private String msg = ApiMsg.msg(ApiMsg.KEY_SUCCESS);

	private int code = ApiMsg.KEY_SUCCESS;

	private T data;

	public ResponseBean() {
		super();
	}

	public ResponseBean(T data) {
		super();
		this.data = data;
	}

	public ResponseBean(T data, int keyCode, int msgCode) {
		super();
		this.data = data;
		this.code = Integer.parseInt(keyCode + "" + msgCode);
		this.msg = ApiMsg.code2Msg(keyCode, msgCode);
	}

	public ResponseBean(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}
}
