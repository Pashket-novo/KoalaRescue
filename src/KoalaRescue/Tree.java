/**
 * Class Tree specify the attributes of a Tree.
 *
 * @author Pavel Zemnukhov
 * @version 1.0 (10-June-2020)
 */

package KoalaRescue;

public class Tree
{
    private String name;                //name of the Tree
    private String type;                //type of the Tree(food, shelter)
    /**
     * Default constructor for objects of class Tree
     */
    public Tree()
    {
        name = "default";
        type = "default";
    }

    /**
     * Parameterized constructor, accepting Tree name, Tree type
     *
     * @param   treeName    String, name of tree
     * @param   treeType    String, type of tree
     */
    public Tree(String treeName, String treeType)
    {
        if (treeName == "Manna Gum" || treeName == "Swamp Gum" || treeName == "Blue Gum" || treeName == "River Red Gum" ||
                treeName == "Wattle")
            name = treeName;
        else
            name = "default";
        if (treeType == "Food" || treeType == "Shelter")
            type = treeType;
        else
            type = "default";
    }

    /**
     * Display state of the Tree object
     *
     * @return      String, information of Tree object
     */
    public String displayTree()
    {
        String treeState = "name " + name + ", type " + type;
        return treeState;
    }

    /**
     * Return name of the Tree
     *
     * @return      String, name of the tree
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return type of the Tree
     *
     * @return      String, type of the tree
     */
    public String getType()
    {
        return type;
    }

    /**
     * Method to set Tree name
     *
     * @param newName      String, containing name of Tree
     */
    public void setName(String newName)
    {
        if (newName == "Manna Gum" || newName == "Swamp Gum" || newName == "Blue Gum" || newName == "River Red Gum" ||
                newName == "Wattle")
            name = newName;
        else
            name = "default";
    }

    /**
     * Method to set Tree type
     *
     * @param   newType     String, containing type of Tree
     */
    public void setType(String newType)
    {
        if (newType == "Food" || newType == "Shelter")
            type = newType;
        else
            type = "default";
    }
}

