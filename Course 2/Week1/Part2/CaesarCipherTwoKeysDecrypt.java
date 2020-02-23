import edu.duke.FileResource;

class CaesarBreaker
{

    public String decrypt(String encrypted)
    {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4)
        {
            dkey = 26 - (4-maxDex);
        }
        System.out.println("key is "+dkey);
        return cc.encrypt(encrypted,26-dkey);
    }
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
    void testDecrypt()
    {
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt("Ferst Legion",7);
        String decryptedMsg = decrypt(encrypted);
        System.out.println("decrypted message is: "+decryptedMsg);
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
    void test()
    {
        String halfstr = halfOfString("Qbkm Zgis",0);
        System.out.println(halfstr);
        halfstr = halfOfString("Qbkm Zgis",1);
        System.out.println(halfstr);
    }
    int getKey(String s)
    {
        int freq[] = countLetters(s);
        int maxdx = maxIndex(freq);
        return maxdx;
    }
    String decryptTwokeys(String encrypted)
    {
        CaesarCipher cc = new CaesarCipher();
        String halfstr1 = halfOfString(encrypted,0);
        String halfstr2 = halfOfString(encrypted,1);
        int key1 = getKey(halfstr1);
        int key2 = getKey(halfstr2);
        System.out.println("key1 is "+key1+" key2 is "+key2);
        return cc.encryptTwoKeys(encrypted,key1,key2);
    }
    public static void main(String args[])
    {
        CaesarCipher cc = new CaesarCipher();
        CaesarBreaker obj = new CaesarBreaker();
        obj.testDecrypt();
        obj.test();
        int key = obj.getKey("Ferst Legion");
        System.out.println("key is "+key);
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("\n");
        System.out.println("The input is "+input);
        String encrypted = cc.encryptTwoKeys(input,23,2);
        System.out.println("The encrypted message is "+encrypted);
        String decrypted = obj.decryptTwokeys(input);
        System.out.println("The decrypted message is "+decrypted);
        
    }
}
