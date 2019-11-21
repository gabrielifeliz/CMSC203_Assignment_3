//package assignment3;

/**
 * This class contains the methods necessary to encrypt/decrypt a Caesar or Bellaso Cipher
 * @author Gabriel I Feliz
 *
 */
public class CryptoManager {

	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds(String plainText) {
		// Assume string is in bound unless proven otherwise
		boolean isWithinRange = true;
		// Read string to be encrypted as an array of characters through enhanced for loop
		for (char c : plainText.toCharArray()) {
			if (!(c >= LOWER_BOUND && c <= UPPER_BOUND)) {
				isWithinRange = false; // Set whether the current character is within range to false
				break; // Break out of the loop
			}
		}
		return isWithinRange;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encrypted = ""; // Initialize encrypted with an empty string
		// Perform encryption only if characters in plain text are in bounds
		if (stringInBounds(plainText)) {		
			// Read string to be encrypted as an array of characters through enhanced for loop
			for (char c : plainText.toCharArray()) {
				int encryption = c + key;
				while (encryption > UPPER_BOUND) {
					encryption -= RANGE;
				}
				encrypted += (char)(encryption); // Encrypt each character by adding ASCII code by a key number
			}
		}
		return encrypted; // Return encrypted text
	}

	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String encrypted = ""; // Initialize encrypted with an empty
		if (stringInBounds(plainText)) {
			// Iterate as many times as the size of string to be encrypted to change ASCII value of each character in the string
			for (int i=0, j=0; i < plainText.length(); i++, j++) {
				if (j == bellasoStr.length()) {	j = 0; } // Handle ArrayIndexOutOfBoundsException Reset index test for Bellaso string if needed
				int bellasoCharNum = plainText.charAt(i) + bellasoStr.charAt(j); // Encrypt each character by performing initial Bellaso Cipher calculation

				while (bellasoCharNum > UPPER_BOUND) {
					bellasoCharNum -= RANGE;
				}
				encrypted += (char)(bellasoCharNum); // Perform special Bellaso Cipher calculation for an ASCII number > 95
			}
		}
		return encrypted; // Return encrypted text
	}

	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		// Don't decrypt if encryption fails
		if (encryptedText.equals("encryption failed")) {
			return "";
		}

		String decrypted = ""; // Initialize decrypted with an empty
		// Read encrypted string as an array of characters through enhanced for loop
		for (char c : encryptedText.toCharArray()) {
			int decryption = c - key; // Decrypt each character by subtracting ASCII code by a key number
			while (decryption < LOWER_BOUND) {
				decryption += RANGE;
			}
			decrypted += (char)(decryption); 		
		}
		return decrypted; // Return decrypted text
	}

	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		// Don't decrypt if encryption fails
		if (encryptedText.equals("encryption failed")) {
			return "";
		}

		String decrypted = ""; // Initialize decrypted with an empty
		// Iterate as many times as the size of encrypted string to change ASCII value of each character in the string
		for (int i=0, j=0; i < encryptedText.length(); i++, j++) {
			if (j == bellasoStr.length()) {	j = 0; } // Handle ArrayIndexOutOfBoundsException Reset index test for Bellaso string if needed
			int bellasoCharNum = encryptedText.charAt(i) - bellasoStr.charAt(j); // Decrypt each character by performing reverse initial Bellaso Cipher calculation
			while (bellasoCharNum < LOWER_BOUND) {
				bellasoCharNum += RANGE; // Perform reverse special Bellaso Cipher calculation
			}
			decrypted += (char)(bellasoCharNum); // Add decryption to decrypted message
		}
		return decrypted; // Return decrypted text
	}
}
