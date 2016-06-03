package receiver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


//Securely manages all users, including admins and handles encryption/decryption using java in built PBKDF2 libraries
public class UserManager {
	
	private HashMap<String, byte[]> voterPasswords;
	private HashMap<String, byte[]> voterSalts;

	public UserManager() {
		voterPasswords = new HashMap<String, byte[]>();
		voterSalts = new HashMap<String, byte[]>();
	}
	
	public void addUser(String user, String pass){
		try {
			byte[] salt = generateSalt();
			voterSalts.put(user, salt);
			try {
				voterPasswords.put(user, encryptPassword(pass, salt));
			} catch (InvalidKeySpecException e) {
				System.out.println("Unable to encrypt password");
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Unable to generate salt");
		}
		
	}
	
	public boolean checkValidUser(String user, String pass){
		/*
      Set set = voters.entrySet();
      // Get an iterator
      Iterator i = (Iterator) set.iterator();
      // Display elements
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         if (user.equals(me.getKey()) && pass.equals(me.getValue())){
        	 return true;
         }
      }
      return false;
      */
		
		boolean valid = false;
		
		try {
			valid = validPassword(pass, voterPasswords.get(user), voterSalts.get(user));
		} catch (NoSuchAlgorithmException e) {
			System.out.print("Could not find encryption algorithm");
		} catch (InvalidKeySpecException e) {
			System.out.print("Invalid key");
		}
		
		return valid;
	}
	
	public boolean validPassword(String attemptedPassword, byte[] encryptedPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Encrypt the clear-text password using the same salt that was used to
		// encrypt the original password
		byte[] encryptedAttemptedPassword = encryptPassword(attemptedPassword, salt);
		// Authentication succeeds if encrypted password that the user entered
		// is equal to the stored hash
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}

	public byte[] generateSalt() throws NoSuchAlgorithmException {
		// VERY important to use SecureRandom instead of just Random
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return salt;
	}
	
	 public byte[] encryptPassword(String password, byte[] salt)
	    throws NoSuchAlgorithmException, InvalidKeySpecException {
	   // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
	   // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
	   String algorithm = "PBKDF2WithHmacSHA1";
	   // SHA-1 generates 160 bit hashes, so that's what makes sense here
	   int derivedKeyLength = 160;
	   // Pick an iteration count that works for you. The NIST recommends at
	   // least 1,000 iterations:
	   // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
	   // iOS 4.x reportedly uses 10,000:
	   // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
	   int iterations = 20000;
	   KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
	   SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
	   return f.generateSecret(spec).getEncoded();
	  }



}
