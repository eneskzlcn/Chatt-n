using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using ClientSide;
public class ClientTester : MonoBehaviour
{
    Client client;
    private void Awake()
    {
        client = new Client();
    }
    public void Connect()
    {
        client.Connect();
    }

}
