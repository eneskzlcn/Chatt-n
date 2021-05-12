using System;
using System.Net;
using System.Net.Sockets;
using UnityEngine;

namespace ClientSide
{
    public class Client
    {
        private TcpClient socket;

        public Client()
        {
            socket = new TcpClient();
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
                Debug.Log("Connected to the server");
            }
            
        }
    }
}