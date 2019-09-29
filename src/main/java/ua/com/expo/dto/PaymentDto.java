package ua.com.expo.dto;

import java.math.BigDecimal;

public class PaymentDto extends AbstractDto{

    private BigDecimal value;

    public PaymentDto(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
