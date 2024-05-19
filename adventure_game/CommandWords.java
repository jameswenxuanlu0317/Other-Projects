/**
 * This class holds a list of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 */

class CommandWords
{
    // a constant that holds all valid command words
    private final String validCommands = 
        "go quit help pick look drop back inventory interact ask ignore lead be-nice impolite yes no";
    /**
     * Check whether a given String is a valid command word. 
     * Return true if it is, false if it isn't.
     **/
    public boolean isCommand(String aString)
    {
        return aString != null && validCommands.indexOf(aString) >= 0;
    }

    /**
     * return all valid commands
     */
    public String toString() 
    {
        return validCommands;
    }
}
