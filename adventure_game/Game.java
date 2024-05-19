import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
/**
 *  This is a text-based adventure game. You are a student during the first day of high school and you realized you forgot your
 *  materials for your math class. Before your math teacher gets angry, you need to go find all the items. The game is set up with
 *  9 rooms, some rooms have items some rooms do not. You can find some items. But you need to interact with characters in order
 *  to get all of the items. If you interact with the teacher in the school front office, he will give you a whole map of the 
 *  high school. The background music would be displayed once the player started the game. It is the clean version of "Mo Bamba"
 *  by Sheck Wes. Enjoy!!!

 *  Class Game - the main class of the adventure game
 *
 *  This class is the main class of the text based adventure game.  
 *  Users can walk around some scenery, interact with characters, pick and drop certain items, a bunch of decision makings for the player.
 *  It should be fun.
 *  
 * 
 *  To play this game, create an instance of this class and call the "play" method
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser, creates the previous room, creates the weights, and starts the game.  It also evaluates the
 *  commands that the parser returns.
 *  
 *  @author  James Lu
 *  @version 1
 */

class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Room lab, MRC, gym, library, math, locker, office, bathroom, english;
    private String bag="";
    private int weight=0;
    final int MAX_WEIGHT=10;
    private String ultimateList = "";
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * Create all the items and characters in each rooms.
     */
    private void createRooms()
    {
        //HINT: If you want to access these in other methods, 
        //      then they should be changed to instance variables
        // create the rooms
        lab = new Room("the lab");
        MRC = new Room("the math recource center");
        gym = new Room("the gym");
        library = new Room("the library");
        math = new Room("the math class");
        locker = new Room("your locker");
        office= new Room("the front office");
        bathroom=new Room("the bathroom");
        english=new Room("the English class");
        // initialize items in each room
        lab.setItems("microscope science-textbook gloves ");
        MRC.setItems(" ");
        MRC.setCharacters("Mr.Mergl");
        gym.setItems("");
        library.setItems("computer printer pencils books ");
        library.setCharacters("librarian");
        math.setItems("");
        math.setCharacters("Mr.Aronson");
        locker.setItems("notebook folder ");
        office.setItems("");
        office.setCharacters("teacher");
        bathroom.setItems("soup paper-towels ");
        english.setItems("Macbeth ");
        // initialise room exits
        locker.setExits(null, office, lab, MRC);
        lab.setExits(locker, bathroom, math, gym);
        MRC.setExits(null, locker, gym, null);
        gym.setExits(MRC, lab, library, locker);
        library.setExits(gym,math, null, null);
        math.setExits(lab, english, null, library);
        office.setExits(null,null,bathroom,locker);
        bathroom.setExits(office,null,english, lab);
        english.setExits(bathroom, null, null, math);
        //A list of items with their weights
        ultimateList="5microscope.2science-textbook.2calculator.1gloves.9computer.5printer.1pencils.2books.5notebook.2folders.1soup.2paper-towels.3Macbeth";
        currentRoom = math;  // start game in the math class
    }

    /**
     * This is the method that displays the background music
     */
    private void music()
    {
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("MoBamba.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the method to check if the player wins the game. If the player is in the math class, and dropped notebook, calculator
     * pencils, folder and computer in there, then the player wins
     */
    public boolean win()
    {
        String roomItems=currentRoom.getItems();
        if(currentRoom==math&&roomItems.indexOf("notebook")>=0&&roomItems.indexOf("calculator")>=0&&roomItems.indexOf("pencils")>=0&&roomItems.indexOf("folder")>=0&&roomItems.indexOf("computer")>=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        music();
        boolean finished = false;
        if(!win())
        {
            printFind();
            while (!win()||!finished)
            {
                Command command = parser.getCommand();
                finished = processCommand(command);
                System.out.println("Total weight: "+weight);
            }
        }
        System.out.println("Congratulations, you won!");
        System.exit(0);
    }

    /**
     * this is the method to print out the message to tell the player keep finding the items.
     */
    private void printFind()
    {
        if(currentRoom==math)
        {
            System.out.println("Go find your items.");
            System.out.println("Type 'help' for instructions.");
        }
        else
        {
            System.out.println("What do you mean buddy?");
        }
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("You need to find your items");
        System.out.println("Type 'help' for the instructions.");
        System.out.println();
        System.out.println(currentRoom);
    }

    /**
     * Given a command, process the command.
     * If this command ends the game, true is returned, otherwise false is returned.
     */
    private boolean processCommand(Command command) 
    {
        if(command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
        {
            goRoom(command);
        }
        else if(commandWord.equals("pick"))
        {
            pickUp(command);
        }
        else if(commandWord.equals("look"))
        {
            look(command);
        }
        else if(commandWord.equals("inventory"))
        {
            inventory(command);
        }
        else if(commandWord.equals("drop"))
        {
            drop(command);
        }
        else if(commandWord.equals("interact"))
        {
            interactCharacters(command);
        }
        else if(commandWord.equals("ask"))
        {
            ask(command);
        }
        else if(commandWord.equals("ignore"))
        {
            ignore(command);
        }
        else if(commandWord.equals("lead"))
        {
            lead(command);
        }
        else if(commandWord.equals("nice"))
        {
            nice(command);
        }
        else if(commandWord.equals("impolite"))
        {
            impolite(command);
        }
        else if(commandWord.equals("back"))
        {
            back(command);
        }
        else if(commandWord.equals("yes"))
        {
            if(currentRoom==math)
            {
                play();
            }
            else
            {
                System.out.println("What do you mean 'yes'");
            }
        }
        else if(commandWord.equals("no"))
        {
            printFind();
        }
        else if (commandWord.equals("quit"))
        {
            System.out.println("Bye quitter");  // signal that we want to quit
            System.exit(0);
        }

        else {
            System.out.println("What do you mean buddy?");
        }
        return false;
    }

    /**
     * Print out some help information.
     * Also prints a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("This is the first day of high school,");
        System.out.println("you are in a math class, and you");
        System.out.println("realized you don't have pencils, calculator,");
        System.out.println("and you left your notebook and folder at your locker.");
        System.out.println("You also need to pick up your computer from the library.");
        System.out.println("You need to go grab all of them, before Mr.Aronson");
        System.out.println("gets angry and send you to the deans");
        System.out.println("Once you find all of your item, go back to");
        System.out.println("your math class and drop all of your items.");
        System.out.println("interact with Mr.Aronson, then you win");
        System.out.println();        
        System.out.println("Your command words are:");
        System.out.println("go quit help pick look drop back inventory interact");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // See what the next room in that direction is
        Room nextRoom = currentRoom.nextRoom(direction);
        if(direction.equals("south")||direction.equals("north")||direction.equals("east")||direction.equals("west"))
        {
            if (nextRoom == null)
                System.out.println("Bad kid, you are not allowed to leave school");
            else 
            {
                // we just entered a new room 
                previousRoom=currentRoom;
                currentRoom = nextRoom;
                System.out.println(currentRoom);
            }
        }
        else
        {
            System.out.println("What do you mean buddy?");
        }

    }

    /** 
     * The method to go back to the room the player was in last time
     */
    private void back(Command command) 
    {
        Room nextRoom = previousRoom;

        if (nextRoom == null)
            System.out.println("Bad kid, you are not allowed to leave school");
        else 
        {
            previousRoom=currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom);
        }

    }

    /**
     * The method to pick up the item if it doesn't exeed the maximum weight.
     */
    private void pickUp(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("pick what?");
            return;
        }
        String roomItem=currentRoom.getItems();
        String item = command.getSecondWord();        
        int index=roomItem.indexOf(item);
        int space=index+item.length();
        if(index>=0&&space>=0&&roomItem.substring(space, space+1).equals(" "))
        {
            int getWeight=ultimateList.indexOf(item)-1;
            String thisweight=ultimateList.substring(getWeight,getWeight+1);
            int itemsWeight=Integer.parseInt(thisweight);
            weight=weight+itemsWeight;

            if(weight>10)
            {
                weight=weight-itemsWeight;
                System.out.println("Too heavy for your bag");
            }
            else
            {                
                roomItem=roomItem.substring(0,index)+roomItem.substring(index+item.length());
                currentRoom.setItems(roomItem);
                bag=bag+" "+item;
            }
        }
        else
        {
            System.out.println("Items not in the room");
        }
    }

    /**
     * The method looking for items in the room, if there are items, 
     * print the list of items, else return an error message
     */
    private void look(Command command)
    {
        String lookItems=currentRoom.getItems();
        if(lookItems.length()>1)
        {
            System.out.println(currentRoom.getItems());
        }
        else
        {
            System.out.println("Nothing's here buddy");
        }

    }

    /**
     * The method looking for items in the bag, if there are items, 
     * print in the list of items, else return an error message
     */
    private void inventory(Command command)
    {
        if(!bag.equals(""))
        {
            System.out.println(bag+" ");
        }
        else if(bag.equals(""))
        {
            System.out.println("Nothing in your bag");
        }
    }

    /**
     * This is the method to drop items in the current room from your bag
     */
    private void drop(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("drop what?");
            return;
        }
        String roomItem=currentRoom.getItems();
        String bagItem=bag+" ";
        String item = command.getSecondWord();        
        int index=bagItem.indexOf(item);
        int space=index+item.length();
        if(index>=0&&space>=0&&bagItem.substring(space, space+1).equals(" "))
        {
            int getWeight=ultimateList.indexOf(item)-1;//To find the position of the number
            String thisweight=ultimateList.substring(getWeight,getWeight+1);//To get the weight as a string
            int itemsWeight=Integer.parseInt(thisweight);//To convert the String to an integer
            weight=weight-itemsWeight;//Drop the weight
            bagItem=bagItem.substring(0,index)+bagItem.substring(index+item.length());
            currentRoom.setItems(roomItem+item+" ");
            bag=bagItem;
        }
        else
        {
            System.out.println("Items not in the bag");
        }

    }

    /**
     * This is a method that interacts with the characters in the room
     */
    private void interactCharacters(Command command)
    {
        String roomCharacters=currentRoom.getCharacters();
        System.out.println(roomCharacters);
        if(!command.hasSecondWord())
        {
            System.out.println("interact with who?");
            System.out.println("Type 'interact+character' to proceed");
            return;
        }
        String character=command.getSecondWord();
        int characterindex=roomCharacters.indexOf(character);
        if(characterindex>=0)
        {

            if(character.equals("teacher"))
            {
                System.out.println("You see a teacher in the front office");
                System.out.println("You have two options");
                System.out.println("You can either ask for help or ignore him");
                System.out.println("However, if you ignored the teacher, he will never help you again");
                System.out.println("Your command words are:");
                System.out.println("ask ignore");
                //This is a dicision making whether you want to talk to
                //the character or ignore
            }
            else if(character.equals("Mr.Mergl"))
            {
                System.out.println("Now you see the greatest math teacher of all time, Mr.Mergl");
                System.out.println("He's always ready for you to solve any problems.");
                System.out.println("You need to borrow a calculator.");
                System.out.println("Your command word is:");
                System.out.println("ask");
            }
            else if(character.equals("librarian"))
            {
                System.out.println("You see a librarian, follow her and she will lead you back to your math class");
                System.out.println("Your command word is:");
                System.out.println("lead");
            }
            else if(character.equals("Mr.Aronson"))
            {
                System.out.println("Mr.Aronson: Are you ready for class?");
                System.out.println("Your command word is:");
                System.out.println("yes no");
                //Respond to the teacher whether you are prepared for class. It will check if you actually matched the requirements
            }
        }
        else
        {
            System.out.println("Character not in the room");
        }
    }

    /**
     * This is the method to ask for help for the teacher in the front
     * office or Mr.Mergl in the MRC.
     */
    private void ask(Command command)
    {
        String ask=currentRoom.getCharacters();
        String roomItems=currentRoom.getItems();
        if(!ask.equals("")&&ask.equals("teacher"))
        {
            System.out.println("teacher: these days dumb kids always get lost."); 
            System.out.println("Here is the map of the school it will help you out.");
            System.out.println("You're welcome");
            JFrame frame = new JFrame();
            ImageIcon icon = new ImageIcon("Drawing.jpeg");
            JLabel label = new JLabel(icon);
            frame.add(label);
            frame.setDefaultCloseOperation
            (JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);       
        }
        else if(!ask.equals("")&&ask.equals("Mr.Mergl"))
        {
            System.out.println("Mr.Mergl: Here is your calculator, let me know if you need any other help");
            currentRoom.setItems(roomItems+ "calculator ");
            System.out.println("calculator ");
        }
        else if(ask.equals(""))
        {
            System.out.println("No one's here buddy");
        }
    }

    /**
     * This is the command that will allow the librarian to lead you 
     * back to class
     */
    private void lead(Command command)
    {
        String lead=currentRoom.getCharacters();
        if(!lead.equals("")&&lead.indexOf("librarian")>=0)
        {
            System.out.println("Sure, follow me to get back to your math class");
            currentRoom.setCharacters("");
            currentRoom=math;
            math.setCharacters("Mr.Aronson librarian");
            String roomCharacters=currentRoom.getCharacters();
            System.out.println("Characters in current room: "+roomCharacters);
            System.out.println("Librarian: You are now at your math class, glad to help you");
            System.out.println("Your command words are:");
            System.out.println("nice impolite");
            //This is another dicision making method whether you treat
            //the character nicely or impolitely
            System.out.println("hint: If you were polite, librarian will return to the library and be available for you anytime, if you are impolite, the librarian would never be there to help you again.");
        }
        else
        {
            System.out.println("No one's here to help you buddy");
            System.out.println("Maybe they don't like you so they disappeared");
        }
    }

    /**
     * This is the command that the player will choose to ignore the 
     * teacher, the teacher will get mad and disappear
     */
    private void ignore(Command command)
    {
        String ignore=currentRoom.getCharacters();
        if(!ignore.equals("")&&ignore.indexOf("teacher")>=0)
        {
            currentRoom.setCharacters("");
            System.out.println("Wow, didn't even say hi to your teacher, now he's mad and never gonna talk to you again.");
        }
        else
        {
            System.out.println("Hahaha, guess what, nobody's here");
        }
    }

    /**
     * This is the command to treat the librarian nicely. The librarian
     * will return to the library and be ready for you if you need other
     * help.
     */
    private void nice(Command command)
    {
        String roomCharacters=currentRoom.getCharacters();
        if(currentRoom==math&&roomCharacters.indexOf("librarian")>=0)
        {
            currentRoom=math;
            System.out.println("Librarian:You are welcome, I will now go back to the library.");
            math.setCharacters("Mr.Aronson");
            library.setCharacters("librarian");
        }
        else
        {
            System.out.println("What do you mean buddy?");
        }
    }

    /**
     * This is the command to treat the librarian impolitely. The librarian
     * will get mad and disappear so they would never help you again.
     */
    private void impolite(Command command)
    {
        String roomCharacters=currentRoom.getCharacters();
        if(currentRoom==math&&roomCharacters.indexOf("librarian")>=0)
        {
            currentRoom=math;
            System.out.println("Librarian: OK, I guess I will never help you again");
            math.setCharacters("Mr.Aronson");
            library.setCharacters("");
        }
        else
        {
            System.out.println("What do you mean buddy?");
        }
    }

}
