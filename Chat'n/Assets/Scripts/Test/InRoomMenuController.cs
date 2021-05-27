
using UnityEngine;
using UnityEngine.UI;
using Messages;
using ClientSide;
public class InRoomMenuController : MonoBehaviour {
    
    public GameObject roomMessagesContent;

    //the message own client send from their app is arranged to left alignment
    public GameObject ownSendedMessage;
    // user input text;
    public Text inputText;
    //the message other clients send seems arranged to right alignment in screen.
    public GameObject otherSendedMessage;

    public string strmsg = "ben geldim";

    public Client client;

    //header contains the room name...
    public Text header;
  
    public void SendTextMessageToRoomAsClient()
    {
        /*message send to room for just a client ( not from server in this part)*/
        InRoomMessage message = new InRoomMessage();
        message.contentType = MessageContentType.TEXT;
        message.senderName = client.userName;
        message.content = strmsg;
        message.roomName = header.text.ToString();
        SendMessageToRoom(message);
        /****/

    }
    public void SendTextMessageToRoomNotAsClient()
    {
        /*message send to room for just a client ( not from server in this part)*/
        InRoomMessage message = new InRoomMessage();
        message.contentType = MessageContentType.TEXT;
        message.senderName = "ahmet";
        message.content = strmsg;
        message.roomName = header.text.ToString();
        SendMessageToRoom(message);
        /****/

    }
    public void SendMessageToRoom(InRoomMessage message)
    {
        if(message.contentType == MessageContentType.TEXT)
        {
            if(message.senderName == client.userName)
            {
                GameObject newMessage = Instantiate(ownSendedMessage,roomMessagesContent.transform);
                Text newMessageTextComponent = newMessage.GetComponent<Text>();
                
                string lastFormOfMessage = message.senderName + ": "+ message.content;

                newMessageTextComponent.text = lastFormOfMessage;
            }
            else
            {
                GameObject newMessage = Instantiate(otherSendedMessage,roomMessagesContent.transform);
                newMessage.GetComponent<Text>().text = message.content;
            }
        }
    }
}