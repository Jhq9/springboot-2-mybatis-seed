package isa.qa.project.utils;

import java.time.*;
import java.util.Date;

/**
 *  日期类型转换工具类
 *
 *  @author    May
 *  @date      2018/9/26 18:03
 *  @version   1.0
 */
public class DateTimeUtils {

    /**
     * date -> localDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(date.toInstant(), zone);
    }

    /**
     * date -> localDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        return dateToLocalDateTime(date).toLocalDate();
    }

    /**
     *  localDate -> Date
     */
    public static Date LocalDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();

        return Date.from(instant);
    }

    /**
     * localDateTime -> Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();

        return Date.from(instant);
    }

    /**
     * 计算截止时间相对于开始时间的第几周
     *
     * @param startDate 起始时间
     * @param endDate 截止时间
     * @return 第几周
     */
    public static Integer calculateWeekOrder(Date startDate, Date endDate) {
        LocalDate startLocalDate = dateToLocalDate(startDate);
        startLocalDate = startLocalDate.plusDays(-startLocalDate.getDayOfWeek().getValue() + 1);
        LocalDate endLocalDate = dateToLocalDate(endDate);

        return Period.between(startLocalDate, endLocalDate).getDays() / 7 + 1;
    }

    /**
     * 计算截止时间相对于开始时间是第几个月
     *
     * @param startDate 起始时间
     * @param endDate 截止时间
     * @return 第几个月
     */
    public static Integer calculateMonthOrder(Date startDate, Date endDate) {
        LocalDate startLocalDate = dateToLocalDate(startDate);
        LocalDate endLocalDate = dateToLocalDate(endDate);

        return endLocalDate.getMonth().getValue() - startLocalDate.getMonth().getValue() + 1;
    }
}
