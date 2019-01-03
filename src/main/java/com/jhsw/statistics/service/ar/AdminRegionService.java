/**
 * 
 */
package com.jhsw.statistics.service.ar;

import java.util.List;

import com.jhsw.statistics.entities.ar.AdminRegion;
import com.jhsw.statistics.entities.ar.AdminRegionQuery;

/**
 * @author Administrator
 *
 */
public interface AdminRegionService {

	/*
	 * 区域列表
	 */
	List<AdminRegion> getAdminRegion(AdminRegionQuery query);

}
