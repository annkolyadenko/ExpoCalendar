package ua.com.expo.persistence.dao.mappers;

import ua.com.expo.entity.Role;
import ua.com.expo.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TO DO BY ENUM Factory
 */
public class UserMapper implements EntityMapper<User> {

    private static volatile UserMapper instance = new UserMapper();

    public static UserMapper getInstance() {
        UserMapper localInstance = instance;
        if (localInstance == null) {
            synchronized (UserMapper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserMapper();
                }
            }
        }
        return localInstance;
    }

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .id(rs.getLong("user_id"))
                .role(new Role(rs.getLong("role_id"), rs.getString("role_name")))
                .name(rs.getString("user_name"))
                .email(rs.getString("user_email"))
                .password(rs.getBytes("user_password"))
                .salt(rs.getBytes("user_salt"))
                .build();
    }
}
