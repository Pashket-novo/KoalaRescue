/**
 * The SafeHaven class is reasponsible for storing of Koala objects,
 * relocated from/or moved to ObservationPoint.
 *
 * @author Pavel Zemnukhov
 * @version 1.0 (10-Jun-2020)
 */

package KoalaRescue;

import java.util .*;

public class SafeHaven
{
    private ArrayList<Koala> koalaSafeHeaven; //declaring ArrayList of Koala's

    /**
     * Default constructor for objects of class SafeHaven
     */
    public SafeHaven()
    {
        koalaSafeHeaven = new ArrayList<>();
    }

    /**
     * Parameterized constructor
     *
     * @param    newKoalaSafeHeaven    ArrayLists of Koala's
     */
    public SafeHaven(ArrayList<Koala> newKoalaSafeHeaven)
    {
        koalaSafeHeaven = newKoalaSafeHeaven;
    }

    /**
     * Method to add Koala object to the ArrayList
     *
     * @param  addKoala     Koala object
     */
    public void addKoala(Koala addKoala)
    {
        koalaSafeHeaven.add(addKoala);
    }

    /**
     * Method to return ArrayList of Koala objects at safe haven
     *
     * @return      ArrayList of Koala objects
     */
    public ArrayList<Koala> getKoalaSafeHeaven()
    {
        return koalaSafeHeaven;
    }

    /**
     * Method to return number of all healthy koalas at the safe haven
     *
     * @return      number of healthy koalas at the safe haven
     */
    public int getNumberHealthyKoala()
    {
        int healthyKoala = 0;
        try
        {
            for (Koala koala : koalaSafeHeaven)
            {
                if (koala.getCondition() == "Healthy")
                    healthyKoala++;
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("There are no koalas at the Safe Haven");
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
        return healthyKoala;
    }

    /**
     * Method to return number of all injured koalas at the safe haven
     *
     * @return      number of injured koalas at the safe haven
     */
    public int getNumberInjuredKoala()
    {
        int injuredKoala = 0;
        try
        {
            for (Koala koala : koalaSafeHeaven)
            {
                if (koala.getCondition() == "Injured")
                    injuredKoala++;
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("There are no koalas at the Safe Haven");
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
        return injuredKoala;
    }

    /**
     * Method to return total number of koalas at the safe haven
     *
     * @return      number, total of koalas at the safe haven
     */
    public int getNumberKoala()
    {
        int koalaNumber = 0;
        try{
            for (Koala koala : koalaSafeHeaven)
            {
                koalaNumber++;
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("There are no koalas at the Safe Haven");
        }
        catch(Exception ex)
        {
            System.out.println("Error" + ex.toString());
        }
        return koalaNumber;
    }

    /**
     * Method to remove oldest healthy koala from safe haven to further movement to observation point
     * It use temporary array to store Koala object.
     *
     * @return      array of Koala object
     */
    public Koala[] removeHealthyKoala()
    {
        int index = 0;
        boolean isCont = true;
        Koala[] tempArray = new Koala[1];
        int newAge = 0;
        while ((index < koalaSafeHeaven.size()))
        {
            if (koalaSafeHeaven.get(index).getCondition() == "Healthy") //look for healthy koala and it's age
            {
                if (koalaSafeHeaven.get(index).getAge() > newAge)
                    newAge = koalaSafeHeaven.get(index).getAge();       //return age of the oldest koala in the safe haven
            }
            index++;
        }
        index = 0;
        while ((index < koalaSafeHeaven.size()) && isCont)
        {
            if (koalaSafeHeaven.get(index).getCondition() == "Healthy" &&
                    koalaSafeHeaven.get(index).getAge() == newAge)          //apply age of the oldest healthy koala
            {
                tempArray[0] = koalaSafeHeaven.remove(index);
                isCont = false;
            }
            index++;
        }
        return tempArray;
    }

    /**
     * Method to set Koala list at safe haven
     *
     * @param   newKoalaSafeHeaven   ArrayList<Koala>
     */
    public void setKoalaSafeHeaven(ArrayList<Koala> newKoalaSafeHeaven)
    {
        koalaSafeHeaven = newKoalaSafeHeaven;
    }

    /**
     *  Method toString show state of the Observation point
     *
     *  @return         String
     */
    public String toString()
    {
        return koalaSafeHeaven + "";
    }
}

