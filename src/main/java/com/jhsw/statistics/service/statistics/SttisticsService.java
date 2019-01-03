/**
 * 
 */
package com.jhsw.statistics.service.statistics;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jhsw.statistics.entities.cbis.CBISttistics;
import com.jhsw.statistics.entities.cbis.CBISttisticsQuery;
import com.jhsw.statistics.entities.cps.CPSttistics;
import com.jhsw.statistics.entities.cps.CPSttisticsQuery;
import com.jhsw.statistics.entities.rbis.RBISttistics;
import com.jhsw.statistics.entities.rbis.RBISttisticsQuery;
import com.jhsw.statistics.entities.rps.RPSttistics;
import com.jhsw.statistics.entities.rps.RPSttisticsQuery;

/**
 * @author Administrator
 *
 */
public interface SttisticsService {

	/**
	 * 区域履职统计列表数据
	 * 
	 * @param query
	 * @return
	 */
	List<RPSttistics> getRPSList(RPSttisticsQuery query);

	/**
	 * 河长履职统计列表数据(分页）
	 * 
	 * @param query
	 * @return
	 */
	PageInfo<CPSttistics> getCPSList4Page(CPSttisticsQuery query);

	/**
	 * 河长履职统计列表数据
	 * 
	 * @param query
	 * @return
	 */
	List<CPSttistics> getCPSList(CPSttisticsQuery query);

	/**
	 * 区域基础信息统计列表数据
	 * 
	 * @param query
	 * @return
	 */
	List<RBISttistics> getRBISList(RBISttisticsQuery query);

	/**
	 * 河道基础信息统计列表数据(分页）
	 * 
	 * @param query
	 * @return
	 */
	PageInfo<CBISttistics> getCBISList4Page(CBISttisticsQuery query);

	/**
	 * 河道基础信息统计列表数据
	 * 
	 * @param query
	 * @return
	 */
	List<CBISttistics> getCBISList(CBISttisticsQuery query);
}
