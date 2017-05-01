package fileManager;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

//Main Class of FileManager
public class Access {

    protected  String tips=null;
    public  File[] disk;
    protected void setTips(String buffer){
    	Date now = new Date(); 
    	SimpleDateFormat dateFormat = new SimpleDateFormat("【HH:mm:ss】");tips=dateFormat.format(now)+buffer;}
    public String getTips(){return tips;}
    
    
}
