using System;
using System.Net;
using System.Net.Sockets;
using UnityEngine;
using Newtonsoft.Json;
using System.Text;

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
            socket.BeginConnect(ServerSettings.HOST,ServerSettings.PORT,new AsyncCallback(onBeginConnect),null);
        }

        public void onBeginConnect(IAsyncResult ar)
        {
            socket.EndConnect(ar);
            if(socket.Connected)   
            {
                _stream = socket.GetStream();
                Debug.Log("Connected to the server");
                _stream.BeginRead(_buffer,0,_buffer.Length,OnReadingData,null);
            }
            
        }

        public void OnReadingData()
        {

        }
        public void SendMessage(string message)
        {
            //converted the json to byte array to send...
            byte[] sendingData = Encoding.UTF8.GetBytes(message);
            try
            {
                _stream.BeginWrite(sendingData,0,sendingData.Length,onBeginWrite,null);
            }
            catch (System.Exception)
            {
                // disconnect or sth.
                return;     
            }
            
            //  string output = JsonConvert.SerializeObject(m);
           //Messagem deserializedProduct = JsonConvert.DeserializeObject<Messagem>(output);
        }

        public void onBeginWrite(IAsyncResult ar)
        {
            _stream.EndWrite(ar);
        }
    }
}