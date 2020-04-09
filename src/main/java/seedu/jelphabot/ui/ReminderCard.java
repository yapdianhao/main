package seedu.jelphabot.ui;

import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.reminder.Reminder;

/**
 * An UI component that displays information of a {@code Reminder}.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "ReminderCard.fxml";

    public final Reminder reminder;

    @FXML
    private Label id;

    @FXML
    private HBox cardPane;

    @FXML
    private Label taskAssociated;

    @FXML
    private Label reminderDay;

    @FXML
    private Label reminderHour;

    public ReminderCard(Reminder reminder, NumberBinding displayedIndex) {
        super(FXML);
        this.reminder = reminder;
        //setId(displayedIndex);
        populateReminderElements(reminder);
    }

    public ReminderCard(Reminder reminder) {
        super(FXML);
        this.reminder = reminder;
        //setId(displayedIndex);
        populateReminderElements(reminder);
    }

    private void setId(NumberBinding displayedIndex) {
        id.textProperty().bind(displayedIndex.asString("%d. "));
    }

    private void setId(int displayedIndex) {
        id.setText(String.format("%d. ", displayedIndex));
    }

    private void populateReminderElements(Reminder reminder) {
        taskAssociated.setText("reminding task: " + reminder.getIndex().getOneBased());
        reminderDay.setText("due in: " + reminder.getDaysToRemind().getReminderDay() + " day(s)");
        reminderHour.setText("" + reminder.getHoursToRemind().getReminderHour() + " hour(s)");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderCard)) {
            return false;
        }

        // state check
        ReminderCard card = (ReminderCard) other;
        return id.getText().equals(card.id.getText())
                   && reminder.equals(card.reminder);
    }
}
