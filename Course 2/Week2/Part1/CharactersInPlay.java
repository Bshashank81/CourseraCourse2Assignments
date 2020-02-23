import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    CharactersInPlay()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    void update(String person)
    {
        int index = myWords.indexOf(person);
        if(index==-1)
        {
            myWords.add(person);
            myFreqs.add(1);
        }
        else
        {
            int freq = myFreqs.get(index);
            myFreqs.set(index,freq+1);
        }
    }
    void findAllCharacters()
    {
        FileResource fr = new FileResource();
        for(String line : fr.lines())
        {
            int index1 = line.indexOf(',');
            int index2 = line.indexOf('.');
            String word = "";
            if((index1<index2)&&(index1!=-1)&&(index2!=-1))
            {
                word = line.substring(0, index1);
                StringBuilder sb = new StringBuilder(word);
                if(Character.isUpperCase(word.charAt(word.length()-1)))
                    update(word);
            }
            else
            {
                if(index2!=-1)
                {
                    word = line.substring(0, index2);
                    if(Character.isUpperCase(word.charAt(word.length()-1)))
                        update(word);
                }
            }
        }
    }
    void charactersWithNumParts(int num1,int num2)
    {
        for(int i=0;i<myFreqs.size();i++)
        {
            int currFreq = myFreqs.get(i);
            if(myFreqs.get(i)>=num1 && myFreqs.get(i)<num2)
                System.out.println(myWords.get(i)+"\t"+myFreqs.get(i));
        }
    }
    void tester()
    {
        findAllCharacters();
        for(int i=0;i<myWords.size();i++)
        {
            System.out.println(myWords.get(i)+"\t"+myFreqs.get(i));
        }
        charactersWithNumParts(10,15);
    }

    public static void main(String args[])
    {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }


}
