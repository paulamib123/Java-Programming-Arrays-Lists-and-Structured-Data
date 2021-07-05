
/**
 * Write a description of WordPlay here.
 * 
 * @author (Paulami Bhattacharya)
 * @version (22/03/2021)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            return true;
        return false;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder str  = new StringBuilder(phrase);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (isVowel(str.charAt(i)))
                ans.append(ch);
            else
                ans.append(str.charAt(i));
        }
        return ans.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder str = new StringBuilder(phrase);
        for (int i = 0; i < str.length(); i++) {
            if (Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) 
                    str.setCharAt(i, '*');
                else
                    str.setCharAt(i, '+');
            }
        }
        return str.toString();
    }
    
    public void test() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
    
}
