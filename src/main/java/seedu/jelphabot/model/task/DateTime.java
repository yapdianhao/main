package seedu.jelphabot.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class representing the Date and Time of the modelled task.
 */
public class DateTime {

    private static final String STANDARD_FORMAT = "MMM-d-yyyy HH mm";
    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format " + STANDARD_FORMAT
                                                         + ". Time should be in the 24 hour format HH mm.";
    private static final String DISPLAY_FORMAT = "d-MMM-yyyy HH mm";
    private static final List<DateFormat> dateFormats =
        Arrays.asList(
            new SimpleDateFormat("MMM-d-yyyy HH mm"),
            new SimpleDateFormat("MMM/d/yyyy HH mm"),
            new SimpleDateFormat("d/M/y HH mm"),
            new SimpleDateFormat("d-MMM-yyyy HH mm"),
            new SimpleDateFormat("d MMM yyyy HH mm")
        );
    private static final DateFormat standardFormatter = new SimpleDateFormat(STANDARD_FORMAT);
    private static final DateFormat displayFormatter = new SimpleDateFormat(DISPLAY_FORMAT);
    public final String value;

    /**
     * Constructs an {@code DateTime}.
     *
     * @param dateTime A valid datetime.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        DateFormat currentFormat = getDateFormatOfString(dateTime);
        value = convertDateToStandardFormat(currentFormat, dateTime);
    }

    /**
     * Returns if the given string is a valid datetime format, specified in the List dateFormatStrings.
     *
     * @param test The date to be checked.
     * @return The boolean representing whether the date provided is valid.
     */
    public static boolean isValidDateTime(String test) {
        for (DateFormat df : dateFormats) {
            try {
                df.parse(test);
                return true;
            } catch (ParseException e) {
                // do nothing, try next
            }
        }
        return false;
    }

    public static DateFormat getFormat() {
        return standardFormatter;
    }

    private static DateFormat getDateFormatOfString(String dateTimeString) {
        for (DateFormat df : dateFormats) {
            try {
                df.parse(dateTimeString);
                return df;
            } catch (ParseException e) {
                // do nothing, try next
            }
        }
        return null;
    }

    /**
     * Converts dateTime to the standard format (Standard format is the format the string is stored, defined in
     * #DateTime.STANDARD_FORMAT).
     *
     * @param currentDateFormat the current format of the DateTime string.
     * @param dateString        the string to be converted.
     * @return dateString converted to standard format.
     */
    private static String convertDateToStandardFormat(DateFormat currentDateFormat, String dateString) {
        String standardDateString = "";
        try {
            Date date = currentDateFormat.parse(dateString);
            standardDateString = standardFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return standardDateString;
    }

    /**
     * Converts stored dateTime value to the display format (Display format is the format the datetime is shown in
     * the view, defined in #DateTime.DISPLAY_FORMAT).
     *
     * @param dateString the string to be converted.
     * @return dateString converted to display format.
     */
    private String convertDateToDisplayFormat(String dateString) {
        String displayString = "";
        try {
            Date date = standardFormatter.parse(dateString);
            displayString = displayFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return displayString;
    }

    public String getDisplayValue() {
        return convertDateToDisplayFormat(value);
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

    public Date getDate() {
        try {
            return standardFormatter.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
