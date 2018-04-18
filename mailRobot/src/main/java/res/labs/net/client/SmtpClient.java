/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labs.net.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import res.labs.model.mail.Mail;
import res.labs.model.mail.Person;
import static res.labs.net.protocol.ServerResponseCode.*;
import res.labs.net.protocol.SmtpPartialProtocol;
import res.labs.utils.Utility;

/**
 *
 * @author fidimala
 */
public final class SmtpClient implements ISmtpClient{
    private Socket clientSocket;
    private final String serverSMTP;
    private final int port;
    private BufferedReader input;
    private PrintWriter output;
    
    
    public SmtpClient(String server, int port) throws IOException{
        serverSMTP = server;
        this.port = port;
        connect(server, port);
    }
    
  
    /**
     * get the code in the server response
     * We are looking for this pattern <XXX>[ ] message stands for xxx is the code.
     * @return integer code XXX
     * @throws IOException 
     */  
    private int getResponseServerCode() throws IOException{

        String response;

        do{
           response = input.readLine();

        }while(response.charAt(3) != ' ');


      return Utility.toInt(response.substring(0, 3));
    }

  /**
   * Send the SMTP command and a message with it
   * Check out if the command send successfully with the server response
   * @param cmd SMTP command
   * @param values message append to command
   */
    private boolean sendCommand(String cmd, String values) throws IOException{

        //Check the server connexion before the process    
        if (!isConnected()){
            throw new IOException("client is not connected");
        }

        StringBuilder sb = new StringBuilder(cmd);
        sb.append(" ");
        sb.append(values);
        output.println(sb.toString());
        output.flush();
        
       
        String serverResponseCode = Utility.read(input, " ");

        return CODE_REQUEST_OK == Utility.toInt(serverResponseCode);
    }
  
    /**
     * send SMTP command without message
     * @param cmd 
     */
    private boolean sendCommand(String cmd) throws IOException{
        return sendCommand(cmd, "");
    }
  

    @Override
    public void connect(String server, int port) throws IOException {
        if(!isConnected()){
            clientSocket = new Socket(server,port);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"UTF-8")));
            
            //Check the server response
            if(CODE_SERVICE_READY != getResponseServerCode() && !sendCommand(SmtpPartialProtocol.CMD_EHLO, "Prank sample")){
                throw new IOException("connection to SMTP is failed!");
            }  
        }
    }

    @Override
    public boolean isConnected() {

        if(clientSocket == null || clientSocket.isClosed())
            return false;

      return clientSocket.isConnected(); 
    }
  
  @Override
    public void disconnect() throws IOException {   
      if(isConnected() && sendCommand(SmtpPartialProtocol.CMD_QUIT)){
          output.flush();    
          input.close();
          output.close();
          clientSocket.close();    
      }

    }
    
    /**
     * Send mail according the SMTP protocol
     * @param mail
     * @throws IOException 
     */
    @Override
    public void sendMail(Mail mail) throws IOException{
        
        sendCommand(SmtpPartialProtocol.CMD_MAIL_FROM, mail.getFrom().getEmailAddress());
        
        for(Person pers: mail.getTo()){
            sendCommand(SmtpPartialProtocol.CMD_RCPT_TO, pers.getEmailAddress());
        }
        
        for (Person pers: mail.getCc()){
            sendCommand(SmtpPartialProtocol.CMD_RCPT_TO, pers.getEmailAddress());
        }

        for (Person pers: mail.getBcc()){
            sendCommand(SmtpPartialProtocol.CMD_RCPT_TO, pers.getEmailAddress());
        }
        
        
        String body = mail.toString();
        sendCommand(SmtpPartialProtocol.CMD_DATA);
        output.println(body);
        output.flush();
        sendCommand(Utility.DATA_DELIMITER);
               
    }


}
