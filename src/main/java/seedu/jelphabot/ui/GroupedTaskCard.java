package seedu.jelphabot.ui;

import seedu.jelphabot.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class GroupedTaskCard extends TaskCard {

    private static final String FXML = "GroupedTaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on JelphaBot level 4</a>
     */

    public GroupedTaskCard(Task task, int displayedIndex) {
        super(FXML, task, displayedIndex);
    }
}
