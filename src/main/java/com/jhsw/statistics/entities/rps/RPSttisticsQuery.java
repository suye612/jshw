/**
 * 
 */
package com.jhsw.statistics.entities.rps;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.jhsw.statistics.entities.ar.AdminRegionQuery;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 *
 */
@Getter
@Setter
public class RPSttisticsQuery extends AdminRegionQuery {

	@NotEmpty(message = "开始日期必须输入")
	@Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "开始日期格式不正确")
	private String startDate;

	@Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "结束日期格式不正确")
	private String endDate;

	private long days;
	private long weeks;
	private long months;

}
