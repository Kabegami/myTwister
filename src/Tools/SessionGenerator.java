package Tools;

import java.security.SecureRandom;
import java.math.BigInteger;

public class SessionGenerator {

	public static String nextKey() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}