public class WordPlay
{
    public boolean isVowel(char ch)
    {
        ch=Character.toLowerCase(ch);
        if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')
        {
            return true;
        }
        else
            return false;
    }
    public void testIsVowel()
    {
        boolean result = isVowel('O');
        System.out.println(result);
    }
    public String replaceVowels(String phrase,char ch)
    {
        StringBuilder convertedString = new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++)
        {
            char currChar = phrase.charAt(i);
            if(isVowel(currChar))
            {
                convertedString.setCharAt(i,ch);
            }
        }
        return convertedString.toString();
    }
    public void testReplaceVowels()
    {
        String replacedVowelsString = replaceVowels("HelloWorld",'*');
        System.out.println(replacedVowelsString);
    }
    public String emphasize(String phrase,char ch)
    {
        StringBuilder newString = new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++)
        {
            char currChar=phrase.charAt(i);
            if(ch==currChar||((Character.toUpperCase(ch))==currChar))
            {
                if((i+1)%2==1)
                {
                    newString.setCharAt(i,'*');
                }
                else
                    newString.setCharAt(i,'+');
            }
        }
        return newString.toString();
    }
    public void testEmphasize()
    {
        String stringWithSubstitution = emphasize("Mary Bella Abracadabra",'a');
        System.out.println(stringWithSubstitution);
    }
    public static void main(String args[])
    {
        WordPlay wp = new WordPlay();
        //wp.testIsVowel();
        //wp.testReplaceVowels();
        wp.testEmphasize();
    }
}
