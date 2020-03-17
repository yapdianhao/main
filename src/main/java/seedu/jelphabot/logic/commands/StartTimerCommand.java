package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;

/**
 * Starts a timer for a task.
 */
public class StartTimerCommand extends Command {
    public static final String COMMAND_WORD = "start";
    public static final String MESSAGE_USAGE = COMMAND_WORD
                                                   + ": Starts the timer for the task specified by the index number.\n"
                                                   + "Parameters: INDEX (must be a positive integer)\n" + "Example: "
                                                   + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Started timer for %1$s";
    public static final String MESSAGE_TASK_ALREADY_TIMED = "Task has already been marked as done and cannot be timed.";

    private Index targetIndex;
    private LocalDateTime start;

    public StartTimerCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToTime = lastShownList.get(targetIndex.getZeroBased());
        this.start = LocalDateTime.now();

        if (taskToTime.getStatus() == Status.COMPLETE) {
            throw new CommandException(MESSAGE_TASK_ALREADY_TIMED);
        }

        taskToTime.startTimer();

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToTime));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartTimerCommand)) {
            return false;
        }

        // state check
        StartTimerCommand d = (StartTimerCommand) other;
        return targetIndex.equals(d.targetIndex);
    }
}
