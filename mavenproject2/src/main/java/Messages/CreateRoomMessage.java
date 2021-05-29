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
public class CreateRoomMessage implements java.io.Serializable {
    
    
    public String creatorName;
    
    public String roomName;

    public CreateRoomMessage(String creatorName, String roomName)
    {
        this.creatorName = creatorName;
        this.roomName = roomName;
    }
}
