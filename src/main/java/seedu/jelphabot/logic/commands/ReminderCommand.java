package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.task.ReminderPredicate;
import seedu.jelphabot.commons.core.index.Index;

/**
 * Displays to the user a list of tasks that will due in a week.
 */
public class ReminderCommand extends Command {

    private final Index index;

    private final Reminder reminder;

    public ReminderCommand(Index index, Reminder reminder) {
        requireNonNull(index);
        requireNonNull(reminder);
        this.index = index;
        this.reminder = reminder;
    }

    public static final String COMMAND_WORD = "reminder";
    public static final String MESSAGE_URGENT_TASKS = "These are the tasks that due soon!";
    public static final String MESSAGE_USAGE = "enter message usage";
    private final ReminderPredicate reminderPredicate = new ReminderPredicate();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //to implement add reminder
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof ReminderCommand // instanceof handles nulls
                           && reminderPredicate.equals(((ReminderCommand) other).reminderPredicate)); // state check
    }
}
