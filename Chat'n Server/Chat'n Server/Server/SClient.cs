using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using Messages;
using Newtonsoft.Json;

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
            this._buffer = new byte[ClientSettings.BUFFER_SIZE];


        }
        public void ReadIncomingData()
        {

            _stream.BeginRead(_buffer, 0, _buffer.Length, new AsyncCallback(OnReadIncomingData), null);
        }

        public void OnReadIncomingData(IAsyncResult ar)
        {
            try
            {
                //EndRead func. returns the byte length of incoming data. If there is no data incoming or there is
                //an error occured on reading, then this value will be zero or lower than zero. So we can control
                //is the reading succeded with this.
                int incomingDataLength = _stream.EndRead(ar);
                _stream.BeginRead(_buffer, 0, _buffer.Length, new AsyncCallback(OnReadIncomingData), null);

                if (incomingDataLength <= 0)
                {
                    //There is an error occured
                    //Disconnect or break
                    Console.WriteLine("There is an error occured on reading.");

                    return;
                }

                //we initalize a temp data array because of the _buffer not completely full or clear.
                // the _buffer always accepts data amount of our BUFFER_SIZE but the incoming data not
                //always equal to BUFFER_SIZE. So we starts an array that exactly the same length with given
                //data = _data.
                byte[] data = new byte[incomingDataLength];
                //Array.Copy provides that the empty or broken bytes not being copied to the new array _data
                Array.Copy(_buffer, data, incomingDataLength);
                string incomingString = Encoding.UTF8.GetString(_buffer);
                // then we have the message struct which has type and a content(json);
                Message message = JsonConvert.DeserializeObject<Message>(incomingString);
                switch (message.type)
                {
                    case Message_Type.CONNECTED:
                        break;

                    case Message_Type.USERNAME:
                        this._userName = message.content;
                        Console.WriteLine("My username is : " + this._userName);
                        break;
                    default:
                        Console.WriteLine("Wrong case came");
                        break;
                }
            }
            catch (System.Exception)
            {

                Console.WriteLine("System.Exception SClient :70");
            }
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
    }
}
