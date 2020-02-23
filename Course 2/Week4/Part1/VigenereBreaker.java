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

    public void breakVigenere()
    {
        FileResource fr = new FileResource();
        String fileInput = fr.asString();
        int key[] = tryKeyLength(fileInput,4,'e');
        for(int i=0;i<key.length;i++)
            System.out.println(key[i]);
        VigenereCipher vc = new VigenereCipher(key);
        String decryptedMsg = vc.decrypt(fileInput);
        System.out.println("The decrypted message is: "+decryptedMsg);
    }
    
}
class TestVigenereBreaker
{
    public static void main(String args[])
    {
        VigenereBreaker vb = new VigenereBreaker();
        String sliceStringValue =vb.sliceString("abcdefghijklm",0,5);
        System.out.println(sliceStringValue);
        vb.breakVigenere();
    }
}
