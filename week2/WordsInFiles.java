
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    
    WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public void buildWordFileMap(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            map.clear(); 
            addWordsFromFile(f);
            int maximum = maxNumber();
            ArrayList<String> TestList = wordsInNumFiles(5);
            System.out.println("The maximum number of files word is in: "+maximum +" and there are "+TestList.size());
            for (int k =0;k< TestList.size(); k++) {
                System.out.println("All the words in the files "+TestList.get(k)+"");
            }
            System.out.println("\t");
    
            for (int k =0;k <TestList.size();k++){
                System.out.println("Filenames where the words are ");
                printFilesIn(TestList.get(k));
            }
        }
    }
    
    private void addWordsFromFile(File f){
        FileResource fileResource = new FileResource(f);
        String name = f.getName();
        
        for(String word:fileResource.words()){
            word = word.toLowerCase();
            if(!map.containsKey(word)){     
                ArrayList<String> newArrayList = new ArrayList<String>();
                newArrayList.add(name);
                map.put(word, newArrayList);
            }
                    
            else if (map.containsKey(word) && !map.get(word).contains(name)) {  
                ArrayList<String> currentFileNameList = map.get(word);
                currentFileNameList.add(name);
                map.put(word, currentFileNameList);
            }                                   
        }
                                                
    }
    
    public int maxNumber() {
        int max = -1;
        for (String s: map.keySet()) {
            ArrayList<String> files = map.get(s);
            if (files.size() > max) {
                max = files.size();
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int n) {
        ArrayList<String> ans = new ArrayList<String>();
        for (String w: map.keySet()) {
            ArrayList<String> files = map.get(w);
            if (files.size() == n) ans.add(w);
        }
        return ans;
    }
    
    public void printFilesIn(String word) {
        ArrayList<String> files = map.get(word);
        for (String file: files) {
            System.out.println(file);
        }
    }
    
    public void tester() {
        buildWordFileMap();
    }
}
