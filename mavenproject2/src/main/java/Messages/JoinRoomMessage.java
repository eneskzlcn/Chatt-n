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
public class JoinRoomMessage implements java.io.Serializable{
    
    public String joinerUserName;
    
    public String roomName;

    public JoinRoomMessage(String joinerUserName, String roomName) {
        this.joinerUserName = joinerUserName;
        this.roomName = roomName;
    }
    
    
}
