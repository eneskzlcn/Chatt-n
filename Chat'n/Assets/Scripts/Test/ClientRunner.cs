using UnityEngine;
using UnityEngine.UI;
using ClientSide;
using Messages;
using Newtonsoft.Json;

namespace Test
{
    public class ClientRunner : MonoBehaviour {
      

        public Text inputField; 
        public void Connect()
        {
            Client.Connect();
            Message message = new Message();
            message.type = Message_Type.USERNAME;
            message.content = inputField.text.ToString();
            string json_message = JsonConvert.SerializeObject(message);
            Client.SendMessage(json_message);
        }
    }
}