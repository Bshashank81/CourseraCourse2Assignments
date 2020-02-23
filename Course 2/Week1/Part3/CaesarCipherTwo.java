import edu.duke.FileResource;

class CaesarCipherTwo
{
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1,key2;
    CaesarCipherTwo(int key1,int key2)
    {
        this.key1 = key1;
        this.key2 = key2;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
    }
    String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0;i<encrypted.length();i=i+2)
        {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currChar);
            if(index!=-1)
            {
                char ch = shiftedAlphabet1.charAt(index);
                encrypted.setCharAt(i,ch);
            }
        }
        for(int i=1;i<encrypted.length();i=i+2)
        {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currChar);
            if(index!=-1)
            {
                char ch = shiftedAlphabet2.charAt(index);
                encrypted.setCharAt(i,ch);
            }
        }
        return encrypted.toString();
    }
    String decrypt(String input)
    {
        TestCaesarCipherTwo tcc = new TestCaesarCipherTwo();
        String halfstr1 = tcc.halfOfString(input,0);
        String halfstr2 = tcc.halfOfString(input,1);
        int[] freqs = tcc.countLetters(halfstr1);
        int maxDex = tcc.maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4)
            dkey = 26 - (4 - maxDex);
        //System.out.println("key is "+dkey);
        key1=maxDex;
        freqs = tcc.countLetters(halfstr2);
        maxDex = tcc.maxIndex(freqs);
        dkey = maxDex - 4;
        if(maxDex < 4)
            dkey = 26 - (4 - maxDex);
        key2=maxDex;
        CaesarCipherTwo cct = new CaesarCipherTwo(key1,key2);
        return cct.encrypt(input);
    }
}
class TestCaesarCipherTwo
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
    String halfOfString(String message,int start)
    {
        String halfstr ="" ;
        for(int i=start;i<message.length();i=i+2)
        {
            halfstr = halfstr + message.charAt(i);
        }
        return halfstr;
    }
    void simpleTests()
    {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
        String encrypted = cct.encrypt(input);
        System.out.println("The encrypted string is \n"+encrypted);
        String decrypted = cct.decrypt(encrypted);
        System.out.println("The decrypted string is \n"+decrypted);
        String decryptedString = breakCaesarCipher(encrypted);
        System.out.println("The decrypted message is \n"+decryptedString);
    }
    String breakCaesarCipher(String input)
    {
        int freq[] = new int[26];
        freq= countLetters(halfOfString(input,0));
        int key1 = maxIndex(freq);
        freq = countLetters(halfOfString(input,1));
        int key2 = maxIndex(freq);
        CaesarCipherTwo cct = new CaesarCipherTwo(key1,key2);
        return cct.decrypt(input);
    }
    public static void main(String args[])
    {
        TestCaesarCipherTwo tcct = new TestCaesarCipherTwo();
        tcct.simpleTests();
    }
}
