package ua.com.expo.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Expo extends Entity {

    private Showroom showroom;
    private Theme theme;
    private Instant date;
    private BigDecimal price;
    private String info;

    public Expo() {
    }

    public Expo(Showroom showroom, Theme theme, Instant date, BigDecimal price, String info) {
        this.showroom = showroom;
        this.theme = theme;
        this.date = date;
        this.price = price;
        this.info = info;
    }

    public Expo(Builder builder) {
        super(builder.id);
        this.showroom = builder.showroom;
        this.theme = builder.theme;
        this.date = builder.date;
        this.price = builder.price;
        this.info = builder.info;
    }

    public Showroom getShowroom() {
        return showroom;
    }

    public void setShowroom(Showroom showroom) {
        this.showroom = showroom;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static class Builder {
        private Long id;
        private Showroom showroom;
        private Theme theme;
        private Instant date;
        //TO DO!
        /*this.expoTicketPrice.setScale(3, RoundingMode.HALF_UP);*/
        private BigDecimal price;
        private String info;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder showroom(Showroom showroom) {
            this.showroom = showroom;
            return this;
        }

        public Builder theme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public Builder date(Instant date) {
            this.date = date;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Expo build() {
            return new Expo(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expo)) return false;

        Expo expo = (Expo) o;

        if (!Objects.equals(showroom, expo.showroom)) return false;
        if (!Objects.equals(theme, expo.theme)) return false;
        if (!Objects.equals(date, expo.date)) return false;
        if (!Objects.equals(price, expo.price)) return false;
        return Objects.equals(info, expo.info);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (showroom != null ? showroom.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Expo: ");
        sb.append(", id: " + this.getId());
        sb.append(", showroom: " + showroom);
        sb.append(", theme: " + theme);
        sb.append(", date: " + date);
        sb.append(", price: " + price);
        sb.append(", info: " + info);
        return sb.toString();
    }
}
