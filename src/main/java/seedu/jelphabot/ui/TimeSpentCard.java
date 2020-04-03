package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.productivity.TimeSpentToday;

/**
 * An UI component that displays information of a {@code TimeSpentToday}.
 */
public class TimeSpentCard extends UiPart<Region> {

    private static final String FXML = "TimeSpentCard.fxml";

    public final TimeSpentToday timeSpentToday;

    @FXML
    private HBox cardPane;
    @FXML
    private Label timeSpent;

    public TimeSpentCard(TimeSpentToday timeSpentToday) {
        super(FXML);
        this.timeSpentToday = timeSpentToday;

        timeSpent.setText(timeSpentToday.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimeSpentCard)) {
            return false;
        }

        // state check
        TimeSpentCard card = (TimeSpentCard) other;
        return timeSpent.getText().equals(card.timeSpent.getText());
    }
}
