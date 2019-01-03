/**
 * 
 */
package com.jhsw.statistics.entities.rps;

import com.jhsw.statistics.entities.Sttistics;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 *
 */
@Getter
@Setter
public class RPSttistics extends Sttistics {

	// 区域 1：省级；2：市级；3：县级；4：镇级/街道；5：村级/社区;
	private String areaCode;
	private int areaLevel;
	private String areaName;

}
