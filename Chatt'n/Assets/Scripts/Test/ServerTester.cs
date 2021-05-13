using UnityEngine;
using ServerSide;

namespace Test
{
    public class ServerTester : MonoBehaviour {
        public void Awake()
        {
            Server.Start();
        }

        public void printClientCount()
        {
            for(int i = 0;i < Server.clients.Count;i++)
            {
                Debug.Log("Key: "+i+", _clientID = "+Server.clients[i]._clientID);
            }
        }
    }
}