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
	//Determines how many people will be voting
	//This is based on official listings of voters
	//A unique ID string is given to each candidate through other means which allows them to register
	private HashMap<String, byte[]> eligibleFirstNames;
	private HashMap<String, byte[]> eligibleLastNames;
	private HashMap<String, byte[]> eligibleSalts;
	private HashMap<String, Boolean> eligibleRegistered;
	
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
		
		eligibleFirstNames = new HashMap<String, byte[]>();
		eligibleLastNames = new HashMap<String, byte[]>();
		eligibleSalts = new HashMap<String, byte[]>();
		eligibleRegistered = new HashMap<String, Boolean>();
		
		//Add initial admin account
		//At this stage, admin accounts should be hardcoded
		addAdmin("admin", "evote2016");
		
		//Add initial user for testing purposes
		addUser("a", "a");
		addUser("b", "b");
		
		//Add eligible users
		addEligible("Jacob", "Paris", "190199");
		addEligible("John", "Lewis", "450456");
		addEligible("Sally", "Jones", "348540");
		addEligible("Leigh", "Friday", "982238");
		addEligible("Olivia", "Schwarz", "934580");
		addEligible("Samantha", "Greenland", "450457");
		addEligible("John", "James", "783459");
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
	
	//Checks first name, last name and ID for a match in the eligible voters, also checks if user has already registered
	public boolean checkEligible(String first, String last, String id){
		if (eligibleSalts.get(id) != null){
			System.out.println("Eligible voter retrieval for id " + id + " has been found");
			boolean isValidFirst = false;
			boolean isValidLast = false;
			try {
				isValidFirst = validPassword(first, eligibleFirstNames.get(id), eligibleSalts.get(id));
				isValidLast = validPassword(last, eligibleLastNames.get(id), eligibleSalts.get(id));
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				System.out.println("Could not check is valid first and last names");
			}
			if (isValidFirst && isValidLast && !eligibleRegistered.get(id)){
				return true;
			}
		}
		return false;
	}
	
	public void setEligibleRegistered(String id){
		eligibleRegistered.put(id, true);
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
	
	public void addEligible(String first, String last, String id){
		try {
			byte[] salt = generateSalt();
			try {
				eligibleFirstNames.put(id, encryptPassword(first, salt));
				eligibleLastNames.put(id, encryptPassword(last, salt));
				eligibleSalts.put(id, salt);
				eligibleRegistered.put(id, false);
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
	   // PBKDF2 with SHA-1 as the hashing algorithm
	   String algorithm = "PBKDF2WithHmacSHA1";
	   int derivedKeyLength = 160;
	   int iterations = 20000;
	   KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
	   SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
	   return f.generateSecret(spec).getEncoded();
	  }



}
