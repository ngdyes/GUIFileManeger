package fileManager;

import java.io.*;
import java.util.zip.*;

public class Compressor extends Access {
	public  int path;
	public Compressor(){};
	public  int compress(String source,String dest) {
		File in=new File(source);
		path=in.getAbsolutePath().substring(0,in.getAbsolutePath().indexOf(in.getName())).length();
		if(!in.exists())
		{
			setTips("Source path doesn't exist");
			return -1;
		}
		if(in.isDirectory()&&dest.indexOf(source)!=-1){
        	setTips("Destination File is subpath of the source path");
        	return -1;
        }
		File out=new File(dest);
		if(out.isDirectory())
		{
			setTips("Destination File is a Directory");
			return -1;
		}try{
		ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(out));
		if(compressDir(in,zos)==-1)
		{
			zos.close();
			setTips("Compressing error");
			return -1;
			}
		}catch(IOException e){
			setTips("The output file can't be streamfied.");
			return -1;
		}
		setTips("Compressing is done");
		return 0;
}
	private int compressDir(File in,ZipOutputStream zos){
		if(in.isDirectory())
		{
			File[] listOfFile=in.listFiles();
			for(int i=0;i<listOfFile.length;i++) 
			{
				if(compressDir(listOfFile[i],zos)==-1)
					return -1;
			}		
		}
		else{
			if(compressFile(in,zos)==-1)
				return -1;
		}
		return 0;
	}
	private int compressFile(File in,ZipOutputStream zos) 
	{
       try{
        FileInputStream fis = new FileInputStream (in) ;
        String temp=in.getAbsolutePath().substring(path);
        zos.putNextEntry(new ZipEntry(temp));
       byte[] buffer = new byte[1024];
	   int i = 0;
	   while ((i = fis.read(buffer)) != -1) {
	   zos.write(buffer, 0, i);
	   }
       fis.close ( ) ;
       }
       catch(IOException e){return -1;}
       return 0;
		
	}

}
