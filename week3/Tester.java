
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("Total unique IP's are: " + la.countUniqueIPs());
    }
    
    public void printAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int num = 400;
        System.out.println("Total IP's greater than " + num + "are: ");
        la.printAllHigherThanNum(num);
    }
    
    public void testcountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int count = la.countUniqueIPsInRange(400, 499);
        System.out.println("Total IP's between 400 and 499 are: " + count);
    }
    
    public void testuniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        
        ArrayList<String> UniqueIPs = la.uniqueIPVisitsOnDay("Sep 24");
        for (int i = 0; i < UniqueIPs.size(); i++) System.out.println(UniqueIPs.get(i)+"\t"); 
        System.out.println("Total unique IP's is: " + UniqueIPs.size());
    }
    
    public void testmostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> IPCount = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(IPCount);
        System.out.println("Max count of IP is " + max);
    }
    
    public void testiPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> IPCount = la.countVisitsPerIP();
        ArrayList<String> ips = la.iPsMostVisits(IPCount);
        System.out.print("IPs with Maximum count is: ");
        System.out.println(ips);
    }
    
    public void testiPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println(map);
        String day = la.dayWithMostIPVisits(map);
        System.out.println("The Day that has most IP's is " + day);
    }
    
    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(map, "Sep 30");
        System.out.println(ips);
    }
}
