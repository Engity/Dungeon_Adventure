/**
 * T CSS 360 A: Software Development And Quality Assurance Techniques
 * Summer 2022
 * Professor Tom Capaul
 */
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * Input Checker with the purpose of checking user input and prompt them again if it is in the wrong format
 * {@code @author:} Toan Nguyen
 * @version 07 28 2022
 */
public class InputChecker {

    private InputStream myInputStream;
    private PrintStream myOutputStream;
    private String myInitialPrompt;
    private String myRepeatingPrompt;
    private String myErrorPrompt;
    private String myWrongFormatPrompt;
    private String mySuccessPrompt;

    private int myDefaultChoice;

    //The order of outputting prompt are:
    //  1. Initial
    //  2. Repeat (Loop begin here)
    //      2.1 Error (If there is) or Wrong format
    //      2.2 Go back to 2.
    //  3. Success Prompt

    private int myLowerBound, myUpperBound;

    public void setMyInitialPrompt(final String theInitialPrompt) {
        this.myInitialPrompt = theInitialPrompt;
    }

    public void setMyRepeatingPrompt(final String theRepeatingPrompt) {
        this.myRepeatingPrompt = theRepeatingPrompt;
    }

    public void setMyErrorPrompt(final String theErrorPrompt) {
        this.myErrorPrompt = theErrorPrompt;
    }

    public void setMyWrongFormatPrompt(final String theWrongFormatPrompt) {
        this.myWrongFormatPrompt = theWrongFormatPrompt;
    }

    public void setMySuccessPrompt(final String theSuccessPrompt) {
        this.mySuccessPrompt = theSuccessPrompt;
    }
    public void setMyDefaultChoice(final int theChoice){
        myDefaultChoice = theChoice;
    }

    public InputChecker(){
        this(System.in, System.out);
    }

    public InputChecker(final InputStream theInput, final PrintStream theOutput){
        myInitialPrompt = "";
        myRepeatingPrompt = "";
        myErrorPrompt = "";
        myWrongFormatPrompt = "Incorrect format, please input again";
        mySuccessPrompt = "";
        myOutputStream = theOutput;
        myInputStream = theInput;
        myDefaultChoice = 0;
    }

    /**
     * Change the lower and upper bound [a,b]. Will not change if upperBound < lowerBound
     * @param theLowerBound the lower bound
     * @param theUpperBound the upper bound
     */
    public void setBound(final int theLowerBound, final int theUpperBound){
        if (theUpperBound >= theLowerBound) {
            myLowerBound = theLowerBound;
            myUpperBound = theUpperBound;
        }
    }

    /**
     * Display the prompts and ask the user for input
     * The order of outputting prompt are:
     *     1. Initial
     *     2. Repeat (Loop begin here)
     *          2.1 Error (If there is) or Wrong format
     *          2.2 Go back to 2.
     *     3. Success Prompt
     * Will repeating until the user type in the correct number within the range
     * @return an int representing the user's choice
     */
    public int inputCheckForNumber(){
        //Only print if the prompt is not blank
        if (!myInitialPrompt.isBlank()){
            myOutputStream.println(myInitialPrompt);
        }

        //Start looping until user getting the prompt right
        Scanner input = new Scanner(myInputStream);
        int choice = 0;

        //Loop till the user get it
        // right
        while(true){
            if (!myRepeatingPrompt.isBlank()){
                myOutputStream.println(myRepeatingPrompt);
            }

            String promptAnswer = input.next();

            //Blank answer provided, will go for default choice
            if (promptAnswer.isBlank()){
                break;
            }

            //Input check
            try {
                choice = Integer.parseInt(promptAnswer);
                //Checking whether the answer is within range
                if (choice < myLowerBound || choice > myUpperBound){
                    myOutputStream.println(myWrongFormatPrompt);
                }
                else{
                    break;
                }
            } catch (final NumberFormatException theException) {
                if (!myErrorPrompt.isBlank()){
                    myOutputStream.println(myErrorPrompt);
                }
                else{
                    myOutputStream.println(theException.getMessage());
                }
            }
        }

        if (!mySuccessPrompt.isBlank()){
            myOutputStream.println(mySuccessPrompt);
        }
        return choice;
    }








}
