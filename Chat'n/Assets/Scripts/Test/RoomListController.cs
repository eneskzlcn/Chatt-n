using UnityEngine;
using UnityEngine.UI;
using InGameResources;
public class RoomListController : MonoBehaviour {
        public GameObject roomListContentPanel;
        public GameObject chatTextPrefab;
        public void createRoomList()
        {
            if(ResourceManager.getReadedRoomNames() == null){return;}
            foreach (Transform child in roomListContentPanel.transform) {
                GameObject.Destroy(child.gameObject);
            }
            foreach(string item in ResourceManager.getReadedRoomNames())   
            {
                 GameObject newText = Instantiate(chatTextPrefab,roomListContentPanel.transform);
                 newText.GetComponent<Text>().text = item;
            }
            
        }
}