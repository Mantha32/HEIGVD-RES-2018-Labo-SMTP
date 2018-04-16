package smtp;

import model.mail.Message;

import java.io.IOException;

public interface ISmtpClient {
    void sendMessage(Message message) throws IOException;
}