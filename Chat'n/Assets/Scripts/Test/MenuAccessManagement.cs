using UnityEngine;

[System.Serializable]
public struct KeyPanelPair
{
    public string key;
    [SerializeField] public GameObject menu;
}

public class MenuAccessManagement : MonoBehaviour {
    
    public KeyPanelPair[] menuDictionary;


    public GameObject getMenu(string key)
    {
        for(int i = 0; i< menuDictionary.Length;i++)
        {
            if(menuDictionary[i].key == key)
            {
                return menuDictionary[i].menu;
            }
        }
        return null;
    }
}