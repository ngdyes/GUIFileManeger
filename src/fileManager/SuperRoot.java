package fileManager;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class SuperRoot extends MyTree {
    boolean notInitialized=true;
	public SuperRoot(File file) {
		super(file);
	}
	public ObservableList<TreeItem<File>> getChildren()
	{
		if(notInitialized){
		notInitialized=false;
		for(File file: File.listRoots())
		{
			super.getChildren().add(new MyTree(file));
		}
		}
		return super.getChildren();
	}
	public void  refresh(){} 
	public boolean isLeaf(){return false;}

}
