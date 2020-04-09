package seedu.jelphabot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.model.reminder.Reminder;

/**
 * The Reminder List Panel. Provides the basic application layout of productivity of reminders.
 */
public class ReminderListPanel extends UiPart<Region> {

    private static final String FXML = "ReminderListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private TabPane mainWindowTabPane;

    @FXML
    private ListView<Reminder> reminderListView;

    public ReminderListPanel(ObservableList<Reminder> reminderList, TabPane tabPane) {
        super(FXML);
        logger.info("" + reminderListView + "here");
        this.mainWindowTabPane = tabPane;
        reminderListView.setItems(reminderList);
        reminderListView.setCellFactory(listView -> new ReminderListPanel.ReminderListViewCell());
    }

    /**
     * Returns true if the productivity panel is currently being shown.
     */
    public boolean isShowing() {
        return mainWindowTabPane.isPressed();
    }

    /**
     * Shows the reminderList panel.
     */
    public void show() {
        //logger.fine("Showing productivity panel of application.");
        mainWindowTabPane.getSelectionModel().select(4);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Reminder} using a {@code ReminderCard}.
     */
    static class ReminderListViewCell extends ListCell<Reminder> {
        @Override
        protected void updateItem(Reminder reminder, boolean empty) {
            super.updateItem(reminder, empty);

            if (empty || reminder == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderCard(reminder).getRoot());
            }
        }
    }
}
