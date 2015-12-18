/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.zanzibar.example.testingcli.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.zanzibar.example.testingcli.Startup;

/**
 *
 * @author ingimar
 */
public class FileHelper {

    public FileHelper() {
    }
    
    public static String createOutputFile(String absoluteFilename) {
        String absPath = absoluteFilename + ".csv";
        System.out.println("creation of file: " + absPath);

        File file = new File(absPath);
        {
            try {
                file.createNewFile();
            } catch (IOException ex) {
               Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return absPath;
    }
    
    /**
     * @TODO(20151219) check that the last character is a '/'
     * @param absolutePath
     * @return 
     */
    public static boolean checkFile(String absolutePath) {
        boolean isReadable = false;
        File file = new File(absolutePath);
        if (file.exists() && file.canRead()) {
            System.out.print(" \n:Able to READ the file");
            isReadable = true;
        } else {
            System.out.print(" \n:Either the file does not exist or it is not readable");
        }
        
        return isReadable;
    }
    
    public static boolean checkDirectory(String value) {
        boolean isWriteable = false;
        File file = new File(value);
        if (file.isDirectory() && file.canWrite()) {
            System.out.println(" \n:Able to write to the directory");
            isWriteable = true;
        } else {
            System.out.println(" \n:Cannot write to the directory " + value);
        }
        
        return isWriteable;
    }
}
