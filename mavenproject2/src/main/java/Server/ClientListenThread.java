/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import Messages.Message;
import Messages.CreateRoomMessage;
import Messages.JoinRoomMessage;
import Messages.RoomMessage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
// The purpose of this thread is listening data incoming to the sclients input stream. After a data come this thread determines what will
// going to do with this data and then recontinue to the listening the input stream. This listening never ends until the sclient connection
// is lost...
public class ClientListenThread extends Thread {

    SClient client;

    public ClientListenThread(SClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!this.client.socket.isClosed()) {

            try {
                Message msg = (Message) (this.client.cInput.readObject());
                switch (msg.type) {
                    case USERNAME:
                        String userName = String.valueOf(msg.content);
                        client.userName = userName;
                        System.out.println("Username:"+client.userName);
                        Message userNameReceivedMsg = new Message(Message.MessageTypes.USERNAME_REACHED);
                        userNameReceivedMsg.content = client.userName;
                        client.Send(userNameReceivedMsg);
                        break;
                    case CREATE_ROOM:
                        CreateRoomMessage crm = (CreateRoomMessage)(msg.content);
                        Room newRoom = new Room(crm.roomName,crm.creatorName);
                        System.out.println("New room created. Room Name: "+crm.roomName+" , Creator: "+crm.creatorName);
                        Server.rooms.add(newRoom);
                        Message roomCreatedMsg = new Message(Message.MessageTypes.ROOM_CREATED);
                        roomCreatedMsg.content = crm.roomName;
                        this.client.Send(roomCreatedMsg);
                        break;
                    case ALL_ROOMS:
                        ArrayList<String> roomNames = new ArrayList<String>();
                        for(Room room: Server.rooms)
                        {
                            roomNames.add(room.name);
                        }
                        Message allRoomsMsg = new Message(Message.MessageTypes.ALL_ROOMS);
                        allRoomsMsg.content = roomNames;
                        this.client.Send(allRoomsMsg);
                        break;
                    case IN_ROOM_MESSAGE:
                        RoomMessage roomMessage = (RoomMessage)msg.content;
                        if(roomMessage.type != RoomMessage.RoomMessageType.TEXT)return;
                        Server.SendMessageToGivenRoom(roomMessage.roomName, msg);                       
                        break;
                    
                    case JOIN_ROOM:
                        System.out.println("JOIN ROOM MSG CAME");
                        JoinRoomMessage jrm = (JoinRoomMessage)msg.content;
                        Room roomToBeJoined = Server.getRoomByName(jrm.roomName);
                        
                        Message message = new Message(Message.MessageTypes.JOIN_ROOM);
                        if(roomToBeJoined.addAndValidateUser(jrm.joinerUserName))
                        {
                            message.content = jrm.roomName;
                        }
                        else
                        {
                            message.content = "NO";
                        }
                        this.client.Send(message);
                        
                        break;

                }
            } catch (IOException ex) {
                Logger.getLogger(ClientListenThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientListenThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
