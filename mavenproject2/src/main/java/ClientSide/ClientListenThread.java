package ClientSide;

import Messages.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClientSide.*;
import GUI.*;
import java.util.ArrayList;
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

                    case CREATE_ROOM:
                       
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
