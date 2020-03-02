package seedu.jelphabot.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class representing the Date and Time of the modelled task.
 */
public class DateTime {

    private static final String STANDARD_FORMAT = "MMM-d-yyyy HH mm";
    private static final String DISPLAY_FORMAT = "MMM-d-yyyy HH mm";
    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format MMM-d-yyyy. "
                                                         + "Time should be in the 24 hour format HH mm.";
    public final String value;
    private final DateFormat format;

    private static final List<String> dateFormatStrings = Arrays.asList("MMM-d-yyyy HH mm", "MMM/d/yyyy HH mm", "d/M/y HH mm", "dd MM yyyy HH mm");

    /**
     * Constructs an {@code DateTime}.
     * @param dateTime A valid email address.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        format = getDateFormat(dateTime);
        value = convertDateToStandardFormat(dateTime);

    }

    /**
     * Returns if the given string is a valid datetime format, specified in the List dateFormatStrings.
     * @param test The date to be checked.
     * @return The boolean representing whether the date provided is valid.
     */
    public static boolean isValidDateTime(String test) {
        boolean correctFormat = false;
        for (String formatString : dateFormatStrings) {
            if (correctFormat) {
                break;
            }
            try {
                new SimpleDateFormat(formatString).parse(test);
                correctFormat = true;
            } catch (ParseException e) {
                continue;
            }
        }
        return correctFormat;
    }

    private DateFormat getDateFormat(String dateTimeString) {
        DateFormat currDateFormat = null;
        ArrayList<DateFormat> dfList = new ArrayList<>();
        for (String dfString : dateFormatStrings) {
            dfList.add(new SimpleDateFormat(dfString));
        }

        for (DateFormat df : dfList) {
            try {
                Date date = df.parse(dateTimeString);
                currDateFormat = df;
            } catch (ParseException e) {
                continue;
            }
        }
        return currDateFormat;
    }

    private String convertDateToStandardFormat(String currDate) {
        String retString = "";
        try {
            Date date = format.parse(currDate);
            retString = new SimpleDateFormat(STANDARD_FORMAT).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retString;
    }

    public DateFormat getFormat() {
        return format;
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
