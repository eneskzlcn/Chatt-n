/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Enes Kızılcın <nazifenes.kizilcin@stu.fsm.edu.tr>
 */
public class FileUtilities {

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
     
    public static String getNameOfFileOnGivenPath(String filePath)
    {
        return null;
    }
    
    public static byte[] getContentOfFileOnGivenPath(String filePath)
    {
        return null;
    }
}
