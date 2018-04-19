package model.mail;

import protocol.SmtpPartialProtocol;
import utilities.Utility;
/**
 * The Message class to define the features of a message to be sent.
 * @author Yosra Harbaoui
 * @author Iando Rafidimalala
 */

public class Message {
    private String from = "";
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];
    private String subject;
    private String body;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private void addLine(StringBuilder sb, String property, String[] people){
        if((sb != null) && !property.isEmpty() && (people.length != 0)){
            sb.append(property);

            //Add first person
            sb.append(people[0]);

            for (int i = 1; i < people.length; i++){
                sb.append(", ");
                sb.append(people[i]);
            }

            sb.append(Utility.COMMAND_DELIMITER);

        }

    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("From:");
        sb.append(from);
        sb.append(Utility.COMMAND_DELIMITER);

        addLine(sb, "To:", to);
        addLine(sb,"Cc:", cc);

        if(subject != null && !subject.equals("")) {
            sb.append("Subject:");
            sb.append(subject);
            sb.append(Utility.COMMAND_DELIMITER);
            sb.append(Utility.COMMAND_DELIMITER);
        }

        sb.append(body);
        sb.append(Utility.DATA_DELIMITER);

        return sb.toString();

    }
}