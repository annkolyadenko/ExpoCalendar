package ua.com.expo.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Payment extends Entity {
    private BigDecimal value;

    public Payment() {
    }

    public Payment(Long id, BigDecimal value) {
        super(id);
        this.value = value;
    }

    public Payment(Builder builder) {
        super(builder.id);
        this.value = builder.value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public static class Builder {
        private Long id;
        private BigDecimal value;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder value(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        return Objects.equals(value, payment.value);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    //TODO
    @Override
    public String toString() {
        return "Payment{" +
                "value=" + value + '}';
    }
}
