package ClientSide;

import Messages.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClientSide.*;
import GUI.*;
import Messages.CreatePersonelRoomMessage;
import Messages.RoomMessage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/*
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
 */

 /* 9
 
  @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
//The purpose of this thread is listening the server continiously if there is a message incoming to our input stream.
// İf there is a message, then decide what will be happen.
public class ClientListenThread extends Thread {

    Client client;

    public ClientListenThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!this.client.socket.isClosed()) {

            try {

                Message msg = (Message) (this.client.sInput.readObject());
                switch (msg.type) {
                    
                    case USERNAME_REACHED:
                        System.out.println("Username reached.");
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.loginMenu,
                                 this.client.applicationFrame.mainMenu);
                        this.client.applicationFrame.mainMenu.mainMenuHeader.setText("Welcome "+msg.content.toString());
                        
                        break;
                    case ALL_ROOMS:
                        ArrayList<String> roomNames = (ArrayList<String>)msg.content;
                        this.client.applicationFrame.roomsMenu.UpdateRoomListWithGivenList(roomNames);
                        break;

                    case ROOM_CREATED:
                        String roomName = msg.content.toString();
                        this.client.applicationFrame.inRoomMenu.inRoomNameLBL.setText(roomName);
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.roomsMenu, 
                                this.client.applicationFrame.inRoomMenu);
                        
                        break;
                    case IN_ROOM_MESSAGE:
                        RoomMessage roomMsg = (RoomMessage)msg.content;
                        if(roomMsg.type == RoomMessage.RoomMessageType.TEXT)
                        {
                          this.client.applicationFrame.inRoomMenu.AddMessageToChat(roomMsg.senderName, roomMsg.content.toString());

                        }
                        break;
                    case JOIN_ROOM:
                        String roomToBeJoined = msg.content.toString();
                        if(roomToBeJoined.equals("No"))
                        {
                            JOptionPane.showMessageDialog(this.client.applicationFrame.roomsMenu,"Can not connected to the room" );
                            return;
                        }
                        this.client.applicationFrame.inRoomMenu.inRoomNameLBL.setText(roomToBeJoined);
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.roomsMenu,
                            this.client.applicationFrame.inRoomMenu);
                        break;
                    case ALL_USERS:
                        ArrayList<String> users = (ArrayList<String>)msg.content;
                        this.client.applicationFrame.allUsersMenu.UpdateUserListWithGivenList(users);
                        break;
                        
                    case ACCEPT_PERSONEL_ROOM:
                        CreatePersonelRoomMessage cprm = (CreatePersonelRoomMessage)msg.content;
                        int inputDialogue = JOptionPane.showConfirmDialog(null, "Do you want to chat with "+cprm.creator+" ?", 
                                "Personel Room Request", JOptionPane.YES_NO_OPTION);
                        String userAnswer;
                        if(inputDialogue == 0)
                        {
                            userAnswer = cprm.joiner;
                        }
                        else
                        {
                            userAnswer = "NO";
                        }
                        cprm.joiner = userAnswer;
                        
                        Message personelRoomAnswerMessage = new Message(Message.MessageTypes.ACCEPT_PERSONEL_ROOM);
                        personelRoomAnswerMessage.content = cprm;
                        this.client.Send(personelRoomAnswerMessage);
                        break;
                    case PERSONEL_ROOM_REJECTED:
                        JOptionPane.showMessageDialog(this.client.applicationFrame.allUsersMenu, "Personel Chat Rejected");
                        break;
                        
                    case PERSONEL_ROOM_ACCEPTED:
                        String userToChat = (String)msg.content;
                        this.client.applicationFrame.personelRoomMenu.personelRoomHeaderLBL.setText("Chat With "+userToChat);
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.allUsersMenu,
                                this.client.applicationFrame.personelRoomMenu);
                        
                        break;
                    case PERSONEL_ROOM_MESSAGE:
                        RoomMessage rm = (RoomMessage)msg.content;
                        if(rm.type == RoomMessage.RoomMessageType.TEXT)
                        {
                           this.client.applicationFrame.personelRoomMenu.AddTextMessageToChat(rm.senderName,rm.content.toString());
                           break;
                        }
                       break;
                }

            } catch (IOException ex) {
                Logger.getLogger(ClientListenThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("Girilen class bulunamadı");
            }
        }
    }
}
