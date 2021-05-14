using UnityEngine;
using UnityEngine.UI;
using ClientSide;
using Messages;
using Newtonsoft.Json;

namespace Test
{
    public class ClientRunner : MonoBehaviour {
        Client client;

        public UsernameKeeper usernameKeeper; 
        public void Awake()
        {
            client = new Client();
            client.Connect();
            Message message = new Message();
            message.type = Message_Type.USERNAME;
            message.content = usernameKeeper.userName;
            string json_message = JsonConvert.SerializeObject(message);
            client.SendMessage(json_message);
        }
    }
}