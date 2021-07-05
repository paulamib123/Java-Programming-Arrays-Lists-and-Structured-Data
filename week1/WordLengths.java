
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int [] counts) {
        for (String s: resource.words()) {
            StringBuilder str = new StringBuilder(s);
            int len = s.length();
            if (!Character.isLetter(s.charAt(0))) len -= 1;
            if (!Character.isLetter(s.charAt(s.length() - 1))) len -= 1;
            if (len > counts.length) counts[counts.length - 1] += 1;
            else counts[len] += 1;
        }
    }
    
    public void testCountWordLengths() {
        int[] counts = new int[31];
        FileResource f = new FileResource();
        int index = indexOfMax(f, counts);
        System.out.println(index);
    }
    
    public int indexOfMax(FileResource resource, int [] counts) {
        countWordLengths(resource, counts);
        int max = counts[0];
        int maxIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            if (max < counts[i]) {
                max = counts[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
