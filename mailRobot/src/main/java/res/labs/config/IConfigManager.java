package res.labs.config;

import java.io.File;
import java.io.IOException;

/**
 * Loading the configuration 
 * 
 * @author Iando Rafidimalalar
 * @author Yosra Harbaoui
 */
public interface IConfigManager {
     /**
     * Parse file and load properties that are needed
     *
     * @param conf file to parse
     * @throws IOException if reading file failed
     */
    void load (File conf) throws IOException;
    
}
