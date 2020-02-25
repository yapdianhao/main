package seedu.jelphabot.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.jelphabot.commons.exceptions.IllegalValueException;
import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable JelphaBot that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableJelphaBot {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableJelphaBot} with the given persons.
     */
    @JsonCreator
    public JsonSerializableJelphaBot(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyJelphaBot} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableJelphaBot}.
     */
    public JsonSerializableJelphaBot(ReadOnlyJelphaBot source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code JelphaBot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public JelphaBot toModelType() throws IllegalValueException {
        JelphaBot addressBook = new JelphaBot();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Task task = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(task);
        }
        return addressBook;
    }

}
