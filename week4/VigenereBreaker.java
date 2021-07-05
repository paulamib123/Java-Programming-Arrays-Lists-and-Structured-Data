import java.util.*;
import edu.duke.*;


public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String result = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            result += message.charAt(i);
        }
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i  = 0; i < key.length; i++) {
            key[i] = cc.getKey(sliceString(encrypted, i, klength));
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> set = new HashSet<String>();
        for (String line : fr.lines()) {
                set.add(line.toLowerCase());
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String[] words = message.split("\\W+");
        for (int i = 0; i < words.length; i++) {
            if (dictionary.contains(words[i].toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCount = -1;
        String answer = "";
        int[] maxKey = {};
        char ch = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted,i, ch);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > maxCount) {
                maxCount = count;
                answer = decrypted;
                maxKey = key;
            }
        }
        System.out.println("Max count is: " + maxCount);
        System.out.println("Max key array length is: " + maxKey.length);
        return answer.substring(0, 100);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary)  
    {
        int[] counts = new int[26];
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int max = -1;
        char ch = '\0';
        
        for (String word: dictionary) {
            for (int i = 0; i < word.length(); i++) {
                int index = alphabets.indexOf(word.charAt(i));
                if (index < 0 || index >= 26) continue;
                else counts[index]++;
            }
        }
        
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
                ch = alphabets.charAt(i);
            }
        }
        
        return ch;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) 
    {
        for (String language: languages.keySet()) {
            System.out.println(breakForLanguage(encrypted, languages.get(language)));
            System.out.println("Language is: " + language);
        }
    }
    
    public void test() {
        FileResource dictionary = new FileResource();
        System.out.println("Most Common Char is: " + mostCommonCharIn(readDictionary(dictionary)));
    }
    
    public HashSet<String> helperBreakVigenere() {
        FileResource dictionary = new FileResource();
        HashSet<String> dict = readDictionary(dictionary);
        return dict;
    }
    
    public void breakVigenere() {
        FileResource file = new FileResource();
        String encrypted = file.asString();
        HashMap<String, HashSet<String>> languages = new HashMap();
        
       String[] lang = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
       for (int i = 0; i < lang.length; i++) {
            languages.put(lang[i], helperBreakVigenere());
            System.out.println("Added dictionary for: " + lang[i]);
       }
       breakForAllLangs(encrypted, languages);
    }
}
