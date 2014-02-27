package be.markito.kubb.api.resource.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;

/**
 * A utility class to host methods that do the translations between the resource and the service layer.
 *
 * @author MArKiTo
 */
public final class WebToServiceConverter {
    /**
     * Suppressing the public constructor.
     */
    private WebToServiceConverter() {
    }

    /**
     * Converts the date that comes in from the web client to a {@link java.util.Date} object.
     *
     * @param value the date as inputted on the client.
     * @return a {@link java.util.Date} object that represents the same date as what was passed in from the client.
     * @throws IllegalArgumentException when the date {@link String} cannot be converted to a {@link java.util.Date}.
     */
    public static Date convertStringToDateTime(String value) {
        return convertStringToDateTime(value, false);
    }

    /**
     * Converts the date that comes in from the web client to a {@link java.util.Date} object.
     *
     * @param value     the date as inputted on the client.
     * @param mandatory Whether the date value is mandatory. When true and value is blank, then an exception is thrown.
     * @return a {@link java.util.Date} object that represents the same date as what was passed in from the client.
     * @throws IllegalArgumentException when the date {@link String} cannot be converted to a {@link java.util.Date}.
     */
    public static Date convertStringToDateTime(String value, boolean mandatory) {
        if (mandatory && StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("String value is null or empty: " + value);
        } else if (StringUtils.isBlank(value)) {
            return null;
        }
        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
        DateTime time = formatter.parseDateTime(value);
        return time.toDate();
    }

    /**
     * Converts the date that comes in from the web client to a {@link java.util.Date} object.
     *
     * @param value the date as inputted on the client.
     * @return a {@link java.util.Date} object that represents the same date as what was passed in from the client.
     * @throws IllegalArgumentException when the date {@link String} cannot be converted to a {@link java.util.Date}.
     */
    public static Date convertStringToDate(String value) {
        return convertStringToDate(value, false);
    }

    /**
     * Converts the date that comes in from the web client to a {@link java.util.Date} object.
     *
     * @param value     the date as inputted on the client.
     * @param mandatory Whether the date value is mandatory. When true and value is blank, then an exception is thrown.
     * @return a {@link java.util.Date} object that represents the same date as what was passed in from the client.
     * @throws IllegalArgumentException when the date {@link String} cannot be converted to a {@link java.util.Date}.
     */
    public static Date convertStringToDate(String value, boolean mandatory) {
        if (mandatory && StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("String value is null or empty: " + value);
        } else if (StringUtils.isBlank(value)) {
            return null;
        }
        String valueToParse = value;
        if (StringUtils.contains(value, "T")) {
            valueToParse = StringUtils.substringBefore(value, "T");
        }
        DateTimeFormatter formatter = ISODateTimeFormat.date();
        LocalDate date = formatter.parseLocalDate(valueToParse);
        return date.toDate();
    }

    /**
     * Converts in incoming ID search parameter from a {@link String} to a {@link Long}.
     *
     * @param idString  The incoming ID to convert.
     * @param mandatory When true, this method will throw an exception in case the idString is empty.
     *                  Otherwise, this method will return null.
     * @return The idString converted to a Long.
     * @throws IllegalArgumentException when:
     *                                  <ul>
     *                                  <li>mandatory == true and the idString is NULL or empty string.
     *                                  <li>idString cannot be converted to a {@link Long}.
     *                                  <li>idString can be converted but results in a value less than 0 (zero).
     *                                  </ul>
     */
    public static Long convertIdToLong(String idString, boolean mandatory) {
        Long result = null;
        if (mandatory && StringUtils.isBlank(idString)) {
            throw new IllegalArgumentException("ID is null or empty: " + idString);
        }
        if (StringUtils.isNotBlank(idString)) {
            try {
                result = Long.parseLong(idString);
                if (result <= 0) {
                    throw new IllegalArgumentException("ID is not greater than 0: " + idString);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ID is not numeric: " + idString, e);
            }
        }
        return result;
    }

    /**
     * Converts an incoming string value from a {@link String} to a {@link Boolean}.
     *
     * @param boolVal The {@link String} value to convert to a {@link Boolean}.
     * @return The {@link boolean} extracted from the boolVal {@link String}.
     * This method returns true if and only if boolVal == "true" (case insensitive).
     */
    public static boolean convertStringToBoolean(String boolVal) {
        return convertStringToBoolean(boolVal, true);
    }

    /**
     * Converts an incoming string value from a {@link String} to a {@link Boolean}.
     *
     * @param boolVal The {@link String} value to convert to a {@link Boolean}.
     * @return The {@link boolean} extracted from the boolVal {@link String}.
     * This method returns true if and only if boolVal == "true" (case insensitive).
     */
    public static boolean convertStringToBoolean(String boolVal, boolean defaultToFalse) {
        if (!defaultToFalse && !(StringUtils.equalsIgnoreCase(boolVal, "true") || StringUtils.equalsIgnoreCase(boolVal, "false"))) {
            throw new IllegalArgumentException("boolVal is not a valid boolean value");
        }
        boolean result = false;
        if (StringUtils.isNotBlank(boolVal)) {
            result = Boolean.valueOf(boolVal);
        }
        return result;
    }

    /**
     * Evaluates an incoming {@link String} value search parameter returning NULL when empty String is given.
     *
     * @param val       The incoming value to evaluate.
     * @param mandatory When true, this method will throw an exception in case the idString is empty.
     *                  Otherwise, this method will return null.
     * @return The idString converted to a Long.
     * @throws IllegalArgumentException when:
     *                                  <ul>
     *                                  <li>mandatory == true and the idString is NULL or empty string.
     *                                  <li>idString cannot be converted to a {@link Long}.
     *                                  <li>idString can be converted but results in a value less than 0 (zero).
     *                                  </ul>
     */
    public static String convertIdToString(String val, boolean mandatory) {
        if (mandatory && StringUtils.isBlank(val)) {
            throw new IllegalArgumentException("String value is null or empty: " + val);
        }
        String result = null;
        if (StringUtils.isNotBlank(val)) {
            result = val;
        }
        return result;
    }
}
