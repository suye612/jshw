/**
 * 
 */
package com.jhsw.statistics.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.jhsw.statistics.JhswConstants;
import com.jhsw.statistics.entities.cbis.CBISttistics;
import com.jhsw.statistics.entities.cbis.CBISttisticsQuery;
import com.jhsw.statistics.entities.cps.CPSttistics;
import com.jhsw.statistics.entities.cps.CPSttisticsQuery;
import com.jhsw.statistics.entities.rbis.RBISttistics;
import com.jhsw.statistics.entities.rbis.RBISttisticsQuery;
import com.jhsw.statistics.entities.rps.RPSttistics;
import com.jhsw.statistics.entities.rps.RPSttisticsQuery;
import com.jhsw.statistics.service.statistics.SttisticsService;
import com.jhsw.statistics.utils.DateUtils;
import com.jhsw.statistics.utils.JsonResult;
import com.jhsw.statistics.utils.excel.ExcelData;
import com.jhsw.statistics.utils.excel.ExportExcelUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/statistics")
@Api("统计接口")
@Slf4j
public class SttisticsController {

	@Autowired
	private SttisticsService sttisticsService;

	/**
	 * 区域履职统计
	 * 
	 * @param query
	 * @param br
	 * @return
	 */
	@PostMapping("/rps/getrpslist")
	@ResponseBody
	@ApiOperation("区域履职统计")
	@ApiImplicitParam(name = "query", value = "检索条件（日期格式：yyyy-MM-dd）", dataType = "RPSttisticsQuery")
	public JsonResult<List<RPSttistics>> getRPSList(@RequestBody @Valid RPSttisticsQuery query, BindingResult br) {
		JsonResult<List<RPSttistics>> result = new JsonResult<>();

		if (br.hasErrors()) {
			return result.fail(br.getAllErrors().get(0).getDefaultMessage());
		}

		query = opreateQuery(query);

		if (query.getRegionLevel() < 2 || query.getRegionLevel() > 5) {
			return result.fail("行政区域等级错误");
		}

		List<RPSttistics> list = sttisticsService.getRPSList(query);

		return result.success(list);
	}

	/**
	 * 区域履职统计导出
	 * 
	 * @param query
	 * @param br
	 * @param response
	 * @return
	 */
	@GetMapping("/rps/exportrpslist")
	@ResponseBody
	@ApiOperation("区域履职统计导出")
	public JsonResult<Object> exportRPSList(@Valid RPSttisticsQuery query, BindingResult br, HttpServletResponse response) {
		JsonResult<Object> result = new JsonResult<>();

		if (br.hasErrors()) {
			return result.fail(br.getAllErrors().get(0).getDefaultMessage());
		}

		query = opreateQuery(query);

		if (query.getRegionLevel() < 2 || query.getRegionLevel() > 5) {
			return result.fail("行政区域等级错误");
		}

		List<RPSttistics> list = sttisticsService.getRPSList(query);

		if (list == null || list.size() == 0) {
			return result.fail("无数据导出");
		}

		ExcelData data = new ExcelData();
		String sTitle = "区域,应填/已填日志,日志完成率,应处理/已处理事件,事件结案率,应巡/已巡河道,巡河达标率";
		String fName = "区域履职统计";
		data.setName(fName);
		List<String> titles = new ArrayList<>(Arrays.asList(sTitle.split(",")));
		data.setTitles(titles);

		List<List<Object>> rows = new ArrayList<>();
		List<Object> row = null;

		for (RPSttistics rps : list) {
			row = new ArrayList<>();

			row.add(rps.getAreaName());
			row.add(rps.getPlanLog() + "/" + rps.getDoneLog());
			row.add(rps.getLogRateDisplay());
			row.add(rps.getPlanEvent() + "/" + rps.getDoneEvent());
			row.add(rps.getEventRateDisplay());
			row.add(rps.getPlanCheckRiver() + "/" + rps.getDoneCheckRiver());
			row.add(rps.getCheckRiverRateDisplay());

			rows.add(row);
		}

		data.setRows(rows);

		try (final OutputStream os = response.getOutputStream()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ExportExcelUtils.exportExcel(response, fName + sdf.format(new Date()) + ".xlsx", data);
			return null;
		} catch (Exception e) {
			log.error("生成csv文件失败", e);
		}

		return result.fail("数据导出出错");
	}

	/**
	 * 河长履职统计
	 * 
	 * @param query
	 * @param br
	 * @return
	 */
	@PostMapping("/cps/getcpslist")
	@ResponseBody
	@ApiOperation("河长履职统计")
	@ApiImplicitParam(name = "query", value = "检索条件（日期格式：yyyy-MM-dd）", dataType = "CPSttisticsQuery")
	public JsonResult<PageInfo<CPSttistics>> getCPSList(@RequestBody @Valid CPSttisticsQuery query, BindingResult br) {
		JsonResult<PageInfo<CPSttistics>> result = new JsonResult<>();

		if (br.hasErrors()) {
			return result.fail(br.getAllErrors().get(0).getDefaultMessage());
		}

		query = (CPSttisticsQuery) opreateQuery(query);

		if (query.getRegionLevel() < 2 || query.getRegionLevel() > 5) {
			return result.fail("行政区域等级错误");
		}

		PageInfo<CPSttistics> cpsList = sttisticsService.getCPSList4Page(query);

		return result.success(cpsList);
	}

	/**
	 * 河长履职统计导出
	 * 
	 * @param query
	 * @param br
	 * @param response
	 * @return
	 */
	@GetMapping("/cps/exportcpslist")
	@ResponseBody
	@ApiOperation("河长履职统计导出")
	public JsonResult<Object> exportCPSList(@Valid CPSttisticsQuery query, BindingResult br, HttpServletResponse response) {
		JsonResult<Object> result = new JsonResult<>();

		if (br.hasErrors()) {
			return result.fail(br.getAllErrors().get(0).getDefaultMessage());
		}

		query = (CPSttisticsQuery) opreateQuery(query);

		if (query.getRegionLevel() < 2 || query.getRegionLevel() > 5) {
			return result.fail("行政区域等级错误");
		}

		List<CPSttistics> list = sttisticsService.getCPSList(query);

		if (list == null || list.size() == 0) {
			return result.fail("无数据导出");
		}

		ExcelData data = new ExcelData();
		String sTitle = "河长,级别,河道名称,应填/已填日志,日志完成率,应处理/已处理事件,事件结案率,应巡/已巡河道,巡河达标率";
		String fName = "河长履职统计";
		data.setName(fName);
		List<String> titles = new ArrayList<>(Arrays.asList(sTitle.split(",")));
		data.setTitles(titles);

		List<List<Object>> rows = new ArrayList<>();
		List<Object> row = null;

		for (CPSttistics cps : list) {
			row = new ArrayList<>();

			row.add(cps.getChairmanName());
			row.add(cps.getChairmanLevelName());
			row.add(cps.getReachName());
			row.add(cps.getPlanLog() + "/" + cps.getDoneLog());
			row.add(cps.getLogRateDisplay());
			row.add(cps.getPlanEvent() + "/" + cps.getDoneEvent());
			row.add(cps.getEventRateDisplay());
			row.add(cps.getPlanCheckRiver() + "/" + cps.getDoneCheckRiver());
			row.add(cps.getCheckRiverRateDisplay());

			rows.add(row);
		}

		data.setRows(rows);

		try (final OutputStream os = response.getOutputStream()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ExportExcelUtils.exportExcel(response, fName + sdf.format(new Date()) + ".xlsx", data);
			return null;
		} catch (Exception e) {
			log.error("生成csv文件失败", e);
		}

		return result.fail("数据导出出错");
	}

	/**
	 * 区域基础信息统计
	 * 
	 * @param query
	 * @return
	 */
	@PostMapping("/rbis/getrbislist")
	@ResponseBody
	@ApiOperation("区域基础信息统计")
	public JsonResult<List<RBISttistics>> getRBISList(@RequestBody RBISttisticsQuery query) {
		JsonResult<List<RBISttistics>> result = new JsonResult<>();

		if (StringUtils.isEmpty(query.getCode())) {
			query.setCode(JhswConstants.REGION_CODE_ZJ);
			query.setRegionLevel(JhswConstants.REGION_LEVEL_PROVINCE);
		}
		query.setRegionLevel(query.getRegionLevel() + 1);

		if (query.getRegionLevel() < 2 || query.getRegionLevel() > 5) {
			return result.fail("行政区域等级错误");
		}

		List<RBISttistics> list = sttisticsService.getRBISList(query);

		return result.success(list);
	}

	/**
	 * 区域基础信息统计导出
	 * 
	 * @param query
	 * @param response
	 * @return
	 */
	@GetMapping("/rbis/exportrbislist")
	@ResponseBody
	@ApiOperation("区域基础信息统计导出")
	public JsonResult<Object> exportRBISList(RBISttisticsQuery query, HttpServletResponse response) {
		JsonResult<Object> result = new JsonResult<>();

		if (StringUtils.isEmpty(query.getCode())) {
			query.setCode(JhswConstants.REGION_CODE_ZJ);
			query.setRegionLevel(JhswConstants.REGION_LEVEL_PROVINCE);
		}
		query.setRegionLevel(query.getRegionLevel() + 1);

		if (query.getRegionLevel() < 2 || query.getRegionLevel() > 5) {
			return result.fail("行政区域等级错误");
		}

		List<RBISttistics> list = sttisticsService.getRBISList(query);

		if (list == null || list.size() == 0) {
			return result.fail("无数据导出");
		}

		ExcelData data = new ExcelData();
		String sTitle = "行政区域,标绘河道,市/县/镇/村级河长,工作人员,保洁员,一河一档,一河一策,公示牌,污染源,污水处理厂,监控摄像头,监测断面";
		String fName = "区域基础信息统计";
		data.setName(fName);
		List<String> titles = new ArrayList<>(Arrays.asList(sTitle.split(",")));
		data.setTitles(titles);

		List<List<Object>> rows = new ArrayList<>();
		List<Object> row = null;

		for (RBISttistics rbis : list) {
			row = new ArrayList<>();

			row.add(rbis.getAreaName());
			row.add(rbis.getReachCnt());
			row.add(rbis.getChairmanCntDisplay());
			row.add(rbis.getStaffCnt());
			row.add(rbis.getCleanerCnt());
			row.add(rbis.getReachDocCnt());
			row.add(rbis.getPolicyDocCnt());
			row.add(rbis.getPublicityCnt());
			row.add(rbis.getPollutionCnt());
			row.add(rbis.getTreatmentCnt());
			row.add(rbis.getCameraCnt());
			row.add(rbis.getSectionCnt());

			rows.add(row);
		}

		data.setRows(rows);

		try (final OutputStream os = response.getOutputStream()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ExportExcelUtils.exportExcel(response, fName + sdf.format(new Date()) + ".xlsx", data);
			return null;
		} catch (Exception e) {
			log.error("生成csv文件失败", e);
		}

		return result.fail("数据导出出错");
	}

	/**
	 * 河道基础信息统计
	 * 
	 * @param query
	 * @param br
	 * @return
	 */
	@PostMapping("/cbis/getcbislist")
	@ResponseBody
	@ApiOperation("河道基础信息统计")
	public JsonResult<PageInfo<CBISttistics>> getCBISList(@RequestBody @Valid CBISttisticsQuery query, BindingResult br) {
		JsonResult<PageInfo<CBISttistics>> result = new JsonResult<>();

		if (br.hasErrors()) {
			return result.fail(br.getAllErrors().get(0).getDefaultMessage());
		}

		query = opreateCBISQuery(query);

		if (query.getRegionLevel() < 2 || query.getRegionLevel() > 5) {
			return result.fail("行政区域等级错误");
		}

		PageInfo<CBISttistics> cpsList = sttisticsService.getCBISList4Page(query);

		return result.success(cpsList);
	}

	/**
	 * 河道基础信息统计导出
	 * 
	 * @param query
	 * @param br
	 * @param response
	 * @return
	 */
	@GetMapping("/cbis/exportcbislist")
	@ResponseBody
	@ApiOperation("河道基础信息统计导出")
	public JsonResult<Object> exportCBISList(@Valid CBISttisticsQuery query, BindingResult br, HttpServletResponse response) {
		JsonResult<Object> result = new JsonResult<>();

		if (br.hasErrors()) {
			return result.fail(br.getAllErrors().get(0).getDefaultMessage());
		}

		query = opreateCBISQuery(query);

		if (query.getRegionLevel() < 2 || query.getRegionLevel() > 5) {
			return result.fail("行政区域等级错误");
		}

		List<CBISttistics> list = sttisticsService.getCBISList(query);

		if (list == null || list.size() == 0) {
			return result.fail("无数据导出");
		}

		ExcelData data = new ExcelData();
		String sTitle = "河道名称,级别,河长,排污口,随手拍,公示牌,一河一档,一河一策,档案策略,畜禽养殖场,处理设施";
		String fName = "河道基础信息统计";
		data.setName(fName);
		List<String> titles = new ArrayList<>(Arrays.asList(sTitle.split(",")));
		data.setTitles(titles);

		List<List<Object>> rows = new ArrayList<>();
		List<Object> row = null;

		for (CBISttistics cbis : list) {
			row = new ArrayList<>();

			row.add(cbis.getReachName());
			row.add(cbis.getReachLevelName());
			row.add(cbis.getChairmanName());
			row.add(cbis.getOutletCnt());
			row.add(cbis.getAlbumCnt());
			row.add(cbis.getPublicityCnt());
			row.add(cbis.getReachDocCnt());
			row.add(cbis.getPolicyDocCnt());
			row.add(cbis.getRpDocCnt());
			row.add(cbis.getFarmCnt());
			row.add(cbis.getTfCnt());

			rows.add(row);
		}

		data.setRows(rows);

		try (final OutputStream os = response.getOutputStream()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ExportExcelUtils.exportExcel(response, fName + sdf.format(new Date()) + ".xlsx", data);
			return null;
		} catch (Exception e) {
			log.error("生成csv文件失败", e);
		}

		return result.fail("数据导出出错");
	}

	private RPSttisticsQuery opreateQuery(RPSttisticsQuery query) {
		if (StringUtils.isEmpty(query.getCode())) {
			query.setCode(JhswConstants.REGION_CODE_ZJ);
			query.setRegionLevel(JhswConstants.REGION_LEVEL_PROVINCE);
		}
		query.setRegionLevel(query.getRegionLevel() + 1);

		if (StringUtils.isEmpty(query.getEndDate())) {
			query.setEndDate(DateUtils.getDate());
		}

		// 计算开始日期与结束日期之间的天数，周数，月数
		Period p = DateUtils.betweenWithString(query.getStartDate(), query.getEndDate());
		query.setDays(DateUtils.untilDays(query.getStartDate(), query.getEndDate()));
		query.setWeeks(DateUtils.untilWeeks(query.getStartDate(), query.getEndDate()) + (p.getDays() % 7 == 0 ? 0 : 1));
		query.setMonths(DateUtils.untilMonths(query.getStartDate(), query.getEndDate()) + (p.getDays() == 0 ? 0 : 1));

		query.setStartDate(query.getStartDate() + " 00:00:00");
		query.setEndDate(query.getEndDate() + " 23:59:59");

		return query;
	}

	private CBISttisticsQuery opreateCBISQuery(CBISttisticsQuery query) {
		query = (CBISttisticsQuery) opreateQuery(query);

		// 开始年
		query.setStartYear(Integer.valueOf(query.getStartDate().substring(0, 4)));
		// 结束年
		query.setEndYear(Integer.valueOf(query.getEndDate().substring(0, 4)));

		return query;
	}

}
