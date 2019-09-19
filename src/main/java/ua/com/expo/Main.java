package ua.com.expo;

import ua.com.expo.util.time.Formatter;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;


public class Main {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, ParseException {
        String date = "2016-02-16";
        ZoneId zone = ZoneId.of("Europe/Kiev");
        LocalDate localDate = LocalDate.parse(date);
        System.out.println(localDate);
        /*Instant instant1 = localDate.atZone(zone).toInstant();
        System.out.println(instant1);*/
        Instant instant = Instant.now();
        Instant instant2 = Instant.from(localDate);
        System.out.println(instant2+"localDate");
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TemporalAccessor ta = formatter.parse(date);
        System.out.println(ta);


        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = "2016-02-16 11:00:02";
        TemporalAccessor temporalAccessor = formatter.parse(timestamp);
        LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Instant result = Instant.from(zonedDateTime);
        System.out.println(result);*/

    }
}

