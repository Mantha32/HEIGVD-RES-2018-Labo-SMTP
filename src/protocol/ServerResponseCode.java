package protocol;

/**
 * This class expose the main message that the server sends to the client.
 * This server response code is useful to handle the client action.
 * The server response code meaning
 * 2xx:The server has completed the task successfully.
 * 3xx: The server has understood the request, but requires further information to complete it.
 * 4xx: The server has encountered a temporary failure
 * 5xx:The server has encountered an error.
 *
 * @author Iando Rafidimalala
 * @author Yosra Harbaoui
 */
public class ServerResponseCode {


    public final static int CODE_TOPIC = 214;
    public final static int CODE_SERVICE_READY = 220;
    public final static int CODE_REQUEST_OK = 250;
    public final static int CODE_TIME_OUT = 421;
    public final static int CODE_START_MSG = 354;
    public final static int CODE_MAIL_BOX_UNVAILABLE = 550;
    public final static int CODE_RECIPIENT_NOT_EXIST = 553;
    public final static int CODE_TRANSACION_FAILED = 554;
}
