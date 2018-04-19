package model.mail;
import java.util.regex.Pattern;

/**
 * The Person class to identify a victim
 * Note: If you are using a different format of e-mail address, you have to adapt this class to use the format you configure
 * @author Yosra Harbaoui
 * @author Iando Rafidimalala
 */


public class Person {
    private String firstName;
    private String lastName;
    private final String address;

    public Person(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Person(String address) {
        this.address = address;
        if (this.address.contains(".")) {
            String[] token = this.address.replace("@heig-vd.ch", "").split("\\.");
            this.firstName = token[0];
            this.lastName = token[1];
        } else {
            throw new IllegalArgumentException("String " + this.getAddress() + " does not contain .");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

}