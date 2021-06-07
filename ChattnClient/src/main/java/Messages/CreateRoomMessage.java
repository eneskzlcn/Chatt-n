
package Messages;

/**
 
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//This message contains creators name and name of Room.
//This is sending to server when a clients want to create a room
public class CreateRoomMessage implements java.io.Serializable {
    
    
    public String creatorName;
    
    public String roomName;

    public CreateRoomMessage(String creatorName, String roomName)
    {
        this.creatorName = creatorName;
        this.roomName = roomName;
    }
}
