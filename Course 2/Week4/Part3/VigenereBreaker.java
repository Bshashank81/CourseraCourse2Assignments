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
            char most_common_char = mostCommonCharIn(dictionary);
            key=tryKeyLength(encrypted,i,most_common_char);
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

    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        int largest = 0;
        char mostCommonChar='\0';
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        ArrayList<Character> charList = new ArrayList<Character>();
        for(int i=0;i<alphabet.length();i++)
        {
            charList.add(alphabet.charAt(i));
        }
        int count=0;
        for(String st : dictionary)
        {
            char currChar='\0';
            for(int k=0;k<st.length();k++)
            {
                currChar = st.charAt(k);
                if(charList.contains(currChar))
                {
                    count++;
                }
            }
            if(count>largest)
            {
                largest=count;
                mostCommonChar=currChar;
            }
        }
        return mostCommonChar;
    }

    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages)
    {
        for(String langName : languages.keySet())
        {
            HashSet<String> listOfWordsInLang = languages.get(langName);
            String decrypted = breakForLanguage(encrypted,listOfWordsInLang);
            System.out.println(langName);
            System.out.println(decrypted);
            System.out.println("\n");
        }
    }


    public void breakVigenere()
    {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String,HashSet<String>> langToWordMap = new HashMap<String, HashSet<String>>();
        for(File currFile : dr.selectedFiles())
        {
            FileResource currFr = new FileResource(currFile);
            HashSet<String> dictionaries = readDictionary(currFr);
            langToWordMap.put(currFile.getName(),dictionaries);
        }

        breakForAllLangs(encrypted,langToWordMap);
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
