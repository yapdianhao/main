package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.predicates.TaskDueWithinDayPredicate;

public class DueTodayCommand extends Command {
    public static final String COMMAND_WORD = "due-today";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all today-tasks in the task list" ;

    public static final String MESSAGE_SUCCESS = "Displaying all today-tasks";

    private final TaskDueWithinDayPredicate predicate;

    public DueTodayCommand(TaskDueWithinDayPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof FindCommand // instanceof handles nulls
                           && predicate.equals(((DueTodayCommand) other).predicate)); // state check
    }
}
