
package res.labs.mailrobot;

import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.EnumSet;
import res.labs.config.ConfigManager;

/**
 * This class provides the main() method for starting the application. It creates an
 * instance of SmtpClient and starts it (it will bind on the default smtp port)
 * 
 * @author Iando Rafidimalala
 * @author Yosra Harbaoui
 */
public class MailRobot {
    public static void main(String[] args) throws IOException{
        if (args.length <= 1){
            System.err.println("The directory path for config is missing as argument!");
            exit(0);
        }else{
            ConfigManager confManager = new ConfigManager(new File(args[1]));
            
            EnumSet<ConfigManager.Setting> settings = EnumSet.allOf(ConfigManager.Setting.class);
            
            settings.forEach((set) -> {
                System.out.println(set.name() + ": " + confManager.getProperty(set));
            });
        }
        
    }
}
