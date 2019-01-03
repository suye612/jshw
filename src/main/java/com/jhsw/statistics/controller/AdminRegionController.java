/**
 * 
 */
package com.jhsw.statistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.jhsw.statistics.JhswConstants;
import com.jhsw.statistics.entities.ar.AdminRegion;
import com.jhsw.statistics.entities.ar.AdminRegionQuery;
import com.jhsw.statistics.service.ar.AdminRegionService;
import com.jhsw.statistics.utils.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/statistics")
@Api("行政区域接口")
public class AdminRegionController {

	@Autowired
	private AdminRegionService adminRegionService;

	/**
	 * 行政区域列表
	 *
	 * @param query
	 * @return
	 */
	@GetMapping(value = "/getregionlist")
	@ApiOperation("行政区域列表")
    //springboot中构建api文档
	public JsonResult<List<AdminRegion>> getRegionList(AdminRegionQuery query) {
		JsonResult<List<AdminRegion>> result = new JsonResult<>();

		if (StringUtils.isEmpty(query.getCode())) {
			query.setCode(JhswConstants.REGION_CODE_ZJ);
			query.setRegionLevel(JhswConstants.REGION_LEVEL_PROVINCE);
		}
		query.setRegionLevel(query.getRegionLevel() + 1);

		List<AdminRegion> regionList = adminRegionService.getAdminRegion(query);

		return result.success(regionList);
	}

}