package fileManager;

import java.io.*;

public class Copyer extends Access {
	public Copyer(){}
    public int copyDir(File in,String dest)
    {
    	String source=in.toString();
    	File path=new File(dest);
        if(in.isDirectory()&&dest.indexOf(source)!=-1){
        	setTips("Destination path is subpath of the source path");
        	return -1;
        }
    	if(!path.mkdirs()){
    		if(path.isFile())
    		{setTips("Destination path can't be established,because the same-named File has existed.");
    		return -1;}
    	}
    	File out=new File(dest+'\\'+in.getName());
    	if(in.isDirectory()){
    	  if(copyDir(in,out)==-1)
    	{
    		setTips("Directory copying error!");
    		return -1;
    	}}
    	else
    	{
    		if(copyFile(in, out)==-1)
    		{
    			setTips("File coyping error!");
    			return -1;
    		}
    	}
    	setTips("Copying Done");
    	return 0;
    }
	public int copyDir(File in,File out) {

		 if(!in.exists())
		 {	 
			setTips("Source file is not existed");
			return -1 ;
		}     

			 if(in.isDirectory()){	
				    out.mkdirs();
					File[] listOfFile=in.listFiles();
					for(int i=0;i<listOfFile.length;i++) 
					{
						 if(copyDir(listOfFile[i],new File(out.toString()+'\\'+listOfFile[i].getName()))==-1)
							 return -1;
					}		
			
			 }
			 else{
				 if(copyFile(in,new File(out.toString()))==-1)
					 return -1;
			 }
			 return 0;
		 }

	public int copyFile(File in,File out)  
	{
		
	   FileInputStream inFile;
	   FileOutputStream outFile;
	  try{
		
			inFile = new FileInputStream(in);
			outFile = new FileOutputStream(out);

	   byte[] buffer = new byte[1024];
	   int i = 0;
	   while ((i = inFile.read(buffer)) != -1) {
	   outFile.write(buffer, 0, i);
	   }
	   inFile.close();
	   outFile.close();
	  }
	  catch(Exception e)
	  {e.printStackTrace();
		  return -1;}
	   return 0;
	}
}

