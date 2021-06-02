/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static String getNameOfFileOnGivenPath(String filePath) {
        File f = new File(filePath);
        return f.getName();
    }

    public static byte[] getContentOfFileOnGivenPath(String filePath) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        File f = new File(filePath);
        byte[] content = new byte[(int) f.length()];
        try {
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            bis.read(content, 0, content.length);

        } catch (FileNotFoundException ex) {

            return null;
        } catch (IOException ex) {
            return null;
        }
        return content;
    }
    
    //return path of the created File
    public static String createFileWithGivenName(String fileName)
    {
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
    
    public static boolean writeContentToFileOnGivenFilePath(String filePath , byte[] content)
    {
        File f = new File(filePath);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            bos.write(content);
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
}
