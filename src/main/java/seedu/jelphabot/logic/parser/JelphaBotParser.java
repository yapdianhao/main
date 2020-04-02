package seedu.jelphabot.logic.parser;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jelphabot.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.jelphabot.logic.commands.AddCommand;
import seedu.jelphabot.logic.commands.CalendarCommand;
import seedu.jelphabot.logic.commands.ClearCommand;
import seedu.jelphabot.logic.commands.Command;
import seedu.jelphabot.logic.commands.DeleteCommand;
import seedu.jelphabot.logic.commands.DeleteReminderCommand;
import seedu.jelphabot.logic.commands.DoneCommand;
import seedu.jelphabot.logic.commands.EditCommand;
import seedu.jelphabot.logic.commands.ExitCommand;
import seedu.jelphabot.logic.commands.FindCommand;
import seedu.jelphabot.logic.commands.HelpCommand;
import seedu.jelphabot.logic.commands.ListCommand;
import seedu.jelphabot.logic.commands.ProductivityCommand;
import seedu.jelphabot.logic.commands.ReminderCommand;
import seedu.jelphabot.logic.commands.ShowCompletedCommand;
import seedu.jelphabot.logic.commands.ShowIncompleteCommand;
import seedu.jelphabot.logic.commands.StartTimerCommand;
import seedu.jelphabot.logic.commands.StopTimerCommand;
import seedu.jelphabot.logic.commands.SummaryCommand;
import seedu.jelphabot.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class JelphaBotParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteReminderCommand.COMMAND_WORD:
            return new DeleteReminderCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_SHORTCUT: // fallthrough
        case ListCommand.COMMAND_SHORTCUT_TWO: // fallthrough
        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ReminderCommand.COMMAND_WORD:
            return new ReminderCommandParser().parse(arguments);

        case ShowCompletedCommand.COMMAND_WORD:
            return new ShowCompletedCommand();

        case ShowIncompleteCommand.COMMAND_WORD:
            return new ShowIncompleteCommand();

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case CalendarCommand.COMMAND_WORD: // fallthrough
        case CalendarCommand.COMMAND_SHORTCUT:
            return new CalendarCommandParser().parse(arguments);

        case SummaryCommand.COMMAND_WORD: // fallthrough
        case SummaryCommand.COMMAND_SHORTCUT:
            return new SummaryCommand();

        case ProductivityCommand.COMMAND_WORD: // fallthrough
        case ProductivityCommand.COMMAND_SHORTCUT:
            return new ProductivityCommand();

        case StartTimerCommand.COMMAND_WORD:
            return new StartTimerCommandParser().parse(arguments);

        case StopTimerCommand.COMMAND_WORD:
            return new StopTimerCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
