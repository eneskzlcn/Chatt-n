using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using Messages;
using Newtonsoft.Json;
using Utilities;

namespace Server
{
    public class SClient
    {
        public TcpClient socket;

        public NetworkStream _stream;

        public byte[] _buffer;

        //this id will allow us to reach the client from the dic in server directly ( mapped with ids).
        public string _userName;

        public SClient(TcpClient socket)
        {
            // initializing socket and io stream.
            this.socket = socket;
            this._stream = socket.GetStream();
            //initializing buffer.
            this.socket.ReceiveBufferSize = ClientSettings.BUFFER_SIZE;
            this.socket.SendBufferSize = ClientSettings.BUFFER_SIZE;
        }
        public void ReadIncomingData()
        {
            if(socket.Connected)
            {
                _buffer = new byte[ClientSettings.BUFFER_SIZE];
                _stream.BeginRead(_buffer, 0, _buffer.Length, new AsyncCallback(OnReadIncomingData), null);

            }
        }

        public void OnReadIncomingData(IAsyncResult ar)
        {
            int incomingDataLength = 0;
            try
            {
                //EndRead func. returns the byte length of incoming data. If there is no data incoming or there is
                //an error occured on reading, then this value will be zero or lower than zero. So we can control
                //is the reading succeded with this.
                incomingDataLength = _stream.EndRead(ar);
                if (incomingDataLength <= 0)
                {
                    //There is an error occured
                    //Disconnect or break
                    Console.WriteLine("Disconnected :53");
                    Disconnect();
                    return;
                }
            }
            catch (System.Exception)
            {
                Console.WriteLine("Disconnected :85");
                Disconnect();
                }
                //we initalize a temp data array because of the _buffer not completely full or clear.
                // the _buffer always accepts data amount of our BUFFER_SIZE but the incoming data not
                //always equal to BUFFER_SIZE. So we starts an array that exactly the same length with given
                //data = _data.
                byte[] data = new byte[incomingDataLength];
                //Array.Copy provides that the empty or broken bytes not being copied to the new array _data
                Array.Copy(_buffer, data, incomingDataLength);
                string incomingString = Encoding.UTF8.GetString(_buffer);
                Console.WriteLine("incoming stringi yziyom"+incomingString);
            // then we have the message struct which has type and a content(json);
                Console.WriteLine("gelen data lengthi:" + incomingDataLength);
                Message message = JsonConvert.DeserializeObject<Message>(incomingString);
                Console.WriteLine("Flag 1");
                switch (message.type)
                {
                    case Message_Type.ALL_USERNAMES_LIST:
                        //making a username list from current sclients
                        Console.WriteLine("Flag 2");
                        
                        List<string> userNames = new List<string>();
                        foreach(SClient sclient in Server.clients)
                        {
                            userNames.Add(sclient._userName);
                        }
                        string[] names = Utilities.Utilities.ListToArray(userNames);
                        Console.WriteLine("Flag 3");
                        //convert this names to json to keep in message content
                        string commaSeperatedNames = string.Join(",", names);
                        //string jsonUserNames = JsonConvert.SerializeObject(commaSeperatedNames);
                        Console.WriteLine("Flag 4");
                        Console.WriteLine("Comma seperated array = " + commaSeperatedNames);
                        //message object that contain this list
                        Message m = new Message();
                            
                        m.type = Message_Type.ALL_USERNAMES_LIST;
                        m.content = commaSeperatedNames;
                        Console.WriteLine("Flag 5");
                        string messageToBeSend = JsonConvert.SerializeObject(m);
                        Console.WriteLine("Message to be send = " + messageToBeSend);
                        this.SendMessage(messageToBeSend);
                        Console.WriteLine("Flag 6");
                        break;
                    case Message_Type.USERNAME:
                        this._userName = message.content;
                        Console.WriteLine("My username is : " + this._userName);
                        break;
                    case Message_Type.CREATE_ROOM:
                        Room room;
                        CreateRoomMessage crm = JsonConvert.DeserializeObject<CreateRoomMessage>(message.content);
                        room = new Room(crm.roomName, crm.creatorName,crm.capacity);
                        Server.rooms.Add(room);
                        Console.WriteLine("Room created with name:" + room.name + " , creator:" + crm.creatorName);
                        break;
                    default:
                        Console.WriteLine("Wrong case came");
                        break;
                }
            ReadIncomingData();
        }
        public void SendMessage(string message)
        {
            byte[] sendingData = Encoding.UTF8.GetBytes(message);
            _stream.BeginWrite(sendingData, 0, sendingData.Length, OnSendingData, null);
        }
        public void OnSendingData(IAsyncResult ar)
        {
            _stream.EndWrite(ar);
        }
        public void Disconnect()
        {
            _stream.Close();
            this.socket.Close();
            Server.clients.Remove(this);
            
        }
    }
}
