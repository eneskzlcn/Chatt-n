using UnityEngine;
using UnityEngine.UI;
using InGameResources;
public class AllUserListController : MonoBehaviour {


        public GameObject chatContentPanel;

        public GameObject chatTextPrefab;

        public string[] userNames = {"enes","ahmet","furk"};
        public void createUserList()
        {
            //if(ResourceManager.getReadedUserNames() == null){return;}
            foreach (Transform child in chatContentPanel.transform) {
                GameObject.Destroy(child.gameObject);
            }
            foreach(string item in ResourceManager.getReadedUserNames())  
            {
                 GameObject newText = Instantiate(chatTextPrefab,chatContentPanel.transform);
                 newText.GetComponent<Text>().text = item;
            }
            
        }
}