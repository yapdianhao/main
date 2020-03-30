package seedu.jelphabot.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.reminder.UniqueReminderList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class JelphaBot implements ReadOnlyJelphaBot {

    private final List<Task> taskList;
    private final List<Reminder> reminderList;
    private final UniqueTaskList tasks;
    private final UniqueReminderList reminders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        taskList = new ArrayList<>();
        reminderList = new ArrayList<>();
        tasks = new UniqueTaskList();
        reminders = new UniqueReminderList();
    }

    public JelphaBot() {}

    /**
     * Creates an JelphaBot using the Tasks in the {@code toBeCopied}
     */
    public JelphaBot(ReadOnlyJelphaBot toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders.setReminders(reminders);
    }

    /**
     * Resets the existing data of this {@code JelphaBot} with {@code newData}.
     */
    public void resetData(ReadOnlyJelphaBot newData) {
        requireNonNull(newData);
        for (Task task : newData.getTaskList()) {
            taskList.add(task);
        }
        for (Reminder reminder : newData.getReminderList()) {
            reminderList.add(reminder);
        }
        setReminders(newData.getReminderList());
        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the JelphaBot.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Returns true if a reminder with the same identity as {@code reminder} exists in JelphaBot.
     */
    public boolean hasReminder(Reminder reminder) {
        requireNonNull(reminder);
        return reminders.contains(reminder);
    }

    /**
     * Returns true if a task is currently being timed.
     */
    public boolean hasTaskBeingTimed() {
        return tasks.hasTaskBeingTimed();
    }

    /**
     * Adds a task to the address book.
     * The task must not already exist in the address book.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    public void addReminder(Reminder r) {
        reminders.add(r);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /*
    public void setReminder(Reminder target, Reminder newReminder) {
        requireNonNull(newReminder);
        reminders.setReminder(target, newReminder);
    }*/

    /**
     * Removes {@code key} from this {@code JelphaBot}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    public void removeReminder(Reminder key) {
        reminders.remove(key);
    }
    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Reminder> getReminderList() {
        return reminders.asUnmodifiableObservableList();
    }

    public List<Task> getTasksAsList() {
        return taskList;
    }

    public List<Reminder> getRemindersAsList() {
        return reminderList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JelphaBot // instanceof handles nulls
                && tasks.equals(((JelphaBot) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
