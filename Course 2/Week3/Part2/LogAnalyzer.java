


import java.util.*;

import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            int statuscode = le.getStatusCode();
            if (!uniqueIPs.contains(ipAddr))
                uniqueIPs.add(ipAddr);
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int currStatusCode = le.getStatusCode();
            if (currStatusCode > num)
                System.out.println(le);
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPVisitsOnDayList = new ArrayList<String>();
        for (LogEntry le : records) {
            Date d = le.getAccessTime();
            String str = d.toString();
            str = str.substring(4, 10);
            if (str.equals(someday) && (!uniqueIPVisitsOnDayList.contains(le.getIpAddress()))) {
                uniqueIPVisitsOnDayList.add(le.getIpAddress());
            }
        }
        return uniqueIPVisitsOnDayList;
    }

    public int countUniqueIPsInRange(int low,int high)
    {
        ArrayList<String> uniqueIPslist = new ArrayList<String>();
        for(LogEntry le : records)
        {
            String currIP = le.getIpAddress();
            if((!uniqueIPslist.contains(currIP))&&(le.getStatusCode()>=low && le.getStatusCode()<=high))
                uniqueIPslist.add(currIP);
        }
        return uniqueIPslist.size();
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }


}
