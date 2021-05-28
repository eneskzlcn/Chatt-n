package ClientSide;

import Messages.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClientSide.*;
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
                    case USERNAME:
                        
                        break;
                    case PAIRING:
                       
                        break;
                    case MOVE:
               
                        break;
                    case CHECK:
                      
                        break;

                    case LEAVE:
                       
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
