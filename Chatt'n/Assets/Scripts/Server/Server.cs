using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using System.Net;
using System.Net.Sockets;


namespace ServerSide
{
    public static class Server
    {
        //TcpListener starts a listener that listends for incoming clients.
        private static TcpListener serverListener = new TcpListener(IPAddress.Any,ServerSettings.PORT);
        public static List<SClient> clients = new List<SClient>();
        
        public static void Start()
        {
            serverListener.Start();
            Debug.Log("Server started"); 
            serverListener.BeginAcceptTcpClient(OnAcceptingTcpClient,null);
        }

        private static void OnAcceptingTcpClient(IAsyncResult ar)
        {
            Debug.Log("Started to accept new socket...");
            TcpClient newSocket = serverListener.EndAcceptTcpClient(ar);
            SClient newClient = new SClient(clients.Count,newSocket);
            newClient.ReadIncomingData();
            newClient.SendMessage("Hosgeldin!");
            Debug.Log($"Accepted Client : {newClient.socket.Client.RemoteEndPoint}");
            // after a client accepted or rejected we must continue for waiting for new clients. So,
            // if we use begin accept method in here then there will be a loop never stops waiting for new clients
            clients.Add(newClient);
            serverListener.BeginAcceptTcpClient(OnAcceptingTcpClient,null);
            
        }
    }
}