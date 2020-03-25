package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.calendar.CalendarDate;

/**
 * UI component for calendar view to be displayed.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    private CalendarDate calendarDate;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Label monthYear;

    public CalendarPanel(CalendarDate calendarDate) {
        super(FXML);
        this.calendarDate = calendarDate;
        monthYear.setText(calendarDate.getMonthName() + ", " + calendarDate.getYear());
    }

    //fill grid pane with day card method
    // public void fillGridPane(CalendarDate firstDay) {
    //     int weekIndex = firstDay.getDayOfWeek();
    //     for (int row = 0; row < 6; row++) {
    //         for (int col = 0; col < 7; col++) {
    //             CalendarDayCard calendarDayCard = new CalendarDayCard();
    //         }
    //     }
    // }

    // public void highlightDayCard(CalendarDate calendarDate) {
    //
    // }
}
