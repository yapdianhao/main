package seedu.jelphabot.model.calendar;

import java.time.LocalDate;

import seedu.jelphabot.commons.util.DateUtil;

/**
 * Class representing a date without time, mainly for the calendar UI.
 */
public class CalendarDate {

    private final LocalDate date;
    private static final String[] months = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};

    public CalendarDate(LocalDate date) {
        this.date = date;
    }

    public static CalendarDate getCurrent() {
        return new CalendarDate(DateUtil.getDateToday());
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isToday() {
        return date.equals(DateUtil.getDateToday());
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

    public static String getMonthNameOf(int monthValue) {
        return months[monthValue - 1];
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

    /**
     * Creates a date of the previous month with the specified day input.
     * @param day Specified day for the previous month.
     * @return CalendarDate object.
     */
    public CalendarDate createPrevMonthDate(int day) {
        LocalDate prevMonth = date.minusMonths(1);
        LocalDate datePrevMonth = prevMonth.withDayOfMonth(day);
        return new CalendarDate(datePrevMonth);
    }

    public boolean isSameMonth(int month) {
        return getMonth() == month;
    }

    public CalendarDate dateNextDay() {
        return new CalendarDate(date.plusDays(1));
    }

}
