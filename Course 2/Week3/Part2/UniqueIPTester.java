import java.util.ArrayList;

public class UniqueIPTester {
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are "+uniqueIPs+" IPs");
        la.printAllHigherThanNum(199);
        ArrayList<String> uniqueIPVistsOnDayList = la.uniqueIPVisitsOnDay("Mar 17");
        for (int i = 0; i < uniqueIPVistsOnDayList.size(); i++) {
            System.out.println(uniqueIPVistsOnDayList.get(i));
        }
        System.out.println(uniqueIPVistsOnDayList.size());
        int count = la.countUniqueIPsInRange(200,399);
        System.out.println(count);
    }

    public static void main(String args[]) {
        UniqueIPTester uipObj = new UniqueIPTester();
        uipObj.testUniqueIP();

    }
}
