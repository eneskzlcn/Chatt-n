
package Utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */

//this class is a utility class to help file operations.
public class FileUtilities {

    //this functions creates a jfile chooser to select a 
    //file from system and returns its path.
    public static String chooseFileAndGetItsPath() { //When you choose a file from jFileChooser
        String filePath = "";
        JFileChooser jf = new JFileChooser();
        jf.setVisible(true);
        jf.showOpenDialog(null);

        try {
            File f = new File(jf.getSelectedFile().getPath()); //in File choose you can not choose directory
            filePath = f.getPath();
        } catch (Exception e) {

        }
        return filePath;
    }
    
    //this functions give the name of the file on given path.
    public static String getNameOfFileOnGivenPath(String filePath) {
        File f = new File(filePath);
        return f.getName();
    }
    
    //this functions read the content of a file on given path and returns it.
    public static byte[] getContentOfFileOnGivenPath(String filePath) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        File f = new File(filePath);
        byte[] content = new byte[(int) f.length()];
        try {
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            bis.read(content, 0, content.length);
            fis.close();
            bis.close();
        } catch (FileNotFoundException ex) {

            return null;
        } catch (IOException ex) {
            return null;
        }
        return content;
    }

    //return path of the created File
    public static String createFileWithGivenName(String fileName) {
        File f = new File(fileName);
        String pathOfFile = null;
        try {
            boolean isCreated = f.createNewFile();
            pathOfFile = f.getPath();
        } catch (IOException ex) {
            Logger.getLogger(FileUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pathOfFile;
    }
    // this functions writes given content to the file on given path.
    public static boolean writeContentToFileOnGivenFilePath(String filePath, byte[] content) {
        File f = new File(filePath);
        OutputStream os = null;

        try {
            os = new FileOutputStream(f);

            os.write(content);
            os.close();
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
}
