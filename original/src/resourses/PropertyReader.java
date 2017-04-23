package src.resourses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by c11epm on 9/4/14.
 */
public class PropertyReader {
    private static PropertyReader prop = null;
    private Properties messageprop = null;
    private Properties registryProperty = null;

    public static PropertyReader getInstance(){
        if(prop == null){
            return new PropertyReader();
        }
        return prop;
    }

    public String readMessageProperties(String file, String type) throws IOException {
        if(messageprop == null){
            messageprop = new Properties();
            InputStream is;
            if(file == null || type == null){
                Logger logger = LogManager.getLogger(PropertyReader.class.getName());
                logger.error("Error in readProperties. Needs filename and attributes");
                return null;
            }
            is = getClass().getClassLoader().getResourceAsStream(file);
            messageprop.load(is);
        }
        return messageprop.getProperty(type);
    }

    public String readRegistryProperties(String file, String attribute) throws IOException {
        if(registryProperty == null){
            registryProperty = new Properties();
            InputStream is;
            if(file == null || attribute == null){
                Logger logger = LogManager.getLogger(PropertyReader.class.getName());
                logger.error("Error in readProperties. Needs filename and attributes");
                return null;
            }
            is = getClass().getClassLoader().getResourceAsStream(file);
            registryProperty.load(is);

        }
        return registryProperty.getProperty(attribute);
    }
}
