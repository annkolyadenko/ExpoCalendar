package ua.com.expo.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Payment extends Entity {
    private BigDecimal value;
    private String type;
    private String status;

    public Payment() {
    }

    public Payment(Long id, BigDecimal value, String type, String status) {
        super(id);
        this.value = value;
        this.type = type;
        this.status = status;
    }

    public Payment(Builder builder) {
        super(builder.id);
        this.value = builder.value;
        this.type = builder.type;
        this.status = builder.status;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Builder {
        private Long id;
        private BigDecimal value;
        private String type;
        private String status;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder value(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
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

        if (!Objects.equals(value, payment.value)) return false;
        if (!Objects.equals(type, payment.type)) return false;
        return Objects.equals(status, payment.status);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    //TODO
    @Override
    public String toString() {
        return "Payment{" +
                "value=" + value +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
