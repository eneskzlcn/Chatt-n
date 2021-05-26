using System;
using System.Net;
using System.Net.Sockets;
using UnityEngine;
using UnityEngine.UI;
using System.Text;
using Messages;
using System.Collections.Generic;
using InGameResources;

namespace ClientSide
{
    public class Client : MonoBehaviour
    {
        public  TcpClient socket = new TcpClient();

        private  NetworkStream _stream;

        private string userName;

        private byte[] _buffer = new byte[ClientSettings.BUFFER_SIZE];

        public LoginMenuController loginMenuController;
        
        public void Connect()
        {
            socket.BeginConnect(ServerSettings.HOST,ServerSettings.PORT,OnConnectingToServer,null);
        }

        private void OnConnectingToServer(IAsyncResult ar)
        {
            socket.EndConnect(ar);
            if(socket.Connected)   
            {
                //if connect successfully then get the stream for io ops.
                _stream = socket.GetStream();
                //send the chosen username to server to keep it as json message.
                Message message = new Message();
                message.type = Message_Type.USERNAME;
                message.content = loginMenuController._userName.text.ToString();
                userName = loginMenuController._userName.text.ToString();
                string json_message = JsonUtility.ToJson(message);
                this.Send(json_message);

                _stream.BeginRead(_buffer,0,_buffer.Length,OnReadingData,null);
            }
            else
            {
                Debug.Log("Can not connect to the server");
            }

        }
        private void OnReadingData(IAsyncResult ar)
        {
           int incomingDataLength = 0;
           try
            {
                //EndRead func. returns the byte length of incoming data. If there is no data incoming or there is
                //an error occured on reading, then this value will be zero or lower than zero. So we can control
                //is the reading succeded with this.
                incomingDataLength = _stream.EndRead(ar);
                Debug.Log("No stream ending problem");
                if(incomingDataLength <= 0 )
                {
                    //There is an error occured
                    //Disconnect or break
                    Debug.Log("There is an error occured on reading.");
            
                    return;
                }
                
            }
            catch (System.Exception)
            {
                
                Debug.Log("Stream ending problem");
            }
                //we initalize a temp data array because of the _buffer not completely full or clear.
                // the _buffer always accepts data amount of our BUFFER_SIZE but the incoming data not
                //always equal to BUFFER_SIZE. So we starts an array that exactly the same length with given
                //data = _data.
                byte[] data = new byte[incomingDataLength];
                //Array.Copy provides that the empty or broken bytes not being copied to the new array _data
                Array.Copy(_buffer,data,incomingDataLength);
                // then convert to string which gonna come in json format
                string incomingString = Encoding.UTF8.GetString(_buffer);
                Debug.Log("Gelen incoming string:"+incomingString);
                // then we have the message struct which has type and a content(json);
                Message message = JsonUtility.FromJson<Message>(incomingString);
                Debug.Log("Gelen mesajın tipi:" + message.type);
                switch(message.type)
                {
                    case Message_Type.ALL_ROOMS_LIST:
                        Debug.Log("All rooms came");
                        ResourceManager.setReadedRoomNames(message.content.Split(','));
                        break;
                    case Message_Type.DISCONNECTED:
                        break;
                    case Message_Type.ALL_USERNAMES_LIST:
                        ResourceManager.setReadedUserNames(message.content.Split(','));                     
                        break;
                }
                _buffer = new byte[ClientSettings.BUFFER_SIZE];
                _stream.BeginRead(_buffer,0,_buffer.Length,OnReadingData,null);
                //cast to message struct than...

        }
        public void Send(string message)
        {
            //converted the json to byte array to send...
            byte[] sendingData = Encoding.UTF8.GetBytes(message);
            try
            {
                Debug.Log("Gönderdiğim mesaj:"+message);
                Debug.Log("Uzunluğu:"+sendingData.Length);
                _stream.BeginWrite(sendingData,0,sendingData.Length,OnSendingData,null);  
            }
            catch (System.Exception)
            {
                // disconnect or sth.
                return;     
            }
        }
        public void CreateAllUserNamesRequest()
        {
            Message msg = new Message();
            msg.type = Message_Type.ALL_USERNAMES_LIST;
            string json_message = JsonUtility.ToJson(msg);
            Send(json_message);
        }
        public void CreateRoomRequest(string roomName, int capacity)
        {
            // this is the content of the message actually. will be kept as json.
            CreateRoomMessage crm = new CreateRoomMessage();
            crm.creatorName = this.userName;
            crm.roomName = roomName;
            crm.capacity = capacity;
            Message m = new Message();
            m.type = Message_Type.CREATE_ROOM;
            m.content = JsonUtility.ToJson(crm);

            string message = JsonUtility.ToJson(m);

            Send(message);
        }
        public void CreateAllRoomNamesRequest()
        {
            Message m = new Message();
            m.type = Message_Type.ALL_ROOMS_LIST;
            string json_message = JsonUtility.ToJson(m);
            Send(json_message);
        }
        private void OnSendingData(IAsyncResult ar)
        {
            _stream.EndWrite(ar);
        }
    }
}