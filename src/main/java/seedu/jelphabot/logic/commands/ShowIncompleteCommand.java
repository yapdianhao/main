package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.TaskIncompletePredicate;

public class ShowIncompleteCommand extends Command {

    public static final String COMMAND_WORD = "incomplete-tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all incomplete tasks in the task list";

    public static final String MESSAGE_SUCCESS = "Displaying all incomplete tasks";

    public static final Predicate<Task> incompletePredicate = new TaskIncompletePredicate();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(incompletePredicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
