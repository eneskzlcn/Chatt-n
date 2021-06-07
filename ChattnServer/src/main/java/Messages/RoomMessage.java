
package Messages;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

// this message is keeping all the needings for a message to understand it is sending to a room
// when a client want to send a message to persoenl or general room, this message works.
public class RoomMessage implements java.io.Serializable{
    
    public static enum RoomMessageType{
        TEXT, FILE , LEAVE
    };
    public String roomName;
    public String senderName;
    public RoomMessageType type;
    public Object content;
    
    public RoomMessage(String roomName, String senderName, RoomMessageType type, Object content) {
        this.roomName = roomName;
        this.senderName = senderName;
        this.type = type;
        this.content = content;
    }

}
