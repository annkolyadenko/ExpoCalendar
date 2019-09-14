package ua.com.expo.util.time;

import java.sql.Timestamp;
import java.time.*;


/**
 * The TIMESTAMP data type is used for values that contain both date and time parts. TIMESTAMP has a range
 * of '1970-01-01 00:00:01' UTC to '2038-01-19 03:14:07' UTC.
 * <p>
 * MySQL converts TIMESTAMP values from the current time zone to UTC for storage, and back from UTC to the current
 * time zone for retrieval. (This does not occur for other types such as DATETIME.)
 * <p>
 * Both java.time.Instant and java.sql.Timestamp classes represent a point on the timeline in UTC. In other words,
 * they represent the number of nanoseconds since the Java epoch.
 */

public class TimeConverter implements IConverter<Instant, Timestamp> {

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
        return Timestamp.valueOf(str + " 00:00:00");
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

    }
}
