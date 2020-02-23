import edu.duke.FileResource;

import java.io.FileReader;

class CaesarCipher
{
    private int mainKey;
    private String alphabet;
    private String shiftedAlphabet;
    public CaesarCipher(int key)
    {
        mainKey = key;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
    }
    String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0;i<encrypted.length();i++)
        {
            char ch = encrypted.charAt(i);
            int index = shiftedAlphabet.indexOf(ch);
            if(index!=-1)
            {
                encrypted.setCharAt(i,shiftedAlphabet.charAt(index));
            }
        }
        return encrypted.toString();
    }


    public String decrypt(String input)
    {
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        String decryptedMessage = cc.encrypt(input);
        return decryptedMessage;
    }
}
class TestCaesarCipher
{
    public int[] countLetters(String encrypted)
    {
        int freqs[] = new int[26];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for(int k=0;k<encrypted.length();k++)
        {
            char ch = encrypted.charAt(k);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if(index!=-1)
                freqs[index]++;
        }
        return freqs;
    }
    public int maxIndex(int[] vals)
    {
        int maxDex = 0;
        for(int k=0; k<vals.length;k++)
        {
            if(vals[k] > vals[maxDex])
                maxDex = k;
        }
        return maxDex;
    }
    void simpleTests()
    {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encryptedString = cc.encrypt(input);
        System.out.println("The encrypted string is "+encryptedString);
        String decryptedString = cc.decrypt(encryptedString);
        System.out.println("The decrypted message is "+decryptedString);
        String decryptedMsg = breakCaesarCipher(encryptedString);
        System.out.println("The decrypted msg is "+decryptedMsg);
    }
    String breakCaesarCipher(String input)
    {

        int freq[] = new int[26];
        freq= countLetters(input);
        int key = maxIndex(freq);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }
    public static void main(String args[])
    {
        TestCaesarCipher tcc = new TestCaesarCipher();
        tcc.simpleTests();
    }

}
