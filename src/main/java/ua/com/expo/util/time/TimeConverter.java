package ua.com.expo.util.time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class TimeConverter implements IConverter<Instant, Timestamp> {

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
    public Timestamp convertStringToDatabase(String str) {
        return Timestamp.valueOf(LocalDateTime.parse(str));
    }


    @Override
    public Timestamp convertStringDateToDatabase(String str) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //TODO STUB
        Date date = null;
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    @Override
    public Instant convertLocalDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(zone).toInstant();
    }

    public static void main(String[] args) {

        TimeConverter converter = new TimeConverter();
        Timestamp timestamp = converter.convertStringToDatabase("2019-09-13");
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
