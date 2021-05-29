/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class RoomMessage implements java.io.Serializable{
    
    public static enum RoomMessageType{
        TEXT, VIDEO, DOCUMENT
    };
    public String roomName;
    public String senderName;
    public RoomMessageType type;
    public String content;
    
    public RoomMessage(String roomName, String senderName, RoomMessageType type, String content) {
        this.roomName = roomName;
        this.senderName = senderName;
        this.type = type;
        this.content = content;
    }

}
