package seedu.jelphabot.storage;

import seedu.jelphabot.commons.exceptions.DataConversionException;
import seedu.jelphabot.model.ReadOnlyAddressBook;
import seedu.jelphabot.model.ReadOnlyUserPrefs;
import seedu.jelphabot.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
