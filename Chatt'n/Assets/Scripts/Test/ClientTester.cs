using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using ClientSide;

namespace Test
{

    public class ClientTester : MonoBehaviour
    {
        Client client;
        public void Connect()
        {
            client = new Client();
            client.Connect();
        }

        public void Send()
        {
            client.SendMessage("asd");
        }
    }
}
