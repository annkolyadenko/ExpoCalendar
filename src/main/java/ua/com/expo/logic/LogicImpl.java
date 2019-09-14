package ua.com.expo.logic;

import java.math.BigDecimal;

public class LogicImpl implements ILogic{

    public BigDecimal totalValue(BigDecimal value, Long ticketAmount){
        return value.multiply(new BigDecimal(ticketAmount));

    }
}
