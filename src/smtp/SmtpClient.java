package smtp;

import model.mail.Message;

import java.io.*;
import java.net.Socket;

public class SmtpClient implements ISmtpClient {
    private String smtpServerAddress;
    private int smtpServerPort;

    private Socket socket;
    private DataOutputStream writer = null;
    private BufferedReader reader = null;

    public SmtpClient(String smtpServerAddress, int smtpServerPort) {
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        socket = new Socket(smtpServerAddress, smtpServerPort);

        writer = new DataOutputStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        String line = reader.readLine();

        writer.writeBytes("EHLO localhost\r\n");

        line = reader.readLine();

        while (line.startsWith("250-")) {
            line = reader.readLine();
        }

        writer.writeBytes("MAIL FROM:");
        writer.writeBytes(message.getFrom());
        writer.writeBytes("\r\n");
        writer.flush();

        for (String to : message.getTo()) {
            writer.writeBytes("RCPT TO:");
            writer.writeBytes(to);
            writer.writeBytes("\r\n");
            writer.flush();
        }

        for (String cc : message.getCc()) {
            writer.writeBytes("RCPT TO:");
            writer.writeBytes(cc);
            writer.writeBytes("\r\n");
            writer.flush();
        }

        writer.writeBytes("DATA");
        writer.writeBytes("\r\n");
        writer.flush();

        //writer.writeBytes("Content-Type: text-plain; charset=\"utf-8\"\r\n");
        writer.writeBytes("From:" + message.getFrom() + "\r\n");

        writer.writeBytes("To:" + message.getTo()[0]);
        for (int i = 1; i < message.getTo().length; ++i) {
            writer.writeBytes(", " + message.getTo()[i]);
        }
        writer.writeBytes("\r\n");

        writer.writeBytes("Cc:" + message.getCc()[0]);
        for (int i = 1; i < message.getCc().length; ++i) {
            writer.writeBytes(", " + message.getCc()[i]);
        }
        writer.writeBytes("\r\n");

        String subject = message.getSubject();
        if(subject != null && !subject.equals("")) {
            writer.writeBytes("Subject:" + subject);
            writer.writeBytes("\r\n");
            writer.writeBytes("\r\n");
        }

        writer.flush();

        writer.writeBytes(message.getBody());
        writer.writeBytes("\r\n");
        writer.writeBytes(".");
        writer.writeBytes("\r\n");
        writer.flush();

    //TODO CHECK WITH A TIMER TO AVOID INFINITE LOOP
        while ((line = reader.readLine()) != null) {
            // for MockMock and for smtp.heig-vd.ch
            // avoid closing too fast the connection
            if (line.contains("Ok") || line.toLowerCase().contains("queued")) {
                break;
            }
        }

        writer.writeBytes("QUIT\r\n");
        writer.flush();
        reader.close();
        writer.close();
        socket.close();
    }
}