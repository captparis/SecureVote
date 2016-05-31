package receiver;

import java.util.Random;

public class VoteReceiver {
	
	//Received messages
	private long encryptedVote;
	
	//Private key variables
	private long privateKey1;
	private long privateKey2;
	private long k;
	
	
	//Public key variables
	private long publicKeyN;
	private long publicKeyG;
	
	private long prime1;
	private long prime2;
	private long primeMultiple;
	private long primeMultipleSquared;
	private long gcdCheck;
	private long g;
	
	//Used to ensure prime numbers are large
	private int primeMinimum = 1000;
	private int primeMultipler = 5;
	
	public VoteReceiver(){
	}
	
	public long[] createPublicKey(){
		prime1 = getPrimeNumber();
		prime2 = prime1;
		
		//Ensure prime1 does not equal prime2 and that the GCD is equal to 1
		while (prime1 == prime2 || getGcd(prime1, prime2) != 1){
			prime2 = getPrimeNumber();
		}
		System.out.println("Prime number 1 is " + prime1 + " and prime number 2 is " + prime2);
		
		primeMultiple = prime1 * prime2;
		primeMultipleSquared = primeMultiple * primeMultiple;
		System.out.println("Multiple of two primes is " + primeMultiple + " and multiple squared is " + primeMultipleSquared);
		
		gcdCheck = primeMultiple;
		
		
		while (getGcd(gcdCheck, primeMultipleSquared) != 1){
			gcdCheck = getRandom();
		}
		
		g = gcdCheck;
		
		System.out.println("g = " + g);
		
		publicKeyN = primeMultiple;
		publicKeyG = g;
		
		long[] keys = new long[2];
		keys[0] = publicKeyN;
		keys[1] = publicKeyG;
		
		createPrivateKey();
		
		return keys;
	}
	
	public void createPrivateKey(){
		privateKey1 = getLcm((prime1 - 1), (prime2 - 1));
		
		long u = (long) Math.pow(g, privateKey1);
		u = getGcd(u, primeMultipleSquared);
		
		k = (u - 1)/primeMultiple;
		
		long kPower = (long) Math.pow(k, -1);
		
		privateKey2 = getGcd(kPower, primeMultiple);		
	}
	
	
	public void receiveVote(long vote){
		this.encryptedVote = vote;
		System.out.println("Receiver thinks encrypted vote is " + this.encryptedVote);
		decryptVote();
	}
	
	
	//Decryption
	public void decryptVote(){
		long temp = ((long) Math.pow(encryptedVote, privateKey1)) % primeMultipleSquared;
		System.out.println("temp is " + temp);
		long lTemp = (temp - 1) / primeMultiple; 
		System.out.println("lTemp is " + lTemp);
		long decrypted = lTemp * (privateKey2 % primeMultiple);
		System.out.println("Decrypted vote is " + decrypted);
	}
	
	
	//Number selection methods
	
	public int getPrimeNumber(){
		
		int num = 0;
		Random rand = new Random();
		num = rand.nextInt(1000) + 1 + primeMinimum;
		primeMultipler = rand.nextInt(10000) + 1;
		
		while(!isPrime(num)){
			num = rand.nextInt(10000) + 1 + primeMinimum;
		}
		
		return num;
	}
	
	public int getRandom(){
		int num = 0;
		Random rand = new Random();
		num = rand.nextInt(1000) + 1 + primeMinimum;
		primeMultipler = rand.nextInt(1000) + 1;
		num = num * primeMultipler;
		return num;
	}
	
	
	//Calculation methods
	
	private static boolean isPrime(int num){
		if (num <= 3 || num % 2 == 0) {
			//this returns false if number is <=1 & true if number = 2 or 3
			return num == 2 || num == 3;
		}
		int divisor = 3;
		while ((divisor <= Math.sqrt(num)) && (num % divisor != 0)){
			//iterate through all possible divisors
			divisor += 2; 
		}
		return num % divisor != 0; 
	}
	
	//TODO move this to Application Controller or another separate class
	public static long getGcd(long num1, long num2){
		
		while (num2 > 0){
			long temp = num2;
			num2 = num1 % num2;
			num1 = temp;
		}
		
		return num1;
	}
	
	private static long getLcm(long a, long b){
		return a * (b / getGcd(a, b));
	}
}
