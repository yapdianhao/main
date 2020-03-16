package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.TaskCompletedPredicate;

public class ShowCompletedCommand extends Command {
    public static final String COMMAND_WORD = "completed-tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all completed tasks in the task list";

    public static final String MESSAGE_SUCCESS = "Displaying all completed tasks";

    public static final Predicate<Task> completedPredicate = new TaskCompletedPredicate();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(completedPredicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
