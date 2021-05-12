using UnityEngine;


namespace ServerSide
{
    public class ServerTester : MonoBehaviour {
        

        public void Awake()
        {
            Server.Start();
        }
    }
}