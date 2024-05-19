import java.io.*;
import java.util.*;
/**
 * @author Lu
 * This program will allow you to play the game of 20 questions
 */
public class TwentyQuestions
{
    private TreeNode root;
    TreeNode temp=root;
    String command;
    public TwentyQuestions()
    {

    }

    /**
     * play the game
     * create a scanner to load the infile and create a buffered reader to read input lines by players
     * create a tree from the infile
     * it prints out questions and answers to allow players choose yes or no
     * allows players to choose whether they want to restart after each round
     * allows players to add questions and save the file to the outfile if computer didn't win
     * if computer guessed the right answer, the computer wins
     * 
     */
    public void play()
    {

        try
        {
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("What file do you want to load. Options: animals.txt or game.txt");
            String infile=reader1.readLine();
            FileReader reader = new FileReader(new File(infile));
            Scanner scan = new Scanner(reader);
            root=createTree(scan);
            temp=root;
            System.out.println("What file do you want to save to");
            String outfile=reader1.readLine();

            while(true)
            {            
                if(root!=null)
                {
                    System.out.println(root.getValue());
                }
                if(isAns(getCommand()))
                {
                    if(command.toLowerCase().equals("yes"))
                    {
                        root=root.getLeft();
                        if(root==null)
                        {
                            System.out.println("Computer Wins");
                            System.out.println("Do you want to play again, answer in yes or no");
                            String game=reader1.readLine();
                            if(game.equals("yes"))
                            {
                                play();
                            }
                            else if(game.equals("no"))
                            {
                                return;
                            }
                        }

                    }
                    else if(command.toLowerCase().equals("no"))
                    {
                        if(root.getRight()==null)
                        {
                            System.out.println("I don't know what it is");
                            System.out.println("Add a question for your answer");
                            String q="#Q:"+reader1.readLine();
                            TreeNode question=new TreeNode(q,null,null);
                            System.out.println("Add your answer");
                            String answer="Is it " +reader1.readLine();
                            TreeNode ans=new TreeNode(answer,null,null);
                            
                            root.setRight(new TreeNode(root.getValue()));
                            root.setValue(question.getValue());//add the question and answers to the tree
                            root.setLeft(ans);
                            System.out.println("Do you want to play again, answer in yes or no");
                            String game=reader1.readLine();
                            outFile(outfile);
                            if(game.equals("yes"))
                            {
                                play();
                            }
                            else if(game.equals("no"))
                            {
                                return;
                            }
                        }
                        root=root.getRight();
                        // System.out.println(root.getValue());
                    }

                }
                else
                {
                    System.out.println("please enter only yes or no");

                }
            }

        }
        catch(Exception e)
        {
            System.out.println("Error Processing Files");
            System.out.println(e);
            e.printStackTrace();
        }

    }

    /**
     * if the answer is yes or no, return true
     */
    public boolean isAns(String str)
    {
        if(str.equals("yes"))     
        {
            return true;
        }
        else if(str.equals("no"))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    /**
     * recursively create the tree
     */
    public TreeNode createTree(Scanner scan)
    {
        if(scan.hasNext())
        {
            String str=scan.nextLine();
            TreeNode t=new TreeNode(str);
            if(str.substring(0,3).equals("#Q:"))
            {
                t.setLeft(createTree(scan));
                t.setRight(createTree(scan));
            }
            return t;
        }
        return null;
    }

   /**
    * save the outfile using helper
    */
    public void outFile(String filename)
    {
        try
        {
            PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            out.print(outHelper(temp));
            out.close();
        }
        catch(Exception e)
        {
            System.out.println("Error Processing Files");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * return a string that contains all elements of the tree 
     */
    private String outHelper(TreeNode root)
    {
        if(root==null)
        {
            return "";
        }
        else
        {
            return root.getValue()+"\n"+outHelper(root.getLeft())+outHelper(root.getRight());            
        }
    }

    /**
     * get the command word 
     */
    public String getCommand()
    {
        String inputLine = "";   // will hold the full input line
        String word1;

        System.out.print("> ");     // print prompt
        // This is how you read input from the console
        BufferedReader reader = 
            new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("There was an error during reading: "
                + exc.getMessage());
        }

        // This is how you split a sentence into its parts
        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if(tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word
        else
            word1 = null;

        // note: we just ignore the rest of the input line.
        command=word1;
        return command;
    }
}
