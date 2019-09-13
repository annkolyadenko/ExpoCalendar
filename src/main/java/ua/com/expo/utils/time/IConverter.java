package ua.com.expo.utils.time;

import java.sql.Timestamp;

//TO DO!!!
//All static utils rewrite!!!
public interface IConverter <Instant, TimeStamp> {

    TimeStamp convertToDatabase(Instant instant);

    Instant convertToEntity(TimeStamp timeStamp);

    Timestamp convertStringToDatabase(String str);

}
