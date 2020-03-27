package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.productivity.Productivity;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Priority;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;

/**
 * Marks the specified as done by updating it's status to COMPLETE.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Marks the task specified by the index number as completed.\n"
        + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_COMPLETE_SUCCESS = "Marked task as completed: %1$s";
    public static final String MESSAGE_TASK_ALREADY_MARKED_COMPLETE = "The specified task has already "
                                                                          + "been marked as complete!";

    private final Index index;

    /**
     * @param index of the task in the filtered task list to mark done
     */
    public DoneCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMarkDone = lastShownList.get(index.getZeroBased());
        Task doneTask = createDoneTask(taskToMarkDone);
        doneTask.setDoneTime();

        if (taskToMarkDone.equals(doneTask)) {
            throw new CommandException(MESSAGE_TASK_ALREADY_MARKED_COMPLETE);
        }

        model.setTask(taskToMarkDone, doneTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.setProductivity(new Productivity(model.getFilteredTaskList()));
        return new CommandResult(String.format(MESSAGE_MARK_TASK_COMPLETE_SUCCESS, doneTask));
    }

    /**
     * Creates and returns a {@code Task} with the updated COMPLETE status,
     * by copying all the details of the given {@code task} and creating a new
     * {@code Task} object.
     * @param task Task object to get the relevant details from.
     * @return Task object with it's status set as COMPLETE.
     */
    public static Task createDoneTask(Task task) {
        Description description = task.getDescription();
        ModuleCode moduleCode = task.getModuleCode();
        Set<Tag> tags = task.getTags();
        DateTime dateTime = task.getDateTime();
        Status status = Status.COMPLETE;
        Priority priority = task.getPriority();
        Duration duration = task.getDuration();

        return new Task(description, status, dateTime, moduleCode, priority, tags, duration);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DoneCommand)) {
            return false;
        }

        // state check
        DoneCommand d = (DoneCommand) other;
        return index.equals(d.index);
    }
}
