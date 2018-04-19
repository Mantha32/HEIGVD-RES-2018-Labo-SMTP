package model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * The Group class to identify the group of victims
 * @author Yosra Harbaoui
 * @author Iando Rafidimalala
 */
public class Group {
    private final List<Person> members = new ArrayList<>();

    public void addMember(Person person) {
        members.add(person);
    }

    public List<Person> getMembers() {
        return new ArrayList<>(members);
    }
}