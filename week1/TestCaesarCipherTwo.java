
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        String result = new String();
        for (int k = start; k< message.length();k = k+2) {
          result = result + message.charAt(k);
        }
        return result;
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
    
    public void breakCaesarCipher(String input) {
        String oddChars = halfOfString(input, 0);
        String evenChars = halfOfString(input, 1);
        int key1 = getKey(oddChars);
        int key2 = getKey(evenChars);
        System.out.println("key1 = " + key1 + ", key2 = " + key2);
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - key1, 26 - key2);
        System.out.println("Encrypted: " + cct.encryptTwoKeys(input));
    }
    
    
    public void simpleTests() {
        FileResource file = new FileResource();
        //CaesarCipherTwo cct = new CaesarCipherTwo(26 - 14, 26 - 24);
        //String encrypted = cct.encryptTwoKeys(file.asString());
        //String decrypted = cct.decrypt(encrypted);
        //System.out.println("Encrypted: "+ encrypted);
        //System.out.println("Decrypted: "+ decrypted);
        breakCaesarCipher(file.asString());
    }
}
