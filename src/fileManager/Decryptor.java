package fileManager;
import java.io.*;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Decryptor extends Access {
	public Decryptor(){}
	public int decryptFile(File in,String dest,String key) 
	{
		if(!in.exists())
		{
			setTips("Source File is not existed");
			return -1 ;
		}
		if(in.isDirectory())
		{
			setTips("Source File is a Directory!");
			return -1 ;
		}
		File out=new File(dest);
		if(out.isDirectory())
		{
			setTips("Destination File is a Directory");
			return -1;
		}
		try{
		FileInputStream inFile = new FileInputStream(in);
		FileOutputStream outFile = new FileOutputStream(out);
		byte[] buffer = new byte[1024];
		   int i = 0;
		   while ((i = inFile.read(buffer)) != -1) {
		   
		   outFile.write(decrypt(buffer,key.getBytes()), 0, i);
		   }
		   inFile.close();
		   outFile.close();
	}catch(Exception e)
		{
		setTips("Failed to Decrypt the File");
		return -1;
		}
		setTips(in+" Decrypt Done.");
		return 0;
	}		
	
	
	
	   public  byte[] decrypt(byte[] data, byte[] key) throws Exception {	
		   if (data==null)
		   {return null;}
		SecureRandom sr = new SecureRandom();
	    DESKeySpec dks = new DESKeySpec(key);
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	    SecretKey securekey=keyFactory.generateSecret(dks);
	    Cipher cipher=Cipher.getInstance("DES/ECB/NoPadding");
	    cipher.init(Cipher.DECRYPT_MODE,securekey,sr);
	    return cipher.doFinal(data);
		
     }

	   
}
