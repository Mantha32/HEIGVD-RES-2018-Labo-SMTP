package config;

import model.mail.Person;

import java.util.List;

/**
 * The Configuration Manager Interface
 * @author Yosra Harbaoui
 * @author Iando Rafidimalala
 */
public interface IConfigurationManager {
    String getSmtpServerAddress();
    int getSmtpServerPort();
    int getNumberOfGroups();
    List<Person> getWitnessesToCC();
    List<Person> getVictims();
    List<String> getMessages();
}
