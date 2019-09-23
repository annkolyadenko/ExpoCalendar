package ua.com.expo.util.time;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class TimeConverter implements IConverter<Instant, Timestamp> {

    private static final Logger LOGGER = LogManager.getLogger(TimeConverter.class.getName());

    ZoneId zone = ZoneId.of("Europe/Kiev");

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
    public Timestamp convertStringDateToDatabase(String str) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return new Timestamp(date.getTime());
    }

    @Override
    public Instant convertLocalDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(zone).toInstant();
    }

    public static void main(String[] args) {

        TimeConverter converter = new TimeConverter();
        Timestamp timestamp = converter.convertStringDateTimeToDatabase("2019-09-13");
        System.out.println(timestamp);

        ZoneId zone = ZoneId.of("Europe/Kiev");
        Instant instant = Instant.now();
        System.out.println(instant);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
        System.out.println(dateTime);
        Instant instant1 = dateTime.atZone(zone).toInstant();
        System.out.println(instant1);

        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.SEPTEMBER, 21, 9, 0);
        Instant instant2 = localDateTime.atZone(zone).toInstant();
        LocalDateTime localDateTime3 = LocalDateTime.now();
        Instant instant3 = localDateTime3.atZone(zone).toInstant();
        System.out.println(instant3);
        Timestamp timestamp1 = converter.convertToDatabase(instant3);
        System.out.println(timestamp1);
    }
}
