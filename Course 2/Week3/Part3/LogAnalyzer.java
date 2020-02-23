import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

public class LogAnalyzer
{
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

    public HashMap<String,Integer> countVisitorsPerIP()
    {
        HashMap<String,Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le : records)
        {
            String currIPAdrr = le.getIpAddress();
            if(!counts.containsKey(currIPAdrr))
            {
                counts.put(currIPAdrr,1);
            }
            else
            {
                counts.put(currIPAdrr,counts.get(currIPAdrr)+1);
            }
        }
        return counts;
    }

}
