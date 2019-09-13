package ua.com.expo.services.servicesImpl;

import org.modelmapper.ModelMapper;
import ua.com.expo.dto.RoleDto;
import ua.com.expo.dto.UserDto;
import ua.com.expo.entity.User;
import ua.com.expo.persistence.dao.interfaces.IUserDao;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.services.IUserService;

import java.sql.SQLException;

public class UserService implements IUserService {

    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static ModelMapper modelMapper = new ModelMapper();
    private IUserDao dao;


    @Override
    public User findUserByEmail(String email) throws SQLException {
        dao = factory.getUserDao();
        return dao.findUserByEmail(email);
    }

    private static UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private static User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static void main(String[] args) {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setRole(new RoleDto(1L, "visitor"));
        userDto.setName("Superman");
        userDto.setEmail("superman@gmail.com");
        User user = convertToEntity(userDto);
        /*System.out.println(user);*/
        System.out.println(convertToDto(user));
    }
}
