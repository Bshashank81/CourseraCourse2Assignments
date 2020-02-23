import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker
{
    public String sliceString(String message, int whichSlice, int totalSlices)
    {
        String st = "";
        for(int i=whichSlice;i<message.length();i=i+totalSlices)
        {
            st = st+message.charAt(i);
        }
        return st;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon)
    {
        int[] key = new int[klength];
        CaesarCracker ccr = new CaesarCracker();
        for(int i=0;i<klength;i++)
        {
            String sliced_string = sliceString(encrypted,i,klength);
            int currKey = ccr.getKey(sliced_string);
            key[i]=currKey;
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> dictionariesList = new HashSet<String>();
        for(String line : fr.lines())
        {
            line = line.toLowerCase();
            dictionariesList.add(line);
        }
        return dictionariesList;
    }

    public int countWords(String message,HashSet<String> dictionaries)
    {
        int count = 0;
        String[] words = message.split("\\W+");
        for(int i=0;i<words.length;i++)
        {
            if(dictionaries.contains(words[i]))
            {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted,HashSet<String> dictionary)
    {
        int key[] = new int[100];
        int bestKey[]=new int[100];
        int largest = 0;
        String decryptedMsg = "";
        for(int i=1;i<=100;i++)
        {
            key=tryKeyLength(encrypted,i,'e');
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted,dictionary);
            if(count>largest)
            {
                largest = count;
                decryptedMsg = decrypted;
                bestKey = key;
            }

        }
        
        return decryptedMsg;
    }

    public void breakVigenere()
    {
        FileResource fr1 = new FileResource();
        String encrypted = fr1.asString();
        FileResource fr2 = new FileResource();
        HashSet<String> dictionaries = readDictionary(fr2);
        String decryptedMsg = breakForLanguage(encrypted,dictionaries);
        System.out.println("The decrypted message is:\n "+decryptedMsg);
        int validWords = countWords(decryptedMsg,dictionaries);
        System.out.println("The number of the valid words in the decrypted message is: "+validWords);
    }


}

class TestVigenereBreaker
{
    public static void main(String args[])
    {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
