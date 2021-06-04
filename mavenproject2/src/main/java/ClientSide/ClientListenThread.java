package ClientSide;

import Messages.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClientSide.*;
import Messages.CreatePersonelRoomMessage;
import Messages.FileMessage;
import Messages.RoomMessage;
import Utilities.FileUtilities;
import java.util.ArrayList;
import javax.swing.JOptionPane;

 /* 
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
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.loginMenu,
                                this.client.applicationFrame.mainMenu);
                        this.client.applicationFrame.mainMenu.mainMenuHeader.setText("Welcome " + msg.content.toString());

                        break;
                    case ALL_ROOMS:
                        //if the server sends all room names as arraylist , this case works and update the all rooms menu with given list.
                        ArrayList<String> roomNames = (ArrayList<String>) msg.content;
                        this.client.applicationFrame.roomsMenu.UpdateRoomListWithGivenList(roomNames);
                        break;

                    case ROOM_CREATED:
                        //this is the feedback case. When user want to create room send a requst to server and if it is created, this case works.
                        //this case initalizes the room for the user and the opens it.
                        String roomName = msg.content.toString();
                        this.client.applicationFrame.inRoomMenu.inRoomNameLBL.setText(roomName);
                        this.client.applicationFrame.inRoomMenu.clearInRoom();
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.roomsMenu,
                                this.client.applicationFrame.inRoomMenu);

                        break;
                    case IN_ROOM_MESSAGE:
                        //this purpose of this case is provide in room messaging system.
                      
                        RoomMessage roomMsg = (RoomMessage) msg.content;
                        //if the room messsage type is text the directly add to chat panel as text,
                        if (roomMsg.type == RoomMessage.RoomMessageType.TEXT) {
                            this.client.applicationFrame.inRoomMenu.AddMessageToChat(roomMsg.senderName, roomMsg.content.toString());
                            
                        //if the room message type is file than prepare a downloadable formatted text and then add to chat panel as text.
                        //if user clicks this message the download request will gone to server.
                        } else if (roomMsg.type == RoomMessage.RoomMessageType.FILE) {
                            String chatMessage = roomMsg.senderName + ": " + "Shared " + roomMsg.content.toString() + " Click to see download button!";
                            this.client.applicationFrame.inRoomMenu.AddMessageToChat(chatMessage);

                        }
                        break;
                    case JOIN_ROOM:
                        //this is the answer of join a room requst that coming from server.
                        //That case works when your join request accepted by server
                        //prepare the room menu for you (initializes) then opens it.
                        String roomToBeJoined = msg.content.toString();
                        if (roomToBeJoined.equals("No")) {
                            JOptionPane.showMessageDialog(this.client.applicationFrame.roomsMenu, "Can not connected to the room");
                            return;
                        }
                        this.client.applicationFrame.inRoomMenu.inRoomNameLBL.setText(roomToBeJoined);
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.roomsMenu,
                                this.client.applicationFrame.inRoomMenu);
                        break;
                    case ALL_USERS:
                        //if the server send all usernames as arraylist this case works. Takes the names and update all user list with this list.
                        ArrayList<String> users = (ArrayList<String>) msg.content;
                        this.client.applicationFrame.allUsersMenu.UpdateUserListWithGivenList(users);
                        break;

                    case ACCEPT_PERSONEL_ROOM:
                        //this is send by server which means "A client want to chat with you, do you accept it or not "
                        CreatePersonelRoomMessage cprm = (CreatePersonelRoomMessage) msg.content;
                        //create input dialogue to ask client if he/she want to chat with given client
                        int inputDialogue = JOptionPane.showConfirmDialog(null, "Do you want to chat with " + cprm.creator + " ?",
                                "Personel Room Request", JOptionPane.YES_NO_OPTION);
                        String userAnswer;
                        //if answer is yes
                        if (inputDialogue == 0) {
                            userAnswer = cprm.joiner;
                        }
                        //if answer is no
                        else {
                            userAnswer = "NO";
                        }
                        cprm.joiner = userAnswer;
                        //send message to server to evaluate.
                        Message personelRoomAnswerMessage = new Message(Message.MessageTypes.ACCEPT_PERSONEL_ROOM);
                        personelRoomAnswerMessage.content = cprm;
                        this.client.Send(personelRoomAnswerMessage);
                        break;
                    case PERSONEL_ROOM_REJECTED:
                        // This case works if the personel room requst rejected by other user. Then server sends personel room rejected
                        //message to rejected client.
                        JOptionPane.showMessageDialog(this.client.applicationFrame.allUsersMenu, "Personel Chat Rejected");
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.personelRoomMenu,
                                this.client.applicationFrame.allUsersMenu);
                        Message refreshUserListMsg = new Message(Message.MessageTypes.ALL_USERS);
                        this.client.Send(refreshUserListMsg);
                        break;

                    case PERSONEL_ROOM_ACCEPTED:
                        //this case works when the personel room operations happens successfully and servers give feedback as
                        // "your room is created" . This case initalizes the personel room menu  and opens it.
                        String userToChat = (String) msg.content;
                        this.client.applicationFrame.personelRoomMenu.personelRoomHeaderLBL.setText("Chat With " + userToChat);
                        this.client.applicationFrame.personelRoomMenu.clearPersonelRoom();
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.allUsersMenu,
                                this.client.applicationFrame.personelRoomMenu);
                        break;
                    case PERSONEL_ROOM_MESSAGE:
                        //this case is for the personel room which is client-client 1-1 chatting.
                        RoomMessage rm = (RoomMessage) msg.content;
                        //if the coming message is already text, then directly add to personel room chat panel as text.
                        if (rm.type == RoomMessage.RoomMessageType.TEXT) {
                            this.client.applicationFrame.personelRoomMenu.AddTextMessageToChat(rm.senderName, rm.content.toString());
                            break;
                        } 
                        //if the given room is a file ,then format it as downloadable text then add to chat panel as text.
                        // when user click on this text, the file download request will gone to server.
                        else if (rm.type == RoomMessage.RoomMessageType.FILE) {
                            String chatMessage = rm.senderName + ": " + "Shared " + rm.content.toString() + " Click to see download button!";
                            this.client.applicationFrame.personelRoomMenu.AddTextMessageToChat(chatMessage);
                        }
                        break;

                    case FILE_UPLOADED:
                        //when a file request send to server by this client to server, server sends file uploaded feedback
                        //then a message dialog opens to show it is uploaded succesfully or not
                        String isUploaded = (String) msg.content;
                        if (isUploaded.equals("YES")) {
                            JOptionPane.showMessageDialog(null, "File uploaded successfully");
                        } else {
                            JOptionPane.showMessageDialog(null, "File couldn't uploaded");
                        }
                        break;

                    case DOWNLOAD_FILE_REQUEST:
                        //server sends the file if clients sends a download request for a file.
                        // saves the file to the project folder and the shows a message dialogue if it is succeed or not.
                        FileMessage downloadedFileMessage = (FileMessage) msg.content;
                        String filePath = FileUtilities.createFileWithGivenName(this.client.userName+downloadedFileMessage.fileName);
                        if (FileUtilities.writeContentToFileOnGivenFilePath(filePath, downloadedFileMessage.content)) {
                            JOptionPane.showMessageDialog(null, "File downloaded successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Download Failed!");
                        }
                        break;
                    case PERSONEL_ROOM_DISPERSED:
                        //when a personel room dispersed server sends this to clients. "It is closed".
                        //and changes the menu to mainmenu.
                        JOptionPane.showMessageDialog(null, "Personel room dispersed");
                        this.client.applicationFrame.changeMenu(this.client.applicationFrame.personelRoomMenu, this.client.applicationFrame.mainMenu);
                        break;
                        
                    case LEFT_FROM_ROOM:  
                        //if any client lefts from room, than change its opened menı from roommenu to main menu
                        //and show a message dialog " you left" .
                        JOptionPane.showMessageDialog(null, "You left from room.");
                        this.client.applicationFrame.changeMenu(null, this.client.applicationFrame.mainMenu);
                }

            } catch (IOException ex) {
                System.out.println("Connection lost!");
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("Connection lost!");
                System.exit(0);
            }
        }
    }
}
