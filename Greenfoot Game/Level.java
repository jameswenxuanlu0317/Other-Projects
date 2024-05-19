/**
 * This is the level class interface. This class would be implemented by the class: space, level 2, and level 3
 * each of those classes should have an act method, printInstruction method, addInstructions method, and a get Counter method
 * 
 * @author Lu
 * @version 1
 */
public interface Level  
{
    // instance variables - replace the example below with your own
    void act();//Each class implementing this class should have an act method to display the game
    void printInstruction();//each class should print out an instructions to the game
    void addInstructions();//each class should add instructions at the start of the game
    Counter getCounter();//each class should be able to return a counter to keep recording the energy level of the character
    
    
}
