package config;

import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The manager to configure the application : the list of victims, the list of messages to send and for what SMTP server
 * date is sent.
 * @author Yosra Harbaoui
 * @author Iando Rafidimalala
 */

public class ConfigurationManager implements IConfigurationManager {
    private String smtpServerAddress;
    private int smtpServerPort;
    private int numberOfGroups;
    private List<Person> witnessesToCC = new ArrayList<>();
    private final List<Person> victims = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();

    public ConfigurationManager() throws IOException {
        loadPropertiesFromFile("config.properties");
        loadAddressesFromFile("victims.txt");
        loadMessagesFromFile("messages.txt");
    }

    private void loadPropertiesFromFile(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(fileName);

        properties.load(inputStream);

        smtpServerAddress = properties.getProperty("smtpServerAddress");
        smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));

        for (String address : properties.getProperty("witnessesToCC").split(",")) {
            witnessesToCC.add(new Person(address));
        }

        inputStream.close();
    }

    private void loadAddressesFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

        String line;
        while ((line = reader.readLine()) != null) {
            victims.add(new Person(line));
        }

        reader.close();
    }

    private void loadMessagesFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

        String line;
        while ((line = reader.readLine()) != null) {
            StringBuilder body = new StringBuilder();
            while(line != null && !line.equals("===")) {
                body.append(line);
                body.append("\r\n");
                line = reader.readLine();
            }
            messages.add(body.toString());
        }

        reader.close();
    }

    @Override
    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    @Override
    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    @Override
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    @Override
    public List<Person> getWitnessesToCC() {
        return witnessesToCC;
    }

    @Override
    public List<Person> getVictims() {
        return victims;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
