package ua.com.expo.util.time;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

public interface IDateConverter<Instant, TimeStamp> {

    ZoneId zone = ZoneId.of("Europe/Kiev");

    TimeStamp convertToDatabase(Instant instant);

    Instant convertToEntity(TimeStamp timeStamp);

    Timestamp convertStringDateTimeToDatabase(String str);

    Timestamp convertStringDateToDatabase(String str);

    Instant convertLocalDateTimeToInstant(LocalDateTime localDateTime);

    LocalDateTime convertInstantToLocalDateTime(Instant instant);
}
