using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using System.Net;
using System.Net.Sockets;
public class Server : MonoBehaviour
{
    public string _ip;
    public int _port;
    public int _maximumClientCount;
    //TcpListener starts a listener that listends for incoming clients.
    public TcpListener serverListener;

    private void Awake()
    {
        Initialize();
    }
    public void Initialize()
    {
        serverListener = new TcpListener(IPAddress.Any,_port); //IPAddress.Any provides accepting every IP.

        Debug.Log($"Server created. Listening on :{_port} port. Maximum client count : {_maximumClientCount}"); 
    }

    private void Start()
    {
        serverListener.Start();
        Debug.Log("Server started"); 

        serverListener.BeginAcceptTcpClient(onBeginAcceptTcpClient,null);
    }

    public void onBeginAcceptTcpClient(IAsyncResult ar)
    {
        Debug.Log("Started to accept new socket...");
        TcpClient newClient = serverListener.EndAcceptTcpClient(ar);
        Debug.Log($"Accepted Client : {newClient.Client.RemoteEndPoint}");
    }
}
