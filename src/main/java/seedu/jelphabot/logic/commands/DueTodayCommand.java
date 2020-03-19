package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.DateUtil.getDueTodayPredicate;

import seedu.jelphabot.model.Model;

/**
 * Displays to the user a list of tasks that are due the same day as the current day.
 */
public class DueTodayCommand extends Command {
    public static final String COMMAND_WORD = "due-today";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all today-tasks in the task list";

    public static final String MESSAGE_SUCCESS = "Displaying all today-tasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(getDueTodayPredicate());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof DueTodayCommand); // instanceof handles nulls
    }
}
