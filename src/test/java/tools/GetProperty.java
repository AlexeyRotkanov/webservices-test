package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {

    private static final String PROPERTIES_PATH = "src/test/resources/config.properties";

    public static String getValueByKey(String propertyKey) {

        String propertyValue = "";

        try {
            FileInputStream inputStream = new FileInputStream(PROPERTIES_PATH);
            Properties properties = new Properties();
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyKey);
        } catch (FileNotFoundException e) {
            System.out.println("config.properties file was not found! Check the file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("config.properties file was not loaded! Check the file");
            e.printStackTrace();
        }

        return propertyValue;
    }
}
