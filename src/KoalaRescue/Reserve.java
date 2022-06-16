/**
 * The Reserve class store ObservationPoint objects in ArrayList. Main processes of the Koala Rescue simulation are
 * operated from this class. It also create SafeHaven object to relocate koalas between observation points and safe
 * have. Read/write from/file implemented in this class.
 *
 * @author Pavel Zemnukhov
 * @version 1.0 (10-Jun-2020)
 */

package KoalaRescue;

import java.util .*;
import java.io.*;

public class Reserve
{
    //declare fields
    private ArrayList<ObservationPoint> observationPointList;    //ArrayList of ObservationPoint objects
    private SafeHaven safeHaven;                                 //declaring object of the SafeHaven class
    private static int budget;                                   //budget of the rescue mission
    private static final int COST_MOVE_INJ_KOALA = 20;           //constant, cost of moving injured koala
    private static final int COST_MOVE_HELTH_KOALA = 10;         //constant, cost of moving healthy koala
    private static final int COST_RELOCATE_KOALA = -5;           //constant, reward for relocate healthy koala
    private int amountRescue;                                    //amount of money, spent on rescue mission

    /**
     * Default constructor of class ObservationPoint
     */
    public Reserve()
    {
        observationPointList = new ArrayList<>();
        safeHaven = new SafeHaven();
        budget = 0;
        amountRescue = 0;
    }

    /**
     *  Non-default constructor of class KoalaRescue
     *
     *  @param      newObservationPointList      ArrayList<ObservationPoint>
     *  @param      newSafeHaven                 SafeHaven object
     *  @param      newBudget                    integer, budget amount
     *  @param      newAmountRescue              integer, rescue amount spent
     */
    public Reserve(ArrayList<ObservationPoint> newObservationPointList, SafeHaven newSafeHaven, int newBudget,
                   int newAmountRescue)
    {
        observationPointList = newObservationPointList;
        safeHaven = newSafeHaven;
        budget = newBudget;
        amountRescue = newAmountRescue;
    }

    /**
     * Method to calculate food and shelter at all observation points
     */
    public void calculateFoodShelter()
    {
        for (ObservationPoint op: observationPointList)
        {
            op.calculateFoodShelter();
        }
    }

    /**
     * Method to check string length and to access first character of the String
     * By default it return ' ' character, if String length less than 1
     *
     * @param   userInput   a string, result of user input
     * @return              single character in upper case
     */
    public char checkCharLength(String userInput)
    {
        char inputOption = ' ';
        if (checkStringLength(userInput))
            inputOption = userInput.toUpperCase().charAt(0);
        return inputOption;
    }

    /**
     * Method to check damage of each type of the Tree at specific observation point.
     * Damage of tree depends on random chance of 5%. If chance is true, damage applied to tree.
     *
     * @param   index       an integer, index of ObservationPoint in the ArrayList
     */
    public void checkDamage(int index)
    {
        if (generateChance(5, 20))
            observationPointList.get(index).applyDamageTree("Manna Gum");
        if (generateChance(5, 20))
            observationPointList.get(index).applyDamageTree("Swamp Gum");
        if (generateChance(5, 20))
            observationPointList.get(index).applyDamageTree("Blue Gum");
        if (generateChance(5, 20))
            observationPointList.get(index).applyDamageTree("River Red Gum");
        if (generateChance(5, 20))
            observationPointList.get(index).applyDamageTree("Wattle");
    }

    /**
     * Method to check if the input string is greater than zero
     *
     * @param   aString     a single string
     * @return              boolean statement
     */
    public boolean checkStringLength(String aString)
    {
        if (aString.length() > 0)
            return true;
        else
            return false;
    }

    /**
     * Method to create Koala objects(injured koalas) at each Observation point. It checking randomly the number of koalas(0-2)
     * and age of the koala(1-18) and applying it.
     */
    public void createHealthyKoala()
    {
        for (ObservationPoint op: observationPointList)
        {
            for (int counter = 0; counter < randomNumberFromZero(9); counter++)
            {
                op.addKoala(randomNumber(18), "Healthy");
            }
        }
    }

    /**
     * Method to create Koala objects(injured koalas) at each Observation point. It checking randomly the number of koalas(0-2)
     * and age of the koala(1-18) and applying it.
     */
    public void createInjuredKoala()
    {
        for (ObservationPoint op: observationPointList)
        {
            for (int counter = 0; counter < randomNumberFromZero(2); counter++)
            {
                op.addKoala(randomNumber(18), "Injured");
            }
        }
    }

    /**
     * Method to create Tree objects at each Observation point.
     */
    public void createTree()
    {
        for (ObservationPoint op: observationPointList)
        {
            op.createTree();
        }
    }

    /**
     * Method to show final statement at the end of the rescue mission.
     * It checking koala deaths at each observation point and shows total number of deaths(or absent of it) for all reserve.
     */
    public void finalStatement()
    {
        System.out.println("");
        int totalKoalaDeath = 0;
        for (ObservationPoint op: observationPointList)
        {
            totalKoalaDeath+= op.getKoalaDeaths();
        }
        if (totalKoalaDeath == 0)
            System.out.println("Rescue was successful, with no koala deaths.");
        else
            System.out.println("Rescue completed with " + totalKoalaDeath + " koalas deaths.");
    }

    /**
     * Method to generate chance of the event. It creates object of RandomNumber class.
     *
     * @param   chancePercent       integer, chance needed to generate
     * @param   maxNumber           integer, max random number(in this program used number 20 for chance calculations)
     * @return                      boolean statement
     */
    public boolean generateChance(int chancePercent, int maxNumber)
    {
        RandomNumber rn = new RandomNumber(maxNumber);
        boolean chance = rn.calculateChance(chancePercent);
        return chance;
    }

    /**
     * Method to return amount spent on the rescue mission
     *
     * @return amount spent on the rescue mission
     */
    public int getAmountRescue()
    {
        return amountRescue;
    }

    /**
     * Method to return current budget
     *
     * @return budget
     */
    public static int getBudget()
    {
        return budget;
    }

    /**
     * Method to return COST_MOVE_HELTH_KOALA costs
     *
     * @return      integer, COST_MOVE_HELTH_KOALA
     */
    public static int getCostMoveHealthKoala()
    {
        return COST_MOVE_HELTH_KOALA;
    }

    /**
     * Method to return COST_MOVE_INJ_KOALA costs
     *
     * @return      integer, COST_MOVE_INJ_KOALA
     */
    public static int getCostMoveInjKoala()
    {
        return COST_MOVE_INJ_KOALA;
    }

    /**
     * Method to return COST_RELOCATE_KOALA costs
     *
     * @return      integer, COST_RELOCATE_KOALA
     */
    public static int getCostRelocateKoala()
    {
        return COST_RELOCATE_KOALA;
    }

    /**
     * Method to show the number of healthy koalas(both at Safe Haven and Observation Points) at the end of the rescue mission.
     */
    public void getHealthyKoalasTotal()
    {
        System.out.println("");
        int healthyKoalasReserve = 0;
        for (ObservationPoint op: observationPointList)
        {
            healthyKoalasReserve += op.getNumberHealthyKoala();
        }
        System.out.println("Number of healthy koalas in the Reserve: " + healthyKoalasReserve);
        int healthyKoalasSafeHaven = 0;
        healthyKoalasSafeHaven = safeHaven.getNumberHealthyKoala();
        System.out.println("Number of healthy koalas in the Safe Haven: " + healthyKoalasSafeHaven);
        System.out.println("Total number of healthy koalas: " + (healthyKoalasSafeHaven+healthyKoalasReserve));
    }

    /**
     * Method to ask user for input, using Scanner class
     *
     * @return      String input
     */
    public String getInput()
    {
        Scanner console = new Scanner(System.in);
        String userInput = console.nextLine();
        return userInput;
    }

    /**
     * Method to return ArrayList<ObservationPoint> observationPointList
     *
     * @return      ArrayList<ObservationPoint>, ArrayList of ObservationPoint objects
     */
    public ArrayList<ObservationPoint> getObservationPointList()
    {
        return observationPointList;
    }

    /**
     * Method to return safeHaven
     *
     * @return      SafeHaven object
     */
    public SafeHaven getSafeHaven()
    {
        return safeHaven;
    }

    /**
     * Method to show the number of injured koalas at the safe haven
     */
    public void injuredKoalaSafeHaven()
    {
        System.out.println("");
        System.out.println("Injured koalas taken to the safe haven: " + safeHaven.getNumberInjuredKoala());
    }

    /**
     * Method to show the total number of  healthy koalas, which were relocated at all observation points.
     */
    public void koalaRelocated()
    {
        System.out.println("");
        int koalaRelocated = 0;
        for (ObservationPoint op: observationPointList)
        {
            koalaRelocated += op.getKoalaRelocated();
        }
        System.out.println("Koalas relocated: " + koalaRelocated);
    }

    /**
     * Method to show short version of the menu to the rescue team leader
     */
    public void menuShort()
    {
        System.out.println("");
        System.out.println("Please choose: ");
        System.out.println("A. Move an injured koala to the safe haven. The cost of moving each injured koala is $" +
                COST_MOVE_INJ_KOALA + ". #A");
        System.out.println("B. Move a healthy koala to the safe haven. The cost of moving each healthy koala is $" +
                COST_MOVE_HELTH_KOALA + ". #B");
        System.out.println("C. Relocate a koala to this location. For each koala relocated, $" +
                (-COST_RELOCATE_KOALA) + " is added to the rescue budget. #C");
        System.out.println("D. Take no further action. #D");
        System.out.println("Please enter your choice(A, B, C, D): ");
    }

    /**
     * Method to move healthy koala from observation point to safe haven. It use temporary array to store Koala object, when
     * it removed from Observation Point before added to Safe Haven
     *
     * @param   index   integer, index of the observation point
     */
    public void moveHealthyKoala(int index)
    {
        Koala[] tempArray = observationPointList.get(index).removeHealthyKoala();
        safeHaven.addKoala(tempArray[0]);
    }

    /**
     * Method to move injured koala from observation point to safe haven. It use temporary array to store Koala object, when
     * it removed from Observation Point before added to Safe Haven
     *
     * @param   index   integer, index of the observation point
     */
    public void moveInjuredKoala(int index)
    {
        Koala[] tempArray = observationPointList.get(index).removeInjuredKoala();
        safeHaven.addKoala(tempArray[0]);
    }

    /**
     * Method to proceed with user command at the observation point (#D Take no further action was selected).
     * Checks various conditions (injured koalas, shortage of food, lack of shelter, predators).
     *
     * @param   index   integer, index of the observation point
     */
    public void noFurtherAction(int index)
    {
        //a. Injured koalas not taken to the safe haven do not survive.
        if (observationPointList.get(index).getNumberInjuredKoala() > 0)
        {
            observationPointList.get(index).injuredKoalaNotSurvive();
        }
        //b. Shortage of food. Checks if food is enough, if not it is 80% chance of not surviving
        double foodWeight;
        int koalaNumber;
        int koalaPotKilled = 0;
        int indexKoala = 0;
        if ((!observationPointList.get(index).isFoodEnough()) && (observationPointList.get(index).koalaQty() > 0))
        {
            foodWeight = observationPointList.get(index).getFoodWeight();
            koalaNumber = observationPointList.get(index).koalaQty();
            koalaPotKilled = (int) Math.ceil(koalaNumber - foodWeight);    // potentially killed koalas
            while (indexKoala < koalaPotKilled)
            {
                if (generateChance(80, 20))
                {
                    observationPointList.get(index).koalaNotSurvive();
                    observationPointList.get(index).updateDeathShortageFood();
                    observationPointList.get(index).updateKoalaDeaths();
                }
                indexKoala++;
            }
        }
        //c. Lack of shelter. Checks if shelter is enough, if not it is 20% chance of not surviving
        if ((!observationPointList.get(index).isShelterEnough()) && (observationPointList.get(index).koalaQty() > 0))
        {
            koalaNumber = observationPointList.get(index).koalaQty();
            int shelter = observationPointList.get(index).getShelterQty();
            koalaPotKilled = koalaNumber - shelter;
            while (indexKoala < koalaPotKilled)
            {
                if (generateChance(20, 20))
                {
                    observationPointList.get(index).koalaNotSurvive();
                    observationPointList.get(index).updateDeathLackShelter();
                    observationPointList.get(index).updateKoalaDeaths();
                }
                indexKoala++;
            }
        }
        //d. Predators: If more than 3 predators, 50% chance of one koala being killed
        int predatorNumber = observationPointList.get(index).getPredatorNo();
        if ((predatorNumber > 3) && (observationPointList.get(index).koalaQty() > 0))
        {
            if (generateChance(50, 20))
            {
                observationPointList.get(index).koalaNotSurvive();
                observationPointList.get(index).updateDeathPredators();
                observationPointList.get(index).updateKoalaDeaths();
            }
        }
    }

    /**
     * Method to show full version of the menu at the beginning of rescue mission
     *
     */
    public void optionsMenu()
    {
        System.out.println("");
        System.out.println("Please decide your further actions for each Observation Point");
        System.out.println("Please consider the budget while making your choice.");
        System.out.println("");
        System.out.println("A. Move an injured koala to the safe haven. The cost of moving each injured koala is $" +
                COST_MOVE_INJ_KOALA +". #A");
        System.out.println("If an injured koala is not taken to the safe haven, it will not survive.");
        System.out.println("");
        System.out.println("B. Move a healthy koala to the safe haven. The cost of moving each healthy koala is $"+
                COST_MOVE_HELTH_KOALA + ". #B");
        System.out.println("If there is a shortage of food or shelter, koala should be sent to the safe haven"
                + " to await possible relocation.");
        System.out.println("");
        System.out.println("C. Relocate a koala to this location. For each koala relocated, $" +
                (-COST_RELOCATE_KOALA) + " is added to the rescue budget. #C");
        System.out.println("A koala is relocated to this location from the safe haven. Note that koalas can only be relocated to");
        System.out.println("this location if it is enough food, enough shelter, and fewer than three predators.");
        System.out.println("");
        System.out.println("D. Take no further action. #D");
        System.out.println("If you want to finish rescue actions at specific OP");
        System.out.println("");
    }

    /**
     * Method to generate random number(from 1 to max number) by computer
     * Creates new object of RandomNumber class
     *
     * @param   maxNumber     integer, maximum number for random generation
     * @return  rn1           integer random number generated by computer
     */
    public int randomNumber(int maxNumber)
    {
        RandomNumber rn = new RandomNumber(maxNumber);
        int rn1 = rn.generateRandomNumber();
        return rn1;
    }

    /**
     * Method to generate random number(from 0 to max number) by computer
     * Creates new object of RandomNumber class
     *
     * @param   maxNumber     integer, maximum number for random generation
     * @return  rn1           integer random number generated by computer
     */
    public int randomNumberFromZero(int maxNumber)
    {
        RandomNumber rn = new RandomNumber(maxNumber);
        int rn1 = rn.generateRandomNumberFromZero();
        return rn1;
    }

    /**
     * Method to read from the file "trees.txt". Based on the lines from file, it add number of each type of tree
     * to the array and then passing this array to new ObservationPoint objects, follows by adding to ArrayList of
     * ObservationPoint objects in this class.
     */
    public void readFile()
    {
//        String filename = "trees.txt";
        try
        {
            FileReader inputFile = new FileReader("./src/KoalaRescue/trees.txt");        //FileReader object to read from file
            try
            {
                Scanner sc = new Scanner(inputFile);
                while (sc.hasNextLine())
                {
                    String oneLine = sc.nextLine();
                    String[] words = oneLine.split(",");
                    int[] treeType = new int[5];
                    treeType[0] = Integer.parseInt(words[0]);
                    treeType[1] = Integer.parseInt(words[1]);
                    treeType[2] = Integer.parseInt(words[2]);
                    treeType[3] = Integer.parseInt(words[3]);
                    treeType[4] = Integer.parseInt(words[4]);
                    ObservationPoint op =  new ObservationPoint(treeType);
                    observationPointList.add(op);
                }
            }
            finally
            {
                inputFile.close();
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found");   //catch of FileNotFoundException
        }
        catch(IOException ex)
        {
            System.out.println("IO error");         //catch of Input/output exception
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString()); //catch of general exception
        }
    }

    /**
     * Method to relocate healthy koala from safe haven to observation point. It use temporary array to store Koala object, when
     * it removed from Safe Haven before added to Observation Point
     *
     * @param   index   integer, index of the observation point
     */
    public void relocateHealthyKoala(int index)
    {
        Koala[] tempArray = safeHaven.removeHealthyKoala();
        observationPointList.get(index).addHealthyKoala(tempArray[0]);
        observationPointList.get(index).updateKoalaRelocated();
    }

    /**
     * Method to proceed with user commands at specific observation point
     *
     * @param   index   integer, index of the observation point
     */
    public void rescueActionsChoose(int index)
    {
        boolean isContinue = true;
        while (isContinue)
        {
            String userInput = getInput();
            switch (checkCharLength(userInput))
            {
                case 'A':
                    System.out.println("#A Move an injured koala to the safe haven was selected");
                    if ((getBudget() - COST_MOVE_INJ_KOALA) >= 0 &&
                            observationPointList.get(index).getNumberInjuredKoala() > 0)
                    {
                        moveInjuredKoala(index);
                        updateBudget(COST_MOVE_INJ_KOALA);
                        updateAmountRescue(COST_MOVE_INJ_KOALA);
                        statusOfOP(index);
                        menuShort();
                    }
                    else
                    {
                        System.out.println("Your request cannot be proceed due to absent of injured koala" +
                                " at OP or insufficient budget.");
                        System.out.println("Please choose other option!");
                        statusOfOP(index);
                        menuShort();
                    }
                    break;
                case 'B':
                    System.out.println("#B Move a healthy koala to the safe haven was selected");
                    if ((getBudget() - COST_MOVE_HELTH_KOALA) >= 0 &&
                            observationPointList.get(index).getNumberHealthyKoala() > 0)
                    {
                        moveHealthyKoala(index);
                        updateBudget(COST_MOVE_HELTH_KOALA);
                        updateAmountRescue(COST_MOVE_HELTH_KOALA);
                        statusOfOP(index);
                        menuShort();
                    }
                    else
                    {
                        System.out.println("Your request cannot be proceed due to absent of healthy koala at OP" +
                                " or insufficient budget.");
                        System.out.println("Please choose other option!");
                        statusOfOP(index);
                        menuShort();
                    }
                    break;
                case 'C':
                    System.out.println("#C Relocate a koala to this location was selected");
                    if (observationPointList.get(index).isFoodEnoughRelocation() &&
                            observationPointList.get(index).isShelterEnoughRelocation() &&
                            observationPointList.get(index).isPredatorSafe() &&
                            (safeHaven.getNumberHealthyKoala() > 0))
                    {
                        relocateHealthyKoala(index);
                        updateBudget(COST_RELOCATE_KOALA);
                        statusOfOP(index);
                        menuShort();
                    }
                    else
                    {
                        System.out.println("Your request cannot be proceed due to food/shelter/predator requirements.");
                        System.out.println("Or there are no healthy koalas in the Safe Haven.");
                        System.out.println("Please choose other option!");
                        statusOfOP(index);
                        menuShort();
                    }
                    break;
                case 'D':
                    System.out.println("#D Take no further action was selected");
                    noFurtherAction(index);
                    isContinue = false; break;
                default:
                    System.out.println("Please enter correct character!"); break;
            }
        }
    }

    /**
     * Method to observe each observation point and to apply specific actions, choosed by user(rescue simulation)
     */
    public void rescueSimulation()
    {
        optionsMenu();
        for(int index = 0; index < observationPointList.size(); index++)
        {
            welcomeOP(index);
            checkDamage(index);
            observationPointList.get(index).calculateFoodShelter();
            statusOfOP(index);
            menuShort();
            rescueActionsChoose(index);
            showKoalaDeaths(index);
            showBudget();
        }
    }

    /**
     * Method to set amount spent on the rescue
     *
     * @param   newAmountRescue   integer, amount
     */
    public void setAmountRescue(int newAmountRescue)
    {
        amountRescue = newAmountRescue;
    }

    /**
     * Method to set budget of the rescue operation
     *
     * @param   newBudget   integer, budget amount
     */
    public static void setBudget(int newBudget)
    {
        budget = newBudget;
    }

    /**
     * Method to set ObservationPointList
     *
     * @param       newObservationPointList   ArrayList<ObservationPoint>
     */
    public void setObservationPointList(ArrayList<ObservationPoint> newObservationPointList)
    {
        observationPointList = newObservationPointList;
    }

    /**
     * Method to set number of predators at each observation point. It generates random number(0-4), as number of predators.
     */
    public void setPredator()
    {
        for(ObservationPoint op: observationPointList)
        {
            op.setPredatorNo(randomNumberFromZero(4));
        }
    }

    /**
     * Method to set SafeHaven
     *
     * @param       newSafeHaven   SafeHaven object
     */
    public void setSafeHaven(SafeHaven newSafeHaven)
    {
        safeHaven = newSafeHaven;
    }

    /**
     * Method to show amount spent on the rescue operation
     */
    public void showAmountRescue()
    {
        System.out.println("");
        System.out.println("Amount spent on the rescue: " + getAmountRescue() + "$.");
    }

    /**
     * Method to show remaining budget
     */
    public void showBudget()
    {
        System.out.println("Amount remaining in budget: " + getBudget());
    }

    /**
     * Method to show information about koala deaths after finish the operation at specific observation point
     *
     * @param   index   integer, index of the observation point
     */
    public void showKoalaDeaths(int index)
    {
        System.out.println("");
        System.out.println("Rescue actions finished at the OP # " + (index + 1) + ".");
        System.out.println("");
        System.out.println("Information about koalas death.");
        int deathInjured = observationPointList.get(index).getDeathInjured();
        System.out.println("The total of injured koalas who were not taken to the safe haven: "
                + deathInjured);
        int deathShortageFood = observationPointList.get(index).getDeathShortageFood();
        System.out.println("Koalas who did not survive a food shortage: "
                + deathShortageFood);
        int deathLackShelter = observationPointList.get(index).getDeathLackShelter();
        System.out.println("Koalas who did not survive lack of shelter: "
                + deathLackShelter);
        int deathPredators = observationPointList.get(index).getDeathPredators();
        System.out.println("Koalas killed by predators: "
                + deathPredators);
        int total = observationPointList.get(index).getKoalaDeaths();
        System.out.println("Total number of koala deaths: "
                + total);
    }

    /**
     * Method to show status of the specific observation point
     *
     * @param   index   integer, index of the observation point
     */
    public void statusOfOP(int index)
    {
        System.out.println("");
        System.out.println("Status of the observation point # " + (index + 1)+ ":");
        System.out.println("The number of injured koalas: " + observationPointList.get(index).getNumberInjuredKoala());
        System.out.println("The number of healthy koalas: " + observationPointList.get(index).getNumberHealthyKoala());
        System.out.println("The weight of available food: " + observationPointList.get(index).getFoodWeight() + " kg");
        System.out.println("Each koala eats 1 kg of leaves per day, whether healthy or injured.");
        System.out.println("The number of shelter trees: " + observationPointList.get(index).getShelterQty());
        System.out.println("The number of predators: " + observationPointList.get(index).getPredatorNo());
        System.out.println("The available budget: " + getBudget());
    }

    /**
     *  Method toString show state of the Reserve
     *
     *  @return         String
     */
    public String toString()
    {
        return observationPointList + "," + safeHaven + "," + budget + "," + COST_MOVE_INJ_KOALA + "," +
                COST_MOVE_HELTH_KOALA + "," + COST_RELOCATE_KOALA + "," + amountRescue;
    }

    /**
     * Method to show total number of Tree's, which been damaged and lost at all observation points
     */
    public void treeLostTotal()
    {
        System.out.println("");
        System.out.println("All the observation points have been visited.");
        System.out.println("");
        int treeLost = 0;
        for (ObservationPoint op: observationPointList)
        {
            treeLost += op.getTreeLost();
        }
        System.out.println("Number of trees that have been lost: " + treeLost);
    }

    /**
     * Method to update amount spent on the rescue mission
     *
     * @param   updateAmount   integer, cost of specific action
     */
    public void updateAmountRescue(int updateAmount)
    {
        amountRescue += updateAmount;
    }

    /**
     * Method to update budget
     *
     * @param   updateValue   integer, cost/income from specific action
     */
    public static void updateBudget(int updateValue)
    {
        budget -= updateValue;
    }

    /**
     * Method to show welcome message at each observation point. At the last observation point, it informs user
     *
     * @param   index   integer, index of the observation point
     */
    public void welcomeOP(int index)
    {
        if (index == (observationPointList.size() - 1))
        {
            System.out.println("");
            System.out.println("It is the last Observation Point");
        }
        System.out.println("");
        System.out.println("You are now at the Observation Point # " + (index + 1));
    }

    /**
     * Method to write updated information about trees at each observation point to file updatedTrees.txt.
     */
    public void writeFile()
    {
        try
        {
            PrintWriter writeFile = new PrintWriter("./src/KoalaRescue/updatedTrees.txt");
            for (ObservationPoint op: observationPointList)
            {
                writeFile.println(op.getTreeTypeQty());
            }
            writeFile.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error writing file");
        }
    }
}
