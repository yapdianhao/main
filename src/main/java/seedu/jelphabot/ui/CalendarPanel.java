package seedu.jelphabot.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.calendar.CalendarDate;

/**
 * UI component for calendar view to be displayed.
 */
public class CalendarPanel extends UiPart<Region> {

    private static final String FXML = "CalendarPanel.fxml";
    private static ArrayList<CalendarDayCard> dayCardsInMonth;
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    private CalendarDate calendarDate;
    private CalendarDayCard highlightedDay;

    @FXML
    private TabPane mainWindowTabPane;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Label monthYear;

    public CalendarPanel(CalendarDate calendarDate, TabPane mainWindowTabPane) {
        super(FXML);
        logger.info("Initialising calendar panel stage");
        this.mainWindowTabPane = mainWindowTabPane;
        this.calendarDate = calendarDate;
        monthYear.setText(calendarDate.getMonthName() + ", " + calendarDate.getYear());

        CalendarDate firstDay = calendarDate.getFirstDay();
        fillGridPane(firstDay);
    }

    /**
     * Fills the grid pane of the calendar.
     * @param firstDay The date representing the first day of the month.
     */
    public void fillGridPane(CalendarDate firstDay) {
        int weekIndex = firstDay.getDayOfWeek() - 1;
        int lengthPrevMonth = firstDay.getLengthPrevMonth();
        int day = lengthPrevMonth - weekIndex + 1;
        CalendarDate currDate = firstDay.createPrevMonthDate(day);
        dayCardsInMonth = new ArrayList<>();

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                CalendarDayCard calendarDayCard = new CalendarDayCard(currDate);
                if (currDate.isThisMonth()) {
                    calendarDayCard.setSameMonth();
                    dayCardsInMonth.add(calendarDayCard);
                } else {
                    calendarDayCard.setDiffMonth();
                }
                if (currDate.isToday()) {
                    // logger.info("today date");
                    calendarDayCard.highlightToday();
                    highlightedDay = calendarDayCard;
                }
                calendarGrid.add(calendarDayCard.getRoot(), col, row);
                currDate = currDate.dateNextDay();
            }
        }
    }

    /**
     * Switches to display the calendar panel tab.
     */
    public void show() {
        logger.fine("Showing calendar panel of application.");
        mainWindowTabPane.getSelectionModel().select(1);
    }

    /**
     * Returns true if the calendar panel is currently being shown.
     */
    public boolean isShowing() {
        return mainWindowTabPane.isPressed();
    }

    public static CalendarDayCard getDayCard(int dayIndex) {
        return CalendarPanel.dayCardsInMonth.get(dayIndex - 1);
    }

    public CalendarDayCard getHighlightedDay() {
        return highlightedDay;
    }

    public void setHighlightedDay(int dayIndex) {
        highlightedDay = dayCardsInMonth.get(dayIndex - 1);
    }

    public boolean isTodayHighlighted() {
        return highlightedDay.getDate().isToday();
    }
}
