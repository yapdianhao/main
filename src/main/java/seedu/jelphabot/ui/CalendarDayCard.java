package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import seedu.jelphabot.model.calendar.CalendarDate;

/**
 * UI component that displays the day on the calendar.
 */
public class CalendarDayCard extends UiPart<Region> {
    private static final String FXML = "CalendarDayCard.fxml";

    private CalendarDate calendarDate;

    @FXML
    private Label calendarDay;

    @FXML
    private Circle circleDay;

    public CalendarDayCard(CalendarDate calendarDate) {
        super(FXML);
        this.calendarDate = calendarDate;
        calendarDay.setText(String.valueOf(calendarDate.getDay()));
    }

    public void highlightDate(CalendarDate calendarDate) {

    }
}
