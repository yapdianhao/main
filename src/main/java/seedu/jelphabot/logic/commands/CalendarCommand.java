package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.TaskDueWithinDayPredicate;

/**
 * Lists all tasks in task list whose date corresponds with the specified date.
 * Shows the tasks that are due on the specified date.
 */
public class CalendarCommand extends Command {

    public static final String COMMAND_WORD = "calendar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all task that is under the due date specified.\n"
                                                   + "Parameters: DATE \n"
                                                   + "Example: " + COMMAND_WORD + " Jan-1-2020";

    private final TaskDueWithinDayPredicate predicate;

    public CalendarCommand(TaskDueWithinDayPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof CalendarCommand // instanceof handles nulls
                           && predicate.equals(((CalendarCommand) other).predicate)); // state check
    }

}

