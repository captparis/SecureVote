package voter;

import java.math.BigInteger;

//Represents a client application that would allow for secure voting

import java.util.Random;

import receiver.VoteReceiver;

public class VoteSender {
	
	private int encryptedVote;
	private long random;
	
	private BigInteger[] publicKeys;
	
	private VoteReceiver voteReceiver;
	
	public BigInteger encrypted;
	public BigInteger randomPower;
	public BigInteger nSquare;
	
	//Holds username of active user
	public String activeUser = "";
	

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
		while(getGcd(random, publicKeys[0].intValue()) != 1){
			random = rand.nextInt(10000) + 1;
		}
		System.out.println("");
		System.out.println("Public key 1 is " + publicKeys[0] + " and public key 2 is " + publicKeys[1]);
		encrypted = publicKeys[1];
		encrypted = encrypted.pow(vote);
		randomPower = BigInteger.valueOf(random);
		randomPower = randomPower.pow(publicKeys[0].intValue());
		encrypted = encrypted.multiply(randomPower);
		
		nSquare = publicKeys[0].multiply(publicKeys[0]);
		
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
	

}
