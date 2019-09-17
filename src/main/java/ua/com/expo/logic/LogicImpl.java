package ua.com.expo.logic;

import ua.com.expo.entity.Theme;

import java.math.BigDecimal;
import java.util.List;

public class LogicImpl implements ILogic {

    public BigDecimal totalValue(BigDecimal value, Long ticketAmount) {
        return value.multiply(new BigDecimal(ticketAmount));
    }

    @Override
    public boolean ifExist(String theme, List<Theme> themes) {
        for (Theme th : themes) {
            if (th.getName().equals(theme))
                return true;
        }
        return false;
    }
}
