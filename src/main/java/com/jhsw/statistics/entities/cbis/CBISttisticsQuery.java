/**
 * 
 */
package com.jhsw.statistics.entities.cbis;

import com.jhsw.statistics.entities.cps.CPSttisticsQuery;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 *
 */
@Getter
@Setter
public class CBISttisticsQuery extends CPSttisticsQuery {

	private String reachName;
	// 一河一档(0:全部 1:有 2:无)
	private int hasReachDoc;
	// 一河一策(0:全部 1:有 2:无)
	private int hasPolicyDoc;
	// 开始年
	private int startYear;
	// 结束年
	private int endYear;
}
