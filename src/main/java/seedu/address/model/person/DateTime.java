package seedu.address.model.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "DateTime should be of the format MMM-d-yyy HH mm. Time should " +
            "be in the 24 hour format.";
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
    public static boolean isValidDateTime(String test) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-d-yyyy HH mm");
        sdf.setLenient(false);
        try {
            sdf.parse(test);
        } catch (ParseException e) {
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
