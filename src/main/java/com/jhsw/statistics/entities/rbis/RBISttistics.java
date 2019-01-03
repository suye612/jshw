/**
 * 
 */
package com.jhsw.statistics.entities.rbis;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class RBISttistics {
	/*
	 * 行政区域,标绘河道,市/县/镇/村级河长,工作人员,保洁员,一河一档,一河一策,公示牌,污染源,污水处理厂,监控摄像头,监测断面
	 */
	private String areaCode;
	private int areaLevel;
	private String areaName;
	// 标绘河道
	private int reachCnt;
	// 市/县/镇/村级河长
	private int lv2ChairmanCnt;
	private int lv3ChairmanCnt;
	private int lv4ChairmanCnt;
	private int lv5ChairmanCnt;
	private String chairmanCntDisplay;
	// 工作人员
	private int staffCnt;
	// 保洁员
	private int cleanerCnt;
	// 一河一档
	private int reachDocCnt;
	// 一河一策
	private int policyDocCnt;
	// 公示牌
	private int publicityCnt;
	// 污染源
	private int pollutionCnt;
	// 污水处理厂
	private int treatmentCnt;
	// 监控摄像头
	private int cameraCnt;
	// 监测断面
	private int sectionCnt;
}
