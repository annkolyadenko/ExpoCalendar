package ua.com.expo;

import ua.com.expo.entity.Expo;
import ua.com.expo.persistence.dao.daoImpl.MySqlTicketDao;
import ua.com.expo.persistence.dao.daoImpl.MySqlUserDao;
import ua.com.expo.util.time.Formatter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, ParseException {
        /*String date = "2016-02-16";
        ZoneId zone = ZoneId.of("Europe/Kiev");
        LocalDate localDate = LocalDate.parse(date);
        System.out.println(localDate);*/
        /*Instant instant1 = localDate.atZone(zone).toInstant();
        System.out.println(instant1);*/
        /*Instant instant = Instant.now();
        Instant instant2 = Instant.from(localDate);
        System.out.println(instant2+"localDate");
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TemporalAccessor ta = formatter.parse(date);
        System.out.println(ta);*/


        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = "2016-02-16 11:00:02";
        TemporalAccessor temporalAccessor = formatter.parse(timestamp);
        LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Instant result = Instant.from(zonedDateTime);
        System.out.println(result);*/
        /*String time = "2019-11-02T09:00";*/

        /*LocalDateTime localDateTime = LocalDateTime.parse(date);
        System.out.println(localDateTime);

        LocalDateTime ldt = LocalDateTime.parse((date+" "+time),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(ldt);*/

        String date = "2019-10-05";
        String time = "12:00";

        String dat = "2019-10-05T20:00";


        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dat);
        System.out.println(dateTime);
        Timestamp ts = Timestamp.valueOf(dateTime);
        System.out.println(ts);


       /* System.out.println(formattedDateTime+"FORMATTED");
        LocalDateTime dateTime = LocalDateTime.parse(dat, format);
        System.out.println(dateTime);*/

    }
}

