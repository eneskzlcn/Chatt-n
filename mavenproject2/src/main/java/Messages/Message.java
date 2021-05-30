package Messages;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//This class' purpose is providing a generic cominication object for us to
// send any serializable objects to the server.
public class Message implements java.io.Serializable {

    public static enum MessageTypes {
        USERNAME,CREATE_ROOM, ALL_ROOMS,USERNAME_REACHED
        ,ROOM_CREATED, IN_ROOM_MESSAGE, JOIN_ROOM, ALL_USERS ,CREATE_PERSONEL_ROOM, ACCEPT_PERSONEL_ROOM,
        PERSONEL_ROOM_REJECTED, PERSONEL_ROOM_ACCEPTED,PERSONEL_ROOM_MESSAGE
    };

    public MessageTypes type;
    public Object content;

    public Message(MessageTypes type) {
        this.type = type;
    }
}
