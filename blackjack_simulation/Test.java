import javax.swing.*;
/**
 * Blackjack game class. Each player gets 2 cards.
 * You try to beat the dealer with your cards, if your card values are 
 * greater than the dealer card values, you win that round.
 * You can add any value of bets to the game, you start with $100.
 * If you you lose all of your money or win above $1000, the game automatically ends.
 * @Author Lu
 */
public class Test
{
    private String getBet;
    private int bet;
    private int cash;
    private int blackJack=21;
    /**
     * This is the code for playing the game. Setting initial cash $100
     * Creating a new deck, shuffle the cards.Creating two hands(players)
     * Start the game. Show the dialogue box, enter a betting value to proceed.
     */
    public void playBlackJack()
    {
        cash=100;
        while(cash>0&&cash<1000)
        {
            Deck deck=new Deck();
            deck.shuffle();
            Hand hand1=new Hand(deck);
            Hand hand2=new Hand(deck);                        
            System.out.println("You are "+hand2.toString());
            System.out.println("You have "+"$"+cash);
            getBet();
            while(getBet==null)
            {
                getBet();
            }
            bet=Integer.parseInt(getBet);        
            if(hand1.getBlackJackValue()==hand2.getBlackJackValue())
            {
                System.out.println("We tied");
                cash=cash;
                System.out.println("You have "+"$"+cash);
            }
            else if(hand1.getBlackJackValue()>hand2.getBlackJackValue())
            {
                System.out.println("Dealer won");
                cash=cash-bet;
                if(cash>0)
                {
                    System.out.println("You have "+"$"+cash);
                }
                else
                {
                    System.out.println("Don't have enough money son");
                }
            }
            else if(hand1.getBlackJackValue()==blackJack&&hand2.getBlackJackValue()!=blackJack)
            {
                System.out.println("Dealer has black jack!");
                cash=0;
            }
            else if(hand1.getBlackJackValue()!=blackJack&&hand2.getBlackJackValue()==blackJack)
            {
                System.out.println("You have black jack!");
                cash=cash+1000;
            }
            else
            {
                System.out.println("You won");
                cash=cash+bet;
                System.out.println("You have "+"$"+cash);
            }
            System.out.println("Dealer hand is "+ hand1.toString());
        }
        if(cash<=0)
        {
        System.out.println("Go home kid, you lost all of your money");
        }
        else if(cash>=1000)
        {
        System.out.println("You won a lot of money, so bye");        
        }
    }

    /**
     * This code shows the dialogue box, enter the value you want to bet.
     */
    public void getBet()
    {
        getBet=JOptionPane.showInputDialog(null,"Enter how much you want to bet:");    
    }

}
