package seedu.jelphabot.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.jelphabot.commons.core.GuiSettings;
import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.logic.commands.Command;
import seedu.jelphabot.logic.commands.CommandResult;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.logic.parser.JelphaBotParser;
import seedu.jelphabot.logic.parser.exceptions.ParseException;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.task.SortedTaskList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final JelphaBotParser jelphaBotParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        jelphaBotParser = new JelphaBotParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = jelphaBotParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveJelphaBot(model.getJelphaBot());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyJelphaBot getJelphaBot() {
        return model.getJelphaBot();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getFilteredByCompleteTaskList() {
        return model.getFilteredByCompleteTaskList();
    }

    @Override
    public ObservableList<Task> getFilteredByIncompleteTaskList() {
        return model.getFilteredByIncompleteTaskList();
    }

    @Override
    public SortedTaskList getSortedTaskList() {
        return model.getSortedTaskList();
    }

    // @Override
    // public ObservableList<Productivity> getFilteredProductivityList() {
    //     return model.getFilteredProductivityList();
    // }

    @Override
    public ObservableList<Task> getFilteredByIncompleteDueTodayTaskList() {
        return model.getFilteredByIncompleteDueTodayTaskList();
    }

    @Override
    public Path getJelphaBotFilePath() {
        return model.getJelphaBotFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public GuiSettings getPopUpWindowGuiSettings() {
        return model.getPopUpWindowGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
