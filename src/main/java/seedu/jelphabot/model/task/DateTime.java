package seedu.jelphabot.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing the Date and Time of the modelled task.
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "DateTime should be of the format MMM-d-yyyy."
            +  "Time should be in the 24 hour format HH mm.";
    public final String value;

    // TODO: Make UI display uniform date formatting, even with multiple date formats
    private static final List<String> dateFormatStrings = Arrays.asList("MMM-d-yyyy HH mm", "MMM/d/yyyy HH mm", "d/M/y HH mm", "dd MM yyyy HH mm");

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
     * Returns if the given string is a valid datetime format, specified in the List dateFormatStrings.
     * @param test The date to be checked.
     * @return The boolean representing whether the date provided is valid.
     */
    // TODO: Write test cases for different valid and invalid date time formats.
    public static boolean isValidDateTime(String test) {
        //SimpleDateFormat sdf = new SimpleDateFormat("MMM-d-yyyy HH mm");
        // SimpleDateFormat sdf2 = new SimpleDateFormat("dd MM yyyy HH mm");
        boolean correctFormat = false;
        for (String formatString : dateFormatStrings) {
            try {
                new SimpleDateFormat(formatString).parse(test);
                correctFormat = true;
            } catch (ParseException e) {
                continue;
            }
        }
//        sdf.setLenient(false);
//        try {
//            sdf.parse(test);
//        } catch (ParseException e) {
//            return false;
//        }
        //return true;
        return correctFormat;
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
