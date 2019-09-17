package ua.com.expo.logic;

import ua.com.expo.entity.Theme;

import java.math.BigDecimal;
import java.util.List;

public interface ILogic {
    BigDecimal totalValue(BigDecimal value, Long ticketAmount);
    boolean ifExist(String theme, List<Theme> themes);
}
