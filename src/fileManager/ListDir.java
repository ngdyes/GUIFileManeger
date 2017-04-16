package fileManager;

import java.io.File;

public class ListDir extends Access {

	public ListDir(String path) {
		File file=new File(path);
		if(file.isDirectory())
		{
			File[] listOfFile=file.listFiles();
			for(int i=0;i<listOfFile.length;i++)
			{
				System.out.println(listOfFile[i].getName());
			}
		}
		else
		{
		System.out.println("Directory " +path+" is not found.");
		}
	}

}
