using UnityEngine;
using UnityEngine.UI;
using ClientSide;
using Messages;
using Newtonsoft.Json;
using System.Threading.Tasks;  
using UnityEngine.SceneManagement;
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

    public void Connect()
    {
        Client.Connect();
        Message message = new Message();
        message.type = Message_Type.USERNAME;
        message.content = _userName.text.ToString();
        string json_message = JsonConvert.SerializeObject(message);
        Client.SendMessage(json_message);
         SceneManager.LoadScene("InGame");
    }
}