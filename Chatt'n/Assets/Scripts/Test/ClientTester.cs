using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using ClientSide;
public class ClientTester : MonoBehaviour
{
    Client client;
    public void Connect()
    {
        client = new Client();
        client.Connect();
    }

}
