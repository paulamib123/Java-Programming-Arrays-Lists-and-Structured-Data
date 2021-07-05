
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    
    public CaesarCipherTwo(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encryptTwoKeys(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                encrypted.setCharAt(i, encryptChar(key1, encrypted.charAt(i)));
            } else {
                encrypted.setCharAt(i, encryptChar(key2, encrypted.charAt(i)));
            }
        }
        return encrypted.toString();
    }
    
    public char encryptChar(int key, char ch){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String upperShiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        String lowerShiftedAlphabet = upperShiftedAlphabet.toLowerCase();
        char newCh = '\0';
        
        int index = alphabet.indexOf(Character.toUpperCase(ch));
        if (index != -1) {
            if (Character.isUpperCase(ch)) {
                newCh = upperShiftedAlphabet.charAt(index);
                return newCh;
            } else {
                newCh = lowerShiftedAlphabet.charAt(index);
                return newCh;
            }
        } else {
            return ch;
        } 
    }
    
    public String decrypt(String encrypted) {
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - key1, 26 - key2);
        String decrypted = cct.encryptTwoKeys(encrypted);
        return decrypted;
    }
}
