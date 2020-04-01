package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.jelphabot.model.Model;

/**
 * Lists all tasks in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String DATE_GROUPING = "date";
    public static final String MODULE_GROUPING = "module";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_SWITCH_PANEL_ACKNOWLEDGEMENT = "Switched to Task List panel";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS).isShowTaskList();
    }
}
