package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.productivity.Productivity;

/**
 * An UI component that displays information of a {@code Productivity}.
 */
public class ProductivityCard extends UiPart<Region> {
    private static final String FXML = "ProductivityCard.fxml";

    public final Productivity productivity;

    @FXML
    private HBox cardPane;
    @FXML
    private Label statusToday;
    @FXML
    private Label runningTimer;

    public ProductivityCard(Productivity productivity) {
        super(FXML);
        this.productivity = productivity;
        statusToday.setText(productivity.getStatusToday().toString());
        runningTimer.setText(productivity.getRunningTimer().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProductivityCard)) {
            return false;
        }

        // state check
        ProductivityCard card = (ProductivityCard) other;
        return statusToday.getText().equals(card.statusToday.getText())
                   && runningTimer.getText().equals(card.runningTimer.getText())
                   && productivity.equals(card.productivity);
    }
}
