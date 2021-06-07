
package Messages;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//this message works when a client want to join a room.
// client sends this message to server and server joins it to the room or not.

public class JoinRoomMessage implements java.io.Serializable{
    
    public String joinerUserName;
    
    public String roomName;

    public JoinRoomMessage(String joinerUserName, String roomName) {
        this.joinerUserName = joinerUserName;
        this.roomName = roomName;
    }
    
    
}
