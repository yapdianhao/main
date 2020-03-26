package seedu.jelphabot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.ASSESSMENT;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.reminder.Reminder;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.exceptions.DuplicateTaskException;
import seedu.jelphabot.testutil.TaskBuilder;

public class JelphaBotTest {

    private final JelphaBot jelphaBot = new JelphaBot();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), jelphaBot.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jelphaBot.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyJelphaBot_replacesData() {
        JelphaBot newData = getTypicalJelphaBot();
        jelphaBot.resetData(newData);
        assertEquals(newData, jelphaBot);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(ASSESSMENT).withTags(VALID_TAG_GRADED)
                .build();
        List<Task> newTasks = Arrays.asList(ASSESSMENT, editedAlice);
        JelphaBotStub newData = new JelphaBotStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> jelphaBot.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jelphaBot.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInJelphaBot_returnsFalse() {
        assertFalse(jelphaBot.hasTask(ASSESSMENT));
    }

    @Test
    public void hasTask_tasknJelphaBot_returnsTrue() {
        jelphaBot.addTask(ASSESSMENT);
        assertTrue(jelphaBot.hasTask(ASSESSMENT));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInJelphaBot_returnsTrue() {
        jelphaBot.addTask(ASSESSMENT);
        Task editedAlice = new TaskBuilder(ASSESSMENT).withTags(VALID_TAG_GRADED)
                .build();
        assertTrue(jelphaBot.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> jelphaBot.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyJelphaBot whose tasks list can violate interface constraints.
     */
    private static class JelphaBotStub implements ReadOnlyJelphaBot {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<Reminder> reminders = FXCollections.observableArrayList();

        JelphaBotStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Reminder> getReminderList() {
            return reminders;
        }

        @Override
        public void setTasks(List<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public void setReminders(List<Reminder> reminders) {
            this.reminders.setAll(reminders);
        }


    }

}
