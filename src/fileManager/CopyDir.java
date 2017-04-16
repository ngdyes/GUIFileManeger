package fileManager;

import java.io.*;

public class CopyDir extends Access {
    public CopyDir(String source,String dest)throws IOException
    {
    	File in=new File(source);
    	File path=new File(dest);
    	if(!path.mkdirs()){
    		if(path.isFile())
    		{System.out.println("Destination path can't be established,because the same-named File has existed.");
    		return;}
    	}
    	File out=new File(dest+'\\'+in.getName());
    	new CopyDir(in,out);
    }
	public CopyDir(File in,File out) throws IOException{

		 if(!in.exists())
		 {	 
			System.out.println("Source file is not existed");
			return;
		}     

			 if(in.isDirectory()){	
				    out.mkdirs();
					File[] listOfFile=in.listFiles();
					for(int i=0;i<listOfFile.length;i++) 
					{
						new CopyDir(listOfFile[i],new File(out.toString()+'\\'+listOfFile[i].getName()));
					}		
			
			 }
			 else{
				 CopyFile(in,new File(out.toString()));
			 }
		 }

	public void CopyFile(File in,File out) throws IOException
	{
	   FileInputStream inFile = new FileInputStream(in);
	   FileOutputStream outFile = new FileOutputStream(out);

	   byte[] buffer = new byte[1024];
	   int i = 0;
	   while ((i = inFile.read(buffer)) != -1) {
	   outFile.write(buffer, 0, i);
	   }
	   inFile.close();
	   outFile.close();
	}
}

