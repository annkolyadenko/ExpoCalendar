package ua.com.expo.persistence.dao.mapper;

import ua.com.expo.entity.*;
import ua.com.expo.exception_draft.RuntimeSqlException;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.util.time.impl.DateConverter;


public enum Mapper implements EntityMapper<Entity> {

    USER {
        @Override
        public User extractFromResultSet(ResultSet rs) {
            try {
                return new User.Builder()
                        .id(rs.getLong("user_id"))
                        .role(rs.getString("user_role"))
                        .name(rs.getString("user_name"))
                        .email(rs.getString("user_email"))
                        .language(rs.getString("user_lang"))
                        .password(rs.getBytes("user_password"))
                        .salt(rs.getBytes("user_salt"))
                        .build();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeSqlException(e);
            }
        }
    },
    EXPO {
        @Override
        public Expo extractFromResultSet(ResultSet rs) {
            try {
                return new Expo.Builder()
                        .id(rs.getLong("expo_id"))
                        .showroom((Showroom) SHOWROOM.extractFromResultSet(rs))
                        .theme((Theme) THEME.extractFromResultSet(rs))
                        .date(timeConverter.convertToEntity(rs.getTimestamp("expo_date")))
                        .price(rs.getBigDecimal("expo_ticket_price"))
                        .info(rs.getString("expo_info"))
                        .build();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeSqlException(e);
            }
        }
    },
    SHOWROOM {
        @Override
        public Showroom extractFromResultSet(ResultSet rs) {
            try {
                return new Showroom.Builder()
                        .id(rs.getLong("showroom_id"))
                        .name(rs.getString("showroom_name"))
                        .info(rs.getString("showroom_info"))
                        .build();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeSqlException(e);
            }
        }
    },
    THEME {
        @Override
        public Theme extractFromResultSet(ResultSet rs) {
            try {
                return new Theme.Builder()
                        .id(rs.getLong("theme_id"))
                        .name(rs.getString("theme_name"))
                        .build();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeSqlException(e);
            }
        }
    },
    PAYMENT {
        @Override
        public Entity extractFromResultSet(ResultSet rs) {
            try {
                return new Payment.Builder()
                        .id(rs.getLong("payment_id"))
                        .value(rs.getBigDecimal("payment_value"))
                        .build();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeSqlException(e);
            }
        }
    },
    TICKET {
        @Override
        public Ticket extractFromResultSet(ResultSet rs) {
            try {
                return new Ticket.Builder()
                        .id(rs.getLong("ticket_id"))
                        .expo((Expo) EXPO.extractFromResultSet(rs))
                        .user((User) USER.extractFromResultSet(rs))
                        .payment((Payment) PAYMENT.extractFromResultSet(rs))
                        .date(timeConverter.convertToEntity(rs.getTimestamp("ticket_date_time")))
                        .amount(rs.getLong("ticket_amount"))
                        .info(rs.getString("ticket_info"))
                        .build();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeSqlException(e);
            }
        }
    };

    private static final Logger LOGGER = LogManager.getLogger(Mapper.class.getName());
    private static final DateConverter timeConverter = DateConverter.getInstance();

}
