
package Server;

import Messages.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;


/*
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//This is a TCP protocol connection based server.
public class Server {

    public ServerSocket socket;
    public int port;
    public ListenConnectionRequestThread listenConnectionRequestThread;

    public static ArrayList<SClient> clients;

    //keeps rooms on server.
    public static ArrayList<Room> rooms;
    
    //keeps every personel room.
    public static ArrayList<PersonelRoom> personelRooms;
    
    //keeps the uploaded files paths on server.
    public static ArrayList<ServerFile> uploadedFiles;

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
            System.exit(0);

        }
    }

    // starts the acceptance of connections.
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
    // sends given message to the room named as given roomName
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
    //give the username and retake it as SClient.
    public static SClient getClientByName(String userName) {
        for (SClient client : Server.clients) {
            if (client.userName.equals(userName)) {
                return client;
            }
        }
        return null;
    }
    //if a client lost connection or need to fire, this functions fires the client from server.
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
    //this functions get the personelroom pair of given user.
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
    //this funct. control is the file in the server.
    public static boolean isFileOnServerByGivenName(String fileName) {
        for (ServerFile sFile : Server.uploadedFiles) {
            if (sFile.name.equals(fileName)) {
                return true;
            }
        }
        return false;
    }
    //returns the path of the file on server by given file name.
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
    //if given client in  a personel room , returns it.
    public static PersonelRoom getPersonelRoomByClientName(String clientName)
    {
        for(PersonelRoom pr : Server.personelRooms)
        {
            if(pr.hasUser(clientName)) return pr;
        }
        return null;
    }
}
