package seedu.jelphabot.model.Calendar;

import seedu.jelphabot.ui.CalendarPanel;

import java.time.LocalDate;

public class CalendarDate {

    private final LocalDate date;
    private final String[] months = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};

    public CalendarDate(LocalDate date) {
        this.date = date;
    }

    public static CalendarDate getCurrent() {
        return new CalendarDate(LocalDate.now());
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

}
