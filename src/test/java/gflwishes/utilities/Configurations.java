package gflwishes.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public interface Configurations {


    Properties configProp = new Properties();

    String currentDateTime = new SimpleDateFormat("MMMddyyyy_hhmmss").format(new Date());

    String TEST_DATA_LOCATION = getProjectDir() + "\\src\\test\\java\\gfl\\testData";

    String configurationPath = getProjectDir() + "/Configuration/config.properties";

    String END_TO_END = "EndToEnd";
    
    String Prospect = "Prospect";
    
    String BASE_URL = getProperty("wishesURL");
    String FM_URL = getProperty("fleetMapperURL");

    String USER_NAME = getProperty("username");
    String PASSWORD = getProperty("password");

    String BROWSER = getProperty("browser");
    String PROJECT_DIR = getProjectDir();

    static String getProjectDir() {
        return System.getProperty("user.dir");
    }

    static String getProperty(String key) {
        InputStream input = null;
        try {
            input = new FileInputStream(configurationPath);
            configProp.load(input);
        } catch (Exception e) {
            System.out.println("Error occurred while reading property file.  -  " + configurationPath);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return configProp.getProperty(key);
    }

}
