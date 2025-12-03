package model;

public class Threshold
    /**
     * Represents a goal threshold consisting of a name and the number
     * of points required to reach that goal.
     * @author Yuliia Iliienko
     * @version 1.0
     */
{
  private String goalName;
  private int requiredPoints;


  /**
   * Constructs a Thresholds object with the given goal name and required points.
   *
   * @param goalName       the name of the goal
   * @param requiredPoints the number of points required to reach the goal
   */
  public Threshold(String goalName, int requiredPoints)
  {
    this.goalName=goalName;
    this.requiredPoints=requiredPoints;
  }

  /**
   * Returns the name of the goal.
   *
   * @return the goal name
   */
  public String getGoalName()
  {
    return goalName;
  }

  /**
   * Returns the number of points required to reach the goal.
   *
   * @return the required number of points
   */
  public int getRequiredPoints()
  {
    return requiredPoints;
  }

  /**
   * Sets a new goal name.
   *
   * @param goalName the new goal name
   */
  public void setGoalName(String goalName)
  {
    this.goalName=goalName;
  }

  /**
   * Updates the number of required points for the goal.
   *
   * @param requiredPoints the new required points value
   */
  public void setRequiredPoints(int requiredPoints)
  {
    this.requiredPoints = requiredPoints;
  }

  /**
   * Determines whether this Thresholds object is equal to another object.
   * Two thresholds are equal if both the goal name and required points match.
   *
   * @param obj the object to compare with
   * @return true if both objects represent the same threshold, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() !=obj.getClass())
    {
      return false;
    }
    Threshold thres = (Threshold) obj;
    return this.goalName.equals(thres.goalName) &&
        this.requiredPoints==thres.requiredPoints;
  }
  public String toString()
  {
    return "The name of goal: " +goalName + " ,the required points: " +requiredPoints;
  }
}

