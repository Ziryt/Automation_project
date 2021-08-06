package core;

import org.testng.Reporter;

import java.util.List;

public class Log {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_GREY = "\u001B[90m";
    private static final String ANSI_WARNING = "\033[38;5;214m";

    public static int warnings = 0;

    public static void check(String description, boolean actual, boolean expected) {
        check(description, String.valueOf(actual), String.valueOf(expected));
    }

    public static void check(String description, boolean actual) {
        check(description, actual, true);
    }

    public static void check(String description, String actual, int expected) {
        check(description, actual, String.valueOf(expected));
    }

    public static void check(String description, String actual, String expected) {
        System.out.println("\t\t" + description);
        if (actual.equals(expected)) {
            System.out.printf(ANSI_GREEN + ANSI_BOLD + "%16s%9s", "", "Actual: " + ANSI_RESET + ANSI_GREEN + actual + "\r\n");
            System.out.printf(ANSI_BOLD + "%14s%9s", "", "Expected: " + ANSI_RESET + ANSI_GREEN + expected + ANSI_RESET);
        } else {
            warnings++;
            Reporter.getCurrentTestResult().setStatus(2);
            System.out.println("\t\t" + ANSI_WARNING + "Warning");
            System.out.printf(ANSI_RED + ANSI_BOLD + "%16s%9s", "", "Actual: " + ANSI_RESET + ANSI_RED + actual + "\r\n");
            System.out.printf(ANSI_GREEN + ANSI_BOLD + "%14s%9s", "", "Expected: " + ANSI_RESET + ANSI_GREEN + expected + ANSI_RESET);
        }
        System.out.println("\r\n");
    }

    public static void check(String description, List<?> actual, List<?> expected) {
        System.out.println("\t\t" + description);
        if (actual.equals(expected)) {
            System.out.printf(ANSI_GREEN + ANSI_BOLD + "%16s%-40s%-40s", "", "Actual:", "Expected:" + ANSI_RESET);
            System.out.println();
            for (int i = 0; i < actual.size(); i++) {
                System.out.printf(ANSI_GREEN + "%16s%-40s%-40s", "", actual.get(i), expected.get(i) + ANSI_RESET);
                System.out.println();
            }
        } else {
            warnings++;
            Reporter.getCurrentTestResult().setStatus(2);
            System.out.println("\t\t" + ANSI_WARNING + "Warning");
            System.out.printf(ANSI_BOLD + ANSI_YELLOW + "%16s%-40s%-40s", "", "Actual:", ANSI_GREEN + "Expected:" + ANSI_RESET);
            System.out.println();
            for (int i = 0; i < actual.size(); i++) {
                if (actual.get(i).equals(expected.get(i))) {
                    System.out.printf(ANSI_GREEN + "%16s%-40s%-40s", "", actual.get(i), expected.get(i) + ANSI_RESET);
                } else {
                    System.out.printf(ANSI_YELLOW + "%16s%-40s%-40s", "", actual.get(i), ANSI_GREEN + expected.get(i) + ANSI_RESET);
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void step(String name) {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(">" + name + "\r\n");
    }

    public static void log(String description) {
        System.out.println("\t\t" + ANSI_GREY + description + ANSI_RESET);
        System.out.println();
    }

    public static void assertAll() {
        System.out.println(ANSI_WARNING + "Warnings: " + Log.warnings + ANSI_RESET);
        System.out.println();
    }


}

