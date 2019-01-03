/**
 * 
 */
package com.jhsw.statistics.service.ar.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhsw.statistics.dao.ar.AdminRegionMapper;
import com.jhsw.statistics.entities.ar.AdminRegion;
import com.jhsw.statistics.entities.ar.AdminRegionQuery;
import com.jhsw.statistics.service.ar.AdminRegionService;

import javax.annotation.Resource;

/**
 * @author Administrator
 *
 */
@Service
public class AdminRegionServiceImpl implements AdminRegionService {

/*	@Autowired
	//	反射机制脱离了spring容器的管理,
	public static AdminRegionServiceImpl dynamicProxy;*/
@Resource
	private AdminRegionMapper adminRegionMapper;

	public List<AdminRegion> getAdminRegion(AdminRegionQuery query) {
		return adminRegionMapper.selectAdminRegion(query);
	}
}
