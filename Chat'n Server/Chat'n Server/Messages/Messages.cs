using System;
using System.Collections.Generic;
using System.Text;

namespace Messages
{
    public enum Message_Type
    {
        CONNECTED, DISCONNECTED, USERNAME
    }
    public struct Message
    {
        public Message_Type type;
        public string content; // maybe another struct (json form) .
    }
}
