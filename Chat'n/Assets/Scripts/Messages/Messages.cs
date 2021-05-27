using System;
using System.Collections.Generic;
using System.Text;

namespace Messages
{
    public enum Message_Type
    {
        CONNECTED, DISCONNECTED, USERNAME, CREATE_ROOM, ALL_USERNAMES_LIST,ALL_ROOMS_LIST
    }
    //this enum controls if the message is text,video or document etc...
    public enum MessageContentType
    {
        VIDEO,TEXT,DOCUMENT
    }
    public struct Message
    {
        public Message_Type type;
        public string content; // maybe another struct (json form) .
    }
    public struct CreateRoomMessage
    {
        public string roomName;
        public string creatorName;
        public int capacity;
    }
    public struct InRoomMessage
    {
        public string roomName;

        public MessageContentType contentType;
        
        public string senderName;

        public string content;
    }
}