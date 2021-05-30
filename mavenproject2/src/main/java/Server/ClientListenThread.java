/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Messages.CreatePersonelRoomMessage;
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
                    case ALL_USERS:
                        ArrayList<String> users = new ArrayList<String>();
                        for(SClient client : Server.clients)
                        {
                            users.add(client.userName);
                        }
                        Message allUsersMessage = new Message(Message.MessageTypes.ALL_USERS);
                        allUsersMessage.content = users;
                        this.client.Send(allUsersMessage);
                        break;
                    case CREATE_PERSONEL_ROOM:
                        CreatePersonelRoomMessage cprm = (CreatePersonelRoomMessage)msg.content;
                        SClient joiner = Server.getClientByName(cprm.joiner);
                        Message acceptPersonelRoomMessage = new Message(Message.MessageTypes.ACCEPT_PERSONEL_ROOM);
                        acceptPersonelRoomMessage.content = cprm;
                        joiner.Send(acceptPersonelRoomMessage);
                        
                    case ACCEPT_PERSONEL_ROOM:
                        CreatePersonelRoomMessage answerMessage = (CreatePersonelRoomMessage)msg.content;
                        if(answerMessage.joiner.equals("NO"))
                        {
                            Message rejectedPersonelRoomMessage = new Message(Message.MessageTypes.PERSONEL_ROOM_REJECTED);
                            SClient rejectedUser = Server.getClientByName(answerMessage.creator);
                            rejectedUser.Send(rejectedPersonelRoomMessage);
                        }
                        else
                        {
                            SClient creatorUser = Server.getClientByName(answerMessage.creator);
                            SClient joinerUser = Server.getClientByName(answerMessage.joiner);
                            PersonelRoom pr = new PersonelRoom(answerMessage.creator,answerMessage.joiner);
                            Server.personelRooms.add(pr);
                            Message acceptedPersonelRoomMessage = new Message(Message.MessageTypes.PERSONEL_ROOM_ACCEPTED);
                            acceptedPersonelRoomMessage.content = answerMessage.joiner;
                            creatorUser.Send(acceptedPersonelRoomMessage);
                            acceptedPersonelRoomMessage.content =answerMessage.creator;
                            joinerUser.Send(acceptedPersonelRoomMessage);
                        }
                        break;
                    case PERSONEL_ROOM_MESSAGE:
                        RoomMessage personelRoomMsg = (RoomMessage)msg.content;
                        SClient userWillReceieveMessage = Server.getPersonelPairByClientName(personelRoomMsg.senderName);
                        SClient userWhoSendMessage = Server.getClientByName(personelRoomMsg.senderName);
                        userWillReceieveMessage.Send(msg);
                        userWhoSendMessage.Send(msg);
                        
                }
            } catch (IOException ex) {
                Server.FireClient(this.client);
            } catch (ClassNotFoundException ex) {
                Server.FireClient(this.client);
            } 
        }
    }
}
