using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using ClientSide;
using Newtonsoft.Json;
using Messages;
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
            Message message = new Message();
            message.type = Message_Type.USERNAME;
            message.content = "Bizarre Cabal";
            string json_message = JsonConvert.SerializeObject(message);
            client.SendMessage(json_message);
        }
    }
}
