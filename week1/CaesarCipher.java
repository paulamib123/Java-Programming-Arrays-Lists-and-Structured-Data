
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    private String alphabet; 
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        String upperShiftedAlphabet = shiftedAlphabet.toUpperCase();
        String lowerShiftedAlphabet = shiftedAlphabet.toLowerCase();
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(ch));
            if (index != -1) {
                if (Character.isUpperCase(ch)) {
                    char newCh = upperShiftedAlphabet.charAt(index);
                    encrypted.setCharAt(i, newCh);
                } else {
                    char newCh = lowerShiftedAlphabet.charAt(index);
                    encrypted.setCharAt(i, newCh);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decrypted = cc.encrypt(encrypted);
        return decrypted;
    }
}
