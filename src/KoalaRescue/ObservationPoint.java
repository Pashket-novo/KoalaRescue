/**
 * The ObservationPoint class store Tree objects and Koala objects in
 * ArrayList's to furhter interactions with these objects
 *
 * @author Pavel Zemnukhov
 * @version 1.0 (10-Jun-2020)
 */

package KoalaRescue;

import java.util .*;
import java.io.*;

public class ObservationPoint
{
    private ArrayList<Tree> treeList;   //declaring ArrayList of Tree objects
    private ArrayList<Koala> koalaList; //declaring ArrayList of Koala's
    private int predatorNo;             //quantity of predators
    private int[] treeType;             //quantity of tree of each type
    private double foodWeight;          //total of available food
    private int shelterQty;             //number of shelter trees
    private int treeLost;               //number of tree's lost after damage
    private int koalaRelocated;         //quantity of relocated koalas
    private int koalaDeaths;            //total number of koalas death
    private int deathInjured;           //quantity of deaths of injured koalas
    private int deathShortageFood;      //quantity of koalas deaths caused by shortage of food
    private int deathLackShelter;       //quantity of koalas deaths caused by lack of shelter
    private int deathPredators;         //quantity of koalas killed by predators

    /**
     * Default constructor for objects of class ObservationPoint
     */
    public ObservationPoint()
    {
        treeList = new ArrayList<>();
        koalaList = new ArrayList<>();
        predatorNo = 0;
        treeType = new int[5];
        foodWeight = 0;
        shelterQty = 0;
        koalaDeaths = 0;
        treeLost = 0;
        koalaRelocated = 0;
        deathInjured = 0;
        deathShortageFood = 0;
        deathLackShelter = 0;
        deathPredators = 0;
    }

    /**
     * Parameterized constructor of class ObservationPoint
     *
     * @param   newTreeType     array contains quantity of each type of tree
     */
    public ObservationPoint(int[] newTreeType)
    {
        treeList = new ArrayList<>();
        koalaList = new ArrayList<>();
        predatorNo = 0;
        treeType = newTreeType;
        foodWeight = 0;
        shelterQty = 0;
        koalaDeaths = 0;
        treeLost = 0;
        koalaRelocated = 0;
        deathInjured = 0;
        deathShortageFood = 0;
        deathLackShelter = 0;
        deathPredators = 0;
    }

    /**
     * Parameterized constructor of class ObservationPoint
     *
     * @param   newTreeList             ArrayList<Tree>
     * @param   newKoalaList            ArrayList<Koala>
     * @param   newPredatorNo           integer, number of predators
     * @param   newTreeType             array, quantity of each type of tree
     * @param   newFoodWeight           double, weight of food
     * @param   newShelterQty           integer, number of shelter trees
     * @param   newKoalaDeaths          integer, number of koala deaths
     * @param   newTreeLost             integer, number of trees lost
     * @param   newKoalaRelocated       integer, number of koalas relocted
     * @param   newDeathInjured         integer, number of injured koalas deaths
     * @param   newDeathShortageFood    integer, number of koalas deaths caused by shortage of food
     * @param   newDeathLackShelter     integer, number of koalas deaths caused by lack of shelter
     * @param   newDeathPredators       integer, number of koalas deaths killed by predators
     */
    public ObservationPoint(ArrayList<Tree> newTreeList, ArrayList<Koala> newKoalaList, int newPredatorNo, int[] newTreeType,
                            double newFoodWeight, int newShelterQty, int newKoalaDeaths, int newTreeLost,int newKoalaRelocated, int newDeathInjured,
                            int newDeathShortageFood, int newDeathLackShelter, int newDeathPredators)
    {
        treeList = newTreeList;
        koalaList = newKoalaList;
        predatorNo = newPredatorNo;
        treeType = newTreeType;
        foodWeight = newFoodWeight;
        shelterQty = newShelterQty;
        koalaDeaths = newKoalaDeaths;
        treeLost = newTreeLost;
        koalaRelocated = newKoalaRelocated;
        deathInjured = newDeathInjured;
        deathShortageFood = newDeathShortageFood;
        deathLackShelter = newDeathLackShelter;
        deathPredators = newDeathPredators;
    }

    /**
     * Method to add healthy Koala object to the ArrayList
     *
     * @param  addKoala     Koala object
     */
    public void addHealthyKoala(Koala addKoala)
    {
        koalaList.add(addKoala);
    }

    /**
     * Method to create and add new Koala object to ArrayList koalaList
     *
     * @param  newAge           integer, Koala age
     * @param  newCondition     String, condition of the Koala
     */
    public void addKoala(int newAge, String newCondition)
    {
        Koala koala = new Koala(newAge, newCondition);
        koalaList.add(koala);
    }

    /**
     * Method to apply damage for specific type of tree. Checking ArrayList of Tree and remove specific element.
     * After each damage, updates number of tree lost.
     *
     * @param  treeName         String, name of the tree
     */
    public void applyDamageTree(String treeName)
    {
        int index = 0;
        boolean isCont = true;
        while ((index < treeList.size()) && isCont)
        {
            if (treeList.get(index).getName() == treeName)
            {
                treeList.remove(index);
                updateTreeLost();
                isCont = false;
            }
            index++;
        }
    }

    /**
     * Method to calculate food and shelter in the observation point, checking each tree by name.
     */
    public void calculateFoodShelter()
    {
        try
        {
            for (Tree tr:treeList)
            {
                switch(tr.getName())
                {
                    case "Manna Gum":
                        foodWeight += 1.00; break;
                    case "Swamp Gum":
                        foodWeight += 0.34; break;
                    case "Blue Gum":
                        foodWeight += 0.90; break;
                    case "River Red Gum":
                        foodWeight += 0.40; break;
                    case "Wattle":
                        shelterQty += 1; break;
                }
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("There are no trees at this OP");
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
    }

    /**
     * Method to create Tree objects at the observation point. Creates all trees at the observation point.
     * Quantity of type of tree specified in treeType array
     */
    public void createTree()
    {
        try
        {
            for(int counter = 0; counter < treeType[0];counter++)
            {
                Tree tr = new Tree("Manna Gum", "Food");
                treeList.add(tr);
            }
            for(int counter = 0; counter < treeType[1];counter++)
            {
                Tree tr = new Tree("Swamp Gum", "Food");
                treeList.add(tr);
            }
            for(int counter = 0; counter < treeType[2];counter++)
            {
                Tree tr = new Tree("Blue Gum", "Food");
                treeList.add(tr);
            }
            for(int counter = 0; counter < treeType[3];counter++)
            {
                Tree tr = new Tree("River Red Gum", "Food");
                treeList.add(tr);
            }
            for(int counter = 0; counter < treeType[4];counter++)
            {
                Tree tr = new Tree("Wattle", "Shelter");
                treeList.add(tr);
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("There are no trees in array");
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
    }

    /**
     * Method to return deaths of injured koalas
     *
     * @return      number of injured koala deaths
     */
    public int getDeathInjured()
    {
        return deathInjured;
    }

    /**
     * Method to return deaths of koalas, as a result of lack of shelter
     *
     * @return      number of koala deaths, caused by lack of shelter
     */
    public int getDeathLackShelter()
    {
        return deathLackShelter;
    }

    /**
     * Method to return number of koalas, killed by predators
     *
     * @return      number of koala deaths, caused by predators
     */
    public int getDeathPredators()
    {
        return deathPredators;
    }

    /**
     * Method to return deaths of koalas, as a result of shortage of food
     *
     * @return      number of koala deaths, caused by shortage of food
     */
    public int getDeathShortageFood()
    {
        return deathShortageFood;
    }

    /**
     * Method to return total weight of food at the observation point
     *
     * @return      double number, rounded to 2 decimal places
     */
    public double getFoodWeight()
    {
        return (double)Math.round(foodWeight * 100) / 100;
    }

    /**
     * Method to return total number of koala deaths from any reason at observation point
     *
     * @return      total number of koala deaths, caused by any reson
     */
    public int getKoalaDeaths()
    {
        return koalaDeaths;
    }

    /**
     * Method to return ArrayList of Koala objects
     *
     * @return      ArrayList of Koala objects
     */
    public ArrayList getKoalaList()
    {
        return koalaList;
    }

    /**
     * Method to return number of koalas, relocated to observation point from safe haven
     *
     * @return      quantity of koalas relocated
     */
    public int getKoalaRelocated()
    {
        return koalaRelocated;
    }

    /**
     * Method to return number of all healthy koalas at the observation point
     *
     * @return      number of healthy koalas at the observation point
     */
    public int getNumberHealthyKoala()
    {
        int healthyKoala = 0;
        try
        {
            for (Koala koala : koalaList)
            {
                if (koala.getCondition() == "Healthy")
                    healthyKoala++;
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("There are no koalas at this OP");
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
        return healthyKoala;
    }

    /**
     * Method to return number of all injured koalas at the observation point
     *
     * @return      number of injured koalas at the observation point
     */
    public int getNumberInjuredKoala()
    {
        int injuredKoala = 0;
        try
        {
            for (Koala koala : koalaList)
            {
                if (koala.getCondition() == "Injured")
                    injuredKoala++;
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("There are no koalas at this OP");
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
        return injuredKoala;
    }

    /**
     * Method to return number of trees at the observation point
     *
     * @return      number of trees at the observation point
     */
    public int getNumberOfTrees()
    {
        return treeList.size();
    }

    /**
     * Method to return number of predators at the observation point
     *
     * @return      quantity of predators at the observation point
     */
    public int getPredatorNo()
    {
        return predatorNo;
    }

    /**
     * Method to return number of shelter trees at the observation point
     *
     * @return      number of shelter trees at the observation point
     */
    public int getShelterQty()
    {
        return shelterQty;
    }

    /**
     * Method to return ArrayList of Tree objects
     *
     * @return      ArrayList of Tree objects
     */
    public ArrayList getTreeList()
    {
        return treeList;
    }

    /**
     * Method to return total number of trees, which been lost due to damage
     *
     * @return      number of trees, lost
     */
    public int getTreeLost()
    {
        return treeLost;
    }

    /**
     * Method to return array of tree types at the observation point
     *
     * @return      array of tree types
     */
    public int[] getTreeType()
    {
        return treeType;
    }

    /**
     * Method to return String with quantity of each tree type at the observation point
     *
     * @return      String, one line with number of each type of tree, separated by ","
     */
    public String getTreeTypeQty()
    {
        int mannaGum = 0;
        int swampGum = 0;
        int blueGum = 0;
        int riverRedGum = 0;
        int wattle = 0;
        String treeNewList = "";
        try
        {
            for (Tree tr:treeList)
            {
                if (tr.getName() == "Manna Gum")
                    mannaGum++;
                if (tr.getName() == "Swamp Gum")
                    swampGum++;
                if (tr.getName() == "Blue Gum")
                    blueGum++;
                if (tr.getName() == "River Red Gum")
                    riverRedGum++;
                if (tr.getName() == "Wattle")
                    wattle++;
            }
            StringBuffer sb = new StringBuffer("");
            sb.append(Integer.toString(mannaGum) + ",");
            sb.append(Integer.toString(swampGum) + ",");
            sb.append(Integer.toString(blueGum) + ",");
            sb.append(Integer.toString(riverRedGum) + ",");
            sb.append(Integer.toString(wattle));
            treeNewList = sb.toString();
        }
        catch(NullPointerException ex)
        {
            System.out.println("There are no trees at this OP");
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
        return treeNewList;
    }

    /**
     * Method to apply death to all injured koalas at the observation point
     */
    public void injuredKoalaNotSurvive()
    {
        int index = 0;
        while (index < koalaList.size())
        {
            if (koalaList.get(index).getCondition() == "Injured")
            {
                koalaList.remove(index);
                index--;
                updateDeathInjured();
                updateKoalaDeaths();
            }
            index++;
        }
    }

    /**
     * Method to check is it enough food for koalas at the observation point
     *
     * @return      boolean statement
     */
    public boolean isFoodEnough()
    {
        if (koalaQty() <=  getFoodWeight())
            return true;
        else
            return false;
    }

    /**
     * Method to check is it enough food for koalas at the observation point and for potential new koala from safe haven
     *
     * @return      boolean statement
     */
    public boolean isFoodEnoughRelocation()
    {
        if ((koalaQty()+1) <=  getFoodWeight())
            return true;
        else
            return false;
    }

    /**
     * Method to check is observation point safe for relocating koala from safe haven
     * Checks number of predators at the observation point
     *
     * @return      boolean statement
     */
    public boolean isPredatorSafe()
    {
        if (getPredatorNo() < 3)
            return true;
        else
            return false;
    }

    /**
     * Method to check is it enough shelter for koalas at the observation point
     *
     * @return      boolean statement
     */
    public boolean isShelterEnough()
    {
        if (koalaQty() <=  getShelterQty())
            return true;
        else
            return false;
    }

    /**
     * Method to check is it enough shelter for koalas at the observation point and for potential new koala from safe haven
     *
     * @return      boolean statement
     */
    public boolean isShelterEnoughRelocation()
    {
        if ((koalaQty()+1) <=  getShelterQty())
            return true;
        else
            return false;
    }

    /**
     * Method to apply death for one koala
     */
    public void koalaNotSurvive()
    {
        int index = 0;
        if (index < koalaList.size())
            koalaList.remove(index);
    }

    /**
     * Method to return number of all koalas at the observation point
     *
     * @return      number of koalas at the observation point
     */
    public int koalaQty()
    {
        return koalaList.size();
    }

    /**
     * Method to remove healthy koala from observation point to further movement to safe haven.
     * It use temporary array to store Koala object.
     *
     * @return      array of Koala object
     */
    public Koala[] removeHealthyKoala()
    {
        int index = 0;
        boolean isCont = true;
        Koala[] tempArray = new Koala[1];
        while ((index < koalaList.size()) && isCont)
        {
            if (koalaList.get(index).getCondition() == "Healthy")
            {
                tempArray[0] = koalaList.remove(index);
                isCont = false;
            }
            index++;
        }
        return tempArray;
    }

    /**
     * Method to remove injured koala from observation point to further movement to safe haven.
     * It use temporary array to store Koala object.
     *
     * @return      array of Koala object
     */
    public Koala[] removeInjuredKoala()
    {
        int index = 0;
        boolean isCont = true;
        Koala[] tempArray = new Koala[1];
        while ((index < koalaList.size()) && isCont)
        {
            if (koalaList.get(index).getCondition() == "Injured")
            {
                tempArray[0] = koalaList.remove(index);
                isCont = false;
            }
            index++;
        }
        return tempArray;
    }

    /**
     * Method to set food weight at the observation point
     *
     * @param   newFoodWeight    double number, food weight
     */
    public void setFoodWeight(double newFoodWeight)
    {
        foodWeight = newFoodWeight;
    }

    /**
     * Method to set Koala deaths at the observation point
     *
     * @param  newKoalaDeaths     integer, number of koalas deaths
     */
    public void setKoalaDeaths(int newKoalaDeaths)
    {
        koalaDeaths = newKoalaDeaths;
    }

    /**
     * Method to set Koala list
     *
     * @param       newKoalaList   ArrayList<Koala>
     */
    public void setKoalaList(ArrayList<Koala> newKoalaList)
    {
        koalaList = newKoalaList;
    }

    /**
     * Method set the number of predators at observationPoint
     *
     * @param newPredatorNo     integer, number of predators
     */
    public void setPredatorNo(int newPredatorNo)
    {
        predatorNo = newPredatorNo;
    }

    /**
     * Method set the shelter quantity at the observation point
     *
     * @param newShelterQty     integer, shelter quantity
     */
    public void setShelterQty(int newShelterQty)
    {
        shelterQty = newShelterQty;
    }

    /**
     * Method to set Tree list
     *
     * @param       newTreeList   ArrayList<Tree>
     */
    public void setTreeList(ArrayList<Tree> newTreeList)
    {
        treeList = newTreeList;
    }

    /**
     * Method set the number of trees lost at the observation point
     *
     * @param newTreeLost     integer, number of trees lost
     */
    public void setTreeLost(int newTreeLost)
    {
        treeLost = newTreeLost;
    }

    /**
     *  Method toString show state of the Observation point
     *
     *  @return         String
     */
    public String toString()
    {
        return treeList + "," + koalaList + "," + predatorNo + "," + treeType+ "," + foodWeight + "," + shelterQty + "," +
                koalaDeaths + "," + treeLost + "," + koalaRelocated + "," + deathInjured + "," + deathShortageFood +
                "," + deathLackShelter + "," + deathPredators;
    }

    /**
     * Method to update number of injured koalas deaths
     */
    public void updateDeathInjured()
    {
        deathInjured++;
    }

    /**
     * Method to update number of koalas deaths due to lack of shelter
     */
    public void updateDeathLackShelter()
    {
        deathLackShelter++;
    }

    /**
     * Method to update number of koalas deaths killed by predators
     */
    public void updateDeathPredators()
    {
        deathPredators++;
    }

    /**
     * Method to update number of koalas deaths due to shortage of food
     */
    public void updateDeathShortageFood()
    {
        deathShortageFood++;
    }

    /**
     * Method to update total number of koalas deaths
     */
    public void updateKoalaDeaths()
    {
        koalaDeaths++;
    }

    /**
     * Method to update total number of koalas relocated to the observation point
     */
    public void updateKoalaRelocated()
    {
        koalaRelocated++;
    }

    /**
     * Method to update number of trees lost at the observation point
     */
    public void updateTreeLost()
    {
        treeLost++;
    }
}