package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {

// to read data from property file >>>>android.properties
 public String readPropertiesFile(String fileName, String keyword) throws IOException{
    Properties prop = new Properties();
    String value;
  
       final String dir = System.getProperty("user.dir");	
       FileInputStream fis = new FileInputStream(dir + fileName);
       prop.load(fis);
       // return value
       value = prop.getProperty(keyword);
       return value;   
 }
}
 

