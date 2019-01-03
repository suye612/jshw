/**
 * 
 */
package com.jhsw.statistics.utils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Administrator
 *
 */
public interface DateUtils {

	static final DateTimeFormatter FORMATTER_DATETIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	static final DateTimeFormatter FORMATTER_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	static LocalDate getLocalDate() {
		return LocalDate.now();
	}

	static LocalTime getLocalTime() {
		return LocalTime.now();
	}

	static LocalDateTime getLocalDateTime() {
		return LocalDateTime.now();
	}

	static long getClockMillis() {
		Clock clock = Clock.systemDefaultZone();
		return clock.millis();
	}

	static String getDateTimestamp() {
		return getLocalDateTime().format(FORMATTER_DATETIMESTAMP);
	}

	static String getDate() {
		return getLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	static String getDateTime() {
		return getLocalDateTime().format(FORMATTER_DATETIME);
	}

	static String getFirstDayOfMonth() {
		return getLocalDate().withDayOfMonth(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	static String getLastDayOfMonth() {
		LocalDate localDate = getLocalDate();
		return localDate.withDayOfMonth(localDate.lengthOfMonth()).format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	static String formatDateTimestamp(String dateTimestamp) {
		return LocalDateTime.parse(dateTimestamp, FORMATTER_DATETIMESTAMP).format(FORMATTER_DATETIME);
	}

	static String formatDateTime(String dateTime) {
		return parseLocalDateTime(dateTime).format(FORMATTER_DATETIMESTAMP);
	}

	static LocalDateTime parseLocalDateTime(String dateTime) {
		return LocalDateTime.parse(dateTime, FORMATTER_DATETIME);
	}

	static LocalDateTime parseLocalDateTimestamp(String dateTimestamp) {
		return LocalDateTime.parse(dateTimestamp, FORMATTER_DATETIMESTAMP);
	}

	static LocalDate parseLocalDate(String dateString) {
		return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
	}

	static String plusDays(String date, int days) {
		LocalDate localDate = parseLocalDate(date);
		return localDate.plusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	static Period betweenWithString(String sd, String ed) {
		LocalDate s = LocalDate.parse(sd);
		LocalDate e = LocalDate.parse(ed);

		return Period.between(s, e);
	}

	static long untilDays(String endDate) {
		return untilDays(LocalDate.now(), LocalDate.parse(endDate));
	}

	static long untilDays(String startDate, String endDate) {
		return untilDays(LocalDate.parse(startDate), LocalDate.parse(endDate));
	}

	static long untilMonths(String endDate) {
		return untilMonths(LocalDate.now(), LocalDate.parse(endDate));
	}

	static long untilMonths(String startDate, String endDate) {
		return untilMonths(LocalDate.parse(startDate), LocalDate.parse(endDate));
	}

	static long untilWeeks(String endDate) {
		return untilWeeks(LocalDate.now(), LocalDate.parse(endDate));
	}

	static long untilWeeks(String startDate, String endDate) {
		return untilWeeks(LocalDate.parse(startDate), LocalDate.parse(endDate));
	}

	static long untilDays(LocalDate endDate) {
		return untilDays(LocalDate.now(), endDate);
	}

	static long untilDays(LocalDate startDate, LocalDate endDate) {
		return startDate.until(endDate, ChronoUnit.DAYS);
	}

	static long untilMonths(LocalDate endDate) {
		return untilMonths(LocalDate.now(), endDate);
	}

	static long untilMonths(LocalDate startDate, LocalDate endDate) {
		return startDate.until(endDate, ChronoUnit.MONTHS);
	}

	static long untilWeeks(LocalDate endDate) {
		return untilWeeks(LocalDate.now(), endDate);
	}

	static long untilWeeks(LocalDate startDate, LocalDate endDate) {
		return startDate.until(endDate, ChronoUnit.WEEKS);
	}

}