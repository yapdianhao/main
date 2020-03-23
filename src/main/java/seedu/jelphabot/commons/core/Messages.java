package seedu.jelphabot.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date format! "
                                                                 + "Date should be one of these possible formats:\n"
                                                                 + "[MMM-d-yyyy],\n[MMM/d/yyyy],\n[d/M/y],"
                                                                 + "\n[d-MMM-yyyy],\n[d MMM yyyy]";
    public static final String MESSAGE_CANNOT_START_MORE_TIMERS = "Unable to start more than one timer at a time!";
    public static final String MESSAGE_DELETE_TIMING_TASK_FAILURE = "Task: %s %s, DateTime: %s cannot be deleted\n"
                                                                        + "while timer is running.";

}
