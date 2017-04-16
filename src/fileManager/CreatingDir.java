package fileManager;

import java.io.File;

public class CreatingDir extends Access {


	public CreatingDir(String path)
	{
		
		 File file=new File(path);
		 if(!file.exists()){
			 if(file.mkdirs())
			 {
			 System.out.println("Success to create!");
			 }
			 else{
				 System.out.println("Failed to create! Exam the full path.");
			 }
		 }else System.out.println("The directory is exist already!");
	}

}
