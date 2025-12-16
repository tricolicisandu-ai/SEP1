package model;

import java.io.Serializable;

/**
 * A class that represents the community's total pool of points.
 * It combines personal points from residents and green points from actions.
 * @author Yuliia Iliienko
 * @version 1.0
 */
public class CommunityPool implements Serializable
{
  private int totalPoints;


  /**
   * Calculates and returns the total points of the community
   * (green points + personal points).
   * @return total community points
   */
  public int getTotalPoints()
  {
    return totalPoints;
  }

  /**
   * sets the total points
   * @param totalPoints the total points (green points + personal points)
   */

  public void setTotalPoints(int totalPoints)
  {
    this.totalPoints = totalPoints;
  }
}
