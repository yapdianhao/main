package seedu.jelphabot.model.reminder.exceptions;

public class DuplicateReminderException extends RuntimeException {
    public DuplicateReminderException() {
        super("Operation would result in duplicate tasks");
    }
}