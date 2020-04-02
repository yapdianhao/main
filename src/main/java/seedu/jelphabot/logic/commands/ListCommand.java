package seedu.jelphabot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.jelphabot.model.Model;

/**
 * Lists all tasks in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_SHORTCUT = ":l";
    public static final String COMMAND_SHORTCUT_TWO = ":L";


    public static final String DATE_GROUPING = "date";
    public static final String MODULE_GROUPING = "module";

    public static final String MESSAGE_SUCCESS = "Listed all tasks by ";
    public static final String MESSAGE_SWITCH_PANEL_ACKNOWLEDGEMENT = "Switched to Task List panel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to Task List panel and lists all tasks.\n"
                                                   + "This command groups tasks by Date by default, but you can also "
                                                   + "group by module. "
                                                   + "Parameters:\n"
                                                   + "    " + "[" + DATE_GROUPING + "]\n"
                                                   + "    " + "[" + MODULE_GROUPING + "]\n"
                                                   + "Example: " + COMMAND_WORD + " module";

    private final String grouping;

    public ListCommand() {
        this.grouping = "none";
    }

    public ListCommand(String grouping) {
        this.grouping = grouping;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        switch (grouping) {
        case "module":
            return new CommandResult(MESSAGE_SUCCESS).isShowModuleTaskList();
        case "date":
            return new CommandResult(MESSAGE_SUCCESS).isShowDateTaskList();
        case "none":
        default:
            return new CommandResult(MESSAGE_SWITCH_PANEL_ACKNOWLEDGEMENT).isShowDateTaskList();
        }
    }
}
