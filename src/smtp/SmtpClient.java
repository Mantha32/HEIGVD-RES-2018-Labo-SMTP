package smtp;

import model.mail.Message;
import protocol.*;
import utilities.Utility;

import java.io.*;
import java.net.Socket;

public class SmtpClient implements ISmtpClient {
    private String smtpServerAddress;
    private int smtpServerPort;

    private Socket socket;
    private DataOutputStream writer = null;
    private BufferedReader reader = null;

    public SmtpClient(String smtpServerAddress, int smtpServerPort) throws IOException {
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;

        connect(smtpServerAddress, smtpServerPort);
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
            response = reader.readLine();

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

        writer.writeBytes(cmd);
        writer.writeBytes(" ");
        writer.writeBytes(values);
        writer.writeBytes(Utility.COMMAND_DELIMITER);
        writer.flush();

        return ServerResponseCode.CODE_REQUEST_OK == getResponseServerCode();
    }

    /**
     * send SMTP command without message
     * @param cmd
     */
    private boolean sendCommand(String cmd) throws IOException{
        return sendCommand(cmd, "");
    }

    @Override
    public void connect(String smtpServerAddress, int smtpServerPort) throws IOException {
        socket = new Socket(smtpServerAddress, smtpServerPort);

        writer = new DataOutputStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

    }
    @Override
    public boolean isConnected() {

        if(socket == null || socket.isClosed())
            return false;

        return socket.isConnected();
    }

    @Override
    public void disconnect() throws IOException {
        if(isConnected() && sendCommand(SmtpPartialProtocol.CMD_QUIT)){
            writer.flush();
            reader.close();
            writer.close();
            socket.close();
        }

    }

    @Override
    public void sendMessage(Message message) throws IOException {


        String line = reader.readLine();

        //sendCommand(SmtpPartialProtocol.CMD_EHLO, "pranck");
        writer.writeBytes("EHLO Pranck\r\n");

        line = reader.readLine();

        while (line.startsWith("250-")) {
            line = reader.readLine();
        }

        sendCommand(SmtpPartialProtocol.CMD_MAIL_FROM, message.getFrom());

        for (String to : message.getTo()) {
            sendCommand(SmtpPartialProtocol.CMD_RCPT_TO, to);
        }

        for (String cc : message.getCc()) {
            sendCommand(SmtpPartialProtocol.CMD_RCPT_TO, cc);
        }

        sendCommand(SmtpPartialProtocol.CMD_DATA);


        writer.writeBytes(message.toString());
        writer.flush();

    //TODO CHECK WITH A TIMER TO AVOID INFINITE LOOP
        while ((line = reader.readLine()) != null) {
            // for MockMock and for smtp.heig-vd.ch
            // avoid closing too fast the connection
            if (line.contains("Ok") || line.toLowerCase().contains("queued")) {
                break;
            }
        }

        disconnect();
    }
}