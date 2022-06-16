/**
 * RandomNumber Class responsible for generating a random number and calculating a possibility
 *
 * @author Pavel Zemnukhov
 * @version 1.0 (10-June-2020)
 */

package KoalaRescue;

public class RandomNumber
{
    private int randomNumber;       //random number

    /**
     * Default constructor of RandomNumber
     */
    public RandomNumber()
    {
        randomNumber = 1;
    }

    /**
     * Non default constructor
     *
     * @param   maxRandomNumber  integer maximum random number
     */
    public RandomNumber(int maxRandomNumber)
    {
        randomNumber = maxRandomNumber;
    }

    /**
     * Method to calculate chance of the event based on the chance percent and maximum random number
     * In this program random number to calculate chance, 20 is used.
     *
     * @param   chancePercent       number, chance percent
     * @return                      boolean statement
     */
    public boolean calculateChance(int chancePercent)
    {
        int number = (chancePercent * randomNumber) / 100;
        if (generateRandomNumber() <= number)
            return true;
        else
            return false;
    }

    /**
     * Method generate and return a random number between 1 and the
     * maximum number, inclusive, entered via parameterised constructor
     *
     * @return      integer, random number
     */
    public int generateRandomNumber()
    {
        int newRandom = 1 + (int)(Math.random() * randomNumber);
        return newRandom;
    }

    /**
     * Method generate and return a random number between 0 and the
     * maximum number, inclusive, entered via parameterised constructor
     *
     * @return      integer, random number
     */
    public int generateRandomNumberFromZero()
    {
        int newRandom = (int)(Math.random() * (randomNumber + 1));
        return newRandom;
    }

    /**
     * Method return maximum random number
     *
     * @return      integer, maximum random number
     */
    public int getRandomNumber()
    {
        return randomNumber;
    }

    /**
     * Method set maximum random number
     *
     * @param maxRandomNumber     integer, maximum random number
     */
    public void setRandomNumber(int maxRandomNumber)
    {
        randomNumber = maxRandomNumber;
    }

    /**
     *  Method toString show state of the Observation point
     *
     *  @return         String
     */
    public String toString()
    {
        return randomNumber + "";
    }
}
