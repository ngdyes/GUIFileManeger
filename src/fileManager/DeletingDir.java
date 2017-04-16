package fileManager;

import java.io.File;
public class DeletingDir extends Access {
     DeletingDir(File file)
     {
	 if(file.exists()){
		 if(file.isDirectory()){
				File[] listOfFile=file.listFiles();
				for(int i=0;i<listOfFile.length;i++)
				{
					new DeletingDir(listOfFile[i]);
				}			 		 
		 }
			 

	 if(!file.delete())
	 {
		 System.out.println("Failed delete! Exam the full path.");
	 }
	 }
	 else
	 {
		 System.out.println("File or Directory is not exsisted");
	 }
     }
	
}

