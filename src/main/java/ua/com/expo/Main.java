package ua.com.expo;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import ua.com.expo.dto.ExpoDto;
import ua.com.expo.dto.ShowroomDto;
import ua.com.expo.dto.ThemeDto;
import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


        Theme theme1 = new Theme();
        theme1.setId(21L);
        theme1.setName("Theme1");
        Theme theme2 = new Theme();
        theme2.setId(22L);
        theme2.setName("Theme2");
        Showroom showroom1 = new Showroom();
        showroom1.setId(21L);
        showroom1.setName("Showroom1");
        showroom1.setInfo("Showroom1 info");
        Showroom showroom2 = new Showroom();
        showroom2.setId(22L);
        showroom2.setName("Showroom2");
        showroom2.setInfo("Showroom2 info");
        Expo expo1 = new Expo.Builder().id(21L).showroom(showroom1).theme(theme1).date(Instant.now()).price(new BigDecimal(21)).info("Expo1").build();
        Expo expo2 = new Expo.Builder().id(22L).showroom(showroom2).theme(theme2).date(Instant.now()).price(new BigDecimal(22)).info("Expo2").build();
        ModelMapper modelMapper = new ModelMapper();
        ExpoDto expoDto1 = modelMapper.map(expo1, ExpoDto.class);
        System.out.println(expoDto1);
        List<Expo> expos = new ArrayList<>();
        expos.add(expo1);
        expos.add(expo2);
        Type listType = new TypeToken<List<ExpoDto>>() {}.getType();
        //FINAL VERSION
        List<ExpoDto> expoDtos = modelMapper.map(expos, new TypeToken<List<ExpoDto>>() {}.getType());
        for (ExpoDto expoDto : expoDtos) {
            System.out.println(expoDto);
        }
        Map<Expo, Long> map = new HashMap<>();
        map.put(expo1, 1L);
        map.put(expo2, 2L);
        Map<ExpoDto, Long> mapp = new HashMap<>();
                map.forEach((k,v)->mapp.put(modelMapper.map(k, ExpoDto.class),v));
        System.out.println(mapp);

    }
}

