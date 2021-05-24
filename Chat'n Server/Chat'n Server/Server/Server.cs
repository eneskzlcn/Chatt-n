using System;
using System.Net;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;
using Messages;
using Newtonsoft.Json;

namespace Server
{
    public static class Server
    {
        //TcpListener starts a listener that listends for incoming clients.
        private static TcpListener serverListener = new TcpListener(IPAddress.Any, ServerSettings.PORT);
        public static List<SClient> clients = new List<SClient>();
        public static List<Room> rooms = new List<Room>();
        public static void Start()
        {
            serverListener.Start();
            Console.WriteLine("Server started");    
            Console.WriteLine("Started to accept new socket...");
            serverListener.BeginAcceptTcpClient(OnAcceptingTcpClient, null);
        }

        private static void OnAcceptingTcpClient(IAsyncResult ar)
        {
            TcpClient newSocket = serverListener.EndAcceptTcpClient(ar);
            if (newSocket == null)
            {
                Console.WriteLine("Null bir socket geldi.");
                return;   
            }
            SClient newClient = new SClient(newSocket);
            newClient.ReadIncomingData();
            // after a client accepted or rejected we must continue for waiting for new clients. So,
            // if we use begin accept method in here then there will be a loop never stops waiting for new clients
            clients.Add(newClient);
            serverListener.BeginAcceptTcpClient(OnAcceptingTcpClient, null);
        }
    }
}
