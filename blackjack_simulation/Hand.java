
/**
 * This is the Hand class. This class draws 2 cards from the deck. Adds up
 * the blackjack values. Returns the two cards.
 * 
 * 
 * @????????
 * @???????????????
 */
public class Hand
{
    private Card a;
    private Card b;
    /**
     * This draws 2 cards from the deck.
     */
    public Hand(Deck deck)
    {
        a=deck.dealCard();
        b=deck.dealCard();
    }

    /**
     * This returns 2 cards.
     */
    public String toString()
    {
        return a.toString()+" "+b.toString();
    }

    /**
     * This specifies a special scenario when a player draws two Aces,
     * in that case to benefit the player the most, one ace has the value
     * of 11, the other one has the value of 1.
     * In other cases, the code would automatically adds up the card values.
     */
    public int getBlackJackValue()
    {
        int ans=0;
        if(a.getValue()==a.ACE&&b.getValue()==b.ACE)
        {
            ans=12;
        }
        else
        {
            ans=getCardValue(a)+getCardValue(b);
        }
        return ans;
    }

    /**
     * This method Specifies the special cases of Ace, Jack, Queen and King
     * Returns the card value.
     */
    public int getCardValue(Card a)
    {
        int ans=0;
        if(a.getValue()==a.ACE)
        {
            ans=ans+11;
        }
        if(a.getValue()==a.QUEEN||a.getValue()==a.JACK||a.getValue()==a.KING)
        {
            ans=ans+10;
        }
        else
        {
            ans=ans+a.getValue();
        }
        return ans;
    }
    
}
