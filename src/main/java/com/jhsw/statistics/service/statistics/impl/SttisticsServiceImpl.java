/**
 * 
 */
package com.jhsw.statistics.service.statistics.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhsw.statistics.dao.statistics.SttisticsMapper;
import com.jhsw.statistics.entities.cbis.CBISttistics;
import com.jhsw.statistics.entities.cbis.CBISttisticsQuery;
import com.jhsw.statistics.entities.cps.CPSttistics;
import com.jhsw.statistics.entities.cps.CPSttisticsQuery;
import com.jhsw.statistics.entities.rbis.RBISttistics;
import com.jhsw.statistics.entities.rbis.RBISttisticsQuery;
import com.jhsw.statistics.entities.rps.RPSttistics;
import com.jhsw.statistics.entities.rps.RPSttisticsQuery;
import com.jhsw.statistics.service.statistics.SttisticsService;

/**
 * @author Administrator
 *
 */
@Service
public class SttisticsServiceImpl implements SttisticsService {

	@Autowired
	private SttisticsMapper sttisticsMapper;

	public List<RPSttistics> getRPSList(RPSttisticsQuery query) {
		int pl = 0, dl = 0, pe = 0, de = 0, pcr = 0, dcr = 0;

		List<RPSttistics> result = sttisticsMapper.selectRPS(query);

		if (result == null || result.size() == 0) {
			return result;
		}

		for (RPSttistics r : result) {
			pl += r.getPlanLog();
			dl += r.getDoneLog();
			pe += r.getPlanEvent();
			de += r.getDoneEvent();
			pcr += r.getPlanCheckRiver();
			dcr += r.getDoneCheckRiver();

			r.setLogRate(r.getDoneLog() == 0 ? 0.0D : calcPercent(r.getDoneLog(), r.getPlanLog()));
			r.setLogRateDisplay(String.format("%.2f", r.getLogRate()) + "%");
			r.setEventRate(r.getDoneEvent() == 0 ? 0.0D : calcPercent(r.getDoneEvent(), r.getPlanEvent()));
			r.setEventRateDisplay(String.format("%.2f", r.getEventRate()) + "%");
			r.setCheckRiverRate(r.getDoneCheckRiver() == 0 ? 0.0D : calcPercent(r.getDoneCheckRiver(), r.getPlanCheckRiver()));
			r.setCheckRiverRateDisplay(String.format("%.2f", r.getCheckRiverRate()) + "%");
		}

		RPSttistics t = new RPSttistics();
		t.setAreaCode("total");
		t.setAreaName("总计");
		t.setAreaLevel(0);
		t.setPlanLog(pl);
		t.setDoneLog(dl);
		t.setPlanEvent(pe);
		t.setDoneEvent(de);
		t.setPlanCheckRiver(pcr);
		t.setDoneCheckRiver(dcr);
		t.setLogRate(dl == 0 ? 0.0D : calcPercent(dl, pl));
		t.setLogRateDisplay(String.format("%.2f", t.getLogRate()) + "%");
		t.setEventRate(de == 0 ? 0.0D : calcPercent(de, pe));
		t.setEventRateDisplay(String.format("%.2f", t.getEventRate()) + "%");
		t.setCheckRiverRate(dcr == 0 ? 0.0D : calcPercent(dcr, pcr));
		t.setCheckRiverRateDisplay(String.format("%.2f", t.getCheckRiverRate()) + "%");

		result.add(t);

		return result;
	}

	public PageInfo<CPSttistics> getCPSList4Page(CPSttisticsQuery query) {
		PageHelper.startPage(query.getPage(), query.getPageSize());

		List<CPSttistics> result = sttisticsMapper.selectCPS(query);

		PageInfo<CPSttistics> info = new PageInfo<>(result);

		info.setList(info.getList().stream().map(cps -> {
			cps.setLogRate(cps.getDoneLog() == 0 ? 0.0D : calcPercent(cps.getDoneLog(), cps.getPlanLog()));
			cps.setLogRateDisplay(String.format("%.2f", cps.getLogRate()) + "%");
			cps.setEventRate(cps.getDoneEvent() == 0 ? 0.0D : calcPercent(cps.getDoneEvent(), cps.getPlanEvent()));
			cps.setEventRateDisplay(String.format("%.2f", cps.getEventRate()) + "%");
			cps.setCheckRiverRate(cps.getDoneCheckRiver() == 0 ? 0.0D : calcPercent(cps.getDoneCheckRiver(), cps.getPlanCheckRiver()));
			cps.setCheckRiverRateDisplay(String.format("%.2f", cps.getCheckRiverRate()) + "%");
			cps.setChairmanLevelName(getLevelName(cps.getChairmanLevel()));
			return cps;
		}).collect(Collectors.toList()));

		return info;
	}

	public List<CPSttistics> getCPSList(CPSttisticsQuery query) {

		List<CPSttistics> result = sttisticsMapper.selectCPS(query).stream().map(cps -> {
			cps.setLogRate(cps.getDoneLog() == 0 ? 0.0D : calcPercent(cps.getDoneLog(), cps.getPlanLog()));
			cps.setLogRateDisplay(String.format("%.2f", cps.getLogRate()) + "%");
			cps.setEventRate(cps.getDoneEvent() == 0 ? 0.0D : calcPercent(cps.getDoneEvent(), cps.getPlanEvent()));
			cps.setEventRateDisplay(String.format("%.2f", cps.getEventRate()) + "%");
			cps.setCheckRiverRate(cps.getDoneCheckRiver() == 0 ? 0.0D : calcPercent(cps.getDoneCheckRiver(), cps.getPlanCheckRiver()));
			cps.setCheckRiverRateDisplay(String.format("%.2f", cps.getCheckRiverRate()) + "%");
			cps.setChairmanLevelName(getLevelName(cps.getChairmanLevel()));
			return cps;
		}).collect(Collectors.toList());

		return result;
	}

	public List<RBISttistics> getRBISList(RBISttisticsQuery query) {
		List<RBISttistics> result = sttisticsMapper.selectRBISList(query);

		if (result == null || result.size() == 0) {
			return result;
		}

		int reachCntTotal = 0;
		int lv2ChairmanCntTotal = 0;
		int lv3ChairmanCntTotal = 0;
		int lv4ChairmanCntTotal = 0;
		int lv5ChairmanCntTotal = 0;
		int staffCntTotal = 0;
		int cleanerCntTotal = 0;
		int reachDocCntTotal = 0;
		int policyDocCntTotal = 0;
		int publicityCntTotal = 0;
		int pollutionCntTotal = 0;
		int treatmentCntTotal = 0;
		int cameraCntTotal = 0;
		int sectionCntTotal = 0;

		for (RBISttistics r : result) {
			reachCntTotal += r.getReachCnt();
			lv2ChairmanCntTotal += r.getLv2ChairmanCnt();
			lv3ChairmanCntTotal += r.getLv3ChairmanCnt();
			lv4ChairmanCntTotal += r.getLv4ChairmanCnt();
			lv5ChairmanCntTotal += r.getLv5ChairmanCnt();
			staffCntTotal += r.getStaffCnt();
			cleanerCntTotal += r.getCleanerCnt();
			reachDocCntTotal += r.getReachDocCnt();
			policyDocCntTotal += r.getPolicyDocCnt();
			publicityCntTotal += r.getPublicityCnt();
			pollutionCntTotal += r.getPollutionCnt();
			treatmentCntTotal += r.getTreatmentCnt();
			cameraCntTotal += r.getCameraCnt();
			sectionCntTotal += r.getSectionCnt();
			r.setChairmanCntDisplay(r.getLv2ChairmanCnt() + "/" + r.getLv3ChairmanCnt() + "/" + r.getLv4ChairmanCnt() + "/" + r.getLv5ChairmanCnt());
		}

		RBISttistics t = new RBISttistics();
		t.setAreaCode("total");
		t.setAreaName("总计");
		t.setAreaLevel(0);
		t.setReachCnt(reachCntTotal);
		t.setLv2ChairmanCnt(lv2ChairmanCntTotal);
		t.setLv3ChairmanCnt(lv3ChairmanCntTotal);
		t.setLv4ChairmanCnt(lv4ChairmanCntTotal);
		t.setLv5ChairmanCnt(lv5ChairmanCntTotal);
		t.setChairmanCntDisplay(lv2ChairmanCntTotal + "/" + lv3ChairmanCntTotal + "/" + lv4ChairmanCntTotal + "/" + lv5ChairmanCntTotal);
		t.setStaffCnt(staffCntTotal);
		t.setCleanerCnt(cleanerCntTotal);
		t.setReachDocCnt(reachDocCntTotal);
		t.setPolicyDocCnt(policyDocCntTotal);
		t.setPublicityCnt(publicityCntTotal);
		t.setPollutionCnt(pollutionCntTotal);
		t.setTreatmentCnt(treatmentCntTotal);
		t.setCameraCnt(cameraCntTotal);
		t.setSectionCnt(sectionCntTotal);

		result.add(t);

		return result;
	}

	public PageInfo<CBISttistics> getCBISList4Page(CBISttisticsQuery query) {
		PageHelper.startPage(query.getPage(), query.getPageSize());

		List<CBISttistics> result = sttisticsMapper.selectCBISList(query);

		PageInfo<CBISttistics> info = new PageInfo<>(result);

		info.setList(info.getList().stream().map(cbis -> {
			cbis.setReachLevelName(getLevelName(cbis.getReachLevel()));
			cbis.setRpDocCnt(cbis.getReachDocCnt() + cbis.getPolicyDocCnt());
			return cbis;
		}).collect(Collectors.toList()));

		return info;
	}

	public List<CBISttistics> getCBISList(CBISttisticsQuery query) {

		List<CBISttistics> result = sttisticsMapper.selectCBISList(query).stream().map(cbis -> {
			cbis.setReachLevelName(getLevelName(cbis.getReachLevel()));
			cbis.setRpDocCnt(cbis.getReachDocCnt() + cbis.getPolicyDocCnt());
			return cbis;
		}).collect(Collectors.toList());

		return result;
	}

	private double calcPercent(int d, int p) {
		double r = Double.valueOf(d) * 100 / Double.valueOf(p);
		return r > 100 ? 100 : r;
	}

	private String getLevelName(int chairmanLevel) {
		// 1：省级；2：市级；3：县级；4：镇级；5：村级;
		switch (chairmanLevel) {
		case 1:
			return "省级";
		case 2:
			return "市级";
		case 3:
			return "县级";
		case 4:
			return "镇级";
		case 5:
			return "村级";
		default:
			return "未登录";
		}
	}

}
