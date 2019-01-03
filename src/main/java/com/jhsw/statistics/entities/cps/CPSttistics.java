/**
 * 
 */
package com.jhsw.statistics.entities.cps;

import com.jhsw.statistics.entities.Sttistics;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 *
 */
@Getter
@Setter
public class CPSttistics extends Sttistics {

	// 区域 1：省级；2：市级；3：县级；4：镇级/街道；5：村级/社区;
	private String chairmanCode;
	private String chairmanName;
	private int chairmanLevel;
	private String chairmanLevelName;
	private String reachCode;
	private String reachName;
	private int reachLevel;

}
