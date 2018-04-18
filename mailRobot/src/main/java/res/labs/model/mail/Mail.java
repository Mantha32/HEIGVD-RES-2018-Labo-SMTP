
package res.labs.model.mail;

import res.labs.utils.Utility;

/**
 * Mail represent mail model with 
 * person who send a message
 * person who receive the message
 * and the message itself (subject and main message)
 * 
 * @author Iando Rafidimalala
 * @author Yosra Harbaoui
 */
public class Mail { 
    private final Person from;
    private final Group to;
    private final Group cc;
    private final Group bcc;    
    private final String data;

    private void addFrom(StringBuilder sb, String property, Person pers){
        if(sb != null && !property.isEmpty() && pers != null){
            sb.append(property);
            sb.append(pers);
            sb.append(Utility.COMMAND_DELIMITER);
        }  
    }
    
    private void addLineInHeader(StringBuilder sb, String property, Group people){
        if(sb != null && !property.isEmpty() && !people.isEmpty()){
            sb.append(property);
            
            //Add first person 
            sb.append(people.get(0));
            
            for (int i = 1; i < people.size(); i++){
                sb.append(", ");
                sb.append(people.get(i));
            }
            
            sb.append(Utility.COMMAND_DELIMITER);   
        }
    }
    
    public Mail (Person from, Group to, Group cc, Group bcc, String data)
    {
        this.from = from;
        this.to   = to;
        this.cc   = cc;
        this.bcc  = bcc;
        this.data = data;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        addFrom(sb, "From: ", from);
        
        addLineInHeader(sb, "To: ", to);
        addLineInHeader(sb, "Cc: ", cc);
        
        sb.append(data);
        
        
        sb.append(from.getFirstName());
        sb.append(" ");
        sb.append(from.getLastName());
        sb.append(Utility.COMMAND_DELIMITER);
        
        return sb.toString();    
    }
    
    
    public Person getFrom ()
    {
        return from;
    }

    public Group getTo ()
    {
        return to;
    }

    public Group getCc ()
    {
        return cc;
    }

    public Group getBcc ()
    {
        return bcc;
    }

    public String getData ()
    {
        return data;
}
    
}
