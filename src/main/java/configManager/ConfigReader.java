package configManager;

import frameworkException.CRMException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ConfigReader {

    public static Properties readConfigFile(){
        Properties prop;
        FileInputStream ip;

        try{
            ip = new FileInputStream(Objects.requireNonNull(ConfigReader.class.getClassLoader().getResource("config.properties")).getFile());
            prop = new Properties();
            prop.load(ip);
        }catch(FileNotFoundException e){
            throw new CRMException("File Not Found");
        }catch (IOException e){
            throw new CRMException("IO Exception occured");
        }

        return prop;
    }
}
