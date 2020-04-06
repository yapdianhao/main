package seedu.jelphabot.model.calendar;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalendarDateTest {

    @Test
    public void getMonthNameOf_returnsTrue() {
        LocalDate localDate = LocalDate.parse("Mar-1-2020", DateTimeFormatter.ofPattern("MMM-d-uuuu"));
        CalendarDate first = new CalendarDate(localDate);
        String month = CalendarDate.getMonthNameOf(first.getMonth());
        assertTrue(month.equals("March"));
    }

    @Test
    public void getMonthNameOf_returnsFalse() {
        LocalDate localDate = LocalDate.parse("Mar-1-2020", DateTimeFormatter.ofPattern("MMM-d-uuuu"));
        CalendarDate first = new CalendarDate(localDate);
        String month = CalendarDate.getMonthNameOf(first.getMonth());
        assertFalse(month.equals("April"));
    }

    @Test
    public void getFirstDay_returnsTrue() {
        LocalDate date = LocalDate.parse("Mar-31-2020", DateTimeFormatter.ofPattern("MMM-d-uuuu"));
        LocalDate firstDate = LocalDate.parse("Mar-1-2020", DateTimeFormatter.ofPattern("MMM-d-uuuu"));
        CalendarDate calendarDay = new CalendarDate(date);
        CalendarDate firstCalendarDay = new CalendarDate(firstDate);
        assertTrue(calendarDay.getFirstDay().equals(firstCalendarDay));
    }
}
