package res.labs.model.prank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * group of message
 * Create a message list from a file
 * @author Iando Rafidimalala
 * @author Yosra Harbaoui
 */
public final class Prank extends ArrayList<String>{
    public Prank (){
        super();
    }
    
    public Prank (InputStream stream, String separator) throws IOException{
        this();
        add(stream, separator);
    }    
    
    // Add some messages from stream

    public void add(InputStream stream, String messageSeparator) throws UnsupportedEncodingException, IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine())!= null){
            if(line.equals(messageSeparator)){
                add(sb.toString());
                sb = new StringBuilder();
            }else{
                sb.append(line);
            }
        }
    }

}
