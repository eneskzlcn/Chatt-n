
package Server;

import Messages.CreatePersonelRoomMessage;
import java.io.IOException;
import Messages.Message;
import Messages.CreateRoomMessage;
import Messages.FileMessage;
import Messages.JoinRoomMessage;
import Messages.RoomMessage;
import Utilities.FileUtilities;
import java.util.ArrayList;


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
                        //when a clients sends its username, this case works. Save the name as clients name.
                        String userName = String.valueOf(msg.content);
                        client.userName = userName;
                        System.out.println("Username:" + client.userName);
                        Message userNameReceivedMsg = new Message(Message.MessageTypes.USERNAME_REACHED);
                        userNameReceivedMsg.content = client.userName;
                        client.Send(userNameReceivedMsg);
                        break;
                    case CREATE_ROOM:
                        //when a clients sends a craeting new room request, this case works.
                        //if can, creates the room and gives room created feedback to the client.
                        CreateRoomMessage crm = (CreateRoomMessage) (msg.content);
                        Room newRoom = new Room(crm.roomName, crm.creatorName);
                        System.out.println("New room created. Room Name: " + crm.roomName + " , Creator: " + crm.creatorName);
                        Server.rooms.add(newRoom);
                        Message roomCreatedMsg = new Message(Message.MessageTypes.ROOM_CREATED);
                        roomCreatedMsg.content = crm.roomName;
                        this.client.Send(roomCreatedMsg);
                        break;
                    case ALL_ROOMS:
                        //when user wants to take all current rooms in server the requst came here.
                        //creating a list has only names of server rooms and send it.
                        ArrayList<String> roomNames = new ArrayList<String>();
                        for (Room room : Server.rooms) {
                            roomNames.add(room.name);
                        }
                        Message allRoomsMsg = new Message(Message.MessageTypes.ALL_ROOMS);
                        allRoomsMsg.content = roomNames;
                        this.client.Send(allRoomsMsg);
                        break;
                    case IN_ROOM_MESSAGE:
                        //when a client send an in room message it came here.
                        // the message sending every client on this room.
                        RoomMessage roomMessage = (RoomMessage) msg.content;
                        if (roomMessage.type == RoomMessage.RoomMessageType.TEXT) {
                            Server.SendMessageToGivenRoom(roomMessage.roomName, msg);
                        } else if (roomMessage.type == RoomMessage.RoomMessageType.LEAVE) {
                            Server.getRoomByName(roomMessage.roomName).removeUser(roomMessage.senderName);
                            Message leaveRoomMsg = new Message(Message.MessageTypes.LEFT_FROM_ROOM);
                            this.client.Send(leaveRoomMsg);
                        }
                        break;

                    case JOIN_ROOM:
                        //when a client want to join, the request of join came here.
                        //If possible, provide client joining the room, and give feedback as you joined or not.
                        JoinRoomMessage jrm = (JoinRoomMessage) msg.content;
                        Room roomToBeJoined = Server.getRoomByName(jrm.roomName);

                        Message message = new Message(Message.MessageTypes.JOIN_ROOM);
                        if (roomToBeJoined.addAndValidateUser(jrm.joinerUserName)) {
                            message.content = jrm.roomName;
                        } else {
                            message.content = "NO";
                        }
                        this.client.Send(message);

                        break;
                    case ALL_USERS:
                        //when a clients want to see all users on server the request came here.
                        //name of every client fills into a list and then server sends it to the client.
                        ArrayList<String> users = new ArrayList<String>();
                        for (SClient client : Server.clients) {
                            users.add(client.userName);
                        }
                        Message allUsersMessage = new Message(Message.MessageTypes.ALL_USERS);
                        allUsersMessage.content = users;
                        this.client.Send(allUsersMessage);
                        break;
                    case CREATE_PERSONEL_ROOM:
                        //when a client wants to create a personel room, the request came here.
                        //Server sends a acceptance message to the user which this client want to chat,
                        //to provide the other client wants this chat.
                        CreatePersonelRoomMessage cprm = (CreatePersonelRoomMessage) msg.content;
                        SClient joiner = Server.getClientByName(cprm.joiner);
                        Message acceptPersonelRoomMessage = new Message(Message.MessageTypes.ACCEPT_PERSONEL_ROOM);
                        acceptPersonelRoomMessage.content = cprm;
                        joiner.Send(acceptPersonelRoomMessage);

                    case ACCEPT_PERSONEL_ROOM:
                        //if a client answers a personel room acceptance request, it comes here.
                        //if it is no, a rejectance message send to rejected client.
                        //if it is yes, personel room accepted message sending both of the client as feedback.
                        CreatePersonelRoomMessage answerMessage = (CreatePersonelRoomMessage) msg.content;
                        if (answerMessage.joiner.equals("NO")) {
                            Message rejectedPersonelRoomMessage = new Message(Message.MessageTypes.PERSONEL_ROOM_REJECTED);
                            SClient rejectedUser = Server.getClientByName(answerMessage.creator);
                            rejectedUser.Send(rejectedPersonelRoomMessage); 
                        } else {
                            SClient creatorUser = Server.getClientByName(answerMessage.creator);
                            SClient joinerUser = Server.getClientByName(answerMessage.joiner);
                            PersonelRoom pr = new PersonelRoom(answerMessage.creator, answerMessage.joiner);
                            Server.personelRooms.add(pr);
                            Message acceptedPersonelRoomMessage = new Message(Message.MessageTypes.PERSONEL_ROOM_ACCEPTED);
                            acceptedPersonelRoomMessage.content = answerMessage.joiner;
                            creatorUser.Send(acceptedPersonelRoomMessage);
                            acceptedPersonelRoomMessage.content = answerMessage.creator;
                            joinerUser.Send(acceptedPersonelRoomMessage);
                        }
                        break;
                    case PERSONEL_ROOM_MESSAGE:
                        //if a user sends personel room message , it comes here.
                        //this case founds the pair of the client on this personel room
                        //and sends it this message.
                        //if it is a leave message, leave both of them and close the personel room.
                        RoomMessage personelRoomMsg = (RoomMessage) msg.content;
                        if(personelRoomMsg.type == RoomMessage.RoomMessageType.LEAVE)
                        {
                            SClient userWillReceieveMessage = Server.getPersonelPairByClientName(personelRoomMsg.senderName);
                            SClient userWhoSendMessage = Server.getClientByName(personelRoomMsg.senderName);
                            Server.personelRooms.remove(Server.getPersonelRoomByClientName(personelRoomMsg.senderName));
                            Message dispersePersonelRoomMsg = new Message(Message.MessageTypes.PERSONEL_ROOM_DISPERSED);
                            userWillReceieveMessage.Send(dispersePersonelRoomMsg);
                            userWhoSendMessage.Send(dispersePersonelRoomMsg);
                        }
                        SClient userWillReceieveMessage = Server.getPersonelPairByClientName(personelRoomMsg.senderName);
                        SClient userWhoSendMessage = Server.getClientByName(personelRoomMsg.senderName);
                        userWillReceieveMessage.Send(msg);
                        userWhoSendMessage.Send(msg);
                        break;

                    case UPLOAD_FILE_REQUEST:
                        //if a clients want to upload a file to the server, it comes here.
                        //if the uploading op. done successfully sending yes message
                        //if not, sending no message.
                        RoomMessage uploadedRoomMessage = (RoomMessage) msg.content;                       
                        FileMessage uploadedFileMessage = (FileMessage) uploadedRoomMessage.content;    
                        String pathOfFile = uploadedFileMessage.fileName;
                        if (FileUtilities.writeContentToFileOnGivenFilePath(pathOfFile, uploadedFileMessage.content)) {
                            ServerFile sf = new ServerFile(uploadedRoomMessage.roomName, uploadedFileMessage.fileName, pathOfFile);
                            Server.uploadedFiles.add(sf);
                            Message uploadedMessage = new Message(Message.MessageTypes.FILE_UPLOADED);
                            uploadedMessage.content = "YES";
                            this.client.Send(uploadedMessage);
                        } else {
                            Message uploadedMessage = new Message(Message.MessageTypes.FILE_UPLOADED);
                            uploadedMessage.content = "NO";
                            this.client.Send(uploadedMessage);
                        }
                        //uploading operations finishes here. and sending feedback operations adn room messages comes.
                        //after uploading a file,defining if this file coming from a personel room or a general room.
                        //if it coming from personel room, send a message to its pair like "xxx shared you a file..."
                        //if it is coming from a room,send message to everyone in this room lik "xxx shared you a file..."
                        //but not send the file directly to the rooms until any clients want to download.
                        RoomMessage roomMsg = new RoomMessage(uploadedRoomMessage.roomName,
                                 uploadedRoomMessage.senderName, RoomMessage.RoomMessageType.FILE, uploadedFileMessage.fileName);

                        if (uploadedRoomMessage.roomName.equals("personel")) {
                            Message uploadedPersonelMessage = new Message(Message.MessageTypes.PERSONEL_ROOM_MESSAGE);
                            uploadedPersonelMessage.content = roomMsg;
                            SClient chatPair = Server.getPersonelPairByClientName(uploadedRoomMessage.senderName);
                            chatPair.Send(uploadedPersonelMessage);
                            this.client.Send(uploadedPersonelMessage);
                        } else {
                            Message uploadedRoomFileMessage = new Message(Message.MessageTypes.IN_ROOM_MESSAGE);
                            uploadedRoomFileMessage.content = roomMsg;
                            Server.SendMessageToGivenRoom(uploadedRoomMessage.roomName, uploadedRoomFileMessage);
                        }
                        break;
                    case DOWNLOAD_FILE_REQUEST:
                        //if a download file request came, server control is such a file exit or not.
                        //if yes then sends this file to the client as File Message
                        String fileToDownload = msg.content.toString();
                        if (!Server.isFileOnServerByGivenName(fileToDownload)) {
                            break;
                        }
                        String path = Server.getFilePathByName(fileToDownload);
                        byte[] content = FileUtilities.getContentOfFileOnGivenPath(path);
                        FileMessage fm = new FileMessage(fileToDownload, content);
                        Message downloadFileMessage = new Message(Message.MessageTypes.DOWNLOAD_FILE_REQUEST);
                        downloadFileMessage.content = fm;
                        this.client.Send(downloadFileMessage);

                }
            } catch (IOException ex) {
                //if any error happens fire the client
                Server.FireClient(this.client);
            } catch (ClassNotFoundException ex) {
                Server.FireClient(this.client);
            }
        }
    }
}
