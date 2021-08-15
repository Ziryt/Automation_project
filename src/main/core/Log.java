package core;

import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class Log {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_GREY = "\u001B[90m";
    private static final String ANSI_WARNING = "\033[38;5;214m";

    private static String buffered_step;
    private static List<String> warnings = new ArrayList<>();
    private static int warning_count = 0;

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
        String log = "\t\t" + description + "\r\n";
        if (actual.equals(expected)) {
            log += String.format(ANSI_GREEN + ANSI_BOLD + "%16s%9s", "", "Actual: " + ANSI_RESET + ANSI_GREEN + actual + "\r\n");
            log += String.format(ANSI_BOLD + "%14s%9s", "", "Expected: " + ANSI_RESET + ANSI_GREEN + expected + ANSI_RESET + "\r\n");
            System.out.println(log);
        } else {
            warning_count++;
            Reporter.getCurrentTestResult().setStatus(2);
            log += "\t\t" + ANSI_WARNING + "Warning" + "\r\n";
            log += String.format(ANSI_RED + ANSI_BOLD + "%16s%9s", "", "Actual: " + ANSI_RESET + ANSI_RED + actual + "\r\n");
            log += String.format(ANSI_GREEN + ANSI_BOLD + "%14s%9s", "", "Expected: " + ANSI_RESET + ANSI_GREEN + expected + ANSI_RESET + "\r\n");
            System.out.println(log);
            warnings.add(log);
        }
    }

    public static void check(String description, List<?> actual, List<?> expected) {
        String log = "\t\t" + description + "\r\n";
        if (actual.equals(expected)) {
            log += String.format(ANSI_GREEN + ANSI_BOLD + "%16s%-40s%-40s%s", "", "Actual:", "Expected:" + ANSI_RESET, "\r\n");
            for (int i = 0; i < actual.size(); i++) {
                log += String.format(ANSI_GREEN + "%16s%-40s%-40s%s", "", actual.get(i), expected.get(i) + ANSI_RESET, "\r\n");
            }
            System.out.println(log);
        } else {
            warning_count++;
            Reporter.getCurrentTestResult().setStatus(2);
            log += "\t\t" + ANSI_WARNING + "Warning" + "\r\n";
            log += String.format(ANSI_BOLD + ANSI_YELLOW + "%16s%-40s%-40s%s", "", "Actual:", ANSI_GREEN + "Expected:" + ANSI_RESET, "\r\n");

            for (int i = 0; i < actual.size(); i++) {
                if (actual.get(i).equals(expected.get(i))) {
                    log += String.format(ANSI_GREEN + "%16s%-40s%-40s%s", "", actual.get(i), expected.get(i) + ANSI_RESET, "\r\n");
                } else {
                    log += String.format(ANSI_YELLOW + "%16s%-40s%-40s%s", "", actual.get(i), ANSI_GREEN + expected.get(i) + ANSI_RESET, "\r\n");
                }
            }
            System.out.println(log);
            warnings.add(log);
        }
    }

    public static void step(String name) {
        String step = "---------------------------------------------------------------------------------------\r\n" +
                ">" + name + "\r\n";
        if (warnings.isEmpty() || !warnings.get(warnings.size() - 1).equals(buffered_step))
            warnings.add(step);
        else
            warnings.set(warnings.size() - 1, step);
        buffered_step = step;
        System.out.println(step);
    }

    public static void log(String description) {
        System.out.println("\t\t" + ANSI_GREY + description + ANSI_RESET);
        System.out.println();
    }

    public static void assertAll() {
        if (warning_count > 0) {
            removeLastIndex(warnings);
            System.out.println(ANSI_WARNING + "Warnings: " + Log.warning_count + ANSI_RESET);
            for (String s : warnings)
                System.out.println(s);
        } else System.out.println(ANSI_GREEN + "Warnings: " + Log.warning_count + ANSI_RESET);
        System.out.println();
        clearWarns();
    }

    private static void removeLastIndex(List<String> list) {
        if (list.get(list.size() - 1).equals(buffered_step))
            list.remove(list.size() - 1);
    }

    protected static void clearWarns() {
        buffered_step = null;
        warning_count = 0;
        warnings.clear();
    }

}

