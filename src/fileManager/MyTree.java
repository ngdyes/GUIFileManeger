package fileManager;

import javafx.scene.control.TreeItem;
import java.io.File;
import javafx.collections.*;

public class MyTree extends TreeItem<File> {
	private boolean notInitialized=true;
	public MyTree(final File file){  
		 
        super(file);  
 
    }  
	@Override 
	 
    public ObservableList<TreeItem<File>> getChildren(){  

        if(notInitialized){  
            notInitialized = false;  
            if(getValue().isDirectory()){  
                for(final File file:getValue().listFiles()){    
                	if(file.listFiles()!=null||file.isFile())
                		
                	{
                   super.getChildren().add(new MyTree(file)); 
                	}
                }  
            }  
        }  
        return super.getChildren();  
    }  
 
    @Override 
 
    public boolean isLeaf(){  
        return !getValue().isDirectory();  
    }  
    public void refresh (){

    	getChildren().removeAll(getChildren());
    	notInitialized=true;
    }
}
