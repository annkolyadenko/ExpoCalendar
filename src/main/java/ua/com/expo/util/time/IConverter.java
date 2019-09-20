package ua.com.expo.util.time;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

//TO DO!!!
//All static utils rewrite!!!
public interface IConverter <Instant, TimeStamp> {

    TimeStamp convertToDatabase(Instant instant);

    Instant convertToEntity(TimeStamp timeStamp);

    Timestamp convertStringToDatabase(String str);

    Instant convertLocalDateTimeToInstant(LocalDateTime localDateTime);

}
