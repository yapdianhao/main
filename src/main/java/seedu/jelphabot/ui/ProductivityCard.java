package seedu.jelphabot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.productivity.Productivity;

/**
 * An UI component that displays information of {@code Productivity}.
 */
// TODO use piechart for visualisation (or progress bar)
public class ProductivityCard extends UiPart<Region> {

    private static final String FXML = "ProductivityListCard.fxml";

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
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;
    @FXML
    private Label dateTime;

    public ProductivityCard(Productivity productivity, int displayedIndex) {
        super(FXML);
        this.productivity = productivity;
        id.setText(displayedIndex + ". ");
        // description.setText(productivity.getDescription().fullDescription);
        // moduleCode.setText(productivity.getModuleCode().value);
        // status.setText(productivity.getStatus().name());
        // dateTime.setText(productivity.getDateTime().getDisplayValue());
        // productivity.getTags().stream()
        //     .sorted(Comparator.comparing(tag -> tag.tagName))
        //     .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
        return id.getText().equals(card.id.getText())
                   && productivity.equals(card.productivity);
    }
}
