/**
 * 
 */
package com.jhsw.statistics.entities.cps;

import com.jhsw.statistics.entities.rps.RPSttisticsQuery;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 *
 */
@Getter
@Setter
public class CPSttisticsQuery extends RPSttisticsQuery {

	private int page = 1;
	private int pageSize = 10;

	private String chairmanName;
	private int chairmanLevel;

}
