import java.util.Arrays;
import java.util.Random;
/**
 * This is the deck class. It contains 52 cards from the Card Class.
 *
 * @author Lu
 * @version 1
 */
public class Deck
{
    // instance variables - replace the example below with your own
    private Card[] cards=new Card[52];
       
    /**
     * This returns an array of all of the cards.
     */
    public Deck()
    {
        int x=0;
        
        for(int i=0;i<4;i++)
        {
            for(int a=1;a<=13;a++)
            {
                Card card=new Card(i,a);
                cards[x]=card;
                x++;
            }
        }
    }
    /**
     * This returns a string of all the cards in the deck.
     */
    public String toString()
    {
        int x=0;
        String str="";
        for(int i=0;i<cards.length;i++)
        {
            if(cards[i]!=null)
            {
            str=str+cards[i].toString();
        }
        }
        return str;
    }

    /**
     * This method tests if the deck is empty
     */public boolean empty()
    {
        for(int i=0;i<cards.length;i++)
        {
            if(cards[i]!=null)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This class returns the number of cards remain in the deck
     */
    public int numCards()
    {
        int count=0;
        for(int i=0;i<cards.length;i++)
        {
            if(cards[i]!=null)
            {
                count++;
            }
        }
        return count;
    }

    /**
     * This method deals(removes) a card from the deck
     */
    public Card dealCard()
    {
        Card temp=null;
        for(int i=0;i<cards.length;i++)
        {
            if(cards[i]!=null)
            {
                temp=cards[i];
                cards[i]=null;
                break;
            }
        }
        return temp;
    }

    /**
     * This method shuffles the card randomly
     */
    public void shuffle()
    {
        Random random=new Random();
        Card one;
        Card two;
        for(int i=0; i<200; i++)
        {
            int num1=random.nextInt(cards.length);
            int num2=random.nextInt(cards.length);
            one=cards[num1];
            two=cards[num2];
            cards[num2]=one;
            cards[num1]=two;
        }
    }

}
