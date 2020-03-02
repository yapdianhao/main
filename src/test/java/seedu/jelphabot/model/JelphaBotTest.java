package seedu.jelphabot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.ALICE;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalJelphaBot;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.exceptions.DuplicatePersonException;
import seedu.jelphabot.testutil.TaskBuilder;

public class JelphaBotTest {

    private final JelphaBot addressBook = new JelphaBot();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyJelphaBot_replacesData() {
        JelphaBot newData = getTypicalJelphaBot();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Task editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_GRADED)
                .build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        JelphaBotStub newData = new JelphaBotStub(newTasks);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasPerson_personNotInJelphaBot_returnsFalse() {
        assertFalse(addressBook.hasTask(ALICE));
    }

    @Test
    public void hasPerson_personInJelphaBot_returnsTrue() {
        addressBook.addTask(ALICE);
        assertTrue(addressBook.hasTask(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInJelphaBot_returnsTrue() {
        addressBook.addTask(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_GRADED)
                .build();
        assertTrue(addressBook.hasTask(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyJelphaBot whose persons list can violate interface constraints.
     */
    private static class JelphaBotStub implements ReadOnlyJelphaBot {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        JelphaBotStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
