package ua.com.expo.command.client;

import ua.com.expo.command.*;
import ua.com.expo.command.commandImpl.*;

/**
 * Enum Singleton Command factory
 */
public enum CommandEnum {

    PURCHASE_TICKET(new PurchaseTicketCommand()),
    GET_ALL_THEMES(new GetAllThemesCommand()),
    GET_ALL_EXPO_BY_THEME_ID_AND_DATE(new GetAllExpoCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    RETURN_TO_HOMEPAGE(new ReturnToHomePageCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}