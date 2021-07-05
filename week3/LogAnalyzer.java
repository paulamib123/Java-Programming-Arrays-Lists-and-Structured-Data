
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
      
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource file = new FileResource(filename);
         for (String line: file.lines()) {
             WebLogParser wlp = new WebLogParser();
             records.add(wlp.parseEntry(line));
            }
     }
     
     public int countUniqueIPs() {
         HashSet<String> ips = new HashSet<String>();
         for (int i = 0; i < records.size(); i++) {
             LogEntry le = records.get(i);
             ips.add(le.getIpAddress());
         }
         return ips.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (int i = 0; i < records.size(); i++) {
             LogEntry le = records.get(i);
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }
     
     public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry New: records) {
            int statusCode = New.getStatusCode();
            String ipAddr = New.getIpAddress();
            if((statusCode >= low) && (statusCode <= high)) {
                if(!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String day) {
         ArrayList<String> UniqueIPs = new ArrayList<String>();
         for(LogEntry le: records) {
             Date d = le.getAccessTime();
             String ip = le.getIpAddress();
             String str = d.toString();
             if (str.contains(day) && !UniqueIPs.contains(ip)) {
                 UniqueIPs.add(ip);
             }
         }
         return UniqueIPs;
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> IPCount = new HashMap<String, Integer>();
         for (LogEntry le: records) {
             String ip = le.getIpAddress();
             if (!IPCount.containsKey(ip)) {
                 IPCount.put(ip, 1);
             } else {
                 int count = IPCount.get(ip);
                 IPCount.put(ip, count + 1);
             }
         }
         return IPCount;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> IPCount){
         int max = -1;
         for (String ip: IPCount.keySet()) {
             int count = IPCount.get(ip);
             if (count > max) max = count;
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> IPCount) {
         ArrayList<String> ips = new ArrayList<String>();
         int max = mostNumberVisitsByIP(IPCount);
         for (String ip: IPCount.keySet()) {
             int count = IPCount.get(ip);
             if (count == max) ips.add(ip);
         }
         return ips;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         for (LogEntry le: records) {
             
             Date d = le.getAccessTime();
             String ip = le.getIpAddress();
             String str = d.toString().substring(4,10);
             if (!map.containsKey(str)) {
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ip);
                 map.put(str, ips);
             } else {
                 ArrayList<String> ips = map.get(str);
                 ips.add(ip);
                 map.put(str, ips);
             }
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayCount){
         int max = -1;
         String days = "";
         for (String day: dayCount.keySet()) {
             int count = dayCount.get(day).size();
             if (count > max) {
                 max = count;
                 days = day;
             }
         }
         return days;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
         ArrayList<String> ips = new ArrayList<String>();
         ips = uniqueIPVisitsOnDay(day);
         return ips;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
