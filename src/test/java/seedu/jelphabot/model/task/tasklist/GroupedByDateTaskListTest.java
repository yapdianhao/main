package seedu.jelphabot.model.task.tasklist;

import org.junit.jupiter.api.Test;

class GroupedByDateTaskListTest {

    private final GroupedByDateTaskList dateTaskList =
        makeEmptyModuleTaskList();

    private static GroupedByDateTaskList makeEmptyModuleTaskList() {
        return new GroupedByDateTaskList(new PinnedTaskListTest.PinnedTaskListStub());
    }

    @Test
    public void iterator_emptySublistsHidden() {
        // do nothing
    }
}
