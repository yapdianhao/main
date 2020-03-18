package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.ReminderPredicate;

/**
 * Displays to the user a list of tasks that will due in a week.
 */
public class ReminderCommand extends Command {

    public static final String COMMAND_WORD = "reminder";
    public static final String MESSAGE_URGENT_TASKS = "These are the tasks that due soon!";

    private final ReminderPredicate reminderPredicate = new ReminderPredicate();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(reminderPredicate);
        return new CommandResult(MESSAGE_URGENT_TASKS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof ReminderCommand // instanceof handles nulls
                           && reminderPredicate.equals(((ReminderCommand) other).reminderPredicate)); // state check
    }
}
