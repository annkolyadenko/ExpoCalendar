package ua.com.expo.util.time;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.*;


public class TimeConverter implements IConverter<Instant, Timestamp> {

    private static final ZoneId zone = ZoneId.of("Europe/Kiev");
    private static final Logger LOGGER = LogManager.getLogger(TimeConverter.class.getName());

    private TimeConverter() {
    }

    private static class Holder {
        static final TimeConverter INSTANCE = new TimeConverter();
    }

    public static TimeConverter getInstance() {
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
