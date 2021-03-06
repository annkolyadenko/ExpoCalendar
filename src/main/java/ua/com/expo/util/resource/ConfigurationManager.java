package ua.com.expo.util.resource;

import ua.com.expo.file.Resources;

import java.util.ResourceBundle;

public enum ConfigurationManager {

    DB_MANAGER {
        {
            resourceBundle = ResourceBundle.getBundle(Resources.DB_PROPERTIES);
        }
    },
    PATH_MANAGER {
        {
            resourceBundle = ResourceBundle.getBundle(Resources.PATH_PROPERTIES);
        }
    },
    SQL_QUERY_MANAGER {
        {
            resourceBundle = ResourceBundle.getBundle(Resources.SQL_QUERIES);
        }
    };

    ResourceBundle resourceBundle;

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
