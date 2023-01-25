/**
 * 
 */
package p1_package;

/**
 * @author Chris Cisneros
 */
public class EncryptionClassMain 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		EncryptionClass Data = new EncryptionClass();
		
		Data.encryptData("The quick brown fox jumped over the lazy dog's back.");
		
		// Data.uploadData("encrypted_1.txt");
		
		Data.displayCharArray();
		
		System.out.print(Data.decryptData());
		
		// Data.downloadData("encrypted_1.txt"); 
	}
}
