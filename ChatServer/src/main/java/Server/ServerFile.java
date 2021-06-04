
package Server;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//a server file contains which room this file uploaded from , name of the file,
//and the path where it saved on server.
public class ServerFile {
    
    public String sharedRoomName;
    public String name;
    public String path;

    public ServerFile(String sharedRoomName, String name, String path) {
        this.sharedRoomName = sharedRoomName;
        this.name = name;
        this.path = path;
    }
    
}
    
