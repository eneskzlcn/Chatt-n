
package Messages;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

// this message contains a filename and its content.
// this message using when client or server want to send the file
// on socket
public class FileMessage implements java.io.Serializable{
    
    public String fileName;
    public byte[] content;

    public FileMessage(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }
    
    
}
