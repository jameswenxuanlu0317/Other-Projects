import java.util.Arrays;
/**
 * This is the Card class that holds all the cards. Each face cards has 
 * value. Each suit stores a final constant value. Jack, Queen and King also
 * stores final constant values. Creating an array of suits and card values.
 * @Author Lu
 */

public class Card
{
    private int suit;
    private int value;
    final int CLUBS=3;
    final int DIAMONDS=2;
    final int HEARTS=1;
    final int SPADES=0;
    final int ACE=1;
    final int JACK=11;
    final int QUEEN=12;
    final int KING=13;
    private String[]suits={"Spades","Hearts","Diamonds","Clubs"};
    private String[]values={"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
    public Card(int suit, int value)
    {
        this.suit = suit;
        this.value = value;
    }
    /**
     * This code returns the value of the card
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * Change the value of the card
     */
    public void setValue(int theValue)
    {
        value = theValue;
    }

    /**
     * Return the suit
     */
    public int getSuit()
    {
        return suit;
    }

    /**
     * Change the suit
     */
    public void setSuit(int theSuit)
    {
        suit = theSuit;
    }

    /**
     * Return the card
     */
    public String toString()
    {
        return values[value-1]+" of "+suits[suit];       
    }

}
