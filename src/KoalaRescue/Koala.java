/**
 * Class Koala specify the attributes and behaviours of a Koala.
 *
 * @author Pavel Zemnukhov
 * @version 1.0 (10-June-2020)
 */

package KoalaRescue;

public class Koala
{
    private int age;                //name of the Koala
    private String condition;       //condition of the Koala

    /**
     * Default constructor for objects of class Koala
     * Initialising fields
     */
    public Koala()
    {
        age = 0;
        condition = "";
    }

    /**
     * Parameterized constructor, accepting Koala age, conditon of the Koala
     *
     * @param   newAge          integer, age of koala
     * @param   newCondition    integer, condition of koala
     */
    public Koala(int newAge, String newCondition)
    {
        age = newAge;
        condition = newCondition;
    }

    /**
     * Display state of the Koala object
     *
     * @return      String, information of Koala object
     */
    public String displayKoala()
    {
        String koalaState = "age " + age + " conditon " + condition;
        return koalaState;
    }

    /**
     * Return age of the Koala
     *
     * @return      integer, age of the koala
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Return condition of the Koala
     *
     * @return      String, condition of the koala
     */
    public String getCondition()
    {
        return condition;
    }

    /**
     * Set age of the Koala
     *
     * @param newAge      integer, Koala age between 1 and 18 years
     */
    public void setAge(int newAge)
    {
        if (newAge >= 1 && newAge <= 18)
            age = newAge;
    }

    /**
     * Set condition of the Koala
     *
     * @param newCondition      String, Koala condition - Healthy or Injured
     */
    public void setCondition(String newCondition)
    {
        if (newCondition == "Healthy" || newCondition == "Injured")
            condition = newCondition;
    }
}
