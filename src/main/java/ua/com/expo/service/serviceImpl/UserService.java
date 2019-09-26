package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.UserDto;
import ua.com.expo.entity.Role;
import ua.com.expo.entity.User;
import ua.com.expo.entity.enums.RoleEnum;
import ua.com.expo.exception_draft.RuntimeServiceException;
import ua.com.expo.persistence.dao.IUserDao;
import ua.com.expo.util.security.IPasswordHashing;
import ua.com.expo.util.security.impl.PasswordHashingImpl;
import ua.com.expo.util.validator.IPasswordHashingValidator;
import ua.com.expo.util.validator.impl.PasswordHashingValidatorImpl;

import java.util.Objects;
import java.util.Optional;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());
    private static final ModelMapper modelMapper = new ModelMapper();

    private IUserDao userDao;

    public UserService() {
        userDao = Context.getInstance().getMySqlDaoFactory().getUserDao();
    }

    public UserDto signInUser(String email, String password) {
        IPasswordHashingValidator passwordHashingValidator = PasswordHashingValidatorImpl.getInstance();
        Optional<User> optionalUser = userDao.findUserByEmail(email);
        User user = optionalUser.orElseThrow(() -> new RuntimeServiceException("User with this email doesn't exist. Please, enter email again"));
        if (Objects.nonNull(user) && passwordHashingValidator.passwordValidate(password, user)) {
            LOGGER.debug("Mapper debug :" + convertToDto(user));
            return convertToDto(user);
        }
        throw new RuntimeServiceException("Password doesn't valid. Please, enter password again");
    }

    public UserDto signUpUser(String name, String email, String language, String password) {
        User user = null;
        Optional<User> optionalUser = userDao.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            throw new RuntimeServiceException("User with this email already registered! Please, choose SignIn form for authentication or use another email");
        } else {
            IPasswordHashing hashing = PasswordHashingImpl.getInstance();

            Role role = new Role.Builder().id(RoleEnum.VISITOR.getId()).role(RoleEnum.VISITOR.toString()).build();
            byte[] salt = hashing.saltGenerator();
            byte[] pass = hashing.hashGenerator(password, salt);
            user = new User.Builder().role(role).name(name).email(email).language(language).password(pass).salt(salt).build();
            Long id = userDao.saveUserWithGeneratedKey(user);
            if (Objects.nonNull(id)) {
                user.setId(id);
            } else {
                throw new RuntimeServiceException("Unfortunately, the internal error occurred during your SignUp action. Please, try again later");
            }
        }
        LOGGER.debug("Mapper debug :" + convertToDto(user));
        return convertToDto(user);
    }

    public boolean saveLang(Long userId, String language) {
        LOGGER.debug("saveLang");
        return userDao.saveLanguageByUserId(userId, language);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
