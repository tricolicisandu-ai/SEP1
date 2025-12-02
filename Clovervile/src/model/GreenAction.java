package model;

public class GreenAction
{
  private String name;
  private int greenPoints;

  public GreenAction(String name, int greenPoints)
  {
    this.name = name;
    this.greenPoints = greenPoints;
  }

  public String getName()
  {
    return name;
  }

  public int getGreenPoints()
  {
    return greenPoints;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setGreenPoints(int greenPoints)
  {
    this.greenPoints = greenPoints;
  }

  public String toString()
  {
    return "Name of the green action is " + name +
        "Gained points is " + greenPoints;
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
