package by.iba.boot_learning.properties;

import by.iba.boot_learning.properties.exception.PropertyException;
import java.util.ResourceBundle;

public class PropertiesHandler {

    public static String getProperty(String key, String bundleName) {
        ResourceBundle resBundle;
        try {
            resBundle = ResourceBundle.getBundle(bundleName);
        } catch (Exception ex) {
            throw new PropertyException("Error while try to get access to properties. ");
        }
        return resBundle.getString(key);
    }
}
