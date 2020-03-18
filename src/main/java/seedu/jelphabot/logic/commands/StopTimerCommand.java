package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.util.List;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.Task;
/**
 * Starts a timer for a task.
 */
public class StopTimerCommand extends Command {
    public static final String COMMAND_WORD = "stop";
    public static final String MESSAGE_USAGE = COMMAND_WORD
                                                   + ": Stops the timer for the task specified by the index number.\n"
                                                   + "Parameters: INDEX (must be a positive integer)\n" + "Example: "
                                                   + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Stopped timer for task %d. %s %s.\nTime spent: %d Minutes %d Seconds";
    public static final String MESSAGE_NO_TIMER_TO_STOP = "No timers were started.";

    private Index targetIndex;

    public StopTimerCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToStop = lastShownList.get(targetIndex.getZeroBased());

        if (!model.hasTimingTask() || taskToStop.getStartTime() == null) {
            throw new CommandException(MESSAGE_NO_TIMER_TO_STOP);
        }

        taskToStop.stopTimer();
        Duration dur = taskToStop.getDuration();
        int seconds = dur.toSecondsPart();
        int mins = dur.toMinutesPart();
        System.out.println("secs: " + seconds + " mins: " + mins);

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased(),
            taskToStop.getModuleCode().toString(), taskToStop.getDescription().toString(), mins, seconds));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StopTimerCommand)) {
            return false;
        }

        // state check
        StopTimerCommand d = (StopTimerCommand) other;
        return targetIndex.equals(d.targetIndex);
    }
}
