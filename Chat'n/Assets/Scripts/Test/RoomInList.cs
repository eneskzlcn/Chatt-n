using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

[RequireComponent(typeof(Button))]
public class RoomInList : MonoBehaviour
{
    [HideInInspector]
    //the name of the room that button belongs to. ( the room writing in this buttons text);        
    public string roomName;
    
    private Button button;

    [HideInInspector]
    //will gonna initialize and open after click this button.
    public GameObject roomPanel;
    
    [HideInInspector]
    //will gonna close after click a room...
    public GameObject allRoomsPanel;

    [HideInInspector]
    public Text roomHeaderText;

    public void Awake()
    {
        button = GetComponent<Button>();
    }

    public void InitializeOpeningRoomListener()
    {
        this.button.onClick.AddListener(()=>{
            roomHeaderText.text = roomName;
            roomPanel.SetActive(true);
            allRoomsPanel.SetActive(false);
        });
    }
    
}
