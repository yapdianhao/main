package seedu.jelphabot.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.jelphabot.commons.exceptions.IllegalValueException;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String moduleCode;
    private final Status status;
    private final String dateTime;
    private final Priority priority;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(
            @JsonProperty("desc") String description,
            @JsonProperty("status") Status status,
            @JsonProperty("dateTime") String dateTime,
            @JsonProperty("module") String moduleCode,
            @JsonProperty("priority") String priority,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged
    ) {
        this.name = description;
        this.status = status;
        this.dateTime = dateTime;
        this.moduleCode = moduleCode;
        this.priority = priority;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getDescription().fullDescription;
        this.status = source.getStatus();
        this.dateTime = source.getDateTime().toString();
        moduleCode = source.getModuleCode().value;
        this.priority = source.getPriority().toString();
        tagged.addAll(source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidName(name)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(name);

        if (moduleCode == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Task(modelDescription, status, new DateTime("Jan-1-2020 10 00"), modelModuleCode, priority, modelTags);
    }

}
