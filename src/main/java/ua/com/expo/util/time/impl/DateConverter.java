package ua.com.expo.util.time.impl;

import ua.com.expo.util.time.IDateConverter;

import java.sql.Timestamp;
import java.time.*;

public class DateConverter implements IDateConverter<Instant, Timestamp> {

    private DateConverter() {
    }

    private static class Holder {
        static final DateConverter INSTANCE = new DateConverter();
    }

    public static DateConverter getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Timestamp convertToDatabase(Instant instant) {
        return Timestamp.from(instant);
    }

    @Override
    public Instant convertToEntity(Timestamp timestamp) {
        return timestamp.toInstant();
    }

    @Override
    public Timestamp convertStringDateTimeToDatabase(String str) {
        return Timestamp.valueOf(LocalDateTime.parse(str));
    }

    @Override
    public Timestamp convertStringDateToDatabase(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    @Override
    public Instant convertLocalDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(zone).toInstant();
    }

    @Override
    public LocalDateTime convertInstantToLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, zone);
    }


    public static void main(String[] args) {
        ZoneId zone = ZoneId.of("Europe/Kiev");
        Instant instant = Instant.now();
        System.out.println(instant);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
        System.out.println(dateTime);
        Instant instant1 = dateTime.atZone(zone).toInstant();
        System.out.println(instant1);
    }
}
