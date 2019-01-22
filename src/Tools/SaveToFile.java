/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Włosek
 */
public class SaveToFile {
    
        public int stringToFile(String filePath,String str,Boolean append,Boolean newLine){
        
        try{
        File file=new File(filePath);
        
        if(!file.exists())
        {
            file.createNewFile();
        }
        
            FileWriter fw=new FileWriter(file.getAbsoluteFile(),append);
            BufferedWriter bw=new BufferedWriter(fw);
            if(newLine==true){
                bw.newLine();
            }
            
            bw.write(str);
            
            bw.close();
            //System.out.println("Saved");
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Błąd podczas zapisywania pliku");
            System.exit(1);
        }
        
        return 0;
    } 
}
