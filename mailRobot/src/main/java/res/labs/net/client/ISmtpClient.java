
package res.labs.net.client;

import java.io.IOException;
import res.labs.model.mail.Mail;

public interface ISmtpClient {
    
  /**
   * Establishes a connection with the server, given its IP address or DNS name
   * and its port.
   *
   * @param server the IP address or DNS name of the servr
   * @param port the TCP port on which the server is listening
   * @throws java.io.IOException
   */
  public void connect(String server, int port) throws IOException;

  /**
   * Disconnects from the server by issuing the 'BYE' command.
   *
   * @throws IOException
   */
  public void disconnect() throws IOException;
  
  /**
   * Checks if the client is connected with the server
   *
   * @return true if the client is connected with the server
   */
  public boolean isConnected();

  
  /**
   * Send an email to the SMTP server 
   * @param mail
   * @throws IOException if sending mail on network failed
   */
  public void sendMail(Mail mail) throws IOException;
  
}
