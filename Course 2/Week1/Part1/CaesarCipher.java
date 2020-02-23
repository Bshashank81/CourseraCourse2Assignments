import edu.duke.FileResource;

import javax.management.remote.JMXServerErrorException;

public class CaesarCipher
{
    public String encrypt(String input,int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String capitalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedSmallAlphabet = smallAlphabet.substring(key)+
                smallAlphabet.substring(0,key);
        String shiftedBigAlphabet = capitalAlphabet.substring(key)+capitalAlphabet.substring(0,key);
        for(int i = 0; i < encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            int idx=0;
            if(Character.isLowerCase(currChar))
            {
                idx = smallAlphabet.indexOf(currChar);
                if(idx != -1){

                    char newChar = shiftedSmallAlphabet.charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
            else
            {
                idx = capitalAlphabet.indexOf(currChar);
                if(idx != -1){

                    char newChar = shiftedBigAlphabet.charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    public void testCaeser()
    {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //int key = 23;
        String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println("message is \n"+"First Legion");
        System.out.println("key is " + "17" + "\n" + encrypted);
    }
    public String encryptTwoKeys(String input,int key1,int key2)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String capitalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedSmallAlphabet1 = smallAlphabet.substring(key1)+
                smallAlphabet.substring(0,key1);
        String shiftedBigAlphabet1 = capitalAlphabet.substring(key1)+capitalAlphabet.substring(0,key1);
        String shiftedSmallAlphabet2 = smallAlphabet.substring(key2)+
                smallAlphabet.substring(0,key2);
        String shiftedBigAlphabet2 = capitalAlphabet.substring(key2)+capitalAlphabet.substring(0,key2);
        for(int i = 0; i < encrypted.length(); i=i+2)
        {
            char currChar = encrypted.charAt(i);
            int idx=0;
            if(Character.isLowerCase(currChar))
            {
                idx = smallAlphabet.indexOf(currChar);
                if(idx != -1){

                    char newChar = shiftedSmallAlphabet1.charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
            else
            {
                idx = capitalAlphabet.indexOf(currChar);
                if(idx != -1){

                    char newChar = shiftedBigAlphabet1.charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        for(int i = 1; i < encrypted.length(); i=i+2)
        {
            char currChar = encrypted.charAt(i);
            int idx=0;
            if(Character.isLowerCase(currChar))
            {
                idx = smallAlphabet.indexOf(currChar);
                if(idx != -1){

                    char newChar = shiftedSmallAlphabet2.charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
            else
            {
                idx = capitalAlphabet.indexOf(currChar);
                if(idx != -1){

                    char newChar = shiftedBigAlphabet2.charAt(idx);

                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public static void main(String args[])
    {
        CaesarCipher cc = new CaesarCipher();
        //cc.testCaeser();
        FileResource fr = new FileResource();
        String input = fr.asString();
        String res = cc.encryptTwoKeys(input,23,2);
        System.out.println(res);
    }
}