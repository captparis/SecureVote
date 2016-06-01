package cloud;

import java.math.BigInteger;
import java.util.List;

public class CloudVoteCalculator {

	public CloudVoteCalculator() {
	}
	
	public BigInteger addEncrypted(List<BigInteger> allVotes, BigInteger nSquare){
		BigInteger tally = new BigInteger("1");
		for (int i = 0; i < allVotes.size(); i++){
			tally = tally.multiply(allVotes.get(i));
			System.out.println("Multiplying by " + (allVotes.get(i)) + " total is now " + tally);
		}
		tally = tally.remainder(nSquare);
		System.out.println("Tally of all encrypted is " + tally);
		return tally;
		
	}

}
