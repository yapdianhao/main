package seedu.jelphabot.model.task.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_ASSIGNMENT;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TUTORIAL;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_GRADED;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalTasks.ASSESSMENT;
import static seedu.jelphabot.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.jelphabot.testutil.TypicalTasks.TUTORIAL;
import static seedu.jelphabot.testutil.TypicalTasks.getTypicalTasks;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.exceptions.DuplicateTaskException;
import seedu.jelphabot.model.task.exceptions.TaskNotFoundException;
import seedu.jelphabot.testutil.TaskBuilder;

class GroupedByModuleTaskListTest {

    private final GroupedByModuleTaskList moduleTaskList =
        makeEmptyModuleTaskList();

    private static GroupedByModuleTaskList makeEmptyModuleTaskList() {
        return new GroupedByModuleTaskList(new PinnedTaskListTest.PinnedTaskListStub());
    }

    @Test
    void isEmpty_emptyList_returnsTrue() {
        assertTrue(moduleTaskList.isEmpty());

        moduleTaskList.setTasks(getTypicalTasks());
        assertFalse(moduleTaskList.isEmpty());

        moduleTaskList.clear();
        assertTrue(moduleTaskList.isEmpty());
    }

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(moduleTaskList.contains(ASSESSMENT));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        moduleTaskList.add(ASSESSMENT);
        assertTrue(moduleTaskList.contains(ASSESSMENT));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        moduleTaskList.add(ASSESSMENT);
        Task editedTask = new TaskBuilder(ASSESSMENT).withTags(VALID_TAG_GRADED)
                              .build();
        assertTrue(moduleTaskList.contains(editedTask));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        moduleTaskList.add(ASSESSMENT);
        assertThrows(DuplicateTaskException.class, () -> moduleTaskList.add(ASSESSMENT));
    }

    @Test
    public void add_taskWithDifferentModuleCode_newSublistCreated() {
        moduleTaskList.add(ASSESSMENT);
        assertEquals(moduleTaskList.size(), 1);
        assertEquals(moduleTaskList.getSublists().size(), 1);

        moduleTaskList.add(ASSIGNMENT);
        assertEquals(moduleTaskList.size(), 2);
        assertEquals(moduleTaskList.getSublists().size(), 2);
    }

    @Test
    public void add_taskWithSameModuleCode_addedToSameSublist() {
        moduleTaskList.add(ASSESSMENT);
        assertEquals(moduleTaskList.size(), 1);
        assertEquals(moduleTaskList.getSublists().size(), 1);

        Task differentTask = new TaskBuilder(ASSESSMENT)
                                 .withDescription("A Different Assesment")
                                 .build();

        moduleTaskList.add(differentTask);
        assertEquals(moduleTaskList.size(), 2);
        assertEquals(moduleTaskList.getSublists().size(), 1);
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTaskList.setTask(null, ASSESSMENT));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTaskList.setTask(ASSESSMENT, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> moduleTaskList.setTask(ASSESSMENT, ASSESSMENT));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        moduleTaskList.add(ASSESSMENT);
        moduleTaskList.setTask(ASSESSMENT, ASSESSMENT);
        GroupedByModuleTaskList expectedModuleTaskList =
            makeEmptyModuleTaskList();
        expectedModuleTaskList.add(ASSESSMENT);
        assertEquals(expectedModuleTaskList, moduleTaskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        moduleTaskList.add(ASSESSMENT);
        Task editedAlice = new TaskBuilder(ASSESSMENT).withTags(VALID_TAG_GRADED)
                               .build();
        moduleTaskList.setTask(ASSESSMENT, editedAlice);
        GroupedByModuleTaskList expectedModuleTaskList = makeEmptyModuleTaskList();
        expectedModuleTaskList.add(editedAlice);
        assertEquals(expectedModuleTaskList, moduleTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        moduleTaskList.add(ASSESSMENT);
        moduleTaskList.setTask(ASSESSMENT, TUTORIAL);
        GroupedByModuleTaskList expectedModuleTaskList = makeEmptyModuleTaskList();
        expectedModuleTaskList.add(TUTORIAL);
        assertEquals(expectedModuleTaskList, moduleTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        moduleTaskList.add(ASSESSMENT);
        moduleTaskList.add(TUTORIAL);
        assertThrows(DuplicateTaskException.class, () -> moduleTaskList.setTask(ASSESSMENT, TUTORIAL));
    }

    @Test
    public void setTask_editedTaskToDifferentModuleCode_newSublistCreated() {
        moduleTaskList.add(ASSIGNMENT);
        Task differentTask = new TaskBuilder(ASSIGNMENT)
                                 .withDescription("A different task")
                                 .build();
        moduleTaskList.add(differentTask);
        assertEquals(moduleTaskList.size(), 2);
        assertEquals(moduleTaskList.getSublists().size(), 1);

        Task differentModule = new TaskBuilder(differentTask)
                                   .withModuleCode(VALID_MODULE_CODE_TUTORIAL)
                                   .build();

        moduleTaskList.setTask(differentTask, differentModule);
        assertEquals(moduleTaskList.size(), 2);
        assertEquals(moduleTaskList.getSublists().size(), 2);
    }

    @Test
    public void setTask_editedTaskToSameModuleCode_addedToSameSublist() {
        moduleTaskList.add(ASSIGNMENT);
        moduleTaskList.add(ASSESSMENT);
        assertEquals(moduleTaskList.size(), 2);
        assertEquals(moduleTaskList.getSublists().size(), 2);

        Task differentTask = new TaskBuilder(ASSESSMENT)
                                 .withModuleCode(VALID_MODULE_CODE_ASSIGNMENT)
                                 .build();

        moduleTaskList.setTask(ASSESSMENT, differentTask);
        assertEquals(moduleTaskList.size(), 2);
        assertEquals(moduleTaskList.getSublists().size(), 1);
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> moduleTaskList.remove(ASSESSMENT));
    }

    @Test
    public void remove_existingTask_removesTask() {
        moduleTaskList.add(ASSESSMENT);
        moduleTaskList.remove(ASSESSMENT);
        GroupedByModuleTaskList expectedModuleTaskList = makeEmptyModuleTaskList();
        assertEquals(expectedModuleTaskList, moduleTaskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_nullTaskList_replacesOwnListWithProvidedGroupedByModuleTaskList() {
        moduleTaskList.add(ASSESSMENT);
        GroupedByModuleTaskList expectedModuleTaskList =
            makeEmptyModuleTaskList();
        expectedModuleTaskList.add(ASSIGNMENT);
        moduleTaskList.setTasks(expectedModuleTaskList);
        assertEquals(expectedModuleTaskList, moduleTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        moduleTaskList.add(ASSESSMENT);
        List<Task> taskList = Collections.singletonList(TUTORIAL);
        moduleTaskList.setTasks(taskList);
        GroupedByModuleTaskList expectedModuleTaskList =
            makeEmptyModuleTaskList();
        expectedModuleTaskList.add(TUTORIAL);
        assertEquals(expectedModuleTaskList, moduleTaskList);
    }

}
