import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles
{
    private HashMap<String, ArrayList<String>> wordMap;
    public WordsInFiles()
    {
        wordMap = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        for(String w : fr.words())
        {

            if(!wordMap.containsKey(w))
            {
                ArrayList<String> currentList = new ArrayList<String>();
                currentList.add(f.getName());
                wordMap.put(w,currentList);
            }
            else
            {
                wordMap.get(w).add(f.getName());
            }
        }
    }
    public void buildWordFileMap()
    {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }
    public int maxNumber()
    {
        int wordWithMaxFiles = 0;
        for(String word : wordMap.keySet())
        {
            ArrayList<String> currList = wordMap.get(word);
            int value = currList.size();
            if(value>wordWithMaxFiles)
            {
                wordWithMaxFiles=value;
            }
        }
        return wordWithMaxFiles;
    }
    public ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> currList = new ArrayList<String>();
        for(String word : wordMap.keySet())
        {
            int currNumOfFiles = wordMap.get(word).size();
            if(currNumOfFiles == number)
                currList.add(word);
        }
        return currList;
    }
    public void printFilesIn(String word)
    {
        for(int i=0;i<wordMap.get(word).size();i++)
        {
            System.out.println(wordMap.get(word).get(i));
        }
    }
    public void tester()
    {
        int count=0;
        buildWordFileMap();
        int maxNumOfFilesForWord = maxNumber();
        System.out.println("The greatest number of files a word appears in is "+maxNumOfFilesForWord);
        ArrayList<String> sameNumWordsList = wordsInNumFiles(5);
        for(int i=0;i<sameNumWordsList.size();i++)
        {
            String word = sameNumWordsList.get(i);
            System.out.println("\""+word+"\""+" appears in the files:");
            printFilesIn(word);
            count++;
        }
        System.out.println("\n");
        printFilesIn("red");

        System.out.println(count);
    }
    public static void main(String args[])
    {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }
}
