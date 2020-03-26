package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_CANNOT_START_MORE_TIMERS;

import java.util.List;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Task;

// TODO: show time spent while timer is running.
/**
 * Starts a timer for a task.
 */
public class StartTimerCommand extends Command {
    public static final String COMMAND_WORD = "start";
    public static final String MESSAGE_USAGE = COMMAND_WORD
                                                   + ": Starts the timer for the task specified by the index number.\n"
                                                   + "Parameters: INDEX (must be a positive integer)\n" + "Example: "
                                                   + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Started timer for task %d. %s %s.";
    public static final String MESSAGE_TASK_ALREADY_TIMED = "Task has already been marked as done and cannot be timed.";
    public static final String MESSAGE_TIMER_ALREADY_STARTED = "Timer for this task has already been started.";

    private Index targetIndex;

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

        Task dummy = lastShownList.get(targetIndex.getZeroBased());
        Task taskToTime = lastShownList.get(targetIndex.getZeroBased());

        if (taskToTime.getStatus() == Status.COMPLETE) {
            throw new CommandException(MESSAGE_TASK_ALREADY_TIMED);
        } else if (taskToTime.isBeingTimed()) {
            throw new CommandException(MESSAGE_TIMER_ALREADY_STARTED);
        } else if (model.hasTimingTask()) {
            throw new CommandException(MESSAGE_CANNOT_START_MORE_TIMERS);
        }

        taskToTime.startTimer();
        // model.setTask(dummy, taskToTime);
        // model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        // model.setProductivity(new Productivity(model.getSortedTaskList(), model.getFilteredTaskList()));
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased(),
            taskToTime.getModuleCode().toString(), taskToTime.getDescription().toString()));
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
