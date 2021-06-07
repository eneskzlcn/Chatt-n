
package Messages;

/**
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//this message keeps the creator and joiner of a personel room.
//this message sending to the server when a clients want to create a personel room with another client.
public class CreatePersonelRoomMessage implements java.io.Serializable{
    
    public String creator;
    
    public String joiner;
}
