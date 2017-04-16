package fileManager;
import java.io.*;

public class EnterDir extends Access {

	public EnterDir(String s) {
		File file=new File(s);
		if(file.isDirectory())
		{
			index=new StringBuffer(s);
			
		}
		else
		{
		System.out.println("Directory " +s+" is not found.");
		}
	}

}
