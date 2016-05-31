package voter;

import java.util.Random;

import receiver.VoteReceiver;

public class VoteSender {
	
	private int encryptedVote;
	private long random;
	
	private long[] publicKey;
	
	private VoteReceiver voteReceiver;
	

	public VoteSender() {
		voteReceiver = new VoteReceiver();
	}
	
	public void setPublicKey(){
	}
	
	public void encryptVote(int vote){
		System.out.println("Encrypting Vote");
		Random rand = new Random();
		random = rand.nextInt(10000) + 1;
		System.out.println("");
		System.out.println("Public key 1 is " + publicKey[0] + " and public key 2 is " + publicKey[1]);
		long encrypted = ((long)Math.pow(publicKey[1], vote)) * ((long)Math.pow(random, publicKey[0]));
		encrypted = voteReceiver.getGcd(encrypted, (publicKey[0] * publicKey[0]));
		
		System.out.println("Sender thinks encrypted vote is " + encrypted);
		
		voteReceiver.receiveVote(encrypted);
	}
	
	public void sendEncryptedVote(){
		
	}
	
	public void sendVote(int candidate){
		System.out.println("Candidate is " + candidate);
		publicKey = voteReceiver.createPublicKey();
		encryptVote(candidate);
	}
	

}
