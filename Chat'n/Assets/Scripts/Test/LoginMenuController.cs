using UnityEngine;
using UnityEngine.UI;

public class LoginMenuController : MonoBehaviour {
    public Button _loginButton;
    public Text _userName;
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
}