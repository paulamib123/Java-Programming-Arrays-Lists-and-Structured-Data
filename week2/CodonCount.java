
/**
 * Write a description of CondonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String, Integer> map;
    
    CodonCount() {
        map = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(String dna, int start) {
        map.clear();
        for (int i = start; i < dna.length() - 3; i += 3) {
            String codon = dna.substring(i, i + 3);
            if (map.containsKey(codon)) {
                int key = map.get(codon);
                map.put(codon, key + 1);
            } else {
                map.put(codon, 1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        int max = -1;
        String ans = "";
        for (String codon: map.keySet()) {
            int count = map.get(codon);
            if (count > max) {
                max = count;
                ans = codon;
            }
        }
        return ans;
    }
    
    public void printCodonCounts(int start, int end) {
        for (String codon: map.keySet()) {
            int count = map.get(codon);
            if (count >= start && count <= end) {
                System.out.println("Codon is: " + codon + " and count is: " + count);
            }
        }
    }
    
    public void tester() {
        //FileResource file = new FileResource();
        //String dna = file.asString();
        String dna = "ATTAATACTTTGTTTAACAGTAATTATTCAACTATTAAATATTTAAATAATTAAGTTATTAAACTATTAAGTACAGGGCCTGTATCTCTGATGCTGAACTATGATGTGTGACTTAAGCCCCCAAATACATCATGTTATTTGGATCCAAGGTGCTGCACAGAACGCTGACCCTCTCTAAGAGCTGGGTATTACT";
        
        System.out.println("FRAME 1");
        buildCodonMap(dna, 0);
        System.out.println("Total Codon Counts is: " + map.size());
        String commonCodon = getMostCommonCodon();
        int count = map.get(commonCodon);
        System.out.println("Most common codon is: " + commonCodon + " and count is: " + count);
        printCodonCounts(4, 4);
        
        System.out.println("FRAME 2");
        buildCodonMap(dna, 1);
        System.out.println("Total Codon Counts is: " + map.size());
        commonCodon = getMostCommonCodon();
        count = map.get(commonCodon);
        System.out.println("Most common codon is: " + commonCodon + " and count is: " + count);
        printCodonCounts(4, 4);
        
        System.out.println("FRAME 3");
        buildCodonMap(dna, 2);
        System.out.println("Total Codon Counts is: " + map.size());
        commonCodon = getMostCommonCodon();
        count = map.get(commonCodon);
        System.out.println("Most common codon is: " + commonCodon + " and count is: " + count);
        printCodonCounts(4, 4);
    }
}
