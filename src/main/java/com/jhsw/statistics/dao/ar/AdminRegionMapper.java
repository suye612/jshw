/**
 * 
 */
package com.jhsw.statistics.dao.ar;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.jhsw.statistics.entities.ar.AdminRegion;
import com.jhsw.statistics.entities.ar.AdminRegionQuery;

/**
 * @author Administrator
 *
 */
@CacheConfig(cacheNames = "jhsw")
//配置了该数据访问对象中返回的内容将存储于名为users的缓存对象中
public interface AdminRegionMapper {
	@Cacheable(key = "#root.methodName+#query.toString()")
	List<AdminRegion> selectAdminRegion(AdminRegionQuery query);
}
