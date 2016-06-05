package receiver;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cloud.CloudVoteCalculator;


//Represents a secure server that would store all votes
//VoteSender asks VoteReceiver to store votes
//Vote Receiver asks CloudVoteCalculator to calculate a tally of all votes

public class VoteReceiver {
	
	private enum VotingState {
		CLOSED, OPENED, FINISHED
	}
	
	private VotingState votingState = VotingState.CLOSED;
	private VotingState registrationState = VotingState.CLOSED;
	
	//Received messages
	private BigInteger encryptedVote = new BigInteger("1");
	private BigInteger cPower = new BigInteger("1");
	
	//Vote storage variables
	private List<BigInteger> votes = new ArrayList<BigInteger>();
	private List<Integer> candidateVotes = new ArrayList<Integer>();
	
	//Private key variables
	private long privateKey1;
	private long privateKey2;
	private long k;
	
	
	//Public key variables
	private long publicKeyN;
	private long publicKeyG;
	public long[] keys;
	private long prime1;
	private long prime2;
	private long primeMultiple;
	private long primeMultipleSquared;
	private long gcdCheck;
	private long g;
	
	//Big Integers
	private BigInteger bigPrivate1;
	private BigInteger bigPrivate2;
	private BigInteger n;
	private BigInteger nSquared;
	private BigInteger bigPrime1;
	private BigInteger bigPrime2;
	private BigInteger bigPrimeMultiple;
	private BigInteger bigPrimeMultipleSquared;
	
	//Used to ensure prime numbers are large
	private int primeMinimum = 200;
	private int primeMultipler = 5;
	
	//Other
	private Random rand = new Random();
	private CloudVoteCalculator cloud;
	private int votersNum = 5;
	private int candidatesNum = 5;
	
	// singleton
	private static VoteReceiver instance;
	
	private VoteReceiver(){
		cloud = new CloudVoteCalculator();
	}
	
	public void setVoters(int voters){
		votersNum = voters;
	}
	
	public int getVoters(){
		return votersNum;
	}
	
	public static VoteReceiver getInstance()
	{
      if(instance==null)
         instance=new VoteReceiver();
      
      return instance;
	}
	
	//Generates both public and private keys
	public void createKeys(){
		int tMax = (int) (votersNum * (Math.pow(10, candidatesNum-1)));
		//Ensure n is greater than tMax
		while ((prime1 * prime2) <= tMax){
			prime1 = getPrimeNumber();
			prime2 = prime1;
			
			//Ensure prime1 does not equal prime2 and that the GCD is equal to 1
			while (prime1 == prime2 || getGcd((prime1*prime2), ((prime1-1)*(prime2-1))) != 1){
				prime2 = getPrimeNumber();
			}
			System.out.println("Prime number 1 is " + prime1 + " and prime number 2 is " + prime2);
		}
		
		primeMultiple = prime1 * prime2;
		bigPrimeMultiple = BigInteger.valueOf(primeMultiple);
		primeMultipleSquared = primeMultiple * primeMultiple;
		bigPrimeMultipleSquared = BigInteger.valueOf(primeMultipleSquared);
		System.out.println("Multiple of two primes is " + primeMultiple + " and multiple squared is " + primeMultipleSquared);
		
		n = BigInteger.valueOf(primeMultiple);
		nSquared = BigInteger.valueOf(primeMultipleSquared);
		
		//Calculate private key 1 here as it is also used to choose g
		privateKey1 = getLcm((prime1 - 1), (prime2 - 1));
		
		g = getG();
		
		System.out.println("g = " + g);
		
		publicKeyN = primeMultiple;
		publicKeyG = g;
		
		keys = new long[2];
		keys[0] = publicKeyN;
		keys[1] = publicKeyG;
		
		createPrivateKey();
	}
	
	public void createPrivateKey(){
		
		BigInteger u = BigInteger.valueOf(g);
		BigInteger n = BigInteger.valueOf(primeMultiple);
		BigInteger nSquare = BigInteger.valueOf(primeMultipleSquared);
		u = u.pow((int) privateKey1);
		u = u.remainder(nSquare);
		BigInteger L = u.subtract(BigInteger.ONE);
		L = L.divide(n);
		
		BigInteger privateKey = L.modPow(BigInteger.ONE.negate(), n);
		
		privateKey2 = privateKey.longValue();
		System.out.println("Calculated private key 2 is " + privateKey2);
		System.out.println("Private keys are " + privateKey1 + " and " + privateKey2);
		
		bigPrivate1 = BigInteger.valueOf(privateKey1);
		bigPrivate2 = BigInteger.valueOf(privateKey2);
	}
	
	
	public void receiveVote(BigInteger vote){
		this.encryptedVote = vote;
		votes.add(encryptedVote);
		System.out.println("Receiver thinks encrypted vote is " + this.encryptedVote);
		//decryptVote();
	}
	
	
	//Decryption
	public void decryptVote(){
		System.out.println("");
		System.out.println("BEGINNING DECRYPTION");
		
		cPower = encryptedVote.pow((int)privateKey1);
		BigInteger n = bigPrimeMultiple;
		BigInteger nSquare = bigPrimeMultipleSquared;
		BigInteger one = new BigInteger("1");
		
		BigInteger mod1 = cPower.remainder(nSquare);
		System.out.println("mod1 is " + mod1);
		BigInteger minusOne = mod1.subtract(one);
		System.out.println("mod1 minus one is " + minusOne);
		BigInteger divide = minusOne.divide(n);
		System.out.println("mod1 minus one divided by n is " + divide);
		BigInteger multiple = divide.multiply(bigPrivate2);
		
		//BigInteger mod2 = bigPrivate2.remainder(n);
		//System.out.println("private key 2 remainder when divided by n is " + mod2);
		
		BigInteger decrypted = multiple.remainder(n);
		
		System.out.println("Decrypted vote is " + decrypted);
	
	}
	
	
	//Number selection methods
	
	public int getPrimeNumber(){
		
		int num = 0;
		
		num = rand.nextInt(2000) + 1 + primeMinimum;
		primeMultipler = rand.nextInt(2000) + 1;
		
		while(!isPrime(num)){
			num = rand.nextInt(2000) + 1 + primeMinimum;
		}
		
		return num;
	}
	
	//Number selection methods
	
	public BigInteger getBigPrimeNumber(){
		
		BigInteger num = new BigInteger("1");
		
		num = BigInteger.valueOf(rand.nextInt(10000) + 1 + primeMinimum);
		
		while(!isBigPrime(num)){
			num = BigInteger.valueOf(rand.nextInt(10000) + 1 + primeMinimum);
		}
		
		return num;
	}
	
	public int getRandom(){
		int num = 0;
		num = rand.nextInt(100) + 1 + primeMinimum;
		//primeMultipler = rand.nextInt(100) + 1;
		//num = num * primeMultipler;
		return num;
	}
	
	//Returns a valid g 
	public long getG(){
		System.out.println("");
		System.out.println("FINDING G");
		BigInteger u = new BigInteger("1000000000000000000000000000000000");
		
		long g = rand.nextInt(10000);
		while(getGcd(g, primeMultipleSquared) != 1 || u.compareTo(BigInteger.ONE) != 0){
			g = rand.nextInt(10000);
			
			u = BigInteger.valueOf(g);
			System.out.println("U has taken value of random g and is " + u);
			u = u.pow((int) privateKey1);
			
			BigInteger bigPrimeMultipleSquared = BigInteger.valueOf(primeMultipleSquared);
			BigInteger bigPrimeMultiple = BigInteger.valueOf(primeMultiple);
			
			u = u.remainder(bigPrimeMultipleSquared);
			u = u.subtract(BigInteger.ONE);
			u = u.divide(bigPrimeMultiple);
			System.out.println("Value for u to be GCD compared with primeMultiple is " + u);
			u = getBigGcd(u, bigPrimeMultiple);
			
			System.out.println("failed GCD calculation for G is " + u);
		}
		System.out.println("successful GCD calculation for G is " + u);
		System.out.println("");
		return g;
		
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
	
	private static boolean isBigPrime(BigInteger num){
		BigInteger three = new BigInteger("3");
		BigInteger two = new BigInteger("2");
		if (num.compareTo(three) == -1 || num.remainder(two) == BigInteger.ZERO) {
			//this returns false if number is <=1 & true if number = 2 or 3
			return num == two || num == three;
		}
		int divisor = 3;
		BigInteger squareRoot = sqrt(num);
		BigInteger threeRemainder = num.remainder(three);
		while (three.compareTo(squareRoot) == 0 || three.compareTo(squareRoot) == 1 && (threeRemainder.compareTo(BigInteger.ZERO) != 1)){
			//iterate through all possible divisors
			three.add(two); 
		}
		return num.remainder(three).compareTo(BigInteger.ZERO) != 1; 
	}
	
	
	//Calculates square root of BigInteger
	private static BigInteger sqrt(BigInteger n) {
		  BigInteger a = BigInteger.ONE;
		  BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		  while(b.compareTo(a) >= 0) {
		    BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
		    if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
		    else a = mid.add(BigInteger.ONE);
		  }
		  return a.subtract(BigInteger.ONE);
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
	
	public static BigInteger getBigGcd(BigInteger num1, BigInteger num2){
			
			while (num2.compareTo(BigInteger.ZERO) == 1){
				BigInteger temp = num2;
				num2 = num1.remainder(num2);
				num1 = temp;
			}
			
			return num1;
		}
	
	private static long getLcm(long a, long b){
		return a * (b / getGcd(a, b));
	}
	
	private static BigInteger getBigLcm(BigInteger a, BigInteger b){
		return a.multiply(b.divide(getBigGcd(a, b)));
	}
	
	//Cloud calculation methods
	
	public List<Integer> tallyVotes(){
		BigInteger tally = cloud.addEncrypted(votes, nSquared);
		return decryptTally(tally);
	}
	
	public List<Integer> decryptTally(BigInteger tally){
		BigInteger u = tally.pow((int)privateKey1);
		u = u.remainder(nSquared);
		u = u.subtract(BigInteger.ONE);
		u = u.divide(n);
		BigInteger L = u.multiply(bigPrivate2);
		BigInteger m = L.remainder(n);
		int intM = m.intValue();
		System.out.println("Decrypted tally is " + m);
		candidateVotes.add((int) Math.floor((intM / Math.pow(10, 0)) % 10));
		candidateVotes.add((int) Math.floor((intM / Math.pow(10, 1)) % 10));
		candidateVotes.add((int) Math.floor((intM / Math.pow(10, 2)) % 10));
		candidateVotes.add((int) Math.floor((intM / Math.pow(10, 3)) % 10));
		candidateVotes.add((int) Math.floor((intM / Math.pow(10, 4)) % 10));
		candidateVotes.add((int) Math.floor((intM / Math.pow(10, 5)) % 10));
		candidateVotes.add((int) Math.floor((intM / Math.pow(10, 6)) % 10));
		
		System.out.println("Number of votes for candidate 1 = " + (Math.floor((intM / Math.pow(10, 0)) % 10)));
		System.out.println("Number of votes for candidate 2 = " + (Math.floor((intM / Math.pow(10, 1)) % 10)));
		System.out.println("Number of votes for candidate 3 = " + (Math.floor((intM / Math.pow(10, 2)) % 10)));
		
		return candidateVotes;
	}
	
	public String getVotingState(){
		switch (votingState){
			case CLOSED:
				return "closed";
			case OPENED:
				return "opened";
			case FINISHED:
				return "finished";
		}
		return "";
	}
	
	public String getRegistrationState(){
		switch (registrationState){
			case CLOSED:
				return "closed";
			case OPENED:
				return "opened";
			case FINISHED:
				return "finished";
		}
		return "";
	}
	
	public void setVotingState(String state){
		if (state == "opened")
			votingState = VotingState.OPENED;
		if (state == "closed")
			votingState = VotingState.CLOSED;
		if (state == "finished")
			votingState = VotingState.FINISHED;
	}
	
	public void setRegistrationState (String state){
		if (state == "opened")
			registrationState = VotingState.OPENED;
		if (state == "closed")
			registrationState = VotingState.CLOSED;
		if (state == "finished")
			registrationState = VotingState.FINISHED;
	}
}
