/**
 * 
 */
package com.jhsw.statistics.dao.statistics;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

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
@CacheConfig(cacheNames = "jhsw")
public interface SttisticsMapper {
	/**
	 * 区域履职统计
	 * 
	 * @param query
	 * @return
	 */
	@Cacheable(key = "#root.methodName+#query.toString()")
	List<RPSttistics> selectRPS(RPSttisticsQuery query);

	/**
	 * 河长履职统计
	 * 
	 * @param query
	 * @return
	 */
	@Cacheable(key = "#root.methodName+#query.toString()")
	List<CPSttistics> selectCPS(CPSttisticsQuery query);

	/**
	 * 区域基础信息统计
	 * 
	 * @param query
	 * @return
	 */
	@Cacheable(key = "#root.methodName+#query.toString()")
	List<RBISttistics> selectRBISList(RBISttisticsQuery query);

	/**
	 * 河道基础信息统计
	 * 
	 * @param query
	 * @return
	 */
	@Cacheable(key = "#root.methodName+#query.toString()")
	List<CBISttistics> selectCBISList(CBISttisticsQuery query);
}
