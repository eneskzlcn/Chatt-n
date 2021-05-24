using UnityEngine;
using UnityEngine.UI;
using ClientSide;
public class CreateRoomMenuController : MonoBehaviour {
    

    public Text roomNameINP;
    public Slider capacityINP;
    public Client client;

    public void CreateRoom()
    {
        int capacity = (int)capacityINP.value;
        string roomName =roomNameINP.text.ToString();
        client.CreateRoomRequest(roomName,capacity);
    }
}