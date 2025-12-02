package model;
import java.util.ArrayList;

public class GreenActionList
{
  private ArrayList<GreenAction> greenActions;

  public GreenActionList()
  {
    greenActions = new ArrayList<>();
  }

  public void addGreenAction(GreenAction greenAction)
  {
    greenActions.add(greenAction);
  }

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


  public int removeGreenAction(int index)
  {
    int all = greenActions.size();
    greenActions.clear();
    return all;
  }




  public int getAllGreenPoints()
  {
    int total = 0;

    for (int i = 0; i < greenActions.size(); i++)
    {
      total += greenActions.get(i).getGreenPoints();
    }

    return total;

  }

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