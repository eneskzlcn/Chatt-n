package ClientSide;

import Messages.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClientSide.*;
import GUI.*;
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
                          this.client.applicationFrame.inRoomMenu.AddMessageToChat(roomMsg.senderName, roomMsg.content);

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
                }

            } catch (IOException ex) {
                Logger.getLogger(ClientListenThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("Girilen class bulunamadı");
            }
        }
    }
}
