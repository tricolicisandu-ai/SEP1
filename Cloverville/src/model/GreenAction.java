package model;
import java.io.Serializable;




/**
 * A class representing a GreenAction with name and green points
 * @author Christina Jacob
 * @version 1.0
 */

public class GreenAction implements Serializable
{
  private String name;
  private int greenPoints;


  /**
   * Two-argument constructor.
   * @param name the name of the green action
   * @param greenPoints the points gained by doing green action
   */

  public GreenAction(String name, int greenPoints)
  {
    this.name = name;
    this.greenPoints = greenPoints;
  }

  /**
   * return the name of the green action
   * @return name
   */
  public String getName()
  {
    return name;
  }

  /**
   * returns the amount of green points gained by doing green action
   * @return greenPoints
   */
  public int getGreenPoints()
  {
    return greenPoints;
  }

  /**
   * sets the name of the green action
   * @param name the name of the green action
   */

  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * sets the amount of points gained by doing the green actions
   * @param greenPoints the green points gained by doing the green actions
   */
  public void setGreenPoints(int greenPoints)
  {
    this.greenPoints = greenPoints;
  }

  /**
   * returns a string representation of GreenAction
   * @return a string representation of the GreenAction in the format: name , greenPoints
   */
  public String toString()
  {
    return   name + " "
          + greenPoints;
  }

  /**
   * Compares name and green points of two GreenActions.
   * @param obj the object to compare with
   * @return true if the given object is equal to this GreenAction
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass()!= obj.getClass())
    {
      return false;
    }

    GreenAction other = (GreenAction) obj;
    return name.equals(other.name)&& greenPoints == other.greenPoints;


  }
}
