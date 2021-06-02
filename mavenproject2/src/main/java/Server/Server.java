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

    public static ArrayList<PersonelRoom> personelRooms;

    public static ArrayList<ServerFile> uploadedFiles;
    //lock mechanism for pairing thread. One client can match with one client at the same time. So we use the lock mechanism to provide
    //other clients not try to pair this client at the same time.

    public Server(int port) {
        try {
            this.port = port;
            this.socket = new ServerSocket(this.port);
            this.listenConnectionRequestThread = new ListenConnectionRequestThread(this);
            this.clients = new ArrayList<SClient>();
            this.rooms = new ArrayList<Room>();
            this.personelRooms = new ArrayList<PersonelRoom>();
            this.uploadedFiles = new ArrayList<ServerFile>();
        } catch (IOException ex) {
            System.out.println("There is an error occured when opening the server on port:" + this.port);

        }
    }

    // starts the acceptance
    public void ListenClientConnectionRequests() {
        this.listenConnectionRequestThread.start();
    }

    public static Room getRoomByName(String roomName) {
        for (Room room : Server.rooms) {
            if (room.name.equals(roomName)) {
                return room;
            }
        }

        return null;
    }

    public static void SendMessageToGivenRoom(String roomName, Message message) {
        Room chosenRoom = getRoomByName(roomName);
        if (chosenRoom == null) {
            return;
        }

        for (Room room : Server.rooms) {
            for (String userName : room.userNames) {
                SClient clientHasThisName = getClientByName(userName);
                if (clientHasThisName == null) {
                    continue;
                }
                clientHasThisName.Send(message);
            }
        }
    }

    public static SClient getClientByName(String userName) {
        for (SClient client : Server.clients) {
            if (client.userName.equals(userName)) {
                return client;
            }
        }
        return null;
    }

    public static void FireClient(SClient client) {
        for (Room room : Server.rooms) {
            if (room.hasUser(client.userName)) {
                room.removeUser(client.userName);
                break;
            }
        }
        for (PersonelRoom pRoom : Server.personelRooms) {
            if (pRoom.hasUser(client.userName)) {
                Server.personelRooms.remove(pRoom);
                break;
            }
        }
        Server.clients.remove(client);
    }

    public static SClient getPersonelPairByClientName(String personelRoomUser) {
        for (PersonelRoom p : Server.personelRooms) {
            if (p.creator.equals(personelRoomUser)) {
                return getClientByName(p.joiner);
            } else if (p.joiner.equals(personelRoomUser)) {
                return getClientByName(p.creator);
            }
        }
        return null;
    }
    
    public static boolean isFileOnServerByGivenName(String fileName) {
        for (ServerFile sFile : Server.uploadedFiles) {
            if (sFile.name.equals(fileName)) {
                return true;
            }
        }
        return false;
    }
    public static String getFilePathByName(String fileName)
    {
        for(ServerFile sFile : Server.uploadedFiles)
        {
            if(sFile.name.equals(fileName))
            {
                return sFile.path;
            }
        }
        return null;
    }
    public static PersonelRoom getPersonelRoomByClientName(String clientName)
    {
        for(PersonelRoom pr : Server.personelRooms)
        {
            if(pr.hasUser(clientName)) return pr;
        }
        return null;
    }
}
