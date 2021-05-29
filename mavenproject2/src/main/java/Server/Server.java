/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Messages.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//This is a TCP protocol connection based server.
public class Server {

    public ServerSocket socket;
    public int port;
    public ListenConnectionRequestThread listenConnectionRequestThread;

    public static ArrayList<SClient> clients;
    
    public static ArrayList<Room> rooms;

    //lock mechanism for pairing thread. One client can match with one client at the same time. So we use the lock mechanism to provide
    //other clients not try to pair this client at the same time.
    public static Semaphore pairingLockForTwoPair = new Semaphore(1, true);

    public Server(int port) {
        try {
            this.port = port;
            this.socket = new ServerSocket(this.port);
            this.listenConnectionRequestThread = new ListenConnectionRequestThread(this);
            this.clients = new ArrayList<SClient>();
            this.rooms = new ArrayList<Room>();
            
        } catch (IOException ex) {
            System.out.println("There is an error occured when opening the server on port:" + this.port);

        }
    }
    // starts the acceptance
    public void ListenClientConnectionRequests() {
        this.listenConnectionRequestThread.start();
    }
    
    public static Room getRoomByName(String roomName)
    {
        for(Room room: Server.rooms)
        {
            if(room.name.equals(roomName))
            {
                return room;
            }
        }
        
       return null;
    }
    
    public static void SendMessageToGivenRoom(String roomName,Message message)
    {
        Room chosenRoom = getRoomByName(roomName);
        if(chosenRoom == null) return;
        
        for(Room room: Server.rooms)
        {
            for(String userName : room.userNames)
            {
                SClient clientHasThisName = getClientByName(userName);
                if(clientHasThisName == null) continue;
                clientHasThisName.Send(message);
            }
        }
    }
    
    public static SClient getClientByName(String userName)
    {
        for(SClient client : Server.clients)
        {
            if(client.userName == userName) return client;
        }
        return null;
    }
}
