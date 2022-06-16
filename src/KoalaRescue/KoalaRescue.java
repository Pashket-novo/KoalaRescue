

package KoalaRescue;

import java.util.*;
/**
 * KoalaRescue class responsible for the the interactions with user and connect with all other classes via Reserve class.
 * Whole Rescue Simulation is implemented via koalaRescueSimulation() method.
 *
 * @author Pavel Zemnukhov
 * @version 1.0 (10-Jun-2020)
 */
public class KoalaRescue
{
    private Reserve reserve;     // declaring object of the Player class
    private String leaderName;   // name of the rescue team leader

    /**
     *  Default constructor of class KoalaRescue
     */
    public KoalaRescue()
    {
        reserve = new Reserve();
        leaderName = "Default";
    }

    /**
     *  Non-default constructor of class KoalaRescue
     *
     *  @param      newReserve      new Reserve object
     *  @param      newName         String,leader name
     */
    public KoalaRescue(Reserve newReserve, String newName)
    {
        reserve = newReserve;
        leaderName = newName;
    }

    /**
     * Method to check length of the String
     *
     * @param   aString     a single string
     * @return              integer number, String length
     */
    public int checkStringLength(String aString)
    {
        int stringLength;
        stringLength = aString.length();
        return stringLength;
    }

    /**
     * Method to enter budget of the Rescue mission,ask input from user
     */
    public void enterBudget()
    {
        System.out.println("");
        System.out.println("Please enter the budget for rescue.");
        System.out.println("Amount should be between 100$ and 200$, inclusive.");
        System.out.println("Please enter number only: ");
        boolean isContinue = true;
        while(isContinue)
        {
            int userInput = getIntInput();
            if (userInput >= 100 && userInput <= 200)       //budget should be between 100 and 200$ inclusive
            {
                Reserve.setBudget(userInput);
                System.out.println("");
                System.out.println("Budget " + userInput + "$ saved!");
                isContinue = false;
            }
            else
            {
                System.out.println("Budget amount should be between 100$ and 200$");
                System.out.println("Please enter correct amount: ");
            }
        }
    }

    /**
     * Method to accept input from the user for leader's name and to check, if the name comply with
     * the simulation requriments(not blank and less than 16 alphabetic character) and updating leaderName
     */
    public void enterLeaderName()
    {
        boolean isContinue = true;
        while (isContinue)
        {
            String userInput = getInput();
            if ((checkStringLength(userInput) < 16) && (checkStringLength(userInput) > 0) &&
                    isStringAlphabetic(userInput))
            {
                System.out.println("");
                System.out.println("Thank you " + userInput + "!");
                setLeaderName(userInput);
                System.out.println("Rescue team leader saved.");
                isContinue = false;
            }
            else
            {
                System.out.println("Name cannot be blank!");
                System.out.println("Name must be less than 16 characters.");
                System.out.println("And can only be alphabetic.");
                System.out.println("");
                System.out.println("Please enter correct name: ");
            }
        }
    }

    /**
     * Method to ask user for input, using Scanner class
     *
     * @return      String input
     */
    public String getInput()
    {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }

    /**
     * Method to ask user for input and converting this input to an integer
     *
     * @return      integer input
     */
    public int getIntInput()
    {
        int intInput = 0;
        boolean isContinue = true;
        while(isContinue)
        {
            String userInput = getInput();
            if ((checkStringLength(userInput) > 0) && isStringNumeric(userInput))
            {
                intInput = Integer.parseInt(userInput); // convert string to integer
                isContinue = false;
            }
            else
            {
                System.out.println("Wrong input!");
                System.out.println("Please enter integer number in required range:");
            }
        }
        return intInput;
    }

    /**
     * Method to return name of the rescue team leader
     *
     * @return      String, name of the leader
     */
    public String getLeaderName()
    {
        return leaderName;
    }

    /**
     * Method to return Reserve object
     *
     * @return      Reserve object
     */
    public Reserve getReserve()
    {
        return reserve;
    }

    /**
     * Method to check if character is alphabetical
     *
     * @param   aChar   a single character
     * @return          boolean statement
     */
    public boolean isCharacterAlphabetic(char aChar)
    {
        boolean isAlpha = Character.isAlphabetic(aChar);
        return isAlpha;
    }

    /**
     * Method to check if character is numeric
     *
     * @param   aChar   a single character
     * @return          boolean statement
     */
    public boolean isCharacterNumeric(char aChar)
    {
        boolean isNum = Character.isDigit(aChar);
        return isNum;
    }

    /**
     * Method to check if String is alphabetical
     *
     * @param   aString     string
     * @return          boolean statement
     */
    public boolean isStringAlphabetic(String aString)
    {
        boolean isAlpha = true;
        int i = 0;
        while (i < aString.length())
        {
            char aChar = aString.charAt(i);
            if (!isCharacterAlphabetic(aChar))
                isAlpha = false;
            i++;
        }
        return isAlpha;
    }

    /**
     * Method to check if String is numeric
     *
     * @param   aString     string
     * @return          boolean statement
     */
    public boolean isStringNumeric(String aString)
    {
        boolean isNum = true;
        for(int i=0; i < aString.length(); i++)
        {
            char aChar = aString.charAt(i);
            if (!isCharacterNumeric(aChar))
                isNum = false;
        }
        return isNum;
    }

    /**
     * Method to start and proceed with whole Rescue Simulation, final information about trees are written to file at the end.
     */
    public void koalaRescueSimulation()
    {
        try
        {
            welcomeMessage();
            enterLeaderName();
            enterBudget();
            reserve.readFile();
            reserve.createTree();
            reserve.createHealthyKoala();
            reserve.createInjuredKoala();
            reserve.setPredator();
            reserve.rescueSimulation();
            reserve.treeLostTotal();
            reserve.getHealthyKoalasTotal();
            reserve.injuredKoalaSafeHaven();
            reserve.koalaRelocated();
            reserve.showAmountRescue();
            reserve.finalStatement();
            reserve.writeFile();
        }
        catch (Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
    }

    /**
     * Method to set leader name
     *
     * @param newLeaderName      String, containing leader name
     */
    public void setLeaderName(String newLeaderName)
    {
        leaderName = newLeaderName;
    }

    /**
     * Method to set Reserve object
     *
     * @param   newReserve    Reserve object
     */
    public void setReserve(Reserve newReserve)
    {
        reserve = newReserve;
    }

    /**
     *  Method toString show state of the KoalaRescue
     *
     *  @return         String
     */
    public String toString()
    {
        return reserve + "," + leaderName;
    }

    /**
     * Method to show welcome message at the beginning of the Rescue simulation
     */
    public void welcomeMessage()
    {
        System.out.println("Welcome to the Koala Rescue Team simulation!");
        System.out.println("###########################################");
        System.out.println("");
        System.out.println("Please enter rescue team leader name: ");
    }
}
