package seedu.jelphabot.model.task.tasklist;

class GroupedByDateTaskListTest {

    private final GroupedByDateTaskList dateTaskList =
        makeEmptyDateTaskList();

    private static GroupedByDateTaskList makeEmptyDateTaskList() {
        return new GroupedByDateTaskList(new PinnedTaskListTest.PinnedTaskListStub());
    }
}
