package res.labs.model.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this group have a list of victim and one of them is the sender
 * @author Iando Rafidimalala
 * @author Yosra Harbaoui
 */
public class Group extends ArrayList<Person>{
    
    public Group(List<Person> people){
        super();
        addAll(people);
    }
    
    public Group (Person... people)
    {
        this(Arrays.asList(people));
    }
    
    /**
     * Construct group according the content of the file based on 'firstname[separator]lastname[separator]email' format
     * 
     * @param stream the stream that need to parse
     * @param fieldSeparator separator that is used to separate each field in the line 
     * @throws IOException if the reading file is failed
     */
    public Group (InputStream stream, char fieldSeparator)throws IOException{
            BufferedReader br  = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null){
                int indexLastName = line.indexOf(fieldSeparator);
                int indexEmail = line.indexOf(fieldSeparator, indexLastName + 1);
                add(new Person(line.substring(0, indexLastName), line.substring(indexLastName + 1, indexEmail), line.substring(indexEmail + 1)));
            }
    }
    
    /**
     * make up a subgroup
     * @param startIndex the index of first person in the subgroup
     * @param endIndex the index of the last excluded person
     * @return subgroup
     */
    public ArrayList<Person> getSubGroup(int startIndex, int endIndex){
        return new Group(subList(startIndex,endIndex));
    }
    
}
