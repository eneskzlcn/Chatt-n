using System;
using System.Net;
using System.Net.Sockets;

// The purpose of thiss class is represent any client as a class in server-side
namespace ServerSide
{
    public class SClient 
    {
        public TcpClient socket;

        public NetworkStream _stream;

        public byte[] _buffer;

        //this id will allow us to reach the client from the dic in server directly ( mapped with ids).
        public int _clientID;
        
        public string _userName;

        public SClient(int clientID,TcpClient socket )
        {
            // initializing socket and io stream.
            this.socket = socket;
            this._clientID = clientID;
            this._stream = socket.GetStream();
            //initializing buffer.
            this.socket.ReceiveBufferSize = ClientSettings.BUFFER_SIZE;
            this.socket.SendBufferSize = ClientSettings.BUFFER_SIZE;
            this._buffer = new byte[ClientSettings.BUFFER_SIZE];

            
        }       
        public ReadIncomingData()
        {
            _stream.BeginRead(_buffer,0,_buffer.Length,new AsyncCallback(OnReadIncomingData),null);
        }

        public OnReadIncomingData(IAsyncResult ar)
        {
            try
            {
                //EndRead func. returns the byte length of incoming data. If there is no data incoming or there is
                //an error occured on reading, then this value will be zero or lower than zero. So we can control
                //is the reading succeded with this.
                int _incomingDataLength = _stream.EndRead(ar);

                if(_incomingDataLength <= 0 )
                {
                    //There is an error occured
                    //Disconnect or break
                    return;
                }
                //we initalize a temp data array because of the _buffer not completely full or clear.
                // the _buffer always accepts data amount of our BUFFER_SIZE but the incoming data not
                //always equal to BUFFER_SIZE. So we starts an array that exactly the same length with given
                //data = _data.
                byte _data = new byte[_incomingDataLength];
                //Array.Copy provides that the empty or broken bytes not being copied to the new array _data
                Array.Copy(buffer,_data,_incomingDataLength);
                //
            }
            catch (System.Exception)
            {
                
                throw;
            }
        }
    }
}