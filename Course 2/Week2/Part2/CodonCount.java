import edu.duke.FileResource;

import java.io.File;
import java.util.HashMap;

public class CodonCount
{
    private HashMap<String,Integer> codonMap;
    public CodonCount()
    {
        codonMap  = new HashMap<String, Integer>();
    }
    public void buildCodonMap(int start,String dna)
    {
        codonMap.clear();
        dna = dna.trim();
        for(int i=start;i<dna.length();i=i+3)
        {
            int k=i+3;
            if(k>dna.length())
                break;
            String w = dna.substring(i,i+3);
            if(w.length()<3)
                break;
            if(w.length()==3)
            {
                if (!codonMap.containsKey(w)){
                    codonMap.put(w,1);
                }
                else {
                    codonMap.put(w,codonMap.get(w)+1);
                }
            }

        }
    }
    public String getMostCommonCodon()
    {
        String largestString = "";
        int largest = 0;
        for(String s : codonMap.keySet())
        {
            int value = codonMap.get(s);
            if(value>largest)
            {
                largest = value;
                largestString = s;
            }
        }
        return largestString;
    }
    public void printCodonCounts(int start,int end)
    {
        for(String word : codonMap.keySet())
        {
            int value = codonMap.get(word);
            if(value>=start && value<=end)
                System.out.println(word+" "+value);
        }
    }
    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        for(int start=0;start<3;start++)
        {
            buildCodonMap(start,dna);
            String largestCodon = getMostCommonCodon();
            System.out.println("Reading frame starting with "+start+" results " +
                    "in "+codonMap.size()+" unique codons"+"\nand " +
                    "most common codon is "+largestCodon+" with count "+codonMap.get(largestCodon));
            int startValue = 1;
            int endValue = 5;
            System.out.println("Counts of codons between "+startValue+" and "+endValue+" inclusive are:");
            printCodonCounts(startValue,endValue);
            System.out.println("\n");
        }

    }
    public static void main(String args[])
    {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
