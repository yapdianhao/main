package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.productivity.Productivity;

/**
 * An UI component that displays information of {@code Productivity}.
 */
// TODO use piechart for visualisation (or progress bar)
public class ProductivityCard extends UiPart<Region> {

    private static final String FXML = "ProductivityCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on JelphaBot level 4</a>
     */

    public final Productivity productivity;

    @FXML
    private HBox cardPane;
    @FXML
    private Label productivityToday;
    @FXML
    private Label runningTimers;
    @FXML
    private Label overdueTasks;
    @FXML
    private Label remainingTasks;

    public ProductivityCard(Productivity productivity, int displayedIndex) {
        super(FXML);
        this.productivity = productivity;
        productivityToday.setText(productivity.getTodayStatus());
        runningTimers.setText(productivity.getTimerStatus());
        overdueTasks.setText(productivity.getProductivityForOverdueTasks());
        remainingTasks.setText(productivity.getRemainingTaskStatus());
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
        // TODO: update the test check
        return productivityToday.getText().equals(card.productivityToday.getText())
                   && productivity.equals(card.productivity);
    }
}
