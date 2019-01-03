/**
 * 
 */
package com.jhsw.statistics.entities;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class Sttistics {
	// 应填日志
	private int planLog;
	// 已填日志
	private int doneLog;
	// 日志完成率
	private double logRate;
	private String logRateDisplay;
	// 应处理事件
	private int planEvent;
	// 已处理事件
	private int doneEvent;
	// 事件结案率
	private double eventRate;
	private String eventRateDisplay;
	// 应巡河道
	private int planCheckRiver;
	// 已巡河道
	private int doneCheckRiver;
	// 巡河达标率
	private double checkRiverRate;
	private String checkRiverRateDisplay;
}
