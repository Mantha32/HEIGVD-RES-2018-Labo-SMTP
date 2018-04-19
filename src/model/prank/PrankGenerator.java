package model.prank;

import config.IConfigurationManager;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class to generate the Prank
 * @author Yosra Harbaoui
 * @author Iando Rafidimalala
 */

public class PrankGenerator {
    private IConfigurationManager configurationManager;

    public PrankGenerator(IConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    public List<Prank> generatePranks() {
        List<Prank> pranks = new ArrayList<>();

        List<String> messages = configurationManager.getMessages();
        int numberOfGroups = configurationManager.getNumberOfGroups();
        int numberOfVictims = configurationManager.getVictims().size();

        if (numberOfVictims / numberOfGroups < 3) {
            numberOfGroups = numberOfVictims / 3;
            //TODO WARNING
        }

        List<Group> groups = generateGroups(configurationManager.getVictims(), numberOfGroups);

        int messageIndex = 0;

        for (Group group : groups) {
            Prank prank = new Prank();

            List<Person> victims = group.getMembers();
            Collections.shuffle(victims);
            Person sender = victims.remove(0);

            prank.setVictimSender(sender);
            prank.addVictimRecipients(victims);
            prank.addWitnessRecipients(configurationManager.getWitnessesToCC());
            prank.setMessage(messages.get(messageIndex));

            messageIndex = (messageIndex + 1) % messages.size();

            pranks.add(prank);
        }

        return pranks;
    }

    private List<Group> generateGroups(List<Person> victims, int numberOfGroups) {
        //TODO EXACTEMENT COMMME DANS LA VIDEO
        List<Person> availableVictims = new ArrayList(victims);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; ++i) {
            groups.add(new Group());
        }
        int turn = 0;
        Group targetGroup;
        while (availableVictims.size() > 0) {
            targetGroup = groups.get(turn);
            targetGroup.addMember(availableVictims.remove(0));
            turn = (turn + 1) % groups.size();
        }
        return groups;
    }
}