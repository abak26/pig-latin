public class PigLatin
{
  private String word;

  public PigLatin(String myWord)
  {
    word=myWord;
  }

  public int findVowel()
  {
    String newWord=word.toLowerCase();
    String vowels="aeiou";
    int len=word.length();
    int j=1;
    int vowelPosition=0;
    for(int i=0;j==1;i++)
    {
      if(vowels.indexOf(newWord.charAt(i))!=-1)
      {
        vowelPosition=i;
        j=0;
      }
      else if(i==newWord.length()-1)
      {
        vowelPosition=newWord.indexOf('y');
        j=0;
      }
    }
    return vowelPosition;
  }

  public int findSpace()
  {
    int i=0;
    int len=word.length();
    int spaceIndex=0;
    while(i<len)
    {
      if(Character.isWhitespace(word.charAt(i)))
      {
        spaceIndex=i;
        i=len;
      }
      i++;
    }
    return spaceIndex;
  }

  public String pigTranslation()
  {
    String newWord=word.toLowerCase();
    String vowels="aeiou";
    PigLatin pigWord=new PigLatin(newWord);
    if(pigWord.findVowel()==0)
    {
      newWord=newWord+"way";
    }
    else if(newWord.indexOf(("y"))==-1)
    {

      String consonants=newWord.substring(0,pigWord.findVowel());
      newWord=newWord.substring(pigWord.findVowel())+consonants+"ay";
    }
    else
    {
      String consonants=newWord.substring(0,pigWord.findVowel());
      newWord=newWord.substring(pigWord.findVowel())+consonants+"ay";
    }
    return newWord;
  }

  public String ifCompoundTranslation()
  {
    String newWord="";
    String vowels="aeiou";
    int hyphen=word.indexOf('-');
    String word1=word.substring(0,hyphen);
    String word2=word.substring(hyphen+1);
    PigLatin word1Pig=new PigLatin(word1);
    PigLatin word2Pig=new PigLatin(word2);
    newWord=word1Pig.pigTranslation()+"-"+word2Pig.pigTranslation();
    return newWord;
  }

  public String finalTranslation()
  {
    String tempSen=word.toLowerCase()+" ";
    String pigSentence="";
    String singleWord="";
    while(tempSen.length()>0)
    {
      PigLatin tempPigSen=new PigLatin(tempSen);
      singleWord=tempSen.substring(0,tempPigSen.findSpace());
      PigLatin singlePigWord=new PigLatin(singleWord);
      String translated="";
      if(singleWord.indexOf('-')!=-1)
      {
        translated=singlePigWord.ifCompoundTranslation();
      }
      else
      {
        translated=singlePigWord.pigTranslation();
      }
      pigSentence+=translated+" ";
      tempSen=tempSen.substring(tempPigSen.findSpace()+1);
    }
    return pigSentence;
  }

  public String fixPunctuation() 
  {
    String tempSen=finalTranslation();
    int len=tempSen.length();
    String letters="qwertyuiopasdfghjklzxcvbnm-";

    for (int i=0;i<len;i++) 
    {
      char currentChar=tempSen.charAt(i);
      if(currentChar=='\'') 
      {
        tempSen=tempSen.substring(0,i)+tempSen.substring(i+1);
        len--;
        i--;
      } 
      else if(letters.indexOf(currentChar)==-1 && currentChar!=' ') 
      {
        PigLatin tempSen2Pig=new PigLatin(tempSen.substring(i));
        int space=tempSen2Pig.findSpace();
        if (space!=-1) 
        {
          tempSen=tempSen.substring(0,i)+tempSen.substring(i+1,i+space)+currentChar+ ' '+tempSen.substring(i+1+space);
          len=tempSen.length();
          i+=space;
        }
      }
    }
    return tempSen;
  }

  public String toString()
  {
    String display=fixPunctuation().substring(0,1).toUpperCase()+fixPunctuation().substring(1);
    return display;
  }
}
