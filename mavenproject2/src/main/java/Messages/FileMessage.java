/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class FileMessage implements java.io.Serializable{
    
    public String fileName;
    public byte[] content;

    public FileMessage(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }
    
    
}
