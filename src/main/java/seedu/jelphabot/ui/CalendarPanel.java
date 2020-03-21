package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.Calendar.CalendarDate;

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

    //fill gridpane with daycard method
}
