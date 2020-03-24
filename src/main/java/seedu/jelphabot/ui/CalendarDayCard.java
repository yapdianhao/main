package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.calendar.CalendarDate;

/**
 * UI component that displays the day on the calendar.
 */
public class CalendarDayCard extends UiPart<Region> {
    private static final String FXML = "CalendarDayCard.fxml";

    private CalendarDate calendarDate;

    @FXML
    private Label calendarDay;

    public CalendarDayCard(CalendarDate calendarDate) {
        super(FXML);
        this.calendarDate = calendarDate;
        calendarDay.setText(String.valueOf(calendarDate.getDay()));
    }
}
