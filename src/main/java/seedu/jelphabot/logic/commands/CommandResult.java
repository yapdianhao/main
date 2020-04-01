package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;
    private switchTab toSwitch = switchTab.STAY_ON_CURRENT;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * The application should switch to the Task List tab
     * @return Sets the "switch to window" flag to Task List.
     */
    public CommandResult isShowTaskList() {
        this.toSwitch = switchTab.TASK_LIST;
        return this;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * The application should switch to the productivity tab.
     * @return Sets the "toSwitch" flag to Productivity.
     */
    public CommandResult isShowProductivity() {
        this.toSwitch = switchTab.PRODUCTIVITY;
        return this;
    }

    /**
     * The application should switch to the Calendar tab.
     * @return Sets the "toSwitch" flag to Calendar.
     */
    public CommandResult isShowCalendar() {
        this.toSwitch = switchTab.CALENDAR;
        return this;
    }

    /**
     * The application should switch to the Summary tab.
     * @return Sets the "toSwitch" flag to Summary.
     */
    public CommandResult isShowSummary() {
        this.toSwitch = switchTab.SUMMARY;
        return this;
    }

    public switchTab getTabSwitch() {
        return toSwitch;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                   && showHelp == otherCommandResult.showHelp
                   && exit == otherCommandResult.exit
                   && toSwitch == otherCommandResult.toSwitch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    /**
     * Enum representing switch that indicates if the command should switch to another window.
     */
    public enum switchTab {
        CALENDAR,
        PRODUCTIVITY,
        SUMMARY,
        TASK_LIST,
        STAY_ON_CURRENT
    }

}
