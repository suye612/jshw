/**
 * 
 */
package com.jhsw.statistics.entities.ar;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class AdminRegion {

	// 区域 1：省级；2：市级；3：县级；4：镇级/街道；5：村级/社区;
	private String code;
	private int regionLevel;
	private String name;

}
