package model.prank;

import model.mail.Message;
import model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Prank class
 * @author Yosra Harbaoui
 * @author Iando Rafidimalala
 */

public class Prank {
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();
    private String message;

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public void addVictimRecipients(List<Person> victims) {
        victimRecipients.addAll(victims);
    }

    public List<Person> getVictimRecipients() {
        return new ArrayList<>(victimRecipients);
    }

    public void addWitnessRecipients(List<Person> witnesses) {
        witnessRecipients.addAll(witnesses);
    }

    public List<Person> getWitnessRecipients() {
        return new ArrayList<>(witnessRecipients);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message generateMessage() {
        Message msg = new Message();

        //if you want to have a signuture to your mail you can use this line
       msg.setBody(this.message + "\r\n" + victimSender.getFirstName() + " " + victimSender.getLastName());

        //otherwise, you can use this one
        //msg.setBody(this.message);

        String[] to = victimRecipients
                .stream()
                .map(Person::getAddress)
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setTo(to);

        String[] cc = witnessRecipients
                .stream()
                .map(Person::getAddress)
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setCc(cc);

        msg.setFrom(victimSender.getAddress());

        return msg;
    }
}