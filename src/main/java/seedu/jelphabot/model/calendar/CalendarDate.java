package seedu.jelphabot.model.calendar;

import java.time.LocalDate;

import seedu.jelphabot.commons.util.DateUtil;

/**
 * Class representing a date without time, mainly for the calendar UI.
 */
public class CalendarDate {

    private final LocalDate date;
    private final String[] months = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};

    public CalendarDate(LocalDate date) {
        this.date = date;
    }

    public static CalendarDate getCurrent() {
        return new CalendarDate(DateUtil.getDateToday());
    }

    public boolean isToday() {
        return date.equals(getCurrent());
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public int getYear() {
        return date.getYear();
    }

    public String getMonthName() {
        return months[getMonth() - 1];
    }

    public int getDayOfWeek() {
        return date.getDayOfWeek().getValue();
    }

    public CalendarDate getFirstDay() {
        return new CalendarDate(date.minusDays(getDay() - 1));
    }

    public int getLengthCurrMonth() {
        return date.lengthOfMonth();
    }

    public int getLengthPrevMonth() {
        LocalDate prevMonth = date.minusMonths(1);
        return prevMonth.lengthOfMonth();
    }

    public CalendarDate createPrevMonthDate(int day) {
        LocalDate prevMonth = date.minusMonths(1);
        LocalDate datePrevMonth = prevMonth.withDayOfMonth(day);
        return new CalendarDate(datePrevMonth);
    }

    public boolean isThisMonth() {
        return getMonth() == getCurrent().getMonth();
    }

    public CalendarDate dateNextDay() {
        return new CalendarDate(date.plusDays(1));
    }

}
