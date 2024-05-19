import javax.swing.*;
import java.util.Random;
/**
 * Hangman game, try to guess right the word by input 1 letter each 
 * times. You have a maximum of 6 time to guess the letters wrong. You
 * win if you guess right all the letters in the word. More informations
 * on the comments in playHangman() method.
 * @Author Lu
 * 
 */
public class Hangman
{
    private String word;
    private String displayword;;
    private String guess;
    private String wrongguess;
    private int countfail;
    private int countsuccess;
    private int money;
    private String showbet;
    final int MAX_NUM_FAIL=6;
    /**
     * This is the setup of the program. Start with underscores with
     * the length equals to the word.
     * You failed 0 times.
     */
    public void setup()
    {
        countfail=0;
        countsuccess=0;
        money=0;
        wrongguess="Wrong Guesses:";
        word=wordbank();
        displayword="________________________";
        displayword = displayword.substring(0,word.length());
        System.out.println(displayword);
        showbet="Your Money: "+money+"$";
        System.out.println(showbet);
    }    

    /**
     * This is the method that randomize the words on the screen. It 
     * also stores as a string of all the words in it.
     */
    public String wordbank()
    {
        Random rand = new Random();
        int rand1 = rand.nextInt(5);
        if(rand1==0)
        {
            return "junction";            
        }
        else if(rand1==1)
        {
            return "intermediate";
        }
        else if(rand1==2)
        {
            return "exergonic";
        }
        else if(rand1==3)
        {
            return "photorespiration";
        }
        else
        {
            return "entropy";
        }

    }

    /**
     * this is a boolean method that checks if the game is finished 
     * or not. The game is done if the player guessed right all the 
     * letters or failed 6 times.
     */
    public boolean done()
    {
        if(displayword.equals(word)||countfail==MAX_NUM_FAIL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * this is the method that checks if the player guessed right all the
     * letters.
     */
    public void checkWin()
    {
        if(displayword.equals(word))
        {
            System.out.println("Congratulations,you won your bet");
        }
    }

    /**
     * this is the method that limits the players to only type in 1 
     * single letter and only enter the same letter once
     */
    public void inGuessed()
    {
        while(guess==null)
        {
            getGuess();
        }
        guess=guess.toLowerCase();
        while(guess.length()>1)
        {
            System.out.println("Too long");
            getGuess();
            inGuessed();
        }
        while(wrongguess.substring(14).indexOf(guess)>=0||displayword.indexOf(guess)>=0)
        {
            System.out.println("You already guessed "+guess);
            getGuess();
            inGuessed();
        }
    }

    /**
     * this is the method that checks that if the guessed letter is 
     * part of the word.
     */
    public boolean checkGuess()
    {

        while(guess==null)
        {
            getGuess();
        }
        guess=guess.toLowerCase();
        if(word.indexOf(guess)>=0&&guess.length()==1)
        {
            return true;            
        }
        else
        {
            return false;
        }
    }

    /**
     * this is the method of graphics if the guess failed. Graphics
     * change each time.
     */
    public void humanGraphics()
    {
        if(countfail==1)
        {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("___|___");
            System.out.println();
            System.out.println("Number of tries left: 5");
        }

        else if(countfail==2)
        {
            System.out.println("\f");
            System.out.println(displayword);
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
            System.out.println("Number of tries left: 4");
        }
        else if(countfail==3)
        {
            System.out.println("\f");
            System.out.println(displayword);
            System.out.println("   ____________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
            System.out.println("Number of tries left: 3");
        }
        else if(countfail==4)
        {
            System.out.println("\f");
            System.out.println(displayword);
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |         \\_ _/      ");
            System.out.println("   |                     ");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
            System.out.println("Number of tries left: 2");
        }
        else if(countfail==5)
        {
            System.out.println("\f");
            System.out.println(displayword);
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |         \\_ _/       ");
            System.out.println("   |           |   ");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |");
            System.out.println("___|___");
            System.out.println("Number of tries left: 1");
        }
        else if(countfail==6)

        {
            System.out.println("\f");
            System.out.println("GAME OVER!");
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |         \\_ _/       ");
            System.out.println("   |           |   ");
            System.out.println("   |          _|_");
            System.out.println("   |         / | \\");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
            System.out.println("GAME OVER! You lost your bet! The word was " + word);
        }

    } 

    /**
     * this is the method after the guess was confirmed to be part of
     * the word. It prints out the letter with the underscores at the
     *correct position.
     */
    public void success()
    {
        countsuccess++;
        int wordindex =word.indexOf(guess);
        money=(countsuccess*100);
        while (wordindex >=0) {
            displayword=displayword.substring(0,wordindex)+guess+displayword.substring(wordindex+1);
            System.out.println("\f");
            showbet="Your Money: "+money+"$";
            wordindex=word.indexOf(guess, wordindex+1);
            humanGraphics();
            System.out.println(wrongguess);
        }
        if(countfail<=1)
        {
            System.out.println(displayword); 
        }
        System.out.println(showbet);
        checkWin();
    }

    /**
     * this is the method after the guess was confirmed not being part
     * of the word. It prints out the graphics.
     */
    public void fail()
    {        
        countfail++;
        System.out.println("Nice try, Enter a different letter:");
        humanGraphics();
        wrongguess=wrongguess+guess;        
        System.out.println(wrongguess);
    }

    /**
     * this displays the dialogue box and ask the player to enter the
     * letter they wanted to guess.
     */
    public void getGuess()
    {
        guess=JOptionPane.showInputDialog(null,"Enter a letter:");    
    }

    /**
     * This is the method that runs the whole hangman project.
     * It runs the setup first, which cleans everything on display and
     * ready to start a new game. If the game did not finish, it will
     * run the JOptionPane to ask the player to enter a letter. If the
     * guess is valid(that means it's a single letter, and only have 
     * been guessed once).
     * Then the program checks the guessed letter, it goes to the success
     * method if guessed right the letter, it goes to the fail method
     * if not. Game will end after everything had been guessed or the
     * player failed 6 times. 
     * Another dialogue box would pop up, click ok to restart the game.
     */
    public void playHangman()
    {                
        setup();
        while (!done())
        {
            getGuess();
            inGuessed();
            if (checkGuess())
            {
                success();                
            } else {
                fail();
            }            
        }
        JOptionPane.showMessageDialog(null,"You are done with this round, click OK to Restart the Game");
        System.out.println("\f");
        playHangman();
    }
}