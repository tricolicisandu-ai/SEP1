package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of GreenAction objects.
 * @author Christina Jacob
 * @version 1.0
 */

public class GreenActionList implements Serializable
{
  private ArrayList<GreenAction> greenActions;

  /**
   * No-argument constructor initializing the GreenActionList.
   */

  public GreenActionList()
  {
    greenActions = new ArrayList<>();
  }

  /**
   * Adds a GreenAction to the list
   * @param greenAction the GreenAction to add to the list
   */
  public void addGreenAction(GreenAction greenAction)
  {
    greenActions.add(greenAction);
  }

  /**
   *
   * @param name gets name to compare
   * @param greenPoints gets greenPoints to compare
   * @return object by index
   */
  public int getIndex(String name, int greenPoints)
  {
    for (int i = 0; i < greenActions.size(); i++)
    {
      GreenAction temp = greenActions.get(i);

      if (temp.getName().equals(name) && temp.getGreenPoints() == greenPoints)
      {
        return i;
      }
    }
    return -1;
  }

  /**
   * Removes all greenActions from the list
   * @return nothing after cleared
   */

  public int removeGreenAction()
  {
    int all = greenActions.size();
    greenActions.clear();
    return all;
  }

  /**
   * returns all green points from all greenActions
   * @return all green points
   */
  public int getAllGreenPoints()
  {
    int total = 0;

    for (int i = 0; i < greenActions.size(); i++)
    {
      total += greenActions.get(i).getGreenPoints();
    }

    return total;

  }

  /**
   * returns a string representation of GreenAction
   * @return a string representation of the GreenAction in the format: name , greenPoints
   */
  public String toString()
  {
    String returnStr = "";

    for (int i = 0; i < greenActions.size(); i++)
    {
      GreenAction temp = greenActions.get(i);

      returnStr += temp + "\n";
    }
    return returnStr;
  }

}