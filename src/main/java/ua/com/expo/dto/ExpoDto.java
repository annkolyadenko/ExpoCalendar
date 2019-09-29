package ua.com.expo.dto;

import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.util.time.impl.DateConverter;
import ua.com.expo.util.time.impl.DateFormatter;

import java.math.BigDecimal;
import java.time.Instant;

public class ExpoDto extends AbstractDto {
    private String showroom;
    private String theme;
    private String date;
    private String price;
    private String info;

    public String getShowroom() {
        return showroom;
    }

    public void setShowroom(Showroom showroom) {
        this.showroom = showroom.getName();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme.getName();
    }

    public String getDate() {
        return date;
    }

    public void setDate(Instant instant) {
        date = DateFormatter.getInstance().format(DateConverter.getInstance().convertInstantToLocalDateTime(instant));
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.toString();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ExpoDto{" +
                "id=" + this.getId() +
                "showroom=" + showroom +
                ", theme=" + theme +
                ", date=" + date +
                ", price=" + price +
                ", info='" + info + '\'' +
                '}';
    }
}
