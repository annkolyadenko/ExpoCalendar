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
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.util.security.IPasswordHashing;
import ua.com.expo.util.security.impl.PasswordHashingImpl;

import java.util.Objects;
import java.util.Optional;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());
    private static final ModelMapper modelMapper = new ModelMapper();
    private final IPasswordHashing hashing;

    private IUserDao userDao;

    public UserService() {
        userDao = MySqlDaoFactory.getInstance().getUserDao();
        hashing = PasswordHashingImpl.getInstance();
    }

    public User findUserByEmail(String email) {
        Optional<User> optionalUser = userDao.findUserByEmail(email);
        return optionalUser.orElseThrow(() -> new RuntimeServiceException("User with this email doesn't exist. Please, enter email again"));
    }

    public User signUpUser(String name, String email, String language, String password) {
        User user = null;
        Role role = new Role.Builder().id(RoleEnum.VISITOR.getId()).role(RoleEnum.VISITOR.toString()).build();
        byte[] salt = hashing.saltGenerator();
        byte[] pass = hashing.hashGenerator(password, salt);
        user = new User.Builder().role(role).name(name).email(email).language(language).password(pass).salt(salt).build();
        Long id = userDao.saveUserWithGeneratedKey(user);
        if (Objects.nonNull(id))
            user.setId(id);
        return user;
    }

    public boolean updateLang(Long userId, String language) {
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
