package fileManager;

import java.io.File;
public class Deletor extends Access {
	public  Deletor(){};
	public int delete(File file)
    {
	 if(file.exists()){
		 if(file.isDirectory()){			 
				File[] listOfFile=file.listFiles();
				for(int i=0;i<listOfFile.length;i++)
				{
					 delete(listOfFile[i]);
				}			 		 
		 }
			 

	 if(!file.delete())
	 {
		 setTips("Failed delete! Exam the full path.");
		 return -1;
	 }
	 else{setTips(file.getName()+" Deletes Well.");};
	 return 0;
	 }
	 else
	 {
		 setTips("File or Directory is not exsisted,still remove it from the tree.");
		 return 0;
	 }
    }
	
}

