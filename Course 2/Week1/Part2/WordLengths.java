import edu.duke.FileResource;

import java.io.File;

public class WordLengths
{
    void countWordLengths(FileResource resource,int counts[])
    {
        for(String word : resource.words())
        {
            int wordlength = word.length();
            for(int i=0;i<word.length();i++)
            {
                if((word.charAt(i)!='-')&&(!Character.isLetter(word.charAt(i))))
                    wordlength--;
            }
            counts[wordlength]++;
        }
        for(int i=0;i<counts.length;i++)
        {
            System.out.println(counts[i]+" words of length "+i);
        }
    }
    void testCountWordLengths()
    {
        FileResource fr = new FileResource();
        int counts[] = new int[31];
        countWordLengths(fr,counts);
        int largest = indexOfMax(counts);
        System.out.println("The most common word length " +
                "in the file is: "+largest);
    }
    int indexOfMax(int values[])
    {
        int largest = -1;
        for(int i=0;i<values.length;i++)
        {
            if(values[i]>largest)
                largest=values[i];
        }
        return largest;
    }
    public static void main(String args[])
    {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}
