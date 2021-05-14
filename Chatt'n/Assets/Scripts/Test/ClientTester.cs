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
       public GameObject clientPrefab;
       public void CreateNewClient()
       {
            Instantiate(clientPrefab, new Vector3(0, 0, 0), Quaternion.identity);
       }
    }
}
