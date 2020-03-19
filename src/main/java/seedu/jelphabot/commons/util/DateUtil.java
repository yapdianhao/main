package seedu.jelphabot.commons.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import seedu.jelphabot.model.task.predicates.FilterTaskByDatePredicate;
import seedu.jelphabot.model.task.predicates.TaskDueAfterDatePredicate;
import seedu.jelphabot.model.task.predicates.TaskDueBeforeDatePredicate;

public class DateUtil {
    /**
     * Find the date of the instance the calendar is called.
     *
     * @return Date representing the current day's date.
     */
    public static LocalDate getDateToday() {
        return LocalDate.now();
    }

    public static LocalDate getDateTomorrow() {
        return LocalDate.now().plusDays(1);

    }

    public static LocalDate getDateNextWeek() {
        return LocalDate.now().plusWeeks(1);
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate();
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDateTime();
    }

    public static FilterTaskByDatePredicate getOverduePredicate() {
        return new TaskDueBeforeDatePredicate(getDateToday());
    }

    public static FilterTaskByDatePredicate getDueTodayPredicate() {
        return new TaskDueAfterDatePredicate(getDateToday()).and(new TaskDueBeforeDatePredicate(getDateTomorrow()));
    }

    public static FilterTaskByDatePredicate getDueThisWeekPredicate() {
        return new TaskDueAfterDatePredicate(getDateTomorrow()).and(new TaskDueBeforeDatePredicate(getDateNextWeek()));
    }

    public static FilterTaskByDatePredicate getDueSomedayPredicate() {
        return new TaskDueAfterDatePredicate(getDateNextWeek());
    }
}
