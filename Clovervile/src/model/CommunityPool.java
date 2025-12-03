package model;
/**
 * A class that represents the community's total pool of points.
 * It combines personal points from residents and green points from actions.
 * @author Yuliia Iliienko
 * @version 1.0
 */
public class CommunityPool
{
  private int totalPoints;
  private ResidentList residents;
  private GreenActionList greenActions;

  /**
   * Creates a CommunityPool with the given residents and green action list.
   *
   * @param residents list of residents
   * @param greenActions list of green actions
   */
  public CommunityPool(ResidentList residents, GreenActionList greenActions)
  {
    this.residents=residents;
    this.greenActions=greenActions;

  }

  /**
   * Returns the total amount of green points accumulated
   * through all green actions.
   * @return total green points
   */
  public int getAllGreenPoints()
  {
    return greenActions.getAllGreenPoints();
  }

  /**
   * Returns the total amount of personal points gained by all residents.
   * @return total personal points
   */
  public int getAllPersonalPoints()
  {
    return residents.getAllPersonalPoints();
  }

  /**
   * Calculates and returns the total points of the community
   * (green points + personal points).
   * @return total community points
   */
  public int getTotalPoints()
  {
    totalPoints = getAllGreenPoints() + getAllPersonalPoints();
    return totalPoints;
  }
}
