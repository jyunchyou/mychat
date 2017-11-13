package org.hjq.storage;
import java.io.File;
import org.hjq.constant.ConstantLogin;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileInputStream;
import android.os.Environment;
public class MessageStorage {

   
    File file = null;
    FileWriter fileWriter = null;
   

    
    FileReader fileReader = null;
    
    public MessageStorage(){
        init();
    }
    
    public void init(){
        
        //sd卡目录
        String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        
        file = new File(sdCardDir,"user");
        
       if(! file.exists()){
           try
           {
               file.createNewFile();
               
           
               
           }
           catch (IOException e)
           {}
          
       }
    }

    
    public boolean  put(String userName,String password) throws IOException{
        
        
        fileReader = new FileReader(file);
        char[] buffer = new char[50];
        fileReader.read(buffer);
    
        String blank = String.valueOf(new char[1]);
        String bufferChar = String.valueOf(buffer[0]);
        
        //如果不为空
        if (!blank.equals(bufferChar)) {
            fileReader.close();
            return false;
            
        }
         int len = userName.length() + password.length();
       
        StringBuilder stringBuilder = new StringBuilder(len + 1);
        
        stringBuilder.append(userName.trim());
        stringBuilder.append("&");
        stringBuilder.append(password.trim());
        fileWriter = new FileWriter(file);
        fileWriter.write(stringBuilder.toString());
        fileWriter.flush();
        fileWriter.close();
        
        return true;
        
    }
    
    public  String getPassword() throws FileNotFoundException, IOException{

        
       fileReader = new FileReader(file);
        char[] buffer = new char[50];
        fileReader.read(buffer);
        String blank = String.valueOf(new char[1]);
        String bufferChar = String.valueOf(buffer[0]);

        //如果为空
        if (blank.equals(bufferChar)) {
            fileReader.close();
            return null;

        }
        String userData = String.valueOf(buffer);
    String word = userData.substring(userData.indexOf("&") + 1,userData.length());
    
    fileReader.close();
    return word;
   }
    public   String getUserName() throws FileNotFoundException, IOException{
        
       
        
       
        fileReader = new FileReader(file);
        char[] buffer = new char[50];
        fileReader.read(buffer);
        String userData = String.valueOf(buffer);
        
        String blank = String.valueOf(new char[1]);
        String bufferChar = String.valueOf(buffer[0]);

        //如果不为空
        if (blank.equals(bufferChar)) {
            fileReader.close();
            return null;

        }
        
        String name = userData.substring(0,userData.indexOf("&"));
        fileReader.close();
        
        return name;
     
    
        
      
       
}
}
