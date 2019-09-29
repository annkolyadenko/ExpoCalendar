package ua.com.expo.util.time.impl;

import ua.com.expo.util.time.IDateFormatter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

public class DateFormatter implements IDateFormatter {

    private DateFormatter() {
    }

    private static class Holder {
        static final DateFormatter INSTANCE = new DateFormatter();
    }

    public static DateFormatter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String format(LocalDateTime localDate) {
        return localDate.format(formatter);
    }
}
