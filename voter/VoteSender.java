package voter;

import java.math.BigInteger;

//Represents a client application that would allow for secure voting

import java.util.Random;

import receiver.VoteReceiver;

public class VoteSender {
	
	private int encryptedVote;
	private long random;
	
	private long[] publicKeys;
	
	private VoteReceiver voteReceiver;
	
	public BigInteger encrypted;
	public BigInteger randomPower;
	public BigInteger nSquare;
	
	//Holds username of active user
	//public String activeUser = "";
	

	public VoteSender() {
		voteReceiver = VoteReceiver.getInstance();
		encrypted = new BigInteger("1");
		randomPower = new BigInteger("1");
		nSquare = new BigInteger("1");
	}
	
	public void setPublicKey(){
	}
	
	public void encryptVote(int vote){		
		System.out.println("Encrypting Vote, candidate is " + vote);
		Random rand = new Random();
		random = rand.nextInt(1000) + 1;
		while(getGcd(random, publicKeys[0]) != 1){
			random = rand.nextInt(10000) + 1;
		}
		System.out.println("");
		System.out.println("Public key 1 is " + publicKeys[0] + " and public key 2 is " + publicKeys[1]);
		encrypted = BigInteger.valueOf(publicKeys[1]);
		encrypted = encrypted.pow(vote);
		randomPower = BigInteger.valueOf(random);
		randomPower = randomPower.pow((int) publicKeys[0]);
		encrypted = encrypted.multiply(randomPower);
		
		nSquare = BigInteger.valueOf(publicKeys[0] * publicKeys[0]);
		
		encrypted = encrypted.remainder(nSquare);
		
		System.out.println("Sender thinks encrypted vote is " + encrypted);
		
		voteReceiver.receiveVote(encrypted);
	}
	
	public void sendEncryptedVote(){
		
	}
	
	public void requestPublicKeys(){
		publicKeys = voteReceiver.keys;
	}
	
	public void sendVote(int candidate1, int candidate2){
		System.out.println("Candidate 1 is " + candidate1 + " candidate 2 is " + candidate2);
		int candidateValue = (int) Math.pow(10, (candidate1 - 1));
		//Check is a second candidate was selected
		if (candidate2 != 0)
			candidateValue = candidateValue + ((int) Math.pow(10, (candidate2 - 1)));
		System.out.println("Sending candidate value of " + candidateValue);
		encryptVote(candidateValue);
	}
	
	//VoteSender requires its own getGcd, as accessing server method would not be ideal in real world application
	public static long getGcd(long num1, long num2){
		
		while (num2 > 0){
			long temp = num2;
			num2 = num1 % num2;
			num1 = temp;
		}
		
		return num1;
	}
	
	//Raises a BigInteger to a power of another BigInteger (default methods only allow for power of int)
	public BigInteger pow(BigInteger base, BigInteger exponent){
		System.out.println("Getting " + base + " to the power of " + exponent);
		BigInteger result = BigInteger.ONE;
		while (exponent.signum() > 0) {
			if (exponent.testBit(0)) result = result.multiply(base);
			base = base.multiply(base);
			exponent = exponent.shiftRight(1);
			System.out.println("Power of result is " + result);
		}
		
		return result;
	}

}
