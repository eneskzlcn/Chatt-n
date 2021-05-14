using System;
using System.Net;
using System.Net.Sockets;
using UnityEngine;
using Newtonsoft.Json;
using System.Text;
using Messages;

namespace ClientSide
{
    public class Client
    {
        private TcpClient socket;

        private NetworkStream _stream;

        private byte[] _buffer;
        public Client()
        {
            socket = new TcpClient();
            _buffer = new byte[ClientSettings.BUFFER_SIZE];
        }
        public void Connect()
        {
            socket.BeginConnect(ServerSettings.HOST,ServerSettings.PORT,OnConnectingToServer,null);
        }

        public void OnConnectingToServer(IAsyncResult ar)
        {
            socket.EndConnect(ar);
            if(socket.Connected)   
            {
                _stream = socket.GetStream();
                Debug.Log("Connected to the server");
                _stream.BeginRead(_buffer,0,_buffer.Length,OnReadingData,null);
            }
            
        }

        public void OnReadingData(IAsyncResult ar)
        {
           try
            {
                //EndRead func. returns the byte length of incoming data. If there is no data incoming or there is
                //an error occured on reading, then this value will be zero or lower than zero. So we can control
                //is the reading succeded with this.
                int incomingDataLength = _stream.EndRead(ar);

                if(incomingDataLength <= 0 )
                {
                    //There is an error occured
                    //Disconnect or break
                    Debug.Log("There is an error occured on reading.");

                    return;
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
                // then we have the message struct which has type and a content(json);
                Message message = JsonConvert.DeserializeObject<Message>(incomingString);
                switch(message.type)
                {
                    case Message_Type.CONNECTED:
                        break;
                    
                    case Message_Type.DISCONNECTED:
                        break;
                }
                _stream.BeginRead(_buffer,0,_buffer.Length,OnReadingData,null);
                //cast to message struct than ...

            }
             catch (System.Exception)
            {
                
                Debug.Log("System.Exception SClient :70");
            }
        }
        public void SendMessage(string message)
        {
            //converted the json to byte array to send...
            byte[] sendingData = Encoding.UTF8.GetBytes(message);
            try
            {
                _stream.BeginWrite(sendingData,0,sendingData.Length,OnSendingData,null);
            }
            catch (System.Exception)
            {
                // disconnect or sth.
                return;     
            }
            
            //  string output = JsonConvert.SerializeObject(m);
           //Messagem deserializedProduct = JsonConvert.DeserializeObject<Messagem>(output);
        }

        public void OnSendingData(IAsyncResult ar)
        {
            _stream.EndWrite(ar);
        }
    }
}