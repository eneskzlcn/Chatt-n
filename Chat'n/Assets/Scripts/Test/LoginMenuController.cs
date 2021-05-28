using UnityEngine;
using UnityEngine.UI;
using ClientSide;
using Messages;
using System.Threading.Tasks;  
using UnityEngine.SceneManagement;



public class LoginMenuController : MonoBehaviour {
    public Button _loginButton;
    public Text _userName;

    public Text loginButtonText;

    public Text _infomationText;
    
    public Client client;
    
    public GameObject mainMenu;

    public GameObject loginMenu;
    
    public void ControlUsernameInput()
    {
        if(_userName.text.ToString().Length >= 2)
        {
            _loginButton.interactable = true;
        }
        else
        {
            _loginButton.interactable = false;
        }
    }
    private void Awake() {
        client.addOnServerConnectionListener(OpenMainMenu);
    }
    public void OpenMainMenu()
    {
        mainMenu.SetActive(true);
        mainMenu.SetActive(false);
    }
}