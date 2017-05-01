package fileManager;

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import java.io.*;
public class MainView extends Application {
   TreeItem<File> choosenFile=null;
   File choosenDirectory=File.listRoots()[1];
   @Override
   public void start(final Stage primaryStage) {
	   
	   final Text tips=  new Text("Welcome~");
	  TreeItem<File> tree=new SuperRoot(new File("Local Disk"));
	   TreeView<File> treeView=new TreeView<>(tree);
	  treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<File>>() {  
		         @Override  
		         public void changed(  
		                    ObservableValue<? extends TreeItem<File>> observable,TreeItem<File> oldValue,TreeItem<File> newValue)
		         {
		        	 
		        	  choosenFile=newValue;
		        	  MyTree subtree=(MyTree)newValue;
		        	  subtree.refresh();

		        	 
		        	 
		        	 
		            }

		 });  
	 
	   final TextField path=new TextField(File.listRoots()[1].toString());
	   Button choosePath=new Button("ChoosePath");
	   choosePath.setOnAction(new EventHandler<ActionEvent>() {
		   @Override
		   public void handle(ActionEvent e){
		   DirectoryChooser dc=new DirectoryChooser();
		   File choosenfile= dc.showDialog(primaryStage);
		   if(choosenfile!=null)
		   { path.setText(choosenfile.toString());}

	}});
	   Button delete=new Button("Delete");
	   delete.setOnAction(new EventHandler<ActionEvent>() {
		   @Override
		   public void handle(ActionEvent e){
			   Deletor dt=new Deletor();
		   if(dt.delete(choosenFile.getValue())==0)
			   {
			   choosenFile.getParent().getChildren().remove(choosenFile);
			   }
            tips.setText(dt.getTips());  

	}});
	   Button compress=new Button("Compress");
	   compress.setOnAction(new EventHandler<ActionEvent>() {
		   @Override
		   public void handle(ActionEvent e){
          
			Compressor cm=new Compressor();
			cm.compress(choosenFile.getValue().toString(),path.getText()+"\\"+choosenFile.getValue().getName()+".zip");	
			tips.setText(cm.getTips());  

	}});
	   Button copy=new Button("Copy");
	   copy.setOnAction(new EventHandler<ActionEvent>() {
		   @Override
		   public void handle(ActionEvent e){
			Copyer cp=new Copyer();
			cp.copyDir(choosenFile.getValue(),path.getText());
			tips.setText(cp.getTips());
			}}); 
	   Button createDir=new Button("CreatDir");
	   createDir.setOnAction(new EventHandler<ActionEvent>() {
		   @Override
		   public void handle(ActionEvent e){
			Creator ct=new Creator();
			ct.CreatingDir(path.getText());
			tips.setText(ct.getTips());
			}}); 
	   Button decompress=new Button("Decompress");
	   decompress.setOnAction(new EventHandler<ActionEvent>() {
		   @Override
		   public void handle(ActionEvent e){
			Decompressor dc=new Decompressor();
			dc.decompress(choosenFile.getValue(),path.getText());
			tips.setText(dc.getTips());
			}});
	   TextField key=new TextField("00000000");
	   Label keyLabel=new Label("  Key:(8-byte)");
	   Button encrypt=new Button("Encrypt");
	   encrypt.setOnAction(new EventHandler<ActionEvent>() {
		   @Override
		   public void handle(ActionEvent e){
			Encryptor ec=new Encryptor();
			ec.encryptFile(choosenFile.getValue(),path.getText()+"Encrypted-"+choosenFile.getValue().getName(),key.getText());
			tips.setText(ec.getTips());
			}});
	   Button decrypt=new Button("Decrypt");
	   decrypt.setOnAction(new EventHandler<ActionEvent>() {
		   @Override
		   public void handle(ActionEvent e){
			Decryptor ec=new Decryptor();
			ec.decryptFile(choosenFile.getValue(),path.getText()+"DecryptedFile.txt",key.getText());
			tips.setText(ec.getTips());
			}});
	  
       BorderPane root= new BorderPane();
       GridPane buttons=new GridPane();  
       buttons.setVgap(10);
       buttons.add(path, 1, 1, 3, 1);
       buttons.add(choosePath,4,1);
       buttons.add(createDir,1,2);
       buttons.add(delete,2,2);
       buttons.add(copy,3,2,2,1);
       buttons.add(compress, 1, 3,3,1);
       buttons.add(decompress, 4, 3,1,1);
       buttons.add(keyLabel, 1, 4);
       buttons.add(key, 2,4,2,1);
       buttons.add(encrypt, 1, 5,2,1);
       buttons.add(decrypt, 3, 5,2,1);
       
       
       root.setBottom(tips);
       root.setCenter(buttons);
       root.setLeft(treeView);
       Scene scene = new Scene(root, 500, 300);
       primaryStage.setTitle("FileManager of "+System.getenv("COMPUTERNAME"));
       primaryStage.setScene(scene);
       primaryStage.show();
   }


public static void main(String[] args)  {

       launch(args);
	  
   }

	   }