package smtp;

import model.mail.Message;

import java.io.IOException;

public interface ISmtpClient {
    void sendMessage(Message message) throws IOException;

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
}