import java.util.HashMap;

public class CountTester
{
    public void testCountTester()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitorsPerIP();
        System.out.println(counts);
    }
    public static void main(String args[])
    {
        CountTester tobj = new CountTester();
        tobj.testCountTester();
    }

}
