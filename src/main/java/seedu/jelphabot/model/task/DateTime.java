package seedu.jelphabot.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class representing the Date and Time of the modelled task.
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "DateTime should be of the format YYYY-MM-DD HH:MM. Time should "
            + "be in the 24 hour format.";
    public final String value;

    /**
     * Constructs an {@code DateTime}.
     *
     * @param dateTime A valid email address.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = dateTime;
    }

    /**
     * Returns if the given string is a valid datetime.
     * @param test The date to be checked.
     * @return The boolean representing whether the date provided is valid.
     */
    // TODO: change storage format to work properly
    public static boolean isValidDateTime(String test) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setLenient(false);
        try {
            sdf.parse(test);
        } catch (ParseException e) {
            System.out.println(true);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && value.equals(((DateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
