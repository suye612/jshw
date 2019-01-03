/**
 * 
 */
package com.jhsw.statistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/statistics")
@Api("统计页面")
public class PageController {

	/**
	 * 区域履职统计页面
	 * 
	 * @return
	 */
	@GetMapping("/rps")
	@ApiOperation("区域履职统计页面")
	public ModelAndView indexRPS() {
		return new ModelAndView("statistics/rps");
	}

	/**
	 * 河长履职统计页面
	 * 
	 * @return
	 */
	@GetMapping("/rmps")
	@ApiOperation("河长履职统计页面")
	public ModelAndView indexRMPS() {
		return new ModelAndView("statistics/rmps");
	}

	/**
	 * 区域基础信息统计页面
	 * 
	 * @return
	 */
	@GetMapping("/rbis")
	@ApiOperation("区域基础信息统计页面")
	public ModelAndView indexRBIS() {

		return new ModelAndView("statistics/rbis");
	}

	/**
	 * 河道基础信息统计页面
	 * 
	 * @return
	 */
	@GetMapping("/rfis")
	@ApiOperation("河道基础信息统计页面")
	public ModelAndView indexRFIS() {

		return new ModelAndView("statistics/rfis");
	}

}
