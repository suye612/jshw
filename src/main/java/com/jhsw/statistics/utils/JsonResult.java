/**
 * 
 */
package com.jhsw.statistics.utils;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class JsonResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 1;
	public static final int ERROR = -1;
	public static final String MSG_SUCCESS = "处理成功";
	public static final String MSG_ERROR = "处理失败";

	private int status;
	private String msg;
	private T data;

	public JsonResult<T> success(T data) {
		this.setStatus(SUCCESS);
		this.setMsg(MSG_SUCCESS);
		this.setData(data);
		return this;
	}

	public JsonResult<T> fail() {
		return fail(MSG_ERROR);
	}

	public JsonResult<T> fail(String msg) {
		this.setStatus(ERROR);
		this.setMsg(msg);
		return this;
	}
}
