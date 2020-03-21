package seedu.jelphabot.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jelphabot.logic.parser.CliSyntax.PREFIX_REMIND_DAY;
import static seedu.jelphabot.logic.parser.CliSyntax.PREFIX_REMIND_HOUR;

import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.ReminderCommand;
import seedu.jelphabot.logic.parser.exceptions.ParseException;
import seedu.jelphabot.model.reminder.ReminderHour;
import seedu.jelphabot.model.reminder.ReminderDay;
import seedu.jelphabot.model.reminder.Reminder;

/**
 * Parses input arguments and returns a new ReminderCommand Object.
 */
public class ReminderCommandParser implements Parser<ReminderCommand> {

    public ReminderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(
                args,
                PREFIX_REMIND_DAY,
                PREFIX_REMIND_HOUR
            );

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderCommand.MESSAGE_USAGE), pe);
        }
        ReminderDay reminderDay = ParserUtil.parseReminderDay(argMultimap.getValue(PREFIX_REMIND_DAY).get());
        ReminderHour reminderHour = ParserUtil.parseReminderHour(argMultimap.getValue(PREFIX_REMIND_HOUR).get());
        Reminder reminder = new Reminder(index, reminderDay, reminderHour);
        return new ReminderCommand(index, reminder);
    }
}
