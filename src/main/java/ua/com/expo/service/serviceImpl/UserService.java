package ua.com.expo.service.serviceImpl;

import org.modelmapper.ModelMapper;
import ua.com.expo.dto.UserDto;
import ua.com.expo.entity.Role;
import ua.com.expo.entity.User;
import ua.com.expo.entity.enums.RoleEnum;
import ua.com.expo.persistence.dao.interfaces.IUserDao;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.service.IUserService;
import ua.com.expo.util.security.IPasswordHashing;
import ua.com.expo.util.security.PasswordHashingImpl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Objects;

public class UserService implements IUserService {

    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static IPasswordHashing hashing = new PasswordHashingImpl();
    private static ModelMapper modelMapper = new ModelMapper();
    private IUserDao userDao;


    @Override
    public User findUserByEmail(String email) throws SQLException {
        userDao = factory.getUserDao();
        return userDao.findUserByEmail(email);
    }

    @Override
    public User signUpUser(String name, String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException, IOException, ClassNotFoundException {
        Role role = new Role.Builder().id(RoleEnum.VISITOR.getId()).role(RoleEnum.VISITOR.toString()).build();
        byte[] salt = hashing.saltGenerator();
        byte[] pass = hashing.hashGenerator(password, salt);
        User user = new User.Builder().role(role).name(name).email(email).password(pass).salt(salt).build();
        //TODO user id return
        Long id = userDao.createUserWithGeneratedKey(user);
        if (Objects.nonNull(id))
            user.setId(id);
         else {
            user = null;
        }
        return user;
    }

    private static UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private static User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
