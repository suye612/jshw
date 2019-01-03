/**
 * 
 */
package com.jhsw.statistics.utils.excel;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class ExcelData implements Serializable {

	private static final long serialVersionUID = 4444017239100620999L;

	private List<String> titles;

	private List<List<Object>> rows;

	private String name;

}
