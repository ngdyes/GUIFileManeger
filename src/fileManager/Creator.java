package fileManager;

import java.io.File;

public class Creator extends Access {


	public Creator(){}
	public int CreatingDir(String path)
	{
		
		 File file=new File(path);
		 if(!file.exists()){
			 if(file.mkdirs())
			 {
			 setTips("Success to create!");
			 return 0;
			 }
			 else{
				 setTips("Failed to create! Exam the full path.");
				 return -1;
			 }
		 }else
			 {setTips("The directory is exist already!");
			 return -1;
			 }
	}

}
