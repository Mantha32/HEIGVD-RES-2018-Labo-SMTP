
import config.ConfigurationManager;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

import static java.lang.System.exit;

/**
 * The main class of the application
 * @author Yosra Harbaoui
 * @author Iando Rafidimalala
 */

public class Main {
    public static void main(String[] args) {
        ConfigurationManager configurationManager = null;
        SmtpClient smtpClient = null;
        try {
            configurationManager = new ConfigurationManager();
            smtpClient = new SmtpClient(configurationManager.getSmtpServerAddress(), configurationManager.getSmtpServerPort());
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }



        PrankGenerator prankGenerator = new PrankGenerator(configurationManager);

        List<Prank> pranks = prankGenerator.generatePranks();

        for (Prank prank : pranks) {
            try {
                smtpClient.sendMessage(prank.generateMessage());
            } catch (IOException e) {
                e.printStackTrace();
                exit(1);
            }
        }
    }
}