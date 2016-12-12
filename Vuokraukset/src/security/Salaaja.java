package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class Salaaja {
		public static final String MD5 = "MD5";
		public static final String SHA1 = "SHA-1";
		public static final String SHA256 = "SHA-256";
		public static final String SHA384 = "SHA-384";
		public static final String SHA512 = "SHA-512";

	
		public static String salaa(String salattavaTeksti, String suola,
				String salausalgoritmi, int montakoKertaa)
				throws NoSuchAlgorithmException, UnsupportedEncodingException {

			MessageDigest md = MessageDigest.getInstance(salausalgoritmi);
			md.reset();
			md.update(suola.getBytes("UTF-8"));
			byte[] input = md.digest(salattavaTeksti.getBytes("UTF-8"));
			for (int i = 0; i < montakoKertaa; i++) {
				md.reset();
				input = md.digest(input);
			}

			String salattuTeksti = DatatypeConverter.printBase64Binary(input);
			return salattuTeksti;

		}
		

		public static String generoiSuola() throws NoSuchAlgorithmException {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] suolaBin = new byte[8];
			random.nextBytes(suolaBin);
			String suolaStr = DatatypeConverter.printBase64Binary(suolaBin);

			return suolaStr;
		}
	}
