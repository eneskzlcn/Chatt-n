
package ServerRunner;
import Server.Server;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class Start {
    
    public static void main(String[] args) {
       Server server = new Server(4000);
       server.ListenClientConnectionRequests();
       
        while (!server.socket.isClosed()) {

            try {
                System.out.println("Ben burada bekliyorum."+Server.clients.size()+" clientim var.");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
