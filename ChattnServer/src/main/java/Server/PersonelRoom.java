
package Server;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//purpose of this room is chat with client-client.
public class PersonelRoom {
    
    
    public String creator;
    
    public String joiner;
    
    public PersonelRoom(String creator, String joiner) {
        this.creator = creator;
        this.joiner = joiner;
    }
    
    public boolean hasUser(String userName)
    {
        return ( creator.equals(userName) || joiner.equals(userName) ) ;
    }
}
