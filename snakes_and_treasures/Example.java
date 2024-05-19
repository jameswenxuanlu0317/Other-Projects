import greenfoot.*;  
import java.util.*;
import javax.swing.JOptionPane;
/**
 * This is an Example class that codes for the game of snakes and treasures
 * Randomly placing snakes and treasures on the map
 * if player digs up a treasure, they earn one point
 * if player digs up a snake, they get a warning
 * if player digs up a second snake, their score resets to zero
 * player can skip their turns
 * the round ends if each player skips their turn
 * the round also ends if everything was dug up
 * new round adds one more snake to the map, level of difficulties increases
 * first player that reaches the win points wins
 * players can choose whether they want to restart the game or not
 * 
 * @author Lu
 * @version 10000000000000
 */
public class Example  extends World
{
    private static final int COLUMNS = 5;  // We have not talked about static yet but these need to be static.
    private static final int ROWS = 5;     // Otherwise the constructor cannot use them.
    private static final int CELL_SIZE = 100;
    private static final int FOUND=3;
    private static final int WIN_POINTS=20;
    private static final int SNAKE=1;
    private static final int TREASURE=2;
    private static final int POWER=5;
    private int numSnakes=0;
    private int round;
    private boolean start = true;
    private boolean ask=true;
    private int world[][]=new int[ROWS][COLUMNS+1]; //the 2-D array that stores all the values of treasure, snake, powerup. using this to find those things on the map
    private boolean isInt;
    private int ans;
    private int power;
    private int treasure;
    private int difficulty;
    private int score;
    private int skip;
    private String printScore;
    private String initialScore;
    private String answer;
    private String players;
    private String powerups;
    private int numPlayers, winPlayer;
    private int turns;
    private int[][]playersScore=new int[2][5];
    private int[]snakesNumbers=new int[5];//stores the values for the number of snakes each player dug up
    private int dug;//number of cells the players dug
    /**
     * Constructor, constructs the world with the number of columns and rows with the constant cell size
     */
    public Example()
    {    
        super(COLUMNS+1, ROWS+1, CELL_SIZE);
    }

    /**
     * At the start of the game, it draws the player score in the last row
     */
    public void drawInitial()
    {      
        drawLastRow();
        for(int i=0;i<numPlayers;i++)
        {
            drawText(ROWS,i,"Player"+(i+1)+":"+playersScore[1][i]);
        }
    }

    /**
     * Draws the number of snakes left in the second cell of the last column
     */
    public void snakesLeft()
    {
        int snakeLeft=ans-numSnakes-1;
        String snakesLeft="Snakes left: "+snakeLeft;
        drawText(1,COLUMNS,snakesLeft);
    }

    /**
     * Draws the level of difficulty in the fourth cell of the last column
     */
    public void Difficulty()
    {
        String dif="Level "+difficulty;
        drawText(3,COLUMNS,dif);
    }

    /**
     * Draws which player's turn in the third cell of the last column
     */
    public void playerTurn()
    {
        int player=turns+1;
        String playerTurn="Player"+""+player+"'s turn";
        drawText(2,COLUMNS,playerTurn);
    }

    /**
     * draws the win score in the 5th cell of the last column
     */
    public void winScore()
    {
        String win="WIN: "+WIN_POINTS;
        drawText(4,COLUMNS,win);
    }

    /**
     * Clears the world by resetting all the values 0 in the 2-D array that stores all the values of snakes, treasures, and power-ups
     * cleaning the background by drawing white squares to clear the background
     */
    public void clearWorld()
    {
        for(int i=0;i<ROWS;i++)
        {
            for(int k=0;k<COLUMNS;k++)
            {
                world[i][k]=0;
            }
        }
        for(int a=0;a<ROWS;a++)
        {
            for(int b=0;b<COLUMNS;b++)
            {
                drawSquare(a, b,Color.WHITE);
            }
        }
    }

    /**
     * randomly making power ups based on the number given in the parameter
     * if the position is already being used, re-randomize the position of powerups
     * adding the power constant value to the 2-D array
     */
    public void makePower(int x)
    {
        int rand1=(int)(Math.random()*ROWS);
        int rand2=(int)(Math.random()*COLUMNS);
        for(int i=0; i<x;i++)
        {
            while(world[rand1][rand2]!=0)
            {
                rand1=(int)(Math.random()*ROWS);
                rand2=(int)(Math.random()*COLUMNS);
            }
            world[rand1][rand2]=POWER;
        }
    }

    /**
     * randomly making snakes based on the number given in the parameter
     * if the position is already being used, re-randomize the position of snakes
     * adding the snakes constant value to the 2-D array
     */
    public void makeSnake(int x)
    {
        int rand1=(int)(Math.random()*ROWS);
        int rand2=(int)(Math.random()*COLUMNS);
        for(int i=0; i<x;i++)
        {
            while(world[rand1][rand2]!=0)
            {
                rand1=(int)(Math.random()*ROWS);
                rand2=(int)(Math.random()*COLUMNS);
            }
            world[rand1][rand2]=SNAKE;
        }
    }

    /**     
     * randomly making treasures based on the number given in the parameter
     * if the position is already being used, re-randomize the position of treasures
     * adding the treasure constant value to the 2-D array
     */ 
    public void makeTreasure(int x)
    {
        int rand1=(int)(Math.random()*ROWS);
        int rand2=(int)(Math.random()*COLUMNS);
        for(int i=0; i<x;i++)
        {
            while(world[rand1][rand2]!=0)
            {
                rand1=(int)(Math.random()*ROWS);
                rand2=(int)(Math.random()*COLUMNS);
            }
            world[rand1][rand2]=TREASURE;
        }

    }

    /**
     * constructing the game
     * asking how many snakes and powerups the player wants
     * making snakes, powerups, treasures based on the number the player is giving
     * import the image to the background
     * draws the player score, players turn, skip icon, difficulty
     */
    public void constructWorld()
    {    
        int x=0;
        int y=0;
        if(ask)
        {
            answer=Greenfoot.ask("How many snakes do you want");
            while(!checkInt(answer))
            {
                answer=Greenfoot.ask("How many snakes do you want");
            }
            ans=Integer.parseInt(answer);
            while(ans>(ROWS*COLUMNS)-2)
            {
                answer=Greenfoot.ask("Input a valid number(1-23)");
                while(!checkInt(answer))
                {
                    answer=Greenfoot.ask("How many snakes do you want");
                }
                ans=Integer.parseInt(answer);
            }
            powerups=Greenfoot.ask("How many power ups?");
            while(!checkInt(powerups))
            {
                powerups=Greenfoot.ask("input a valid number of powerups kid");
            }
            power=Integer.parseInt(powerups);
            while(power>ROWS*COLUMNS-1-ans)
            {
                powerups=Greenfoot.ask("to many powerups");
                while(!checkInt(powerups))
                {
                    powerups=Greenfoot.ask("How many power ups?");
                }
                power=Integer.parseInt(powerups);
            }
        }
        makePower(power);
        makeSnake(ans);
        makeTreasure((ROWS*COLUMNS)-power-ans);         
        if(ask)
        {
            players=Greenfoot.ask("How many players are there? Maximum is 4");
            while(!checkInt(players)||Integer.parseInt(players)>4||Integer.parseInt(players)<1)
            {
                players=Greenfoot.ask("How many players?");
            }
            numPlayers=Integer.parseInt(players);
            while(numPlayers>4)
            {
                players=Greenfoot.ask("How many players are there? Maximum is 4");
                while(!checkInt(players)||Integer.parseInt(players)>4||Integer.parseInt(players)<1)
                {
                    players=Greenfoot.ask("How many players?");
                }
                numPlayers=Integer.parseInt(players);
            }
        }
        GreenfootImage background=new GreenfootImage("map.jpg");
        background.scale(500,500);
        setBackground(background);
        drawLastRow();
        drawLastColumn();        
        drawSkip(0,COLUMNS);        
        world[0][COLUMNS]=10;
        ask=false;
        drawInitial();
        Difficulty();
        ans++;
        difficulty++;
    }

    /**
     * clears the last column of the world by drawing white squares
     */
    public void drawLastColumn()
    {
        for(int i=0;i<ROWS;i++)
        {
            drawSquare(i, COLUMNS,Color.WHITE);
        }
    }

    /**
     * returns true if it finds a snake at a given position of the 2-D array
     * otherwise returns false
     */
    public boolean checkSnake(int row, int column)
    {
        if(row<ROWS&&column<COLUMNS)
        {
            if(world[row][column]==1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * returns true if it finds a treasure at a given position in the 2-D array, otherwise returns false
     */
    public boolean checkTreasure(int row, int column)
    {
        if(row<ROWS&&column<COLUMNS)
        {
            if(world[row][column]==2)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * returns true if it finds a snake at a given position of the 2-D array
     * otherwise returns false
     */
    public boolean checkPowerUp(int row, int column)
    {
        if(row<ROWS&&column<COLUMNS)
        {
            if(world[row][column]==5)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * returns true if the row and column had already been clicked, using constant FOUND
     */
    public boolean checkClicked(int row, int column)
    {
        if(row<ROWS&&column<COLUMNS)
        {
            if(world[row][column]==FOUND)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * each player can't find more than one snakes, otherwise score goes back to zero
     * if found a snake, number of snakes that player found is added
     */
    public void snakeTurn()
    {
        snakesNumbers[turns]++;//number of snakes for each player is stored in this 2-D array based on the turn
        if(snakesNumbers[turns]<2)
        {
            JOptionPane.showMessageDialog(null,"You found a snake, if you find another one, score goes back to zero");
            drawScore();
        }
        else
        {
            snakesNumbers[turns]=0;
            playersScore[1][turns]=0;
            drawInitial();
            JOptionPane.showMessageDialog(null,"You found another snake, score goes back to zero");
        }
        turns++;
        turns%=numPlayers;
    }

    /**
     * clears the number snakes for each player
     */
    public void clearSnake()
    {
        for(int i=0; i<numPlayers;i++)
        {
            snakesNumbers[i]=0;
        }
    }

    /**
     * score is added in the 2-D array if player has dug up a treasure
     * draws the score of the player, moving on to the next player
     */
    public void treasureTurn()
    {
        playersScore[1][turns]++;
        drawScore();
        turns++;
        turns%=numPlayers;
    }

    /**
     * score is added by 5 when the powerup has been dug by the player, using constant represent the score of the power
     * draws the score of the player, moves on to the next player
     */
    public void powerTurn()
    {
        playersScore[1][turns]+=POWER;
        drawScore();
        JOptionPane.showMessageDialog(null,"You found a Power-up, and got a 5-pt boost!");
        turns++;
        turns%=numPlayers;        
    }

    /**
     * allow players to skip their turn
     * prints out the message about which player skipped their turn
     * if all players skipped their turns, next round is started
     */
    public void skipTurn()
    {
        showSkip();
        if(skip==numPlayers)
        {
            start=true;
            constructWorld();
            skip=0;
        }
        skip++;
        turns++;
        turns%=numPlayers;
    }

    /**
     * prints out the message about which player skipped their turn
     */
    public void showSkip()
    {
        int x=turns+1;
        JOptionPane.showMessageDialog(null,"Player"+x+" skipped the turn");
    }

    /**
     * shows the option of whether the player wants to restart the game or not. 
     * returns true if the player wants to skip their turns, other wise return false
     */
    public boolean checkNew()
    {
        int reply=JOptionPane.showConfirmDialog(null, "Restart?","Game",JOptionPane.YES_NO_OPTION);
        if(reply==JOptionPane.YES_OPTION)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *if one player wins, plays the music, reset all the variables
     *allows player to choose whether they wants to restart a new game
     *reset all the values in the 2-D arrays
     *if player doesn't want to restart, end the game
     */
    public void ifWin()
    {
        if(checkWin())
        {
            GreenfootSound backgroundMusic = new GreenfootSound("winsound.mp3");
            backgroundMusic.play();
            JOptionPane.showMessageDialog(null,"Player"+winPlayer+" won the game");
            start=true;
            skip=0;
            dug=0;
            numSnakes=0;
            difficulty=0;
            turns=0;
            if(checkNew())
            {
                ask=true;
                Reset();
                backgroundMusic.stop();
            }
            else
            {
                Greenfoot.stop();
            }
        }
    }

    /**
     * the game.
     * construct the world, prints out the number of snakes left, difficulty, which player's turn and win score on the screen
     * check if wins at multiple situations
     * check if the round is finished
     * reset variables if the player wins or finised the round
     * draw snakes, treasures, powers accordingly if player dug up 
     * does not allow players to click at one place more than once
     */
    public void act()
    {        
        if (start)
        {
            constructWorld();
            start=false;
        }
        snakesLeft();
        Difficulty();
        playerTurn();
        winScore();
        ifWin();
        if(checkRound())
        {            
            ifWin();
            JOptionPane.showMessageDialog(null,"This round is over, one more snake is added to the next round");
            start=true;
            skip=0;
            dug=0;
            numSnakes=0;
            clearWorld();
            drawInitial();
            if(ans+power>=ROWS*COLUMNS)
            {
                if(!tieScore())
                {
                    JOptionPane.showMessageDialog(null,"Game is over, no more treasure left, player"+highPlayer()+"wins");
                    Greenfoot.stop();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Game is over, no more treasure left, tied game");
                    Greenfoot.stop();
                }
            }
        }

        else if (Greenfoot.mouseClicked(this)) { 
            MouseInfo m = Greenfoot.getMouseInfo();
            if (m == null)
                return;
            int col = m.getX();
            int row = m.getY();
            if(row==0&&col==COLUMNS)
            {                    
                skipTurn();
            }
            else if(row>=0||col>=0||row<ROWS||col<COLUMNS)
            {
                skip=0;
                if(checkSnake(row,col))
                {                    
                    ifWin();
                    snakeMusic();
                    numSnakes++;
                    dug++;
                    drawSquare(row, col, Color.WHITE);
                    drawSnakes(row, col);
                    world[row][col]=FOUND;                    
                    snakeTurn();
                }
                else if(checkTreasure(row,col))
                {
                    ifWin();
                    treasureMusic();
                    dug++;
                    drawSquare(row, col, Color.WHITE);
                    drawTreasure(row,col);
                    world[row][col]=FOUND; 
                    treasureTurn();
                }
                else if(checkPowerUp(row,col))
                {
                    ifWin();
                    powerMusic();
                    dug++;
                    drawSquare(row, col, Color.WHITE);
                    drawPower(row,col);
                    world[row][col]=FOUND;
                    powerTurn();
                }
                else if(checkClicked(row,col))
                {
                    ifWin();
                    JOptionPane.showMessageDialog(null,"Already clicked here boi");
                }
            }
        }
    }

    /**
     * plays a certain music if the player dug up the snake
     */
    public void snakeMusic()
    {
        GreenfootSound backgroundMusic = new GreenfootSound("snakesound.mp3");
        backgroundMusic.play();
    }

    /**
     * plays a certain music if the player dug up the treasure
     */
    public void treasureMusic()
    {
        GreenfootSound backgroundMusic = new GreenfootSound("treasuresound.mp3");
        backgroundMusic.play();
    }

    /**
     * plays a certain music if the player dug up the powerups
     */
    public void powerMusic()
    {
        GreenfootSound backgroundMusic = new GreenfootSound("powersound.mp3");
        backgroundMusic.play();
    }

    /**
     * returns true if the score is tied among the players
     * otherwise returns false;
     */
    public boolean tieScore()
    {
        for(int i=0;i<numPlayers-1;i++)
        {
            if(playersScore[1][i]==playersScore[1][i+1])
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    /**
     * returns the player with the higher score
     */
    public int highPlayer()
    {
        int x=0;
        for(int i=0;i<numPlayers-1;i++)
        {
            if(playersScore[1][i]<playersScore[1][i+1])
            {
                x=i+1;
            }
            else
            {
                x=i;
            }
        }
        x=x+1;
        return x;
    }

    /**
     * reset the variables in the world 2-D array
     * clears the board
     * redraw the score of the players
     */
    public void Reset()
    {        
        for(int i=0;i<numPlayers;i++)
        {
            playersScore[1][i]=0;            
        }
        clearSnake();
        clearWorld();
        drawInitial();
    }

    /**
     * draws the scores of the player
     */
    public void drawScore()
    {
        if(checkRound())        
        {
            drawLastRow();
        }
        for(int i=0;i<numPlayers;i++)
        {
            drawText(ROWS,turns,getScores());
        }

    }

    /**
     * returns true if the players dug up everything or every players skipped their turns
     * otherwise returns false
     */
    public boolean checkRound()
    {
        if(dug==(ROWS*COLUMNS)||skip==numPlayers)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * return the score of the game
     */
    public String getScores()
    {
        int x=turns+1;
        printScore="Player"+x+":"+playersScore[1][turns];
        return printScore;
    }

    /**
     * check if the string is an integer
     */
    public boolean checkInt(String x)
    {
        boolean Int=false;
        try
        {
            Integer.parseInt(x);
            Int=true;
        }
        catch(Exception e)
        {

        }
        if(Int==true)
        {
            if(Integer.parseInt(x)>0)
            {
                return true;                
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * check if the score exceeds the win points constant, return true if it is
     */
    public boolean checkWin()
    {
        for(int i=0;i<numPlayers;i++)
        {
            if(playersScore[1][i]>=WIN_POINTS)
            {
                if(playersScore[1][i]<playersScore[1][highPlayer()-1])
                {
                    winPlayer=highPlayer();
                }
                else
                {
                    winPlayer=i+1;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * clear the last row of the cell by drawing white squares
     */
    public void drawLastRow()
    {
        for(int i=0;i<COLUMNS;i++)
        {
            drawSquare(ROWS, i,Color.WHITE);
        }
    }

    /**
     * Draw text in a given cell
     */
    public void drawText(int row, int col, String str) 
    {
        GreenfootImage imageText = new GreenfootImage(str, 18, Color.BLACK, Color.WHITE); 
        int r = row*CELL_SIZE + CELL_SIZE/2;
        int c = col*CELL_SIZE + CELL_SIZE/40;
        getBackground().drawImage(imageText, c, r);
    }

    /**
     * Draws a colored square
     */
    public void drawSquare(int row, int col, Color color)
    {
        GreenfootImage square = new GreenfootImage(CELL_SIZE*50,CELL_SIZE*50);
        square.setColor(color);
        square.fillRect(col, row, CELL_SIZE+1, CELL_SIZE+1);
        getBackground().drawImage(square, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws a picture of a snake
     */
    public void drawSnakes(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("snakes.jpg");
        pic.scale(CELL_SIZE, CELL_SIZE);
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws a picture of treasure
     */
    public void drawTreasure(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("treasures.jpg");
        pic.scale(CELL_SIZE, CELL_SIZE);
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws a picture of treasure
     */
    public void drawPower(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("powerup.jpg");
        pic.scale(CELL_SIZE, CELL_SIZE);
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

    /**
     * Draws a picture of treasure
     */
    public void drawSkip(int row, int col)
    {
        GreenfootImage pic = new GreenfootImage("skip.png");
        pic.scale(CELL_SIZE, CELL_SIZE);
        getBackground().drawImage(pic, col*CELL_SIZE, row*CELL_SIZE);
    }

}