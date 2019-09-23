package ua.com.expo.util.time;

import java.time.format.DateTimeFormatter;


public class Formatter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public static DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }

    public static DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }

    public static void main(String[] args) {

    }
}
