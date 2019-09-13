package ua.com.expo.utils.time;

//TO DO!!!
//All static utils rewrite!!!
public interface IConverter <Instant, TimeStamp> {

    TimeStamp convertToDatabase(Instant instant);

    Instant convertToEntity(TimeStamp timeStamp);

}
