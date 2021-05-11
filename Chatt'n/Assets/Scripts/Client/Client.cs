using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using System.Net;
using System.Net.Sockets;

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
            Debug.Log("Connected to the server");
        }
    }
}