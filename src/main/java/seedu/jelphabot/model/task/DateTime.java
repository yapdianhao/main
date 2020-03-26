package seedu.jelphabot.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class representing the Date and Time of the modelled task.
 */
public class DateTime {

    private static final String STANDARD_FORMAT = "MMM-d-yyyy HHmm";
    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format " + STANDARD_FORMAT
                                                         + ". Time should be in the 24 hour format HH mm.";
    private static final String DISPLAY_FORMAT = "d-MMM-yyyy HH mm";

    private static final DateTimeFormatter standardFormatter =
        DateTimeFormatter.ofPattern(STANDARD_FORMAT).withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter displayFormatter =
        DateTimeFormatter.ofPattern(DISPLAY_FORMAT).withResolverStyle(ResolverStyle.STRICT);
    private static final List<DateTimeFormatter> dateFormats =
        Arrays.asList(
            standardFormatter,
            DateTimeFormatter.ofPattern("MMM/d/yyyy HH mm").withResolverStyle(ResolverStyle.STRICT),
            DateTimeFormatter.ofPattern("d/M/y HH mm").withResolverStyle(ResolverStyle.STRICT),
            displayFormatter,
            DateTimeFormatter.ofPattern("d MMM yyyy HH mm").withResolverStyle(ResolverStyle.STRICT)
        );

    public final LocalDateTime value;

    /**
     * Constructs an {@code DateTime}.
     *
     * @param dateTime A valid datetime.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        System.out.println(standardFormatter.parse(dateTime));
        value = LocalDateTime.parse(dateTime, standardFormatter);
    }

    /**
     * Returns if the given string is a valid datetime format, specified in the List dateFormatStrings.
     *
     * @param test The date to be checked.
     * @return The boolean representing whether the date provided is valid.
     */
    public static boolean isValidDateTime(String test) {
        for (DateTimeFormatter df : dateFormats) {
            try {
                System.out.println(df.parse(test));
                return true;
            } catch (DateTimeParseException e) {
                // do nothing, try next
            }
        }
        return false;
    }

    /**
     * Converts stored dateTime value to the display format (Display format is the format the datetime is shown in
     * the view, defined in #DateTime.DISPLAY_FORMAT).
     *
     * @return dateString converted to display format.
     */
    public String getDisplayValue() {
        return value.format(displayFormatter);
    }

    /**
     * Converts stored dateTime value to the standard format
     * (Standard format is the DateTimeFormatter used for storing the date in Json and is defined in
     * #DateTime.STANDARD_FORMAT).
     * @return
     */
    @Override
    public String toString() {
        return value.format(standardFormatter);
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

    public LocalDate getDate() {
        return value.toLocalDate();
    }

    public LocalDateTime getDateTime() {
        return value;
    }
}
