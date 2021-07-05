
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipher {
    public int [] countLetters(String s) {
        int [] freq = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < s.length(); i++) {
            char ch = Character.toLowerCase(s.charAt(i));
            int index = alphabet.indexOf(ch);
            if (index != -1) freq[index] += 1;
        }
        return freq;
    }
    
    public int getMaxIndex(int [] freq) {
        int max = freq[0];
        int maxIndex = -1;
        for (int i = 0; i < freq.length; i++) {
            if (max < freq[i]) {
                max = freq[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public int getKey(String s) {
        int [] freq = countLetters(s);
        int maxIndex = getMaxIndex(freq);
        int key = maxIndex - 4;
        if (maxIndex < 4)  {
            key = 26 - (4 - maxIndex);
        }
        return key;
    }
   
    public void breakCaesarCipher(String input) {
        int key = getKey(input);
        System.out.println("The key is: " + key);
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(input);
        System.out.println(decrypted);
    }
    
    public void simpleTests() {
        FileResource file = new FileResource();
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(file.asString());
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Encrypted: "+ encrypted);
        System.out.println("Decrypted: " + decrypted);
        breakCaesarCipher(encrypted);
    }
}
