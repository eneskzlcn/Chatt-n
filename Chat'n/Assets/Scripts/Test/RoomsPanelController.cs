using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class RoomsPanelController : MonoBehaviour
{
    public GameObject roomListContent;

    public GameObject roomInListPrefab;

    public GameObject allRoomsPanel;

    public GameObject roomChatPanel;
    public Text inRoomHeaderText;

    public string[] roomNames = {"enesin odasi","esramm","bennn"};

    public void CreateRoomList()
    {
        foreach(string name in roomNames)
        {
            GameObject roomObj = Instantiate(roomInListPrefab,roomListContent.transform);
            Text innerText = roomObj.GetComponentsInChildren<Text>()[0];
            innerText.text = name;
            RoomInList roomInListComponent = roomObj.GetComponent<RoomInList>();
            roomInListComponent.allRoomsPanel = allRoomsPanel;
            roomInListComponent.roomPanel = roomChatPanel;
            roomInListComponent.roomName = name;
            roomInListComponent.roomHeaderText = inRoomHeaderText;
            roomInListComponent.InitializeOpeningRoomListener();
        }
    }
}
