
package res.labs.model.mail;

/**
 * This class represent the person model with name and email address 
 * @author Iando Rafidimalala
 * @author Yosra Harbaoui
 */
public class Person {
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    
    public Person(String firstN, String lastN, String email){
        this.firstName = firstN;
        this.lastName = lastN;
        this.emailAddress = email;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getEmailAddress(){
        return emailAddress;
    }
    
    @Override
    //fitting to recipient or sender format
    public String toString(){        
        return firstName + " " + lastName + " <" + emailAddress + ">";
    }
}
