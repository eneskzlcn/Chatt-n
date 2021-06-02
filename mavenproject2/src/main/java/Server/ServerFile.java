/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
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
    
