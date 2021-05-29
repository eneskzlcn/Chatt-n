/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class Room {
    
    public String name;
    
    public ArrayList<String> userNames;
    
    public Room(String name, String creatorName)
    {
        userNames = new ArrayList<String>();
        this.name = name;
        this.userNames.add(creatorName);
    }
    
}
