package Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public final class InputValidator {

    // Date format: dd/MM/yyyy
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.ENGLISH)
                    .withResolverStyle(ResolverStyle.STRICT);

    private InputValidator() {
    }

    // Validate student ID
    public static boolean isValidId(int id)
    {
        return id > 0;
    }

    // Validate text fields
    public static boolean isValidText(String value) {

        return value != null
                && !value.trim().isEmpty()
                && !value.contains(",")
                && !value.contains("\n")
                && !value.contains("\r");
    }

    // Validate gender
    public static boolean isValidGender(String gender) {

        return gender != null &&
                (gender.equalsIgnoreCase("male")
                        || gender.equalsIgnoreCase("female")
                        || gender.equalsIgnoreCase("other"));
    }

    // Validate date
    public static boolean isValidDate(String value) {

        if (value == null) {
            return false;
        }

        try {
            LocalDate.parse(value.trim(), DATE_FORMAT);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Validate all student details
    public static String validationMessage(
            String name,
            String gender,
            String dob,
            String department,
            String course,
            String admissionDate) {

        if (!isValidText(name)) {
            return "Name is required and must not contain commas.";
        }

        if (!isValidGender(gender)) {
            return "Gender must be Male, Female, or Other.";
        }

        if (!isValidDate(dob)) {
            return "Enter a valid DOB (dd/MM/yyyy).";
        }

        if (!isValidText(department)) {
            return "Department is required.";
        }

        if (!isValidText(course)) {
            return "Course is required.";
        }

        if (!isValidDate(admissionDate)) {
            return "Enter a valid admission date (dd/MM/yyyy).";
        }
        return null;
    }
}