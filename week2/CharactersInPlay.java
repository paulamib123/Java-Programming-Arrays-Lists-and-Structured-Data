
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> myWords; 
    private ArrayList<Integer> myFreqs; 
    
    CharactersInPlay() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void update(String s) {
        int index = myWords.indexOf(s);
        if (index == -1) {
            myWords.add(s);
            myFreqs.add(1);
        } else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value + 1);
        }
    }
    
    public void findAllCharacters() {
        myWords.clear();
        myFreqs.clear();
        FileResource file = new FileResource();
        String ans = "";
        int i = 0;
        for (String line: file.lines()) {
            if (line.contains(".")) {
               int idx = line.indexOf(".");
               String possible_name = line.substring(0,idx);
               update(possible_name);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < myWords.size(); i++) {
            if (myFreqs.get(i) >= num1 && myFreqs.get(i) <= num2)
                System.out.println(myWords.get(i) + " : " + myFreqs.get(i));
        }
    }
    
    public void tester() {
        findAllCharacters();
        System.out.println("********************");
        for (int i = 0; i < myWords.size(); i++) {
            if (myFreqs.get(i) > 1)
                System.out.println(myWords.get(i) + " : " + myFreqs.get(i));
        }
        System.out.println("********************");
        charactersWithNumParts(10, 15);
    }
}
