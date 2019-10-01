package ua.com.expo.dto;

import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Payment;
import ua.com.expo.entity.User;
import ua.com.expo.util.time.impl.DateConverter;
import ua.com.expo.util.time.impl.DateFormatter;

import java.math.BigDecimal;
import java.time.Instant;

public class TicketDto extends AbstractDto {
    private String expo;
    private String user;
    private BigDecimal payment;
    private String date;
    private Long amount;
    private String info;

    public TicketDto() {
    }

    public String getExpo() {
        return expo;
    }

    public void setExpo(Expo expo) {
        this.expo = expo.getInfo();
    }

    public String getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user.getName();
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment.getValue();
    }

    public String getDate() {
        return date;
    }

    public void setDate(Instant instant) {
        date = DateFormatter.getInstance().format(DateConverter.getInstance().convertInstantToLocalDateTime(instant));
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
