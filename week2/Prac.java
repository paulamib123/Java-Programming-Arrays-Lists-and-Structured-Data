
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Kenneth Umali 
 * @version 22.2.2017
 */
import edu.duke.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
public class Prac {

    private HashMap<String, ArrayList<String>> wordsMap;
    
    public Prac() {
        wordsMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for(String word : fr.words()) {
            ArrayList<String> words;
            if(!wordsMap.containsKey(word)) {
                words = new ArrayList<String>();
                words.add(f.getName());
                wordsMap.put(word, words);
            } else {
                words = wordsMap.get(word);
                if(!words.contains(f.getName())) {
                    words.add(f.getName());
                    wordsMap.put(word, words);
                }
            }
        }
    }
    
    void buildWordFileMap() {
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    int maxNumber() {
        int size = 0;
        for(String word : wordsMap.keySet()) {
            ArrayList<String> filenames = new ArrayList<String>();
            filenames = wordsMap.get(word);
            int currentSize = filenames.size();
            if(currentSize > size) {
                size = currentSize;
            }
        }
        return size;
    }
    
    ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for(String key : wordsMap.keySet()) {
            if(wordsMap.get(key).size() == number) {
                words.add(key);
            }
        }
        return words;
    }
    
    void printFilesIn(String word) {
        for(String key : wordsMap.keySet()) {
            ArrayList<String> filename = new ArrayList<String>();
            filename = wordsMap.get(key);
            if(word.equals(key)) {
                for(int i = 0; i < filename.size(); i++) {
                    System.out.println(filename.get(i));
                }
            }
        }
    }
    
    void tester() {
       buildWordFileMap();
       for (String w: wordsMap.keySet()) {
           System.out.println("Word " + w + " is in " + wordsMap.get(w).size() + " files");
       }
       ArrayList<String> ans = wordsInNumFiles(0);
       for(int i = 0; i < ans.size(); i++) System.out.println(ans.get(i));
       System.out.println("Total words is: " + ans.size());
    }
    
    
}
