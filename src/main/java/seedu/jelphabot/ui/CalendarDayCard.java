package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.calendar.CalendarDate;

import java.util.logging.Logger;

/**
 * UI component that displays the day on the calendar.
 */
public class CalendarDayCard extends UiPart<Region> {
    private static final String FXML = "CalendarDayCard.fxml";

    private final Logger logger = LogsCenter.getLogger(CalendarDayCard.class);

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

    public CalendarDate getDate() {
        return calendarDate;
    }

    /**
     * Highlights today's date on the calendar.
     */
    public void highlightToday() {
        calendarDay.setTextFill(Paint.valueOf("#ffffff"));
        calendarDay.setStyle("-fx-font-weight:bold");
        circleDay.setFill(Paint.valueOf("#4169E1"));
    }

    /**
     * Remove highlight for today's date on the calendar.
     */
    public void removeHighlightedToday() {
        calendarDay.setTextFill(Paint.valueOf("#4169E1"));
        circleDay.setFill(Color.TRANSPARENT);
    }

    /**
     * Remove highlight for a date on the calendar.
     */
    public void removeHighlightedDay() {
        circleDay.setFill(Color.TRANSPARENT);
    }

    /**
     * Highlights the day card on the calendar when calendar command is run.
     */
    public void highlightDay() {
        circleDay.setFill(Paint.valueOf("#B0C4DE"));
    }

    /**
     * Sets the colour of labels for dates this month.
     */
    public void setSameMonth() {
        calendarDay.setTextFill(Paint.valueOf("000000"));
    }

    /**
     * Sets the colour of labels for trailing dates from the previous and next months.
     */
    public void setDiffMonth() {
        calendarDay.setTextFill(Paint.valueOf("808080"));
        calendarDay.setOpacity(0.75);
    }
}
