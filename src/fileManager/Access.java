package fileManager;
import java.util.*;
import java.io.*;

//Main Class of FileManager
public class Access {

    public static StringBuffer index=new StringBuffer("C:\\");
    public static File[] disk;
    public static Scanner scanner=new Scanner(System.in);
    protected void finalize(){scanner.close();}
    public static final String DEFAULT_QUERY_REGEX = "/\\:*\"<>|?";
	public static void main(String[] args) throws IOException{
		while(true)
		{
		System.out.println(index);
		String s=scanner.nextLine();
		if(s.length()==0)
		{
	    s="?";
		}
		String command[]=s.split(" ");
	
		
		if(command.length==1)
		{
			new EnterDir(Trans(s));
		}
		if(command.length==2)
		{
			switch(command[0]){
			case "cd":new CreatingDir(Trans(command[1]));
			          break;
			case "dd":new DeletingDir(new File(Trans(command[1])));
			          break;
			case "ld":new ListDir(Trans(command[1]));
			          break;
			          
		    default:System.out.println("Wrong Command Format.");
				
			}	
		}
		if(command.length==3)
		{
	     switch(command[0])
	     {
	     case "cp":new CopyDir(Trans(command[1]),Trans(command[2]));      
	               break;            
	     default:System.out.println("Wrong Command Format.");
	     }
		}
	}
  }

	
	public static String Trans (String S) 
	{
		boolean flag=true;
		StringBuffer s=new StringBuffer(S);
		for(int i=0;i<s.length();i++)
		{
	         if(s.charAt(i)=='/')
	         { 
	        	 s.deleteCharAt(i);
	        	 s.insert(i,'\\');
	         }
		}
		disk=File.listRoots();
		for(int i=0;i<disk.length;i++)
		{
		if(s.charAt(0)==disk[i].getPath().charAt(0)&&s.charAt(1)==disk[i].getPath().charAt(1))
		 {
			flag=false;
			break;
		 }
		}	
		if(flag){
		
	         s.insert(0,index);
		}
		
		if(s.length()==index.length()+1)
		{
			if(s.charAt(s.length()-1)=='.')
			{
			  s.deleteCharAt(s.length()-1);
			}
		}
		if(s.length()==index.length()+2)
		{
	     if(s.charAt(s.length()-1)=='.'&&s.charAt(s.length()-2)=='.')
	        {
	        		s.deleteCharAt(s.length()-1);
	        		s.deleteCharAt(s.length()-1);
	        		if(s.charAt(s.length()-1)=='\\')
	        		{
	        			s.deleteCharAt(s.length()-1);
	        		}
	        		for(int i=s.length()-1;i>1;i--)
	        		{
	        			if(s.charAt(i)=='\\')
	        			{
	        				s.delete(i,s.length());
	        				break;
	        				
	        			}
	        		}
	        		}
	        		
 
	      }
		if(new File(s.toString()).isDirectory()){
		 if(s.charAt(s.length()-1)!='\\')
		 {
			 s.append('\\');
		 }
		}
		return s.toString();	
	}

}
