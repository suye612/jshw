/**
 * 
 */
package com.jhsw.statistics.entities.cbis;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class CBISttistics {
	// 河道名称
	private String reachCode;
	private String reachName;
	// 级别
	private int reachLevel;
	private String reachLevelName;
	// 河长
	private String chairmanCode;
	private String chairmanName;
	// 排污口
	private int outletCnt;
	// 随手拍
	private int albumCnt;
	// 公示牌
	private int publicityCnt;
	// 一河一档
	private int reachDocCnt;
	// 一河一策
	private int policyDocCnt;
	// 档案策略
	private int rpDocCnt;
	// 畜禽养殖场
	private int farmCnt;
	// 处理设施
	private int tfCnt;

}
