
package ClientRunner;

import ClientSide.Client;
import Messages.Message;
/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class Start {
    
    public static void main(String[] args) {
        Client client = new Client();
        client.Connect("127.0.0.1", 4000);
        Message m = new Message(Message.MessageTypes.USERNAME);
        m.content = "Enes";
        client.Send(m);
    }
     
}
