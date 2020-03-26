package seedu.jelphabot.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.jelphabot.commons.core.GuiSettings;
import seedu.jelphabot.model.productivity.Productivity;
import seedu.jelphabot.model.productivity.ProductivityList;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.task.Task;

/**
 * The API of the Model component.
 */
// TODO check the file paths here for saving as jelphabot.json
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Returns the GUI settings for a popup window
     */
    GuiSettings getPopUpWindowGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getJelphaBotFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setJelphaBotFilePath(Path addressBookFilePath);

    Path getRemindersFilePath();

    /** Returns the JelphaBot */
    ReadOnlyJelphaBot getJelphaBot();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setJelphaBot(ReadOnlyJelphaBot addressBook);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

    boolean hasReminder(Reminder reminder);
    /**
     * Returns true if a task being timed exists in the address book.
     */
    boolean hasTimingTask();

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the address book.
     */
    void addTask(Task task);

    void addReminder(Reminder reminder);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Replaces the existing productivity with {@code productivity}.
     */
    void setProductivity(Productivity productivity);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered task list in the Calendar*/
    ObservableList<Task> getFilteredCalendarTaskList();

    /** Returns an unmodifiable view of the completed tasks in the task list */
    ObservableList<Task> getFilteredByCompleteTaskList();

    /** Returns an unmodifiable view of the incomplete tasks in the task list */
    ObservableList<Task> getFilteredByIncompleteTaskList();

    /**
     * Returns an unmodifiable view of the incomplete tasks that are due today in
     * the task list
     */
    ObservableList<Task> getFilteredByIncompleteDueTodayTaskList();

    /**
     * Returns an unmodifiable view of the user's productivity.
     */
    ProductivityList getProductivityList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered calendar task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCalendarTaskList(Predicate<Task> predicate);

    /**
     * Returns an unmodifiable view of the incomplete tasks in the task list
     */
    SortedTaskList getSortedTaskList();
}
