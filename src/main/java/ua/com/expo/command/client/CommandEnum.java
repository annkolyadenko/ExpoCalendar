package ua.com.expo.command.client;

import ua.com.expo.command.*;

/**
 * Enum Singleton Command factory
 */
public enum CommandEnum {

    GET_ALL_THEMES(new GetAllThemesCommand()),
    GET_ALL_EXPO_BY_THEME_ID(new GetAllExpoCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}