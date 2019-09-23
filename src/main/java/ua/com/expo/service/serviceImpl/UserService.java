package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import ua.com.expo.dto.UserDto;
import ua.com.expo.entity.Role;
import ua.com.expo.entity.User;
import ua.com.expo.entity.enums.RoleEnum;
import ua.com.expo.exception_draft.RuntimeServiceException;
import ua.com.expo.persistence.dao.IUserDao;
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
import java.util.Optional;

public class UserService implements IUserService {

    //TODO HOW TO WIRE DAOFACTORY
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());
    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static IPasswordHashing hashing = new PasswordHashingImpl();
    private static ModelMapper modelMapper = new ModelMapper();
    private IUserDao userDao;


    //TODO DTO
    @Override
    public User findUserByEmail(String email) throws SQLException {
        userDao = factory.getUserDao();
        Optional<User> user = userDao.findUserByEmail(email);
        User us = user.orElseThrow(() -> new RuntimeException("Can't find user by email"));
        return us;
    }

    @Override
    public User signUpUser(String name, String email, String language, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException, IOException, ClassNotFoundException {
        Role role = new Role.Builder().id(RoleEnum.VISITOR.getId()).role(RoleEnum.VISITOR.toString()).build();
        byte[] salt = hashing.saltGenerator();
        byte[] pass = hashing.hashGenerator(password, salt);
        User user = new User.Builder().role(role).name(name).email(email).language(language).password(pass).salt(salt).build();
        //TODO user id return
        Long id = userDao.saveUserWithGeneratedKey(user);
        if (Objects.nonNull(id))
            user.setId(id);
        else {
            user = null;
        }
        return user;
    }

    @Override
    public boolean updateLang(Long userId, String language) throws SQLException, IOException, ClassNotFoundException {
        return userDao.updateLanguageByUserId(userId, language);
    }

    //TODO MAPPERS
    private static UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private static User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
