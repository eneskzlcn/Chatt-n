using System;
using System.Net;
using System.Net.Sockets;


// The purpose of thiss class is represent any client as a class in server-side
public class SClient 
{
    public TcpClient socket;

    //this id will allow us to reach the client from the dic in server directly ( mapped with ids).
    public int _clientID;
    
    public string _userName;

    public SClient(int clientID,TcpClient socket )
    {
        this.socket = socket;
        this._clientID = clientID;
    }
}
