package seedu.jelphabot.storage;

import org.junit.jupiter.api.Tags;

import seedu.jelphabot.model.task.DateTime;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Priority;
import seedu.jelphabot.model.task.Status;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.commons.exceptions.IllegalValueException;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jelphabot.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import org.junit.jupiter.api.Test;

import static seedu.jelphabot.testutil.TypicalTasks.MA1101R;

//import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

// TODO rewrite entire test class to replace missing fields
public class JsonAdaptedTaskTest {
    private static final String INVALID_DESCRIPTION = "∫3L L1M";
    private static final String INVALID_PRIORITY = "!!!!"; // dont test this first
    private static final String INVALID_STATUS = "Ω(n log log n)"; //dont test this first
    private static final String INVALID_MODULE_CODE = "JELJELJEL!@#$%%";
    private static final String INVALID_TAG = "#rabbit"; // dont test this first
    private static final String INVALID_DATETIME = "03 19 1999 00 00";

    private static final String VALID_DESCRIPTION = MA1101R.getDescription().toString();
    private static final String VALID_MODULE_CODE = MA1101R.getModuleCode().toString();
    private static final String VALID_DATETIME = MA1101R.getDateTime().toString();
    private static final Priority VALID_PRIORITY = MA1101R.getPriority();
    private static final Status VALID_STATUS = MA1101R.getStatus();
    private static final List<JsonAdaptedTag> VALID_TAGS = MA1101R.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    // Task = task(description, status, datetime, modulecode, priority, tags)


     @Test
    public void toModelType_validTaskDetails_returnsPerson() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(MA1101R);
        assertEquals(MA1101R, task.toModelType());
    }

    @Test //done
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(INVALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, VALID_MODULE_CODE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test //done
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask person = new JsonAdaptedTask(null, VALID_STATUS, VALID_DATETIME, VALID_MODULE_CODE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test //done
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, INVALID_MODULE_CODE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test //done
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedTask person = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, null, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, INVALID_DATETIME, VALID_MODULE_CODE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedTask person = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, null, INVALID_MODULE_CODE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, VALID_MODULE_CODE, VALID_PRIORITY, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullTags_throwsIllegalValueException() {
        JsonAdaptedTask person = new JsonAdaptedTask(VALID_DESCRIPTION, VALID_STATUS, VALID_DATETIME, INVALID_MODULE_CODE, VALID_PRIORITY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tags.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


}
