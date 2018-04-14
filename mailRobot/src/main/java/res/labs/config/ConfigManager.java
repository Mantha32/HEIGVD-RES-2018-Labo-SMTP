package res.labs.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Read the config.properties file and get the configuration that this app is needed
 * @author Iando Rafidimalala
 * @author Yosra Harbaoui
 */
public final class ConfigManager implements IConfigManager{
    
    public enum Setting
    {
        SmtpServerAddress,
        SmtpServerPort,
        NumberOfGroups,
        WitnessToCC;
    };    
    
    private Properties properties;
    
    public ConfigManager(File conf) throws IOException{
        load(conf);
    }
    
    
    @Override
    public void load(File conf) throws IOException {
        
        properties = new Properties();
        properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(conf), "UTF-8")));

    }
    
    /**
     * Get the property hold in config file by name
     * @param name property name
     * @return the property value
     */
    public String getProperty(Setting name){
        return properties.getProperty(name.name());
    }
    
    
    
}
