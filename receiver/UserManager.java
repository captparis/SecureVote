package receiver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	
	private String activeUser;
	
	private HashMap<String, byte[]> voterPasswords;
	private HashMap<String, byte[]> voterSalts;
	private HashMap<String, Boolean> voterVoted;
	
	//List of hardcoded names and codes for eligible voters for this election
	//This is based on official listings of voters
	//A unique ID string is given to each candidate through other means which allows them to register
	private HashMap<String, HashMap<String, String>> eligibleVoters; 
	
	private HashMap<String, byte[]> adminPasswords;
	private HashMap<String, byte[]> adminSalts;
	
	// singleton
	private static UserManager instance;
		

	private UserManager() {
		voterPasswords = new HashMap<String, byte[]>();
		voterSalts = new HashMap<String, byte[]>();
		voterVoted = new HashMap<String, Boolean>();
		adminPasswords = new HashMap<String, byte[]>();
		adminSalts = new HashMap<String, byte[]>();
		
		//Add initial admin account
		//At this stage, admin accounts should be hardcoded
		addAdmin("admin", "evote2016");
		
		//Add initial user for testing purposes
		addUser("a", "a");
	}
	
	public static UserManager getInstance()
	{
      if(instance==null)
         instance= new UserManager();
      
      return instance;
	}
	
	public String getActiveUser(){
		return activeUser;
	}
	
	public void setActiveUser(String user){
		activeUser = user;
	}
	
	public void addUser(String user, String pass){
		try {
			byte[] salt = generateSalt();
			try {
				voterPasswords.put(user, encryptPassword(pass, salt));
				voterSalts.put(user, salt);
				voterVoted.put(user, false);
			} catch (InvalidKeySpecException e) {
				System.out.println("Unable to encrypt password");
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Unable to generate salt");
		}
	}
	
	//TODO Code repetition, can improve
	public void addAdmin(String user, String pass){
		try {
			byte[] salt = generateSalt();
			adminSalts.put(user, salt);
			try {
				adminPasswords.put(user, encryptPassword(pass, salt));
			} catch (InvalidKeySpecException e) {
				System.out.println("Unable to encrypt password");
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Unable to generate salt");
		}
	}
	
	public void setVoted(String user, boolean voted){
		voterVoted.put(user, voted);
	}
	
	public boolean checkVoted(String user){
		if (voterVoted.get(user) != null)
			return voterVoted.get(user);
		else 
			return false;
	}
	
	public boolean checkValidUser(String user, String pass, boolean admin){
		
		boolean valid = false;
		
		//Try to match password by re-encrypting given password
		try {
			if (!admin) {
				byte[] storedPass = voterPasswords.get(user);
				byte[] storedSalt = voterSalts.get(user);
				if (storedPass != null && storedSalt != null)
					valid = validPassword(pass, storedPass, storedSalt);
			}
			else {
				byte[] storedPass = adminPasswords.get(user);
				byte[] storedSalt = adminSalts.get(user);
				if (storedPass != null && storedSalt != null)
					valid = validPassword(pass, storedPass, storedSalt);
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.print("Could not find encryption algorithm");
		} catch (InvalidKeySpecException e) {
			System.out.print("Invalid key");
		}
		
		return valid;
	}
	
	public boolean validPassword(String attemptedPassword, byte[] encryptedPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Encrypt given password using same salt that was used to encrypt registration password
		byte[] encryptedAttemptedPassword = encryptPassword(attemptedPassword, salt);
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}

	public byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
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
