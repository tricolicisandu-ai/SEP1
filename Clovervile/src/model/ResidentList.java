package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a list of residents.
 * Provides functionality for adding, removing, and retrieving residents,
 * as well as calculating the total amount of personal points in the list.
 * @author Yuliia Iliienko
 *  @version 1.0
 */

public class ResidentList implements Serializable
{
  private ArrayList<Resident> residents;

  /**
   * Creates an empty ResidentList.
   */
  public ResidentList()
  {
    residents= new ArrayList<>();  // отрібно додати до конструктора ??
  }

  /**
   * Returns the number of residents in the list.
   * @return number of residents
   */
  public int getNumberOfResidents()
  {
    return residents.size();
  }

  /**
   * Returns the resident stored at a specific index.
   * @param index the position of the resident
   * @return the resident at the given index
   */
  public Resident getResident(int index)
  {
    return residents.get(index);
  }

  /**
   * Adds a new resident to the list.
   * @param resident the resident to add
   */
  public void addResident(Resident resident)
  {
    residents.add(resident);
  }

  /**
   * removes a resident from the list
   * @param resident the resident that is removed
   */
  public void removeResident(Resident resident)
  {
    residents.remove(resident);
  }

  /**
   * adds personal points to residents
   * @param resident the selected resident to add points to.
   * @param points the amount of points resident gets
   */

public void addPersonalPoints(Resident resident, int points)
{
  for (int i = 0; i < residents.size(); i++)
  {
    if (residents.get(i).equals(resident))
    {
      residents.get(i).setPersonalPoints(residents.get(i).getPersonalPoints()+points);
    }

  }
}

  /**
   * resets all residents personal points to 0
   */

  public void resetAllPersonalPoints()
  {
    for (int i = 0; i < residents.size(); i++)
    {

      residents.get(i).setPersonalPoints(0);
    }

  }


  /**
   * Calculates the total number of personal points
   * accumulated by all residents in the list.
   * @return sum of all personal points
   */
  public int getAllPersonalPoints()
  {
    int sum = 0;
    for(int i =0; i<residents.size(); i++)
    {
      sum += residents.get(i).getPersonalPoints();
    }
    return sum;
  }

  /**
   * Compares this ResidentList to another object.
   * @param obj another object
   * @return true if both ResidentList objects contain the same residents
   */
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    ResidentList other = (ResidentList) obj;
    {
      return residents.equals(other.residents);
    }
  }

  /**
   * Returns a string representation of the list,
   * displaying each resident on a new line.
   * @return string containing all residents
   */
  public String toString()
  {
    String returnStr = "";
    for (int i = 0; i < residents.size(); i++)
    {
      returnStr += residents.get(i) + "\n";
    }
    return returnStr;
  }



}

