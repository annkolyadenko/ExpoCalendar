package ua.com.expo.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Ticket extends Entity {
    private Expo expo;
    private User user;
    private Payment payment;
    private Instant time;
    private Long amount;
    private String info;

    public Ticket() {
    }

    public Ticket(Long id, Expo expo, User user, Payment payment, Instant time, Long amount, String info) {
        super(id);
        this.expo = expo;
        this.user = user;
        this.payment = payment;
        this.time = time;
        this.amount = amount;
        this.info = info;
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

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
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

    public Ticket(Builder builder) {
        super(builder.id);
        this.expo = builder.expo;
        this.user = builder.user;
        this.payment = builder.payment;
        this.time = builder.time;
        this.amount = builder.amount;
        this.info = builder.info;
    }

    public static class Builder {
        private Long id;
        private Expo expo;
        private User user;
        private Payment payment;
        private Instant time;
        private Long amount;
        private String info;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder expo(Expo expo) {
            this.expo = expo;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder payment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder time(Instant time) {
            this.time = time;
            return this;
        }

        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (!Objects.equals(expo, ticket.expo)) return false;
        if (!Objects.equals(user, ticket.user)) return false;
        if (!Objects.equals(payment, ticket.payment)) return false;
        if (!Objects.equals(time, ticket.time)) return false;
        if (!Objects.equals(amount, ticket.amount)) return false;
        return Objects.equals(info, ticket.info);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (expo != null ? expo.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    //TODO
    @Override
    public String toString() {
        return "Ticket{" +
                "expo=" + expo +
                ", user=" + user +
                ", payment=" + payment +
                ", time=" + time +
                ", amount=" + amount +
                ", info='" + info + '\'' +
                '}';
    }
}
