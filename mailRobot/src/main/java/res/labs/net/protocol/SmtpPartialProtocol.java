
package res.labs.net.protocol;

/**
 * This class defines constants for the partial smtp Protocol 
 * SMTP command description: 
 * HELO/HELO: identifies the sender (the client using IP or FQDN domain name) to the SMTP server. This command
 * serves to identify itself and initiate the SMTP conversation
 * MAIL FROM: specifies the e-mail address for the sender
 * RCPT TO:  specifies the email address for the recipient
 * 
 * @author Iando Rafidimalala
 * @author Yosra Harboui
 */
public class SmtpPartialProtocol {
    
  public final static int DEFAULT_PORT = 2525; 

  public final static String CMD_EHLO = "EHLO";
  public final static String CMD_HELO = "HELO";
  public final static String CMD_MAIL_FROM = "MAIL FROM";
  public final static String CMD_RCPT_TO = "RCPT TO";
  public final static String CMD_DATA = "DATA";
  public final static String CMD_ENDOFDATA_MARKER = "ENDOFDATA";
  public final static String CMD_RESET = "RSET";
  public final static String CMD_QUIT = "QUIT";
  
}
