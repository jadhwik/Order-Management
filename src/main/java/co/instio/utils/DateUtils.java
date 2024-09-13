package co.instio.utils;


import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    public static String millisToLocalDateString(Long millis) {
        return millisToLocalDate(millis).toString();
    }

    public static String millisToLocalDateString(Long millis, ZoneId zoneId) {
        return millisToLocalDate(millis, zoneId).toString();
    }


    public static String millisToLocalDateString(Long millis, String format) {
        return millisToLocalDate(millis).format(DateTimeFormatter.ofPattern(format));
    }

    public static String millisToLocalDateTimeString(Long millis) {
        return millisToLocalDateTimeString(millis, "dd MM yyyy hh:mm:ss");
    }

    public static String millisToLocalDateTimeString(Long millis, ZoneId zoneId) {
        return millisToLocalDateTimeString(millis, "yyyy-MM-dd'T'HH:mm:ss.000'Z'", zoneId);
    }

    public static String millisToLocalDateTimeString(Long millis, String format) {
        return millisToLocalDateTime(millis).format(DateTimeFormatter.ofPattern(format));
    }

    public static String millisToLocalDateTimeString(Long millis, String format, ZoneId zoneId) {
        return millisToLocalDateTime(millis, zoneId).format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate millisToLocalDate(Long millis) {
        return millisToLocalDate(millis, ZoneId.systemDefault());
    }

    public static LocalDateTime millisToLocalDateTime(Long millis) {
        return millisToLocalDateTime(millis, ZoneId.systemDefault());
    }

    public static LocalDate millisToLocalDate(Long millis, ZoneId zoneId) {
        return millis == null ? LocalDate.now(zoneId) : Instant.ofEpochMilli(millis).atZone(zoneId).toLocalDate();
    }

    public static LocalDateTime millisToLocalDateTime(Long millis, ZoneId zoneId) {
        return millis == null ? LocalDateTime.now(zoneId) : Instant.ofEpochMilli(millis).atZone(zoneId).toLocalDateTime();
    }

    public static LocalDate getLocalDate() {
        return getLocalDate(ZoneId.systemDefault());
    }

    public static LocalDate getLocalDate(ZoneId zoneId) {
        return LocalDate.now(zoneId);
    }

    public static String localDateToString(String format) {
        return localDateToString(LocalDate.now(), format);
    }

    public static String localDateToString(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static Long getCurrentMillis() {
        return new Date().getTime();
    }

    public static Integer getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Integer getLastYear() {
        return Calendar.getInstance().get(Calendar.YEAR) - 1;
    }

    public static long getYearStartInMillis(Integer year) {
        return getStartOfDayInMillis(year, Calendar.JANUARY, 1);
    }

    public static long getYearEndInMillis(Integer year) {
        return getEndOfDayInMillis(year, Calendar.DECEMBER, getDaysInMonth(year, 12));
    }

    public static Long getStartMillisFromLocalDate(LocalDate date) {
        return date.toEpochDay() * 86400000L;
    }

    public static Long getEndMillisFromLocalDate(LocalDate date) {
        LocalDateTime dateTime = date.atTime(23, 59, 59, 999999999);
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);

        return instant.toEpochMilli();
    }

    public static DayOfWeek dayOfWeek(LocalDate date) {
        if (date == null) date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return DayOfWeek.valueOf(dayOfWeek.name());
    }

    public static Integer getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static Integer getPlusMonth(int i) {
        return Calendar.getInstance().get(Calendar.MONTH) + i;
    }

    public static List<Month> getLastSixMonths(List<Month> months) {
        YearMonth currentMonth = YearMonth.now();
        for (int i = 0; i <= 5; i++) {
            YearMonth yearMonth = currentMonth.plusMonths(i);
            months.remove(Month.of(yearMonth.getMonth().getValue()));
        }
        return months;
    }

    public static Integer getLastMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) - 1;
    }

    public static Integer getMonth(int minusMonth) {
        return Calendar.getInstance().get(Calendar.MONTH) - minusMonth;
    }

    public static Integer getWeek() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    public static Integer getLastWeek() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1;
    }

    public static Date getWeekStartDate(Integer weekOfYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getWeekEndDate(Integer weekOfYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getWeekDate(Integer year, Integer month, Integer week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    public static Integer getDay() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    public static Integer getDay(int minusDay) {
        return Calendar.getInstance().get(Calendar.DATE) - minusDay;
    }

    public static long getMillisWithStartTime(int minusDay) {
        return LocalDate.now().minusDays(minusDay).atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    public static long getMillisWithEndTime(int minusDay) {
        return LocalDate.now().minusDays(minusDay).atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    public static Long getStartOfDayInMillis(int minusDay, ZoneId zoneId) {
        return LocalDate.now(zoneId).minusDays(minusDay).atStartOfDay(zoneId).toEpochSecond() * 1000;
    }

    public static Long getEndOfDayInMillis(int minusDay, ZoneId zoneId) {
        return ZonedDateTime.now(zoneId).minusDays(minusDay).with(LocalTime.MAX).toEpochSecond() * 1000;
    }

    public static Long getMonthStartOfDay(int minusMonth) {
        LocalDate localDate = LocalDate.now().minusMonths(minusMonth);
        return getStartOfDayInMillis(localDate.getYear(), localDate.getMonthValue() - 1, 1);
    }

    public static Long getMonthEndOfDay(int minusMonth) {
        LocalDate localDate = LocalDate.now().minusMonths(minusMonth);
        return getEndOfDayInMillis(localDate.getYear(), localDate.getMonthValue() - 1, getDaysInMonth(localDate.getYear(), localDate.getMonthValue()));
    }

    public static Long getStartOfDayInMillis(Integer year, Integer month, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime().getTime();
    }

    public static Long getEndOfDayInMillis(Integer year, Integer month, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime().getTime();
    }

    public static Integer getDaysInMonth(Integer year, Integer month) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

    public static Long getDaysFromDateRange(Long startTime, Long endTime) {
        LocalDate startDate = Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDate();
        Long noOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return noOfDaysBetween;

    }

    public static List<LocalDate> getDatesFromDateRange(Long startTime, Long endTime) {
        List<LocalDate> days = new ArrayList<>();
        LocalDate startDate = Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDate();
        while (!startDate.isAfter(endDate)) {
            days.add(startDate);
            startDate = startDate.plusDays(1);
        }
        return days;
    }

    public static Integer getYesterday() {
        return Calendar.getInstance().get(Calendar.DATE) - 1;
    }

    public static Integer getWeekOfMonth() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
    }


    public static LocalTime toLocalTime(Timestamp millis, ZoneId zoneId) {
        return Instant.ofEpochMilli(millis.getTime()).atZone(zoneId).toLocalTime();
    }

    public static String toLocalTimeString(Timestamp millis, ZoneId zoneId) {
        return Instant.ofEpochMilli(millis.getTime()).atZone(zoneId).toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    }


    public static boolean isToday(Long millis, ZoneId zoneId) {
        LocalDate now = Instant.ofEpochMilli(ZonedDateTime.now().toEpochSecond() * 1000).atZone(zoneId).toLocalDate();
        LocalDate requested = Instant.ofEpochMilli(millis).atZone(zoneId).toLocalDate();
        return now.isEqual(requested);
    }

    public static long noOfDaysDifference(LocalDate date, String timeZone) {
        return noOfDaysDifference(date, LocalDate.now(StringUtils.isNotBlank(timeZone) ? ZoneId.of(timeZone) : ZoneId.systemDefault()));
    }

    public static long noOfDaysDifference(LocalDate date, LocalDate date2) {
        if (date == null || date2 == null) return 0;
        return ChronoUnit.DAYS.between(date, date2);
    }

    public static boolean isDaysDifferenceGreaterThan(LocalDate date1, String timeZone, int numberOfDays) {
        return isDaysDifferenceGreaterThan(date1, LocalDate.now(StringUtils.isNotBlank(timeZone) ? ZoneId.of(timeZone) : ZoneId.systemDefault()), numberOfDays);
    }

    public static boolean isDaysDifferenceGreaterThan(LocalDate date1, LocalDate date2, int numberOfDays) {
        if (date1 == null || date2 == null) return false;
        return ChronoUnit.DAYS.between(date1, date2) > numberOfDays;
    }

    public static void main(String[] args) {
        System.out.println(ChronoUnit.DAYS.between(LocalDate.parse("2024-05-11"), LocalDate.parse("2023-11-10")));
    }
}
