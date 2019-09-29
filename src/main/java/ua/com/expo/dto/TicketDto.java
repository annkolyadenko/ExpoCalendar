package ua.com.expo.dto;

import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Payment;
import ua.com.expo.entity.User;

import java.time.LocalDateTime;

public class TicketDto extends AbstractDto{
    private Expo expo;
    private User user;
    private Payment payment;
    private LocalDateTime date;
    private Long amount;
    private String info;

    public TicketDto() {
    }

    public Expo getExpo() {
        return expo;
    }

    public void setExpo(Expo expo) {
        this.expo = expo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
