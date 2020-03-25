package seedu.jelphabot.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.jelphabot.commons.exceptions.DataConversionException;
import seedu.jelphabot.model.ReadOnlyJelphaBot;

import javax.swing.text.html.Option;

/**
 * Represents a storage for {@link seedu.jelphabot.model.JelphaBot}.
 */
public interface JelphaBotStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getJelphaBotFilePath();

    Path getJelphaBotReminderPath();

    /**
     * Returns JelphaBot data as a {@link ReadOnlyJelphaBot}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */

    Optional<ReadOnlyJelphaBot> readJelphaBot() throws DataConversionException, IOException;

    Optional<ReadOnlyJelphaBot> readJelphaBot(boolean isReminder) throws DataConversionException, IOException;
    /**
     * @see #getJelphaBotFilePath()
     */
    Optional<ReadOnlyJelphaBot> readJelphaBot(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyJelphaBot} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveJelphaBot(ReadOnlyJelphaBot jelphaBot) throws IOException;

    void saveJelphaBot(ReadOnlyJelphaBot jelphaBot, boolean isReminder) throws IOException;

    /**
     * @see #saveJelphaBot(ReadOnlyJelphaBot)
     */
    void saveJelphaBot(ReadOnlyJelphaBot jelphaBot, Path filePath) throws IOException;

}
