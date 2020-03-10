package seedu.jelphabot.logic.parser;

import static seedu.jelphabot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.DoneCommand;
import seedu.jelphabot.logic.parser.exceptions.ParseException;

public class DoneCommandParser implements Parser<DoneCommand> {

    @Override
    public DoneCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE), pe);
        }
    }

}
