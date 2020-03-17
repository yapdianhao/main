package seedu.jelphabot.logic.parser;

import java.util.Date;

import seedu.jelphabot.logic.commands.DueTodayCommand;
import seedu.jelphabot.logic.parser.exceptions.ParseException;
import seedu.jelphabot.model.task.predicates.TaskDueWithinDayPredicate;

/**
 * Parses input arguments and creates a new DueTodayCommand object
 */
public class DueTodayCommandParser implements Parser<DueTodayCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DueTodayCommand
     * and returns a DueTodayCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DueTodayCommand parse(String args) throws ParseException {
        return new DueTodayCommand(new TaskDueWithinDayPredicate(new Date()));
    }
}