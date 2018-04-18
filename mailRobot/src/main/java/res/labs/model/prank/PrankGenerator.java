
package res.labs.model.prank;

import res.labs.config.ConfigManager;

public class PrankGenerator {
    public static final int GROUP_SIZE_MIN = 3;
    private final ConfigManager confManager;
    
    public PrankGenerator(ConfigManager conf){
        confManager = conf;
    }
    
    public void generate(){}
}
