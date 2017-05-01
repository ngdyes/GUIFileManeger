package fileManager;
import java.io.*;
import java.util.zip.*;

public class Decompressor extends Access {
	public Decompressor(){}
   public int decompress(File in,String dest){
	if(!in.exists())
		{
			setTips("Source File is not existed");
			return -1;
		}
		if(in.isDirectory())
		{
			setTips("Source File is a Directory!");
			return -1;
		}
		File out=new File(dest);
		if(out.isFile())
		{
			setTips("Destination File is a File");
			return -1;
		}
		out.mkdirs();
		try{
		ZipInputStream Zin=new ZipInputStream(new FileInputStream(in));
		ZipEntry entry;  
		File Fout=null;
    
             while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
                 Fout=new File(dest,entry.getName());  
                 if(!Fout.exists()){  
                     (new File(Fout.getParent())).mkdirs();  
                 }  
                 FileOutputStream Zout=new FileOutputStream(Fout);  
                 byte[] buffer = new byte[1024];
          	   int i = 0;
          	   while ((i = Zin.read(buffer)) != -1) {
          	   Zout.write(buffer, 0, i);
          	   }
                 Zout.close();      
             } 
             if(Fout==null)
             {
            	 setTips("The File can't be decompress.");
            	 
             }
             else{
             setTips(" Decompress complete.");  
             }
             Zin.close();  
		}catch (Exception e){
			setTips("Createe Stream Failed.You have no authority of the File.");
			return -1;}
             return 0;
		

   }
}
