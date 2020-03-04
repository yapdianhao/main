package seedu.jelphabot.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_SCHOOL;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.jelphabot.testutil.TypicalTasks.JOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.model.task.exceptions.DuplicateTaskException;
import seedu.jelphabot.model.task.exceptions.TaskNotFoundException;
import seedu.jelphabot.testutil.TaskBuilder;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(ASSIGNMENT));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueTaskList.add(ASSIGNMENT);
        assertTrue(uniqueTaskList.contains(ASSIGNMENT));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(ASSIGNMENT);
        Task editedAlice = new TaskBuilder(ASSIGNMENT).withTags(VALID_TAG_SCHOOL)
                .build();
        assertTrue(uniqueTaskList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueTaskList.add(ASSIGNMENT);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(ASSIGNMENT));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, ASSIGNMENT));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(ASSIGNMENT, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(ASSIGNMENT, ASSIGNMENT));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueTaskList.add(ASSIGNMENT);
        uniqueTaskList.setTask(ASSIGNMENT, ASSIGNMENT);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(ASSIGNMENT);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueTaskList.add(ASSIGNMENT);
        Task editedAlice = new TaskBuilder(ASSIGNMENT).withTags(VALID_TAG_SCHOOL)
                .build();
        uniqueTaskList.setTask(ASSIGNMENT, editedAlice);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedAlice);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueTaskList.add(ASSIGNMENT);
        uniqueTaskList.setTask(ASSIGNMENT, JOB);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(JOB);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueTaskList.add(ASSIGNMENT);
        uniqueTaskList.add(JOB);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(ASSIGNMENT, JOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(ASSIGNMENT));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueTaskList.add(ASSIGNMENT);
        uniqueTaskList.remove(ASSIGNMENT);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueTaskList.add(ASSIGNMENT);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(JOB);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(ASSIGNMENT);
        List<Task> taskList = Collections.singletonList(JOB);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(JOB);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(ASSIGNMENT, ASSIGNMENT);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}
