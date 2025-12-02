package model;

import java.io.Serializable;


/**
 * A class representing a Trade Offer with a offer name, point cost and the seller.
 * @author Christina Jacob
 * @version 1.0
 */

public class GreenAction
{
  private String name;
  private int greenPoints;


  /**
   * Three-argument constructor.
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
   * returns a string representation of green action
   * @return a string representation of the TradeOffer in the format:
   */
  public String toString()
  {
    return   name + " "
          + greenPoints;
  }

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
